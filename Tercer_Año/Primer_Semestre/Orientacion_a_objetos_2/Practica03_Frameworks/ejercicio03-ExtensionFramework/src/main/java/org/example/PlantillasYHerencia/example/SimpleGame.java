package org.example.PlantillasYHerencia.example;

import org.example.PlantillasYHerencia.model.GameBoard;
import org.example.PlantillasYHerencia.model.NuclearCaterpillarRobotWithBombs;
import org.example.PlantillasYHerencia.model.NuclearOvercraftRobotWithLasers;
import org.example.PlantillasYHerencia.model.SolarEnergyBateryCaterpillarRobotWithBombs;
import org.example.PlantillasYHerencia.model.SolarEnergyBateryCaterpillarRobotWithLasers;

public class SimpleGame {
    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        board.add(new NuclearCaterpillarRobotWithBombs("Twonky"));
        board.add(new NuclearOvercraftRobotWithLasers("Hammer Bot"));
        board.add(new SolarEnergyBateryCaterpillarRobotWithBombs("Lazy Bot"));
        board.add(new SolarEnergyBateryCaterpillarRobotWithLasers("Twinkie Bot"));
        board.runForCicles(5);
    }
}
