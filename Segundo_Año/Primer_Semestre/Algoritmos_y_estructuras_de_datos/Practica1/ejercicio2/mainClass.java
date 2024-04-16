package Practica1.ejercicio2;

import java.util.Scanner;

public class mainClass {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		array a = new array();
		int n = s.nextInt(); 
		while (n != -1) {
			a.vector(n);
			System.out.println(a.toString());
			n = s.nextInt();
        }
		
		s.close();
	}
}
