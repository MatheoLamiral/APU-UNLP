package ar.edu.unlp.info.oo2.ejercicio02_ExtensionLogging;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;
import ar.edu.unlp.info.oo2.ejercicio02_ExtensionLogging.HideHandler.HideHandler;
import ar.edu.unlp.info.oo2.ejercicio02_ExtensionLogging.MailHandler.MailHandler;

public class HandlersMain {
	private static final Logger logger = Logger.getLogger(HandlersMain.class.getName());
	
	public static void main (String[] args) {
		List<String> bannedWords = new ArrayList();
		bannedWords.add("switch-statements");
		logger.addHandler(new HideHandler(bannedWords));
		logger.addHandler(new ConsoleHandler());
		logger.addHandler(new MailHandler());
		logger.warning("I love switch-statements");
	}

}
