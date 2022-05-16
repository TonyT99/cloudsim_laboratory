package org.cloudsimplus.examples;

import ddsgen.DDSReport;
import ddsgen.DatacenterSimple;
import ddsgen.VmSimple;
import ddsgen.HostSimple;

import java.util.ArrayList;

public class Configurator {
    private ArrayList<VmSimple> vms = new ArrayList<>();
    private ArrayList<HostSimple> hosts = new ArrayList<>();
    private ArrayList<DatacenterSimple> dcs = new ArrayList<>();
    private ArrayList<DDSReport> reports = new ArrayList<>();

    public ArrayList<VmSimple> getVms() {
        return vms;
    }
    public ArrayList<HostSimple> getHosts() {
        return hosts;
    }
    public ArrayList<DatacenterSimple> getDcs() { return dcs; }
    public ArrayList<DDSReport> getReports() { return reports; }

    public void runConfigurator(){
        // VM
        MyVmsimpleSubscriber vmSubscriberApplication = new MyVmsimpleSubscriber(3);
        vmSubscriberApplication.runSubscriber();
        ArrayList<VmSimple> collectedVmData = vmSubscriberApplication.getCollectedData();
        System.out.println("All data has arrived....");
        System.out.println(collectedVmData);
        vms = collectedVmData;
        vmSubscriberApplication.close();

        // Host
        MyHostsimpleSubscriber hostSubscriberApplication = new MyHostsimpleSubscriber(3);
        hostSubscriberApplication.runSubscriber();
        ArrayList<HostSimple> collectedHostData = hostSubscriberApplication.getCollectedData();
        System.out.println("All data has arrived....");
        //System.out.println(collectedHostData);
        hosts = collectedHostData;
        hostSubscriberApplication.close();
        // DataCenter
        MyDatacenterSubscriber datacenterSubscriberApplication = new MyDatacenterSubscriber(1);
        datacenterSubscriberApplication.runSubscriber();
        ArrayList<DatacenterSimple> collectedDcData = datacenterSubscriberApplication.getCollectedData();
        System.out.println("All data has arrived....");
        //System.out.println(collectedDcData);
        dcs = collectedDcData;
        datacenterSubscriberApplication.close();
        // Report
        MyDDSReportSubscriber reportSubscriberApplication = new MyDDSReportSubscriber(1);
        reportSubscriberApplication.runSubscriber();
        ArrayList<DDSReport> collectedReportData = reportSubscriberApplication.getCollectedData();
        System.out.println("All data has arrived....");
        //System.out.println(collectedReportData);
        reports = collectedReportData;
        reportSubscriberApplication.close();
    }
}
