package Practica1.ejercicio7;

import java.util.*;

public class EjercicioSumarLinkedList {

	//EL MODULO DEBE SER RECURSIVO 
	public static int sumarLinkedList(LinkedList<Integer>list) {
		if(list.isEmpty()) {
			return 0;
		}
		else {
			return list.remove()+sumarLinkedList(list);
		}
	}
	
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(10);list.add(2);list.add(3);
		System.out.println(sumarLinkedList(list));

	}

}
