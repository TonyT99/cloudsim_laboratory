package org.cloudsimplus.examples.dds_classes.ddsreport;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.publication.Publisher;
import com.rti.dds.topic.Topic;

import java.util.Objects;

public class DDSReportWrapper {
    private DomainParticipant participant; // A means to start communicating in a domain.
    int id; // The id of the DDS report.
    int vmNumber; // The number of virtual machines in the DDS report.
    int hostNumber; // The number of hosts in the DDS report.
    int dataCenterNumber; // The number of datacenters in the DDS report.
    /*DDSReport report; // The DDS report the class will wrap.
    String typeName; // The datatype to use when creating the Topic.
    Topic topic; // A topic for the application.
    String topicName; // The name of the application's topic.
    Publisher publisher; // A Publisher allows an application to create one or more DataWriters.*/
    DDSReportDataWriter writer; // Writes data
    DDSReportPublisher dataPublisher;

    public DDSReportWrapper(int rwId, int vms, int hosts, int datacenters, String topicName) {
        //String typeName = DDSReportTypeSupport.get_type_name();
        id = rwId;
        vmNumber = vms;
        hostNumber = hosts;
        dataCenterNumber = datacenters;
        dataPublisher = new DDSReportPublisher(rwId, vms, hosts, datacenters);
        //this.topicName = topicName;
        /*participant = Objects.requireNonNull(
            DomainParticipantFactory.get_instance().create_participant(
                0,
                DomainParticipantFactory.PARTICIPANT_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));

        // A Publisher allows an application to create one or more DataWriters
        Publisher publisher = Objects.requireNonNull(
            participant.create_publisher(
                DomainParticipant.PUBLISHER_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));

        // Register the datatype to use when creating the Topic
        String typeName = DDSReportTypeSupport.get_type_name();
        DDSReportTypeSupport.register_type(participant, typeName);

        // Create a Topic with a name and a datatype
        Topic topic = Objects.requireNonNull(
            participant.create_topic(
                "testTopic",
                typeName,
                DomainParticipant.TOPIC_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));

        // This DataWriter writes data on "Example ddsreport" Topic
        writer = (DDSReportDataWriter) Objects.requireNonNull(
            publisher.create_datawriter(
                topic,
                Publisher.DATAWRITER_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));

        DDSReport data = new DDSReport();
        for (int samplesWritten = 0; samplesWritten < 20; samplesWritten++) {

            // Modify the data to be written here
            data.timestamp = samplesWritten;
            data.vmNumber = samplesWritten;
            data.hostNumber = samplesWritten;
            data.dataCenterNumber = samplesWritten;

            System.out.println("Writing ddsreport, count " + samplesWritten);

            writer.write(data, InstanceHandle_t.HANDLE_NIL);
        }*/
    }

    public void publish() {
        String[] args = { String.valueOf(id), String.valueOf(vmNumber),
            String.valueOf(hostNumber), String.valueOf(dataCenterNumber) };
        DDSReportPublisher.main(args);
        /*DDSReport report = new DDSReport();
        // Modify the data to be written here
        report.timestamp = (short) id;
        report.vmNumber = vmNumber;
        report.hostNumber = hostNumber;
        report.dataCenterNumber = dataCenterNumber;
        /*System.out.println("The id of the report: " + id);
        System.out.println("The number of virtual machines: " + vmNumber);
        System.out.println("The number of hosts: " + hostNumber);
        System.out.println("The number of datacenters: " + dataCenterNumber);
        writer.write(report, InstanceHandle_t.HANDLE_NIL);
        try {
            final long sendPeriodMillis = 1000; // 1 second
            Thread.sleep(sendPeriodMillis);
        } catch (InterruptedException ix) {
            System.err.println("INTERRUPTED");
        }*/
    }

    public class RWSubThread extends Thread {
        String[] args = {};
        public void run() {
            try {
                DDSReportSubscriber.main(args);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void runSubscriber() {
        RWSubThread thread = new RWSubThread();
        thread.start();
    }

    public class RWPubThread extends Thread {
        String[] args = {};
        public void run() { DDSReportPublisher.main(args); }
    }
}
