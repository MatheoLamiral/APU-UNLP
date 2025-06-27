package org.example.PlantillasYHerencia.model;

public abstract class NuclearCaterpillarRobot extends NuclearRobot
{
    public NuclearCaterpillarRobot(String name) {
        super(name);
    }
    
    public void move() {
        System.out.println("Robot " + this.getName() + " moving on caterpillar");
    }
}
