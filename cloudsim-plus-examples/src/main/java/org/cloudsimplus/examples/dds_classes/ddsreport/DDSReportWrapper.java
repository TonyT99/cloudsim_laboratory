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
    //DDSReportPublisher dataPublisher;

    public DDSReportWrapper(int rwId, int vms, int hosts, int datacenters, String topicName) {
        //String typeName = DDSReportTypeSupport.get_type_name();
        id = rwId;
        vmNumber = vms;
        hostNumber = hosts;
        dataCenterNumber = datacenters;
        //dataPublisher = new DDSReportPublisher(rwId, vms, hosts, datacenters);
    }

    public void publish() {
        String[] args = { String.valueOf(id), String.valueOf(vmNumber),
            String.valueOf(hostNumber), String.valueOf(dataCenterNumber) };
        RWPubThread thread = new RWPubThread(args);
        thread.start();
        //DDSReportPublisher.main(args);
    }


    public class RWPubThread extends Thread {
        String[] args = {};
        RWPubThread(String[] args) {
            this.args = args;
        }
        public void run() { DDSReportPublisher.main(args); }
    }
}
