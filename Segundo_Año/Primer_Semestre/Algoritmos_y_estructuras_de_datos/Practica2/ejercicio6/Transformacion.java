package Practica2.ejercicio6;

import Practica2.ejercicio1y2.BinaryTree;

public class Transformacion {
	private BinaryTree<Integer> bt;
	
	public Transformacion(BinaryTree<Integer> binaryTree) {
		this.bt = binaryTree;
	}
	
	public BinaryTree<Integer> suma(){
		total(this.bt);
		return this.bt;
	}
	
	private int total(BinaryTree<Integer> bint){
		 int sum = 0;
		 if(bint.isLeaf()) {
			 sum = bint.getData();
			 bint.setData(0);
			 return sum;
		 }
		 if(bint.hasLeftChild())sum+=total(bint.getLeftChild());
		 if(bint.hasRightChild())sum+=total(bint.getRightChild());
		 int act = bint.getData();
		 bint.setData(sum);
		 return sum + act;
			 
		 }
	
	public static void main(String[] args) {
		BinaryTree<Integer> bt = new BinaryTree<Integer>(1);
		bt.addLeftChild(new BinaryTree<Integer>(2));
		bt.addRightChild(new BinaryTree<Integer>(3));
		bt.getLeftChild().addRightChild(new BinaryTree<Integer>(4));
		bt.getRightChild().addLeftChild(new BinaryTree<Integer>(5));
		bt.getRightChild().addRightChild(new BinaryTree<Integer>(6));
		bt.getRightChild().getLeftChild().addLeftChild(new BinaryTree<Integer>(7));
		bt.getRightChild().getLeftChild().addRightChild(new BinaryTree<Integer>(8));
		
		Transformacion t = new Transformacion(bt);
		System.out.println("Arbol original: ");
		bt.printTree();
		System.out.println("\n-------------");
		t.suma();
		System.out.println("Arbol transformado: ");
		bt.printTree();
		
	}
	
	
}
