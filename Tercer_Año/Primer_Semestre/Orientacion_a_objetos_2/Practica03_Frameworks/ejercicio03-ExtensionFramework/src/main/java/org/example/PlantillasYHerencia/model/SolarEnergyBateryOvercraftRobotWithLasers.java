package org.example.PlantillasYHerencia.model;

public class SolarEnergyBateryOvercraftRobotWithLasers extends SolarEnergyBateryOvercraftRobot{

    public SolarEnergyBateryOvercraftRobotWithLasers(String name) {
        super(name);
    }

    public void fireArms() {
        System.out.println("Robot " + this.getName() + " firing lasers");
    }
}
