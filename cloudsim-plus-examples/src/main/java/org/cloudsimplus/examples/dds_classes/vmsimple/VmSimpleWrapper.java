package org.cloudsimplus.examples.dds_classes.vmsimple;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.publication.Publisher;
import com.rti.dds.topic.Topic;
import org.cloudbus.cloudsim.vms.Vm;
import org.cloudsimplus.examples.dds_classes.ddsreport.DDSReportPublisher;

import java.util.Objects;

public class VmSimpleWrapper {
    private DomainParticipant participant; // A means to start communicating in a domain.
    int id;
    int hostId;
    int timestampOfReport;
    int mips;
    int numberOfPEs;
    int ram;
    int bw;
    int size;

    public VmSimpleWrapper(int vmId, int hostId, int timestampOfReport, int mips, int numberOfPEs, int ram, int bw, int size) {
        this.id = vmId;
        this.hostId = hostId;
        this.timestampOfReport = timestampOfReport;
        this.mips = mips;
        this.numberOfPEs = numberOfPEs;
        this.ram = ram;
        this.bw = bw;
        this.size = size;
    }

    public void publish() {
        String[] args = { String.valueOf(id), String.valueOf(timestampOfReport), String.valueOf(hostId),
            String.valueOf(mips), String.valueOf(numberOfPEs), String.valueOf(ram), String.valueOf(bw), String.valueOf(size) };
        VmSimplePublisher.main(args);
    }

    public class VmThread extends Thread {
        String[] args = {};
        VmThread(String[] args) {
            this.args = args;
        }
        public void run() {
            VmSimplePublisher.main(args);
        }
    }
}
