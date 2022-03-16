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
    DDSReport report; // The DDS report the class will wrap.
    String typeName; // The datatype to use when creating the Topic.
    Topic topic; // A topic for the application.
    String topicName; // The name of the application's topic.
    Publisher publisher; // A Publisher allows an application to create one or more DataWriters.
    DDSReportDataWriter writer; // Writes data

    DDSReportWrapper(int rwId, int vms, int hosts, int datacenters, String topicName) {
        typeName = DDSReportTypeSupport.get_type_name();
        id = rwId;
        vmNumber = vms;
        hostNumber = hosts;
        dataCenterNumber = datacenters;
        this.topicName = topicName;
        participant = Objects.requireNonNull(
            DomainParticipantFactory.get_instance().create_participant(
                0,
                DomainParticipantFactory.PARTICIPANT_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));
        DDSReportTypeSupport.register_type(participant, typeName);
        publisher = Objects.requireNonNull(
            participant.create_publisher(
                DomainParticipant.PUBLISHER_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));
        topic = Objects.requireNonNull(
            participant.create_topic(
                topicName,
                typeName,
                DomainParticipant.TOPIC_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));
        writer = (DDSReportDataWriter) Objects.requireNonNull(
            publisher.create_datawriter(
                topic,
                Publisher.DATAWRITER_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));
    }

    void publish() {
        report = new DDSReport();
        // Modify the data to be written here
        report.timestamp = (short) id;
        report.vmNumber = vmNumber;
        report.hostNumber = hostNumber;
        report.dataCenterNumber = dataCenterNumber;
        System.out.println("id of the report: " + id);
        writer.write(report, InstanceHandle_t.HANDLE_NIL);
    }

    public class RWThread extends Thread {
        String args[] = {topicName};
        public void run() { DDSReportSubscriber.main(args); }
    }
    public void runSubscriber() {
        RWThread thread = new RWThread();
        thread.start();
    }
}
