package org.example.PlantillasYComposicion.model;

public class FourXFour extends Locomotion{

    @Override
    public void move(Robot r) {
        System.out.println("Robot " + r.getName() + " moving on four by four traction");
    }
}
