package org.cloudsimplus.examples.dds_classes.hostsimple;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.publication.Publisher;
import com.rti.dds.topic.Topic;

import java.util.Objects;

public class HostSimpleWrapper {
    private DomainParticipant participant; // A means to start communicating in a domain.
    int id; // The id of the host.
    DdsHostSimple host; // The host the class will wrap.
    String typeName; // The datatype to use when creating the Tpic.
    Topic topic; // A topic for the application.
    Publisher publisher; // A Publisher allows an application to create one or more DataWriters.
    HostSimpleDataWriter writer; // Writes data

    HostSimpleWrapper(int hostId, String topicName) {
        typeName = HostSimpleTypeSupport.get_type_name();
        id = hostId;
        participant = Objects.requireNonNull(
            DomainParticipantFactory.get_instance().create_participant(
                0,
                DomainParticipantFactory.PARTICIPANT_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));
        HostSimpleTypeSupport.register_type(participant, typeName);
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
        writer = (HostSimpleDataWriter) Objects.requireNonNull(
            publisher.create_datawriter(
                topic,
                Publisher.DATAWRITER_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));
    }

    void publish() {
        host = new DdsHostSimple();
        // Modify the data to be written here
        host.id = (short) id;
        //datacenter.pressure = pressure;
        System.out.println("id of the host: " + id);
        writer.write(host, InstanceHandle_t.HANDLE_NIL);
    }

    public class HostThread extends Thread {
        String args[] = {};
        public void run() { HostSimpleSubscriber.main(args); }
    }
    public void runSubscriber() {
        HostThread thread = new HostThread();
        thread.start();
    }
}
