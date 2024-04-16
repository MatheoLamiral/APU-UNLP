package Practica1.ejercicio7;

import java.util.*;

public class EjercicioCombinarOrdenado {

	//METODO SIN ELIMINAR LISTAS ORIGINALES
	public static ArrayList<Integer> combinarOrdenado(ArrayList<Integer> list1,ArrayList<Integer> list2){
		ArrayList<Integer> aux = new ArrayList<Integer>();
		int cont1 = 0;
		int cont2 = 0;
		while((cont1 < list1.size())&&(cont2 < list2.size())) {
			if (list1.get(cont1)<list2.get(cont2)){
				aux.add(list1.get(cont1));
				cont1++;
			}
			else {
				aux.add(list2.get(cont2));
				cont2++;
			}
		}
		if(cont1==list1.size()) {
			while(cont2<list2.size()) {
				aux.add(list2.get(cont2));
				cont2++;
			}
		}
		else {
			while(cont1<list1.size()) {
				aux.add(list1.get(cont1));
				cont1++;
			}
		}
		return aux;
	}
	
	//METODO ELIMINANDO LISTAS ORIGINALES
	public static ArrayList<Integer> combinarOrdenadoEliminando(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		ArrayList<Integer> aux = new ArrayList<Integer>();
		while ((!list1.isEmpty()) && (!list2.isEmpty())) {
			if (list1.get(0)<list2.get(0)) {
				aux.add(list1.remove(0));
			} else {
				aux.add(list2.remove(0));
			}
		}
		if (list1.isEmpty()) {
			aux.addAll(list2);
		} else {
			aux.addAll(list1);
		}
		return aux;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> list1= new ArrayList<Integer>();
		list1.add(1);list1.add(2);list1.add(3);
		ArrayList<Integer> list2= new ArrayList<Integer>();
		list2.add(1);list2.add(4);list2.add(5);list2.add(6);list2.add(8);
		System.out.println(combinarOrdenado(list1,list2));
		
		

	}

}
