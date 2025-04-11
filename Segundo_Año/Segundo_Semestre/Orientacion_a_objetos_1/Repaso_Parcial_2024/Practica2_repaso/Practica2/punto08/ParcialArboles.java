package Practica2.punto08;
import Practica2.ejercicio1y2.*;

public class ParcialArboles {
	
	public boolean esPrefijo (BinaryTree<Integer> bt1, BinaryTree<Integer> bt2) {
		boolean ok = true;
		if(bt1.getData()==bt2.getData()) {
			if(bt1.hasLeftChild() && bt2.hasLeftChild()) {
				ok = ok && esPrefijo(bt1.getLeftChild(),bt2.getLeftChild());
			}else if (bt1.hasLeftChild() && !bt2.hasLeftChild()) {
				return false;
			}
			if(bt1.hasRightChild() && bt2.hasRightChild()) {
				ok = ok && esPrefijo(bt1.getRightChild(),bt2.getRightChild());
			}else if (bt1.hasRightChild() && !bt2.hasRightChild()) {
				return false;
			}
		}else return false;
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
		bt2.getLeftChild().addRightChild(new BinaryTree<Integer>(4));
		bt2.getRightChild().addLeftChild(new BinaryTree<Integer>(5));
		bt2.getRightChild().addRightChild(new BinaryTree<Integer>(6));
		bt2.getRightChild().getRightChild().addLeftChild(new BinaryTree<Integer>(9));
		bt2.getRightChild().getRightChild().addRightChild(new BinaryTree<Integer>(10));
		
		ParcialArboles pa = new ParcialArboles();
		System.out.println(pa.esPrefijo(bt1,bt2));
	}
}
