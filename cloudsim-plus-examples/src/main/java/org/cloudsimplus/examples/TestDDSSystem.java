package org.cloudsimplus.examples;

import org.cloudsimplus.examples.dds_classes.ddsreport.DDSReportWrapper;

public class TestDDSSystem {

    DDSReportWrapper reporter;
    TestDDSSystem(){
        reporter  = new DDSReportWrapper(0, 7, 2, 1, "TestTopic");
    }
    public static void main(String args[]) {
        TestDDSSystem testSystem = new TestDDSSystem();
        for (int i = 0; i < 10; i++) {
            testSystem.reporter.publish();
        }
    }
}
