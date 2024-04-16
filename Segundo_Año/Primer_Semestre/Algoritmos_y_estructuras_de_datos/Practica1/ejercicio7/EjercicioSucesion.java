package Practica1.ejercicio7;

import java.util.*;

public class EjercicioSucesion {
	
	public static List <Integer> calcularSucesion(int n){
		List<Integer>list = new LinkedList<Integer>();
			if((n % 2)==0) {
				list = calcularSucesion(n/2);
			}
			else if (n!=1){
				list = calcularSucesion((3*n)+1);
			}
		list.add(0,n);
		return list;
		}


	
	public static void main(String[] args) {
		
		List<Integer> list = new LinkedList<Integer>(calcularSucesion(6));
		System.out.println(list);
		
	}
	
}
