package Practica3.ejercicio11;

import java.util.*;

import Practica1.ejercicio8.Queue;
import Practica3.ejercicio01_03_05.*;

public class ParcialArboles {
	
    public static boolean resolver(GeneralTree<Integer> arbol) {
        if(arbol.isEmpty()) return false;
        else return (!arbol.isLeaf()) ? resolverHelper(arbol) : true;
    }
	    
	public static boolean resolverHelper(GeneralTree<Integer> arbol) {
		 GeneralTree<Integer> tree_aux;
	     Queue<GeneralTree<Integer>> queue = new Queue<GeneralTree<Integer>>();
	     int cant_nodos=1;
	     int cant_nodos_act=0;
	 	 queue.enqueue(arbol);
		 queue.enqueue(null);	 
		 while (!queue.isEmpty()) {
			 tree_aux = queue.dequeue();
			 if(tree_aux!=null) {
				 cant_nodos_act++;
				 if(cant_nodos_act>cant_nodos) {
					 return false;
				 }
				 for (GeneralTree<Integer> child: tree_aux.getChildren()) {
					 queue.enqueue(child);
				 } 
			 }else if((!queue.isEmpty())||(cant_nodos_act < cant_nodos)) {
				 if(cant_nodos_act < cant_nodos) {
					 return false;
				 }
				 cant_nodos++;
				 cant_nodos_act=0;
				 queue.enqueue(null);
			 }
		 }	 
		 return true;
	}
	
	public static void main(String[] args) {
		List <GeneralTree<Integer>> subChildren1 = new LinkedList <GeneralTree<Integer>>();
        subChildren1.add(new GeneralTree<Integer>(83));
        GeneralTree<Integer> subAb1 = new GeneralTree<Integer>(18, subChildren1);
        
        List <GeneralTree<Integer>> subChildren2 = new LinkedList <GeneralTree<Integer>>();
        subChildren2.add(subAb1);
        GeneralTree<Integer> subAb2 = new GeneralTree<Integer>(5, subChildren2);
        
        List <GeneralTree<Integer>> subChildren3 = new LinkedList <GeneralTree<Integer>>();
        subChildren3.add(new GeneralTree<Integer>(33));
        subChildren3.add(new GeneralTree<Integer>(12));
        subChildren3.add(new GeneralTree<Integer>(17));
        subChildren3.add(new GeneralTree<Integer>(9));
        GeneralTree<Integer> subAb3 = new GeneralTree<Integer>(3, subChildren3);
        
        List <GeneralTree<Integer>> subChildren4 = new LinkedList <GeneralTree<Integer>>();
        subChildren4.add(new GeneralTree<Integer>(7));
        subChildren4.add(new GeneralTree<Integer>(11));
        subChildren4.add(subAb3);
        GeneralTree<Integer> subAb4 = new GeneralTree<Integer>(4, subChildren4);
        
        List <GeneralTree<Integer>> subArbIzq = new LinkedList <GeneralTree<Integer>>();
        subArbIzq.add(subAb2);
        subArbIzq.add(subAb4);
        GeneralTree<Integer> arbIzq = new GeneralTree<Integer>(1, subArbIzq);
        
        List <GeneralTree<Integer>> subArbDer = new LinkedList <GeneralTree<Integer>>();
        subArbDer.add(new GeneralTree<Integer>(13));
        //subArbDer.add(new GeneralTree<Integer>(14));
        GeneralTree<Integer> arbDer = new GeneralTree<Integer>(25, subArbDer);
        
        List <GeneralTree<Integer>> arbol = new LinkedList <GeneralTree<Integer>>();
        arbol.add(arbIzq);
        arbol.add(arbDer);
        GeneralTree<Integer> a = new GeneralTree<Integer>(2, arbol);
        
        System.out.println("Es creciente: " + resolver(a));
	}

}
