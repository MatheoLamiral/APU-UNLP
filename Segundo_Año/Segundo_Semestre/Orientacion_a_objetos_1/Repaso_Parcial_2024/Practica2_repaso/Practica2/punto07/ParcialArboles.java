package Practica2.punto07;
import Practica2.ejercicio1y2.*;

public class ParcialArboles {
	BinaryTree<Integer> bt;
	
	public ParcialArboles(BinaryTree<Integer> bt) {
		this.bt = bt;
	}
	
	public boolean isLeftTree(int num) {
		BinaryTree<Integer> aux = buscar(this.bt,num);
		int izq = -1;
		int der = -1;
		if(aux!=null) {
			if(aux.hasLeftChild()) {
				izq = contarUnicoHijo(aux.getLeftChild());
			}
			if(aux.hasRightChild()) {
				der = contarUnicoHijo(aux.getRightChild());
			}
			return izq > der;
		}else return false;
		
	}
	
	private BinaryTree<Integer> buscar(BinaryTree<Integer> bt,int num){
		BinaryTree<Integer> bint = bt;
		Queue<BinaryTree<Integer>> cola = new Queue<BinaryTree<Integer>>();
		cola.enqueue(bint);
		cola.enqueue(null);
		while((!cola.isEmpty())&&(bint.getData()!=num)) {
			bint = cola.dequeue();
			if(bint!=null) {
				if(bint.hasLeftChild()) {
					cola.enqueue(bint.getLeftChild());
				}
				if(bint.hasRightChild()) {
					cola.enqueue(bint.getRightChild());
				}
			}
			else {
				if(!cola.isEmpty()) { 
					cola.enqueue(null);
				}
			}
		}
		return bint;
	}
	
	private int contarUnicoHijo(BinaryTree<Integer>bt) {
		int cant = 0;
		if(bt.isLeaf()) {
			return 0;
		}
		else {
			if(bt.hasLeftChild()) {
				if(!bt.hasRightChild()){
					cant++;
				contarUnicoHijo(bt.getLeftChild());
				}
			}
			if(bt.hasRightChild()){
				if(!bt.hasLeftChild()) {
					cant++;
				}
				contarUnicoHijo(bt.getRightChild());
			}
		}
		return cant;
	}
	
	public static void main (String [] args) {
		BinaryTree<Integer> bt = new BinaryTree<Integer>(1);
		bt.addLeftChild(new BinaryTree<Integer>(2));
		bt.addRightChild(new BinaryTree<Integer>(3));
		bt.getLeftChild().addLeftChild(new BinaryTree<Integer>(4));
		//bt.getLeftChild().addRightChild(new BinaryTree<Integer>(7));
		bt.getRightChild().addLeftChild(new BinaryTree<Integer>(5));
		bt.getRightChild().addRightChild(new BinaryTree<Integer>(6));
		ParcialArboles p = new ParcialArboles(bt);
		
		System.out.println(p.isLeftTree(1));
	}
}
