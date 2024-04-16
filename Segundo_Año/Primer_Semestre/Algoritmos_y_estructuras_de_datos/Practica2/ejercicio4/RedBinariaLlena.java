package Practica2.ejercicio4;

import Practica2.ejercicio1y2.BinaryTree;

public class RedBinariaLlena {
	private BinaryTree<Integer> bt;
	
	public RedBinariaLlena(BinaryTree<Integer> binaryTree) {
		this.bt = binaryTree;
	}
	
	public int retardoReenvio(){
		if(!this.bt.isEmpty()) {
			return this.buscarRetardoReenvio(this.bt);
		}
		else
			return -1;
	}

	private int buscarRetardoReenvio(BinaryTree<Integer>bint){
		int retHi = 0;
		int retHd = 0;
		if(bint.hasLeftChild()) {
			retHi = buscarRetardoReenvio(bint.getLeftChild());
		}
		if(bint.hasRightChild()) {
			retHd = buscarRetardoReenvio(bint.getRightChild());
		}
		if(retHi>retHd) {
			return retHi + bint.getData();
		}
		else return retHd + bint.getData();
	}
	
	public static void main(String[] args){
		BinaryTree<Integer> bt = new BinaryTree<Integer>(10);
		bt.addLeftChild(new BinaryTree<Integer>(11));
		bt.addRightChild(new BinaryTree<Integer>(5));
		bt.getLeftChild().addLeftChild(new BinaryTree<Integer>(6));
		bt.getLeftChild().addRightChild(new BinaryTree<Integer>(12));
		bt.getRightChild().addLeftChild(new BinaryTree<Integer>(7));
		bt.getRightChild().addRightChild(new BinaryTree<Integer>(4));
		
		RedBinariaLlena rb = new RedBinariaLlena(bt);
		System.out.println("El mayor retardo es de: "+rb.retardoReenvio()+" segundos");
	}
}
