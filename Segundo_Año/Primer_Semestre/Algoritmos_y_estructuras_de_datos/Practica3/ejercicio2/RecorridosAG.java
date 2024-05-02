package Practica3.ejercicio2;

import java.util.*;

import Practica1.ejercicio8.Queue;
import Practica3.ejercicio1_3_5.GeneralTree;

public class RecorridosAG {
	//PREORDEN
	public List<Integer> numerosImparesMayoresQuePreOrden (GeneralTree <Integer> a,Integer n){
		List<Integer> children = new LinkedList <Integer>();
		if (!a.isEmpty())
			agregarListaPreOrden(children,a,n);
		return children;
	}
	
	private void agregarListaPreOrden(List<Integer> list,GeneralTree <Integer> a,Integer n) {
		if((a.getData()%2!=0)&&(a.getData()%2>n)) {
			list.add(a.getData());
		}
		for(GeneralTree<Integer> children: a.getChildren()) {
			agregarListaPreOrden(list,children,n);
		}
	}
	
	//INORDEN
	public List<Integer> numerosImparesMayoresInOrden (GeneralTree <Integer> a,Integer n){
		List<Integer> children = new LinkedList <Integer>();
		if(!a.isEmpty())
			agregarListaInOrden(children,a,n);
		return children;
	}
	
	private void agregarListaInOrden(List<Integer> list,GeneralTree <Integer> a,Integer n) {
		int dato = a.getData();
		List <GeneralTree<Integer>> children = a.getChildren();
		if(a.hasChildren()) {
			agregarListaInOrden(list,children.get(0),n);
		}
		if((dato%2!=0)&&(dato%2>n)) {
			list.add(dato);
		}
		for(int i=1;i<children.size();i++) {
			agregarListaPreOrden(list,children.get(i),n);
		}
	}
	
	//POSTORDEN
	public List<Integer> numerosImparesMayoresQuePostOrden (GeneralTree <Integer> a,Integer n){
		List<Integer> children = new LinkedList <Integer>();
		if(!a.isEmpty())
			agregarListaPostOrden(children,a,n);
		return children;
	}
	
	private void agregarListaPostOrden(List<Integer> list,GeneralTree <Integer> a,Integer n) {
		for(GeneralTree<Integer> children: a.getChildren()) {
			agregarListaPostOrden(list,children,n);
		}
		if((a.getData()%2!=0)&&(a.getData()%2>n)) {
			list.add(a.getData());
		}
	}
	
	//POR NIVELES
	public List<Integer> numerosImparesMayoresQuePorNiveles(GeneralTree<Integer> a,int n) {
		
		List<Integer> result = new LinkedList<Integer>();
		GeneralTree<Integer> tree_aux;
		Queue<GeneralTree<Integer>> queue = new Queue<GeneralTree<Integer>>();
		queue.enqueue(a);
		 
	    while (!queue.isEmpty()) {
			 tree_aux = queue.dequeue();
			 if((tree_aux.getData()%2!=0)&&(tree_aux.getData()%2>n))result.add(tree_aux.getData());
			 List<GeneralTree<Integer>> children = tree_aux.getChildren();
			 for (GeneralTree<Integer> child: children) {
				 queue.enqueue(child);
			 }
		 }
		 
		 return result;
		 }

	
	public static void main(String[] args) {
		
		RecorridosAG rec = new RecorridosAG();
		List <GeneralTree <Integer>> childs = new LinkedList<GeneralTree <Integer>>();
		childs.add(new GeneralTree<Integer>(3));
		childs.add(new GeneralTree<Integer>(4));
		childs.add(new GeneralTree<Integer>(5));
		childs.add(new GeneralTree<Integer>(6));
		GeneralTree <Integer> gt = new GeneralTree <Integer>(1, childs);
		
		
		System.out.println(rec.numerosImparesMayoresQuePreOrden(gt,0));
		System.out.println(rec.numerosImparesMayoresQuePostOrden(gt, 0));
		System.out.println(rec.numerosImparesMayoresInOrden(gt, 0));
		System.out.println(rec.numerosImparesMayoresQuePorNiveles(gt, 0));
		

	}
}
