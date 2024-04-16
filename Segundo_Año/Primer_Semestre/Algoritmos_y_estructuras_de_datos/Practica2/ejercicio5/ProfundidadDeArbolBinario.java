package Practica2.ejercicio5;

import Practica2.ejercicio1y2.BinaryTree;

public class ProfundidadDeArbolBinario {
	private BinaryTree<Integer> bt;
	
	public ProfundidadDeArbolBinario(BinaryTree<Integer> binaryTree) {
		this.bt = binaryTree;
	}
	
	public int sumaElementosProfundidad(int prof) {
		if (!this.bt.isEmpty())
			return sumElementos(prof,this.bt,0);
		else
			return -1;			
	}
	
	private int sumElementos(int prof,BinaryTree<Integer> bint,int nivAct) {
		if(nivAct == prof) {
			return bint.getData();
		}
		else {
			int sum = 0;
			if(bint.hasLeftChild())sum+=sumElementos(prof,bint.getLeftChild(),nivAct+1);
			if(bint.hasRightChild())sum+=sumElementos(prof,bint.getRightChild(),nivAct+1);
			return sum;
		}
	}
	
	public static void main(String[] args) {
		BinaryTree<Integer> bt = new BinaryTree<Integer>(15);
		bt.addLeftChild(new BinaryTree<Integer>(11));
		bt.addRightChild(new BinaryTree<Integer>(5));
		bt.getLeftChild().addLeftChild(new BinaryTree<Integer>(6));
		bt.getLeftChild().addRightChild(new BinaryTree<Integer>(24));
		bt.getRightChild().addLeftChild(new BinaryTree<Integer>(7));
		bt.getRightChild().addRightChild(new BinaryTree<Integer>(2));
		
		ProfundidadDeArbolBinario pf = new ProfundidadDeArbolBinario(bt);
		System.out.println("la suma de todos los nodos del árbol que se encuentran a la profundidad ingresada es: "+
							pf.sumaElementosProfundidad(2));
	}
}
