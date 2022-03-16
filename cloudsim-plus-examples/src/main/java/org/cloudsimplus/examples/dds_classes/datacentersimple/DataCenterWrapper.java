package org.cloudsimplus.examples.dds_classes.datacentersimple;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.publication.Publisher;
import com.rti.dds.topic.Topic;

import java.util.Objects;

public class DataCenterWrapper {
    private DomainParticipant participant; // A means to start communicating in a domain.
    int id; // The id of the datacenter.
    DatacenterSimple datacenter; // The datacenter the class will wrap.
    String typeName; // The datatype to use when creating the Tpic.
    Topic topic; // A topic for the application.
    Publisher publisher; // A Publisher allows an application to create one or more DataWriters.
    DatacenterSimpleDataWriter writer; // Writes data

    DataCenterWrapper(int dcId, String topicName) {
        typeName = DatacenterSimpleTypeSupport.get_type_name();
        id = dcId;
        participant = Objects.requireNonNull(
            DomainParticipantFactory.get_instance().create_participant(
                0,
                DomainParticipantFactory.PARTICIPANT_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));
        DatacenterSimpleTypeSupport.register_type(participant, typeName);
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
        writer = (DatacenterSimpleDataWriter) Objects.requireNonNull(
            publisher.create_datawriter(
                topic,
                Publisher.DATAWRITER_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));
    }

    void publish() {
        datacenter = new DatacenterSimple();
        // Modify the data to be written here
        datacenter.id = (short) id;
        //datacenter.pressure = pressure;
        System.out.println("id of the datacenter: " + id);
        writer.write(datacenter, InstanceHandle_t.HANDLE_NIL);
    }

    public class DCThread extends Thread {
        String args[] = {};
        public void run() { DatacenterSimpleSubscriber.main(args); }
    }
    public void runSubscriber() {
        DCThread thread = new DCThread();
        thread.start();
    }
}
