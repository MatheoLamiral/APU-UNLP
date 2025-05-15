package ar.edu.unlp.info.oo2.ejercicio1_SingleThreadTCP;

import java.io.PrintWriter;
import java.util.Random;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PasswordServer extends SingleThreadTCPServer {

	@Override
	public void handleMessage(String message, PrintWriter out) {
		// TODO Auto-generated method stub
		String[] args = message.split(" ");
		if(args.length != 3) {
			System.exit(1);
		}
		out.println(this.generatePassword(args));
	}
	
	private String generatePassword(String[] args) {
		Random random = new Random();
		String letters = args[0];
		String numbers = args[1];
		String specialChars = args[2];
		
		//aseguro un solo carater y al menos una letra y un numero
		StringBuilder password = new StringBuilder()
								.append(specialChars.charAt(random.nextInt(specialChars.length())))
								.append(letters.charAt(random.nextInt(letters.length())))
								.append(numbers.charAt(random.nextInt(numbers.length())));

        // Lleno las 5 posiciones restantes con letras o números aleatorios
        String lettersAndNumbers = letters + numbers;
        for (int i = 0; i < 5; i++) {
            password.append(lettersAndNumbers.charAt(random.nextInt(lettersAndNumbers.length())));
        }
        
        //desordeno la contraseña conviritendoal en un array
        List<String> sortedPassword = Arrays.asList(password.toString().split(""));
        Collections.shuffle(sortedPassword);
        
        //la vuelvo a convertir a string
        StringBuilder finalPassword = new StringBuilder();
        for (String c : sortedPassword) {
            finalPassword.append(c);
        }
        
        return finalPassword.toString();
	}
	
	public static void main(String[] args) {

        new PasswordServer().startLoop(args);

    }

}
