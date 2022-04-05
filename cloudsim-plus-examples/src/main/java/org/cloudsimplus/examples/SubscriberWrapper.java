package org.cloudsimplus.examples;

import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.datacenters.DatacenterSimple;
import org.cloudbus.cloudsim.hosts.HostSimple;
import org.cloudbus.cloudsim.resources.Pe;
import org.cloudbus.cloudsim.resources.PeSimple;
import org.cloudbus.cloudsim.vms.VmSimple;
import org.cloudsimplus.examples.dds_classes.datacentersimple.DatacenterSimpleSubscriber;
import org.cloudsimplus.examples.dds_classes.ddsreport.DDSReportSubscriber;
import org.cloudsimplus.examples.dds_classes.hostsimple.HostSimpleSubscriber;
import org.cloudsimplus.examples.dds_classes.vmsimple.VmSimpleSubscriber;

import java.util.ArrayList;

public class SubscriberWrapper {

    public void startSimulation() {
        int dcs = DDSReportSubscriber.dataCenters;
        int hosts = DDSReportSubscriber.hosts;
        int vms = DDSReportSubscriber.vms;
        final var peList = new ArrayList<Pe>(2);
        peList.add(new PeSimple(200));
        ArrayList<DatacenterSimple> dcList = new ArrayList<DatacenterSimple>();
        ArrayList<HostSimple> hostList = new ArrayList<HostSimple>();
        ArrayList<VmSimple> vmList = new ArrayList<VmSimple>();
        ArrayList<ArrayList<HostSimple>> listOfHostlists = new ArrayList<>();
        for (int i = 0; i < vms; i++) {
            int mips = VmSimpleSubscriber.mips.get(i);
            int peNumber = VmSimpleSubscriber.numberOfPEs.get(i);
            int ram = VmSimpleSubscriber.ram.get(i);
            int bw = VmSimpleSubscriber.bw.get(i);
            int size = VmSimpleSubscriber.size.get(i);
            VmSimple vm = new VmSimple(mips, peNumber);
            vm.setRam(ram).setBw(bw).setSize(size);
            vmList.add(vm);
        }
        System.out.println("Processed virtual machines.\nvirtual machines: " + vmList);
        for (int i = 0; i < hosts; i++) {
            if (HostSimpleSubscriber.datacenterId.get(i) > listOfHostlists.size() - 1) {
                for (int j = 0; j < HostSimpleSubscriber.datacenterId.get(i) - listOfHostlists.size(); j++) {
                    listOfHostlists.add(new ArrayList<HostSimple>());
                }
            }
            int ram = HostSimpleSubscriber.ram.get(i);
            int bw = VmSimpleSubscriber.bw.get(i);
            int size = VmSimpleSubscriber.size.get(i);
            listOfHostlists.get(HostSimpleSubscriber.datacenterId.get(i)).add(new HostSimple(ram, bw, size, peList));
        }
        System.out.println("Processed hosts.\nhosts: " + listOfHostlists);
        for (int i = 0; i < dcs; i++) {
            CloudSim simulation = new CloudSim();
            dcList.add(new DatacenterSimple(simulation, listOfHostlists.get(i)));
            simulation.start();
        }
        System.out.println("Processed datacenters and started simulations.\ndatacenters: " + dcList);
        /*DDSReportSubscriber.clear();
        DatacenterSimpleSubscriber.clear();
        HostSimpleSubscriber.clear();
        VmSimpleSubscriber.clear();*/
        System.out.println("Simulation is set.");
    }

}
