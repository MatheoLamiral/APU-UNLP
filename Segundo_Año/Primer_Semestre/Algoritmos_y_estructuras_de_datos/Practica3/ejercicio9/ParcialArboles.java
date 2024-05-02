package Practica3.ejercicio9;

import java.util.*;

import Practica3.ejercicio1_3_5.GeneralTree;

public class ParcialArboles {

	public static boolean esDeSeleccion (GeneralTree<Integer> arbol) {
		return (!arbol.isEmpty()) ? esDeSeleccionHelper(arbol):false;
	}
	
	private static boolean esDeSeleccionHelper(GeneralTree<Integer> arbol) {
		if(!arbol.isLeaf()) {
			Integer min = Integer.MAX_VALUE;
			boolean esDeSelec = true;
			List<GeneralTree<Integer>> children = arbol.getChildren();
			Iterator<GeneralTree<Integer>> it = children.iterator();
			while(it.hasNext() && esDeSelec) {
				GeneralTree<Integer> child = it.next();
				min = Math.min(min,child.getData());
				esDeSelec = esDeSeleccionHelper(child);
			}
			return ((min.equals(arbol.getData())) && (esDeSelec));
		}else return true;
	}

	
	public static void main(String[] args) {
		
		List <GeneralTree <Integer>> childs1 = new LinkedList<GeneralTree <Integer>>();
		childs1.add(new GeneralTree<Integer>(35));
		GeneralTree <Integer> subAb1 = new GeneralTree <Integer>(35, childs1);
		
		List <GeneralTree <Integer>> childs2 = new LinkedList<GeneralTree <Integer>>();
		childs2.add(new GeneralTree<Integer>(35));
		childs2.add(new GeneralTree<Integer>(83));
		childs2.add(new GeneralTree<Integer>(90));
		childs2.add(new GeneralTree<Integer>(33));
		GeneralTree <Integer> subAb2 = new GeneralTree <Integer>(33, childs2);
		
		List <GeneralTree <Integer>> childs3 = new LinkedList<GeneralTree <Integer>>();
		childs3.add(subAb1);
		GeneralTree <Integer> subAb3 = new GeneralTree <Integer>(35, childs3);
		
		List <GeneralTree <Integer>> childs4 = new LinkedList<GeneralTree <Integer>>();
		childs4.add(new GeneralTree<Integer>(14));
		childs4.add(new GeneralTree<Integer>(12));
		childs4.add(subAb2);
		GeneralTree <Integer> subAb4 = new GeneralTree <Integer>(12, childs4);
		
		List <GeneralTree <Integer>> childs5 = new LinkedList<GeneralTree <Integer>>();
		childs5.add(subAb3);
		childs5.add(subAb4);
		GeneralTree <Integer> subAb5 = new GeneralTree <Integer>(12, childs5);
		
		List <GeneralTree <Integer>> childs6 = new LinkedList<GeneralTree <Integer>>();
		childs6.add(new GeneralTree<Integer>(25));
		GeneralTree <Integer> subAb6 = new GeneralTree <Integer>(25, childs6);
		
		List <GeneralTree <Integer>> childs = new LinkedList<GeneralTree <Integer>>();
		childs.add(subAb5);
		childs.add(subAb6);
		GeneralTree <Integer> arbolGeneral = new GeneralTree <Integer>(12, childs);
		
		System.out.println(esDeSeleccion(arbolGeneral));
		
		
	}
}
