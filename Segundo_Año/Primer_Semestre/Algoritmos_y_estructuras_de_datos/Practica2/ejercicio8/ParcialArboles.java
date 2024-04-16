package Practica2.ejercicio8;

import Practica2.ejercicio1y2.BinaryTree;

public class ParcialArboles {
	
	public boolean esPrefijo(BinaryTree<Integer> bin1,BinaryTree<Integer> bin2){
		if(bin1.getData()!=bin2.getData())return false;
		boolean ok = true;
		if(bin1.hasLeftChild()) {
			if(bin2.hasLeftChild())ok=ok&&esPrefijo(bin1.getLeftChild(),bin2.getLeftChild());
			else return false;
		}
		if(bin1.hasRightChild()) {
			if(bin2.hasRightChild())ok=ok&&esPrefijo(bin1.getRightChild(),bin2.getRightChild());
			else return false;
		}
		return ok;
	}
	
	public static void main(String[] args) {
		BinaryTree<Integer> bt1 = new BinaryTree<Integer>(1);
		bt1.addLeftChild(new BinaryTree<Integer>(2));
		bt1.addRightChild(new BinaryTree<Integer>(3));
		bt1.getLeftChild().addRightChild(new BinaryTree<Integer>(4));
		bt1.getRightChild().addLeftChild(new BinaryTree<Integer>(5));
		bt1.getRightChild().addRightChild(new BinaryTree<Integer>(6));
		
		BinaryTree<Integer> bt2 = new BinaryTree<Integer>(1);
		bt2.addLeftChild(new BinaryTree<Integer>(2));
		bt2.addRightChild(new BinaryTree<Integer>(3));
		//bt2.getLeftChild().addRightChild(new BinaryTree<Integer>(4));
		bt2.getRightChild().addLeftChild(new BinaryTree<Integer>(5));
		bt2.getRightChild().addRightChild(new BinaryTree<Integer>(6));
		bt2.getRightChild().getRightChild().addLeftChild(new BinaryTree<Integer>(9));
		bt2.getRightChild().getRightChild().addRightChild(new BinaryTree<Integer>(10));
		
		ParcialArboles pa = new ParcialArboles();
		System.out.println(pa.esPrefijo(bt1,bt2));
	}
}
