package org.example.PlantillasYHerencia.model;

public abstract class NuclearOvercraftRobot extends NuclearRobot
{
    public NuclearOvercraftRobot(String name) {
        super(name);
    }
    
    public void move() {
        System.out.println("Robot " + this.getName() + " moving on overcraft");
    }
}
