package org.example.PlantillasYHerencia.model;

public class SolarEnergyBateryOvercraftRobotWithBombs extends SolarEnergyBateryOvercraftRobot {

    public SolarEnergyBateryOvercraftRobotWithBombs(String name) {
        super(name);
    }

    public void fireArms() {
        System.out.println("Robot " + this.getName() + " firing bombs");
    }
}
