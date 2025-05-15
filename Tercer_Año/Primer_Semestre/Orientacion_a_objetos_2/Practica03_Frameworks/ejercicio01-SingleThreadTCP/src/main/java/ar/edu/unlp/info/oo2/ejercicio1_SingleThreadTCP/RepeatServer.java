package ar.edu.unlp.info.oo2.ejercicio1_SingleThreadTCP;

import java.io.PrintWriter;
import java.util.StringJoiner;

public class RepeatServer extends SingleThreadTCPServer {

	@Override
	public void handleMessage(String message, PrintWriter out) {
		// TODO Auto-generated method stub
		String[] args = message.split(" ");
		String str = args[0];

	    if (args.length < 2) {
	        out.println("Error: Se requieren al menos dos argumentos (cadena y cantidad).");
	        return;
	    }

	    if (str.isEmpty()) {
	        out.println("Error: El primer argumento no puede estar vacío.");	
	        return;
	    }

	    int repetitions;
	    try {
	        repetitions = Integer.parseInt(args[1]);
	        if (repetitions <= 0) {
	            out.println("Error: El número de repeticiones debe ser mayor que 0.");
	            return;
	        }
	    } catch (NumberFormatException e) {
	        out.println("Error: El segundo argumento debe ser un número entero válido.");
	        return;
	    }

	    String separator = (args.length == 3) ? args[2] : " ";

	    // Generar la respuesta repitiendo el string con el separador
	    StringJoiner joiner = new StringJoiner(separator);
	    for (int i = 0; i < repetitions; i++) {
	        joiner.add(str);
	    }

	    out.println(joiner.toString());
	}
	
	public static void main(String[] args) {

        new RepeatServer().startLoop(args);

    }

}
