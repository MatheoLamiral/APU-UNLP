package Practica3.ejercicio01_03_05;

import java.util.*;

import Practica1.ejercicio8.Queue;

public class GeneralTree <T>{
		 private T data;
		 private List<GeneralTree<T>> children = new LinkedList<GeneralTree<T>>();
		 
		 public GeneralTree(T data) {
			 this.data = data;
		 }
		 
		 public GeneralTree(T data,List<GeneralTree<T>> children){
			 this(data);
			 this.children = children;
		 }
		 
		 public boolean hasChildren() {
			 return children!=null && !children.isEmpty();
		 }
		 
		 public void setChildren(List<GeneralTree<T>> children) {
			 if (children != null)
				 this.children = children;
		 }
		 
		 public List<GeneralTree<T>> getChildren() {
			 return this.children;
		 }
		 
		 public void addChild(GeneralTree<T> child) {
			 getChildren().add(child);
		 }
		 
		 public T getData() {
			 return data;
		 }
		 
		 public void setData(T data) {
			 this.data = data;
		 }
		 
		 public boolean isLeaf() {
			 return !hasChildren();
		 }
		 
		 public boolean isEmpty() {
			 return data==null && !this.hasChildren();
		 }
		 
		 public void removeChild(GeneralTree<T> child) {
			 if (this.hasChildren()) {
				 children.remove(child);
		 }
			 
		}
		
		 //ALTURA
		 public int altura() {
			 if(!this.isEmpty()) {
				 return alturaHelper();
			 }
			 else
				 return -1;
		 }
		 
		 private int alturaHelper() {
			 if(!this.isLeaf()){
				 int alt_max=-1;
				 for (GeneralTree<T> children: this.getChildren()) {
					 alt_max=Math.max(alt_max,children.alturaHelper());
				 }
				 return alt_max+1;
			 }
			 else return 0;
		 }
		 
		 //ANCHO
		 public int ancho() {
			 if(this.isEmpty())return 0;
			 else return (!this.isLeaf()) ? anchoHelper() : 1;
		 }
		 
		 private int anchoHelper() {
			 GeneralTree<T> tree_aux;
		     Queue<GeneralTree<T>> queue = new Queue<GeneralTree<T>>();
		     int ancho_max=-1;
		     int ancho_act=0;
		 	 queue.enqueue(this);
			 queue.enqueue(null);	 
			 while (!queue.isEmpty()) {
				 tree_aux = queue.dequeue();
				 if(tree_aux!=null) {
					 System.out.println(tree_aux.getData());
					 List<GeneralTree<T>> children = tree_aux.getChildren();
					 for (GeneralTree<T> child: children) {
						 queue.enqueue(child);
						 ancho_act++;
					 } 
				 }else if(!queue.isEmpty()) {
					 queue.enqueue(null);
					 System.out.println();
					 ancho_max = Math.max(ancho_max, ancho_act);
					 ancho_act=0;
				 }
			 }	 
			 return ancho_max;
		 }
		 
		 //NIVEL
		 public int nivel(T dato) {
			 GeneralTree<T> tree_aux;
		     Queue<GeneralTree<T>> queue = new Queue<GeneralTree<T>>();
		     int nivel_act= 1;
		 	 queue.enqueue(this);
			 queue.enqueue(null);	 
			 while (!queue.isEmpty()) {
				 tree_aux = queue.dequeue();
				 if(tree_aux!=null) {
					 if(tree_aux.getData()==dato)return nivel_act;
					 List<GeneralTree<T>> children = tree_aux.getChildren();
					 for (GeneralTree<T> child: children) {
						 queue.enqueue(child);
					 } 
				 }else if(!queue.isEmpty()) {
					 queue.enqueue(null);
					 nivel_act++;
				 }
			 }	 
			 return -1;
		 }
		 
		//EJERCICIO 5 
	    public boolean esAncestro(T a, T b) {
	        if (this.isEmpty()) return false;
	        else return esAncestroBusqueda(a, b);
	    }
	    
	    private boolean esAncestroBusqueda(T a, T b) {
	        boolean esAncestro = false;
	        GeneralTree<T> subAb = null;
	        GeneralTree<T> ab;
	        Queue<GeneralTree<T>> cola = new Queue<GeneralTree<T>>();
	        cola.enqueue(this);
	        while(!cola.isEmpty() && (!esAncestro)) {
	            ab = cola.dequeue();
	            //SI ENCONTRE B ANTES QUE A DEVUELVO FALSO
	            if(ab.getData().equals(b) && (!esAncestro)) return false;
	            if(ab.getData().equals(a)) {
	                esAncestro = true;
	                subAb = ab;
	            }
	            if(!esAncestro) {
	                List<GeneralTree<T>> children = ab.getChildren();
	                for(GeneralTree<T> child: children) {
	                        cola.enqueue(child);
	                    }
	                }
	        }
	        return esAncestro ? esAncestroHelper(subAb, b): false; 
	    }
	    
	    private boolean esAncestroHelper(GeneralTree<T> ab, T b) {
	        GeneralTree<T> aux;
	        Queue<GeneralTree<T>> queue = new Queue<GeneralTree<T>>();
	        queue.enqueue(ab);
	        while(!queue.isEmpty()) {
	            aux = queue.dequeue();
	            if(aux.getData().equals(b)) {
	                return true;
	            } else {
	                List<GeneralTree<T>> children = aux.getChildren();
	                for (GeneralTree<T> child: children) {
	                    queue.enqueue(child);
	                }
	            }
	        }
	        return false;
	    }

		 
		 

}









