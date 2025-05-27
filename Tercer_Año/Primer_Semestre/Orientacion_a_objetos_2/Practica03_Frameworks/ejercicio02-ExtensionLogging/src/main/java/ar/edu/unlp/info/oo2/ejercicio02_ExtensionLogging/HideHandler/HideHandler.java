package ar.edu.unlp.info.oo2.ejercicio02_ExtensionLogging.HideHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

//La clase HideHandler no responde a las asignaciones
//de formatters, utiliza por defecto HideFormatter
public class HideHandler extends Handler{
	private static final Logger logger = Logger.getLogger(HideHandler.class.getName());
	private HideFormatter hideFormatter;
	
	public HideHandler(List<String> bannedWords) {
		super();
		this.hideFormatter = new HideFormatter(bannedWords);
	}

	@Override
	public void publish(LogRecord record) {
		// TODO Auto-generated method stub
		record.setMessage(hideFormatter.format(record));
	}

	@Override
	public void flush() {
		//no manejo buffers por lo que no es necesario manejar un método flush
	}

	@Override
	public void close() throws SecurityException {
		//no utilizo recursos externos por lo que no es necesario manejar un método close
	}

}
