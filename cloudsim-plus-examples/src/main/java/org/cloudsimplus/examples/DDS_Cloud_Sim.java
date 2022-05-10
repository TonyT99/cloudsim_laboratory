package org.cloudsimplus.examples;

public class DDS_Cloud_Sim {

    public void runSimulationDDSData(){
        Configurator configurator = new Configurator();
        configurator.runConfigurator();

//        System.out.println(configurator.getHosts());

//        Simulator sim = Simulator(configurator.getHosts(), configurator.getVms());
    }

    public static void main(String[] args) {
        DDS_Cloud_Sim dds_cloud_sim = new DDS_Cloud_Sim();
        dds_cloud_sim.runSimulationDDSData();

    }
}

