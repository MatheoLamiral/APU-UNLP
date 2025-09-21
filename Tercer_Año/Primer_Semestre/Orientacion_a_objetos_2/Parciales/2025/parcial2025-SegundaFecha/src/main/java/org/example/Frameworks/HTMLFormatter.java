package org.example.Frameworks;

import java.util.logging.*;

public class HTMLFormatter extends Formatter{

    @Override
    public String format(LogRecord record) {
        return "<div class=" + record.getLevel().getName() + ">" + record.getMessage() + "</div>";
    }
}
