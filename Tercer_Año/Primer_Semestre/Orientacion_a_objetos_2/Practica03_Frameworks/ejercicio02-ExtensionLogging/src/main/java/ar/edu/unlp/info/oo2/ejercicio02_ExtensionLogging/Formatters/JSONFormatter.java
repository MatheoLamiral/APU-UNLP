package ar.edu.unlp.info.oo2.ejercicio02_ExtensionLogging.Formatters;

import java.util.logging.*;
import org.json.simple.*;

public class JSONFormatter extends Formatter{

	@Override
	public String format(LogRecord record) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		json.put("message", record.getMessage());
		json.put("level", record.getLevel());
		return json.toJSONString();
	}

}
