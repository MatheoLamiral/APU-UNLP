package Practica3.ejercicio04;

import java.util.*;
import Practica1.ejercicio8.Queue;
import Practica3.ejercicio01_03_05.GeneralTree;

public class AnalizarArbol {
	
	public double devolverPromedioMaximo(GeneralTree<AreaEmpresa> arbol) {
		if(arbol.isEmpty())return -1;
		else return (!arbol.isLeaf())? maximo(arbol):arbol.getData().getTardanza();
	}
	
	private double maximo(GeneralTree<AreaEmpresa> arbol) {
	     double max_prom= -1;
	     double prom_act= 0;
	     int cant_nodos=0;
		 GeneralTree<AreaEmpresa> tree_aux;
	     Queue<GeneralTree<AreaEmpresa>> queue = new Queue<GeneralTree<AreaEmpresa>>();
	 	 queue.enqueue(arbol);
		 queue.enqueue(null);	 
		 while (!queue.isEmpty()) {
			 tree_aux = queue.dequeue();
			 if(tree_aux!=null) {
				 cant_nodos++;
				 prom_act+=tree_aux.getData().getTardanza();
				 List<GeneralTree<AreaEmpresa>> children = tree_aux.getChildren();
				 for (GeneralTree<AreaEmpresa> child: children) {
					 queue.enqueue(child);
				 }
			 }else {
				 prom_act = prom_act/cant_nodos;
				 max_prom = Math.max(max_prom, prom_act);
				 if(!queue.isEmpty())queue.enqueue(null);
				 prom_act = 0;
				 cant_nodos = 0;
			 }
		 }	 
		 return max_prom;
	}
	public static void main(String[] args) {
		AnalizarArbol analizar = new AnalizarArbol();
		AreaEmpresa area1= new AreaEmpresa("ar1",1);
		AreaEmpresa area2= new AreaEmpresa("ar2",2);
		AreaEmpresa area3= new AreaEmpresa("ar3",3);
		AreaEmpresa area4= new AreaEmpresa("ar4",4);
		AreaEmpresa area5= new AreaEmpresa("ar5",5);
		AreaEmpresa area6= new AreaEmpresa("ar6",6);
		AreaEmpresa area7= new AreaEmpresa("ar7",7);
		AreaEmpresa area8= new AreaEmpresa("ar8",8);
		AreaEmpresa area9= new AreaEmpresa("ar9",9);
		
		List <GeneralTree <AreaEmpresa>> childs1 = new LinkedList<GeneralTree <AreaEmpresa>>();
		childs1.add(new GeneralTree<AreaEmpresa>(area6));
		GeneralTree <AreaEmpresa> subAb1 = new GeneralTree <AreaEmpresa>(area3,childs1);
	
		List <GeneralTree <AreaEmpresa>> childs2 = new LinkedList<GeneralTree <AreaEmpresa>>();
		childs2.add(new GeneralTree<AreaEmpresa>(area7));
		childs2.add(new GeneralTree<AreaEmpresa>(area8));
		childs2.add(new GeneralTree<AreaEmpresa>(area9));
		GeneralTree <AreaEmpresa> subAb2 = new GeneralTree <AreaEmpresa>(area4,childs2);
		
		List <GeneralTree <AreaEmpresa>> childs = new LinkedList<GeneralTree <AreaEmpresa>>();
		childs.add(new GeneralTree<AreaEmpresa>(area2));
		childs.add(subAb1);
		childs.add(subAb2);
		childs.add(new GeneralTree<AreaEmpresa>(area5));
		
		GeneralTree <AreaEmpresa> arbolGeneral = new GeneralTree <AreaEmpresa>(area1, childs);
		
		System.out.println("El promedio máximo es: "+analizar.devolverPromedioMaximo(arbolGeneral));
	}

}
