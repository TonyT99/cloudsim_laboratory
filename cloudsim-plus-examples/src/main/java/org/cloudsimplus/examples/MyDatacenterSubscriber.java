package org.cloudsimplus.examples;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.*;
import com.rti.dds.subscription.*;
import com.rti.dds.topic.Topic;

import java.util.ArrayList;
import java.util.Objects;

public class MyDatacenterSubscriber {
    private DomainParticipant participant = null; // Usually one per application
    private DatacenterSimpleDataReader reader = null;
    private final DatacenterSimpleSeq dataSeq = new DatacenterSimpleSeq();
    private final SampleInfoSeq infoSeq = new SampleInfoSeq();

    private int sampleNumber = 0;
    private final ArrayList<MyDatacenterSubscriber> collectedData = new ArrayList<>();

    public MyDatacenterSubscriber(int sampleNumber) {this.sampleNumber = sampleNumber; }

    public ArrayList<MyDatacenterSubscriber> getCollectedData() {return collectedData;}
    private int processData() {
        int samplesRead = 0;

        try {
            // Take available data from DataReader's queue
            reader.take(dataSeq, infoSeq,
                ResourceLimitsQosPolicy.LENGTH_UNLIMITED,
                SampleStateKind.ANY_SAMPLE_STATE,
                ViewStateKind.ANY_VIEW_STATE,
                InstanceStateKind.ANY_INSTANCE_STATE);

            for (int i = 0; i < dataSeq.size(); ++i) {
                SampleInfo info = infoSeq.get(i);

                if (info.valid_data) {
                    System.out.println("Received" + dataSeq.get(i));
                }
                samplesRead++;
            }
        } catch (RETCODE_NO_DATA noData) {
            // No data to process, not a problem
        } finally {
            // Data loaned from Connext for performance. Return loan when done.
            reader.return_loan(dataSeq, infoSeq);
        }

        return samplesRead;
    }

    private void runSubscriber() {
        // Start communicating in a domain
        participant = Objects.requireNonNull(
            DomainParticipantFactory.get_instance().create_participant(
                getDomainId(),
                DomainParticipantFactory.PARTICIPANT_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));

        // A Subscriber allows an application to create one or more DataReaders
        Subscriber subscriber = Objects.requireNonNull(
            participant.create_subscriber(
                DomainParticipant.SUBSCRIBER_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));

        // Register the datatype to use when creating the Topic
        String typeName = DatacenterSimpleTypeSupport.get_type_name();
        DatacenterSimpleTypeSupport.register_type(participant, typeName);

        // Create a Topic with a name and a datatype
        Topic topic = Objects.requireNonNull(
            participant.create_topic(
                "Example DatacenterSimple",
                typeName,
                DomainParticipant.TOPIC_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));

        // This DataReader reads data on "Example DatacenterSimple" Topic
        reader = (DatacenterSimpleDataReader) Objects.requireNonNull(
            subscriber.create_datareader(
                topic,
                Subscriber.DATAREADER_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));

        // Create ReadCondition that triggers when data in reader's queue
        ReadCondition condition = reader.create_readcondition(
            SampleStateKind.ANY_SAMPLE_STATE,
            ViewStateKind.ANY_VIEW_STATE,
            InstanceStateKind.ANY_INSTANCE_STATE);

        // WaitSet will be woken when the attached condition is triggered, or timeout
        WaitSet waitset = new WaitSet();
        waitset.attach_condition(condition);
        final Duration_t waitTimeout = new Duration_t(1, 0);

        int samplesRead = 0;
        ConditionSeq activeConditions = new ConditionSeq();

        // Main loop. Wait for data to arrive and process when it arrives
        while (samplesRead < sampleNumber) {
            try {
                // Wait fills in activeConditions or times out
                waitset.wait(activeConditions, waitTimeout);

                // Read condition triggered, process data
                samplesRead += processData();

            } catch (RETCODE_TIMEOUT timeout) {
                // No data received, not a problem
                System.out.printf("No data after %d seconds.%n", waitTimeout.sec);
            }
        }
    }

    public void close() {
        // Delete all entities (DataReader, Topic, Subscriber, DomainParticipant)
        if (participant != null) {
            participant.delete_contained_entities();

            DomainParticipantFactory.get_instance()
                .delete_participant(participant);
        }
    }
}
