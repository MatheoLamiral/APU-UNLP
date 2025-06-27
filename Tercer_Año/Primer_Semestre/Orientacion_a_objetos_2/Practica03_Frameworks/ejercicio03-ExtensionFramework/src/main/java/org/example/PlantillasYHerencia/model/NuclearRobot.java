package org.example.PlantillasYHerencia.model;

public abstract class NuclearRobot extends Robot
{
    public NuclearRobot(String name) {
        super(name);
    }
    
    public void consumeBattery() {
        System.out.println("Robot " + this.getName() + " using nuclear energy");
    }
}
