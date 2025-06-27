package org.example.PlantillasYComposicion.model;

public class SolarPanel extends EnergySource
{
    public void consumeBattery(Robot r) {
        System.out.println("Robot " + r.getName() + " using solar panel energy");
    }
 
}
