package org.cloudsimplus.examples;

import org.cloudbus.cloudsim.brokers.DatacenterBroker;
import org.cloudbus.cloudsim.brokers.DatacenterBrokerSimple;
import org.cloudbus.cloudsim.cloudlets.Cloudlet;
import org.cloudbus.cloudsim.cloudlets.CloudletSimple;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.datacenters.Datacenter;
import org.cloudbus.cloudsim.datacenters.DatacenterSimple;
import org.cloudbus.cloudsim.hosts.HostSimple;
import org.cloudbus.cloudsim.resources.Pe;
import org.cloudbus.cloudsim.resources.PeSimple;
import org.cloudbus.cloudsim.utilizationmodels.UtilizationModelDynamic;
import org.cloudbus.cloudsim.vms.VmSimple;
import org.cloudsimplus.builders.tables.CloudletsTableBuilder;

import java.util.ArrayList;
import java.util.List;

public class DDS_Cloud_Sim {
    Configurator configurator = new Configurator();
    private DatacenterBroker broker0;
    private List<VmSimple> vmList = new ArrayList<>();
    private List<HostSimple> hostList = new ArrayList<>();
    private List<Cloudlet> cloudletList = new ArrayList<>();
    private Datacenter datacenter0;
    private CloudSim simulation;

    public void runSimulationDDSData() {
        simulation = new CloudSim();
        configurator.runConfigurator();
        ArrayList<ddsgen.Cloudlet> cloudletData = configurator.getCloudlets();

        for(ddsgen.VmSimple vmData : configurator.getVms()) {
            System.out.println(vmData.toString());
            int mips = vmData.mips;
            int numberOfPEs = 4;
            int ram = vmData.ram;
            int bw = vmData.bw;
            int size = vmData.size;
            VmSimple newVm = new VmSimple(mips, numberOfPEs);
            newVm.setRam(ram).setSize(size).setBw(bw);
            vmList.add(newVm);
        }

        for(ddsgen.HostSimple hostData : configurator.getHosts()) {
            System.out.println(hostData.toString());
            ArrayList<Pe> pes = new ArrayList<>();
            int ram = hostData.ram;
            int bw = hostData.bw;
            int size = hostData.size;
            for (int i = 0; i < 4; i++) {
                pes.add(new PeSimple(hostData.mips));
            }
            HostSimple newHost = new HostSimple(ram, bw, size, pes);
            hostList.add(newHost);
        }

        datacenter0 = new DatacenterSimple(simulation, hostList);

        for (int i = 0; i < cloudletData.size(); i++) {
            System.out.println(cloudletData.get(i).toString());
            UtilizationModelDynamic utilizationModel = new UtilizationModelDynamic(cloudletData.get(i).utilizationModelParam);
            final var cloudlet = new CloudletSimple(cloudletData.get(i).ttl, cloudletData.get(i).peNumber, utilizationModel);
            cloudlet.setSizes(1024);
            cloudletList.add(cloudlet);
        }
//        System.out.println(configurator.getHosts());

//        Simulator sim = Simulator(configurator.getHosts(), configurator.getVms());

        //Creates a broker that is a software acting on behalf a cloud customer to manage his/her VMs and Cloudlets
        DatacenterBrokerSimple broker0 = new DatacenterBrokerSimple(simulation);
        broker0.submitCloudletList(cloudletList);
        broker0.submitVmList(vmList);

        simulation.start();

        final List<Cloudlet> finishedCloudlets = broker0.getCloudletFinishedList();
        new CloudletsTableBuilder(finishedCloudlets).build();

    }

    public static void main(String[] args) {
        DDS_Cloud_Sim dds_cloud_sim = new DDS_Cloud_Sim();
        dds_cloud_sim.runSimulationDDSData();
    }
}

