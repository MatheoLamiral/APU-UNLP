package Practica1.ejercicio7;

import java.util.*;

public class EjercicioEsCapicua {
	
	public static boolean isCapicua(ArrayList<Integer> list) {
		boolean isCap=true;
		int first= 0;
		int last= list.size()-1;
		while((first<last)&&(isCap)){
			if(list.get(first)==list.get(last)) {
				first ++;
				last --;
			}
			else
				isCap=false;
		}
		return isCap;
	}
	
	public static void main(String[] args) {
		
		ArrayList <Integer> list= new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(2);
		list.add(1);
		System.out.println(isCapicua(list));
		//System.out.println(Metodos.isCapicua(new ArrayList<Integer>(Arrays.asList(3,2,2,3))));
		//System.out.println(Metodos.isCapicua(new ArrayList<Integer>(Arrays.asList(1,2,3,4))));
	
	}
	
	
}
