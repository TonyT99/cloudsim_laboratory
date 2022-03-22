package org.cloudsimplus.examples;

import org.cloudsimplus.examples.dds_classes.ddsreport.DDSReportWrapper;

public class TestDDSSystem {
    DDSReportWrapper reporter;
    TestDDSSystem(){
        reporter  = new DDSReportWrapper(0, 7, 2, 1, "TestTopic");
    }
    public static void main(String args[]) throws InterruptedException {
        TestDDSSystem testSystem = new TestDDSSystem();
        testSystem.reporter.runSubscriber();
        for (int i = 0; i < 20; i++) {
            testSystem.reporter.publish();
        }

    }
}
