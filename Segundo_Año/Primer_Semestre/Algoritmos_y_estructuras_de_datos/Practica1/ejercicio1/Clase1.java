package Practica1.ejercicio1;

public class Clase1 {
	
	public static void conFor(int a,int b) {
		for(int i=a;i<=b;i++) {
			System.out.println(i);
		}
	}
	
	public static void conWhile(int a, int b) {
		while(a<=b) {
			System.out.println(a);
			a++;
		}
	}
	
	public static void sinEstructurasDeControl(int a,int b) {
		if (a<=b) {
			System.out.println(a);
			a++;
			sinEstructurasDeControl(a, b);
		}
	}
	
}