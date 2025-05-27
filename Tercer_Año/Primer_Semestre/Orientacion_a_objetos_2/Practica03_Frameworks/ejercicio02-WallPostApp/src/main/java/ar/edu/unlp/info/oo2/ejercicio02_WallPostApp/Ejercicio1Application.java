package ar.edu.unlp.info.oo2.ejercicio02_WallPostApp;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

public class Ejercicio1Application {

	
	
	public static void main(String[] args) throws SecurityException, IOException {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new WallPostUI();
			}
		});
	}

}
