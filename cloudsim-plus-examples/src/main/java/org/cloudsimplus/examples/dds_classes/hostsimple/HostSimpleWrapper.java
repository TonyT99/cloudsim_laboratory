package org.cloudsimplus.examples.dds_classes.hostsimple;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.publication.Publisher;
import com.rti.dds.topic.Topic;
import org.cloudsimplus.examples.dds_classes.vmsimple.VmSimplePublisher;

import java.util.Objects;

public class HostSimpleWrapper {
    private DomainParticipant participant; // A means to start communicating in a domain.
    int id;
    int datacenterId ;
    int timestampOfReport;
    int ram;
    int bw;
    int size;

    public HostSimpleWrapper(int id, int datacenterId, int timestampOfReport, int ram, int bw, int size) {
        this.id = id;
        this.datacenterId = datacenterId;
        this.timestampOfReport = timestampOfReport;
        this.ram = ram;
        this.bw = bw;
        this.size = size;
    }

    public void publish() {
        String[] args = { String.valueOf(id), String.valueOf(datacenterId), String.valueOf(timestampOfReport),
            String.valueOf(ram), String.valueOf(bw), String.valueOf(size) };
        HostSimplePublisher.main(args);
    }

    public class HostThread extends Thread {
        String[] args;
        HostThread(String[] args) {
            this.args = args;
        }
        public void run() {
            HostSimplePublisher.main(args);
        }
    }
}
