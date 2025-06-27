package org.example.PlantillasYHerencia.model;

public abstract class SolarEnergyBateryRobot extends Robot{

    public SolarEnergyBateryRobot(String name) {
        super(name);
    }

    public void consumeBattery() {
        System.out.println("Robot " + this.getName() + " using solar energy");
    }
}
