package org.example.PlantillasYComposicion.example;


import org.example.PlantillasYComposicion.model.*;

public class SimpleGame {
    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        board.add(new Robot("Twonky", new Caterpillar(), new NuclearPlant(), new BombsSystem()));
        board.add(new Robot("Hammer Bot", new Overcraft(), new NuclearPlant(), new LasersSystem()));
        board.add(new Robot("Mountain Bot", new FourXFour(), new NuclearPlant(), new LasersSystem()));
        board.add(new Robot("Mountain Bot", new FourXFour(), new SolarPanel(), new BombsSystem()));
        board.runForCicles(5);
    }
}
