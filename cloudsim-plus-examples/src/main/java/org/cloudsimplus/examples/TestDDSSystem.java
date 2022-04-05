package org.cloudsimplus.examples;

import org.cloudsimplus.examples.dds_classes.datacentersimple.DataCenterWrapper;
import org.cloudsimplus.examples.dds_classes.datacentersimple.DatacenterSimpleSubscriber;
import org.cloudsimplus.examples.dds_classes.ddsreport.DDSReportSubscriber;
import org.cloudsimplus.examples.dds_classes.ddsreport.DDSReportWrapper;
import org.cloudsimplus.examples.dds_classes.hostsimple.HostSimpleSubscriber;
import org.cloudsimplus.examples.dds_classes.hostsimple.HostSimpleWrapper;
import org.cloudsimplus.examples.dds_classes.vmsimple.VmSimpleSubscriber;
import org.cloudsimplus.examples.dds_classes.vmsimple.VmSimpleWrapper;

public class TestDDSSystem {
    DDSReportWrapper reporter;
    DataCenterWrapper dc;
    VmSimpleWrapper vms;
    HostSimpleWrapper hosts;
    SubscriberWrapper source;

    TestDDSSystem(){
        reporter  = new DDSReportWrapper(0, 7, 2, 1, "TestTopic");
        dc = new DataCenterWrapper(0, 0);
        vms = new VmSimpleWrapper(0, 0, 0, 200, 20, 128, 10, 100);
        hosts = new HostSimpleWrapper(0, 0, 0, 512, 30, 300);
        source = new SubscriberWrapper();
    }
    public static void main(String args[]) throws InterruptedException {
        TestDDSSystem testSystem = new TestDDSSystem();
        testSystem.reporter.publish();
        testSystem.dc.publish();
        testSystem.vms.publish();
        testSystem.hosts.publish();
        testSystem.source.startSimulation();
    }
}
