package Practica1.ejercicio7;

import java.util.*;

public class EjercicioInveritArrayList {

	public static void invertirArrayList(ArrayList <Integer> list) {
		int aux1;
		int aux2;
		int first = 0;
		int last = list.size()-1;
		while((first<last)){
			 aux1 = list.get(first);
			 aux2 = list.get(last);
			 list.set(first, aux2);
			 list.set(last, aux1);
			 first ++;
	   		 last --;
		}
		//OTRA FORMA
		/*ArrayList<Integer> aux = new ArrayList();
		for (int i=(list.size()-1);i>=0;i--) {
			aux.add(list.get(i));
		}
		list.clear();
		list.addAll(aux);*/
	}
	
	public static void main(String[] args){
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);list.add(2);list.add(3);
		invertirArrayList(list);
		System.out.println(list);
	}
}
