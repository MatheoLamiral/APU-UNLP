package org.example.PlantillasYHerencia.model;

public abstract class SolarEnergyBateryCaterpillarRobot extends SolarEnergyBateryRobot{

    public SolarEnergyBateryCaterpillarRobot(String name) {
        super(name);
    }

    @Override
    public void move() {
        System.out.println("Robot " + this.getName() + " moving on caterpillar");
    }
}
