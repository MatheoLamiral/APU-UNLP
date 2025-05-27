package ar.edu.unlp.info.oo2.ejercicio02_ExtensionLogging.Formatters;

import java.util.logging.*;

public class UppercaseFormatter extends Formatter{

	@Override
	public String format(LogRecord record) {
		// TODO Auto-generated method stub
		String format = record.getMessage();
		return format.toUpperCase();
	}

}
