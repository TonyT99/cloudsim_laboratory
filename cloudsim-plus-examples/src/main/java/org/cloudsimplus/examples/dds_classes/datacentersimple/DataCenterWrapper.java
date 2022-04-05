package org.cloudsimplus.examples.dds_classes.datacentersimple;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.publication.Publisher;
import com.rti.dds.topic.Topic;
import org.cloudsimplus.examples.dds_classes.ddsreport.DDSReportPublisher;

import java.util.Objects;

public class DataCenterWrapper {
    private DomainParticipant participant; // A means to start communicating in a domain.
    int id; // The id of the datacenter.
    int timestamp; // The id of the timestamp.
    DdsDatacenterSimple datacenter; // The datacenter the class will wrap.
    String typeName; // The datatype to use when creating the Tpic.
    Topic topic; // A topic for the application.
    Publisher publisher; // A Publisher allows an application to create one or more DataWriters.
    DatacenterSimpleDataWriter writer; // Writes data

    public DataCenterWrapper(int dcId, int timestamp) {
        this.id = dcId;
        this.timestamp = timestamp;
    }

    public void publish() {
        String[] args = { String.valueOf(id), String.valueOf(timestamp) };
        DatacenterSimplePublisher.main(args);
    }

    public class DCThread extends Thread {
        String args[] = {};
        public void run() {
            try {
                DatacenterSimpleSubscriber.main(args);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void runSubscriber() {
        DCThread thread = new DCThread();
        thread.start();
    }

    public class DCPubThread extends Thread {
        String[] args;
        DCPubThread(String[] args) {
            this.args = args;
        }
        public void run() { DatacenterSimplePublisher.main(args); }
    }
}
