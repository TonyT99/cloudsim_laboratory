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

package org.cloudsimplus.examples.dds_classes.datacentersimple;

import java.util.ArrayList;
import java.util.Objects;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.ConditionSeq;
import com.rti.dds.infrastructure.Duration_t;
import com.rti.dds.infrastructure.RETCODE_NO_DATA;
import com.rti.dds.infrastructure.RETCODE_TIMEOUT;
import com.rti.dds.infrastructure.ResourceLimitsQosPolicy;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.infrastructure.WaitSet;
import com.rti.dds.subscription.*;
import com.rti.dds.topic.Topic;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.datacenters.DatacenterSimple;
import org.cloudbus.cloudsim.hosts.HostSimple;
import org.cloudbus.cloudsim.resources.Pe;
import org.cloudbus.cloudsim.resources.PeSimple;
import org.cloudbus.cloudsim.vms.VmSimple;
import org.cloudsimplus.examples.dds_classes.ddsreport.DDSReportDataReader;
import org.cloudsimplus.examples.dds_classes.ddsreport.DDSReportSeq;
import org.cloudsimplus.examples.dds_classes.ddsreport.DDSReportSubscriber;

/**
* Simple example showing all Connext code in one place for readability.
*/
public class DatacenterSimpleSubscriber extends Application implements AutoCloseable {

    private DomainParticipant participant = null; // Usually one per application
    private DatacenterSimpleDataReader reader = null;
    private final DatacenterSimpleSeq dataSeq = new DatacenterSimpleSeq();
    private final SampleInfoSeq infoSeq = new SampleInfoSeq();

    private static class ReaderListener extends DataReaderAdapter {
        public void on_requested_deadline_missed(
            DataReader dataReader,
            RequestedDeadlineMissedStatus status)
        {
            System.out.println("ReaderListener: on_requested_deadline_missed()");
        }

        public void on_requested_incompatible_qos(
            DataReader dataReader,
            RequestedIncompatibleQosStatus status)
        {
            System.out.println("ReaderListener: on_requested_incompatible_qos()");
        }

        public void on_sample_rejected(
            DataReader dataReader,
            SampleRejectedStatus status)
        {
            System.out.println("ReaderListener: on_sample_rejected()");
        }

        public void on_liveliness_changed(
            DataReader dataReader,
            LivelinessChangedStatus status)
        {
            System.out.println("ReaderListener: on_liveliness_changed()");
            System.out.print("  Alive writers: " + status.alive_count + "\n");
        }

        public void on_sample_lost(
            DataReader dataReader,
            SampleLostStatus status)
        {
            System.out.println("ReaderListener: on_sample_lost()");
        }

        public void on_subscription_matched(
            DataReader dataReader,
            SubscriptionMatchedStatus status)
        {
            System.out.println("ReaderListener: on_subscription_matched()");
        }

        public void on_data_available(DataReader dataReader)
        {
            System.out.println("ReaderListener: on_data_available()");

            DDSReportDataReader listenersReader =
                (DDSReportDataReader ) dataReader;

            DDSReportSeq _dataSeq = new DDSReportSeq();
            SampleInfoSeq _infoSeq = new SampleInfoSeq();

            try {
                listenersReader.take(
                    _dataSeq, _infoSeq,
                    ResourceLimitsQosPolicy.LENGTH_UNLIMITED,
                    SampleStateKind.ANY_SAMPLE_STATE,
                    ViewStateKind.ANY_VIEW_STATE,
                    InstanceStateKind.ANY_INSTANCE_STATE);

                for(int i = 0; i < _infoSeq.size(); ++i) {
                    SampleInfo info = (SampleInfo)_infoSeq.get(i);

                    /*if (info.valid_data) {
                        System.out.println("Received" + _dataSeq.get(i));
                        id = _dataSeq.get(i).timestamp;
                        dataCenters = _dataSeq.get(i).dataCenterNumber;
                        hosts = _dataSeq.get(i).hostNumber;
                        vms = _dataSeq.get(i).vmNumber;
                        final var peList = new ArrayList<Pe>(2);
                        peList.add(new PeSimple(200));
                        ArrayList<DatacenterSimple> dcList = new ArrayList<DatacenterSimple>();
                        ArrayList<HostSimple> hostList = new ArrayList<HostSimple>();
                        ArrayList<VmSimple> vmList = new ArrayList<VmSimple>();
                        for (int j = 0; j < vms; j++) { vmList.add(new VmSimple(j, 20,5000)); }
                        for (int j = 0; j < hosts; j++) { hostList.add(new HostSimple(512, 30, 40, peList)); }
                        for (int j = 0; j < dataCenters; j++) {
                            CloudSim simulation = new CloudSim();
                            dcList.add(new DatacenterSimple(simulation, hostList));
                        }
                        System.out.println("Simulation is set.");
                    } else {
                        System.out.print("   Got metadata\n");
                    }*/
                }
            } catch (RETCODE_NO_DATA noData) {
                // No data to process
            } finally {
                listenersReader.return_loan(_dataSeq, _infoSeq);
            }
        }
    }
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

    private void runApplication() throws InterruptedException {
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
                "Example datacentersimple",
                typeName,
                DomainParticipant.TOPIC_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));

        // This DataReader reads data on "Example datacentersimple" Topic
        ReaderListener listener = new ReaderListener();
        reader = (DatacenterSimpleDataReader) Objects.requireNonNull(
            subscriber.create_datareader(
                topic,
                Subscriber.DATAREADER_QOS_DEFAULT,
                listener, // listener
                //StatusKind.LIVELINESS_CHANGED_STATUS |
                StatusKind.DATA_AVAILABLE_STATUS));

        // Create ReadCondition that triggers when data in reader's queue
        ReadCondition condition = reader.create_readcondition(
            SampleStateKind.ANY_SAMPLE_STATE,
            ViewStateKind.ANY_VIEW_STATE,
            InstanceStateKind.ANY_INSTANCE_STATE);

        while (true) {
            //processData();
            Thread.sleep(100);  // in millisec
            //System.out.println("Waiting for data");
        }
        // WaitSet will be woken when the attached condition is triggered, or timeout
        /*WaitSet waitset = new WaitSet();
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
        }*/
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

    public static void main(String[] args) throws InterruptedException {
        // Create example and run: Uses try-with-resources,
        // subscriberApplication.close() automatically called
        try (DatacenterSimpleSubscriber subscriberApplication = new DatacenterSimpleSubscriber()) {
            subscriberApplication.parseArguments(args);
            subscriberApplication.addShutdownHook();
            subscriberApplication.runApplication();
        }

        // Releases the memory used by the participant factory. Optional at application exit.
        DomainParticipantFactory.finalize_instance();
    }
}
