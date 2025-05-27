package ar.edu.unlp.info.oo2.ejercicio02_ExtensionLogging;

import java.util.logging.*;

import ar.edu.unlp.info.oo2.ejercicio02_ExtensionLogging.Formatters.JSONFormatter;

public class FormattersMain {
	private static final Logger logger = Logger.getLogger(FormattersMain.class.getName());

	public static void main (String [] args){
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new JSONFormatter());
		logger.addHandler(handler);
		logger.info("Logging with json");
	}
}
