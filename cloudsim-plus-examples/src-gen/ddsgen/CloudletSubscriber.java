package ddsgen;
/*
* (c) Copyright, Real-Time Innovations, 2020.  All rights reserved.
* RTI grants Licensee a license to use, modify, compile, and create derivative
* works of the software solely for use with RTI Connext DDS. Licensee may
* redistribute copies of the software provided that all such copies are subject
* to this license. The software is provided "as is", with no warranty of any
* type, including any warranty for fitness for any purpose. RTI is under no
* obligation to maintain or support the software. RTI shall not be liable for
* any incidental or consequential damages arising out of the use or inability
* to use the software.
*/

import java.util.Objects;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.domain.DomainParticipantFactoryQos;
import com.rti.dds.infrastructure.ConditionSeq;
import com.rti.dds.infrastructure.Duration_t;
import com.rti.dds.infrastructure.RETCODE_NO_DATA;
import com.rti.dds.infrastructure.RETCODE_TIMEOUT;
import com.rti.dds.infrastructure.ResourceLimitsQosPolicy;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.infrastructure.WaitSet;
import com.rti.dds.subscription.InstanceStateKind;
import com.rti.dds.subscription.ReadCondition;
import com.rti.dds.subscription.SampleInfo;
import com.rti.dds.subscription.SampleInfoSeq;
import com.rti.dds.subscription.SampleStateKind;
import com.rti.dds.subscription.Subscriber;
import com.rti.dds.subscription.ViewStateKind;
import com.rti.dds.topic.Topic;

/**
* Simple example showing all Connext code in one place for readability.
*/
public class CloudletSubscriber extends Application implements AutoCloseable {

    private DomainParticipant participant = null; // Usually one per application
    private CloudletDataReader reader = null;
    private final CloudletSeq dataSeq = new CloudletSeq();
    private final SampleInfoSeq infoSeq = new SampleInfoSeq();

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

    private void runApplication() {
        // Load custom QoS profile
        DomainParticipantFactoryQos factoryQos =
            new DomainParticipantFactoryQos();
        DomainParticipantFactory.TheParticipantFactory.get_qos(factoryQos);

        /* We are only going to add one XML file to the url_profile
         * sequence, so we set a maximum length of 1.
         */
        factoryQos.profile.url_profile.setMaximum(1);

        /* The XML file will be loaded from the working directory. That
         * means, you need to run the example like this:
         * ./objs/<architecture>/profiles_publisher
         * (see README.txt for more information on how to run the example).
         *
         * Note that you can specify the absolute path of the XML QoS file
         * to avoid this problem.
         */
        factoryQos.profile.url_profile.add("C:\\Users\\Admin\\IdeaProjects\\cloudsim_laboratory\\cloudsim-plus-examples\\src-gen\\Cloudlet_qos_profile.xml");
        DomainParticipantFactory.TheParticipantFactory.set_qos(factoryQos);

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
        String typeName = CloudletTypeSupport.get_type_name();
        CloudletTypeSupport.register_type(participant, typeName);

        // Create a Topic with a name and a datatype
        Topic topic = Objects.requireNonNull(
            participant.create_topic(
                "Example Cloudlet",
                typeName,
                DomainParticipant.TOPIC_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));

        // This DataReader reads data on "Example Cloudlet" Topic
        reader = (CloudletDataReader) Objects.requireNonNull(
            subscriber.create_datareader_with_profile(
                topic,
                "profiles_Library",
                "cloudlet_local_profile",
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
        while (!isShutdownRequested() && samplesRead < getMaxSampleCount()) {
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

    @Override
    public void close() {
        // Delete all entities (DataReader, Topic, Subscriber, DomainParticipant)
        if (participant != null) {
            participant.delete_contained_entities();

            DomainParticipantFactory.get_instance()
            .delete_participant(participant);
        }
    }

    public static void main(String[] args) {
        // Create example and run: Uses try-with-resources,
        // subscriberApplication.close() automatically called
        try (CloudletSubscriber subscriberApplication = new CloudletSubscriber()) {
            subscriberApplication.parseArguments(args);
            subscriberApplication.addShutdownHook();
            subscriberApplication.runApplication();
        }

        // Releases the memory used by the participant factory. Optional at application exit.
        DomainParticipantFactory.finalize_instance();
    }
}
