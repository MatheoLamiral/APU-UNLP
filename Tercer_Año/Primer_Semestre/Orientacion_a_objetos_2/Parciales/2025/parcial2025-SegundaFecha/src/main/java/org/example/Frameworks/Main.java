package org.example.Frameworks;

import java.util.logging.*;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Handler handler = new ConsoleHandler();
        handler.setFormatter(new HTMLFormatter());
        logger.addHandler(handler);
        logger.log(Level.WARNING, "Orientación a objetos 2");
    }
}
