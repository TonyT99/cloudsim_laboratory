package org.cloudsimplus.examples.dds_classes.vmsimple;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.publication.Publisher;
import com.rti.dds.topic.Topic;

import java.util.Objects;

public class VmSimpleWrapper {
    private DomainParticipant participant; // A means to start communicating in a domain.
    int id; // The id of the virtual machine.
    DdsVmSimple vm; // The virtual machine the class will wrap.
    String typeName; // The datatype to use when creating the Tpic.
    Topic topic; // A topic for the application.
    Publisher publisher; // A Publisher allows an application to create one or more DataWriters.
    VmSimpleDataWriter writer; // Writes data

    VmSimpleWrapper(int vmId, String topicName) {
        typeName = VmSimpleTypeSupport.get_type_name();
        id = vmId;
        participant = Objects.requireNonNull(
            DomainParticipantFactory.get_instance().create_participant(
                0,
                DomainParticipantFactory.PARTICIPANT_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));
        VmSimpleTypeSupport.register_type(participant, typeName);
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
        writer = (VmSimpleDataWriter) Objects.requireNonNull(
            publisher.create_datawriter(
                topic,
                Publisher.DATAWRITER_QOS_DEFAULT,
                null, // listener
                StatusKind.STATUS_MASK_NONE));
    }

    void publish() {
        vm = new DdsVmSimple();
        // Modify the data to be written here
        vm.id = (short) id;
        //datacenter.pressure = pressure;
        System.out.println("id of the virtual machine: " + id);
        writer.write(vm, InstanceHandle_t.HANDLE_NIL);
    }

    public class VmThread extends Thread {
        String args[] = {};
        public void run() { VmSimpleSubscriber.main(args); }
    }
    public void runSubscriber() {
        VmThread thread = new VmThread();
        thread.start();
    }
}
