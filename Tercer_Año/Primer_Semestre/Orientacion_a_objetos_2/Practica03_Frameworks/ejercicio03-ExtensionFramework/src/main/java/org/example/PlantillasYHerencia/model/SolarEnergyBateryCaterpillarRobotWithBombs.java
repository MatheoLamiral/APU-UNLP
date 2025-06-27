package org.example.PlantillasYHerencia.model;

public class SolarEnergyBateryCaterpillarRobotWithBombs extends SolarEnergyBateryCaterpillarRobot {

    public SolarEnergyBateryCaterpillarRobotWithBombs(String name) {
        super(name);
    }

    public void fireArms() {
        System.out.println("Robot " + this.getName() + " firing bombs");
    }
}
