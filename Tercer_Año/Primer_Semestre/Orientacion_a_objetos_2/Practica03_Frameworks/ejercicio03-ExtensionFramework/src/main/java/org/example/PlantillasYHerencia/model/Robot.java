package org.example.PlantillasYHerencia.model;

public abstract class Robot
{
    String name;
   
    public Robot (String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void step() {
        this.move();
        this.consumeBattery();
        this.fireArms();
        this.collectArtifacts();
    }
    
    public abstract void move();
    
    public abstract void consumeBattery();
    
    public abstract void fireArms();
    
    public void collectArtifacts() {
        System.out.println("Robot " + this.getName() + " collecting");
    }
    
}
