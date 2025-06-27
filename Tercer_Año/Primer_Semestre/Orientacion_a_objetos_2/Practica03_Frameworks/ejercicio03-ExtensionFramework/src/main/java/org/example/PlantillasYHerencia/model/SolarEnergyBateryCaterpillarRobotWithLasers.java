package org.example.PlantillasYHerencia.model;

public class SolarEnergyBateryCaterpillarRobotWithLasers extends SolarEnergyBateryCaterpillarRobot {

    public SolarEnergyBateryCaterpillarRobotWithLasers(String name) {
        super(name);
    }

    public void fireArms() {
        System.out.println("Robot " + this.getName() + " firing lasers");
    }
}
