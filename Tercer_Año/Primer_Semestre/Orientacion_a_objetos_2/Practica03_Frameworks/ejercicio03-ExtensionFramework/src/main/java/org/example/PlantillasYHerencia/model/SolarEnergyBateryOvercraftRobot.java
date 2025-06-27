package org.example.PlantillasYHerencia.model;

public abstract class SolarEnergyBateryOvercraftRobot extends SolarEnergyBateryRobot {

    public SolarEnergyBateryOvercraftRobot(String name) {
        super(name);
    }

    @Override
    public void move() {
        System.out.println("Robot " + this.getName() + " moving on overcraft");
    }
}
