package Practica3.ejercicio01_03_05;

import java.util.*;

public class Tests {

	public static void main(String[] args) {
		List <GeneralTree <Integer>> childs1 = new LinkedList<GeneralTree <Integer>>();
		childs1.add(new GeneralTree<Integer>(6));
		GeneralTree <Integer> subAb1 = new GeneralTree <Integer>(3, childs1);
		List <GeneralTree <Integer>> childs2 = new LinkedList<GeneralTree <Integer>>();
		childs2.add(new GeneralTree<Integer>(7));
		childs2.add(new GeneralTree<Integer>(8));
		childs2.add(new GeneralTree<Integer>(9));
		childs2.add(new GeneralTree<Integer>(10));
		GeneralTree <Integer> subAb2 = new GeneralTree <Integer>(4, childs2);
		List <GeneralTree <Integer>> childs = new LinkedList<GeneralTree <Integer>>();
		childs.add(new GeneralTree<Integer>(2));
		childs.add(subAb1);
		childs.add(subAb2);
		childs.add(new GeneralTree<Integer>(5));
		GeneralTree <Integer> arbolGeneral = new GeneralTree <Integer>(1, childs);
		
		
		System.out.println("La altura del árbol es: "+arbolGeneral.altura());
		System.out.println("El ancho del árbol es: "+arbolGeneral.ancho());
		
		System.out.println("Ingrese un numero para devlover en que nivel del árbol se encuentra: ");
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		System.out.println("El nivel del nodo es: "+arbolGeneral.nivel(n));
		
		System.out.println("Ingrese un numero A y un número B para verificar si A es ancestro de B: ");
		int a= s.nextInt();
		int b= s.nextInt();
		System.out.println("A es ancestro de B: "+arbolGeneral.esAncestro(a, b));
		
		s.close();


	}

}
