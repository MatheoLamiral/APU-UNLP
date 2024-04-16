package Practica2.ejercicio7;

import Practica2.ejercicio1y2.BinaryTree;

public class ParcialArboles {
	private BinaryTree<Integer> bt;
	
	public ParcialArboles(BinaryTree<Integer> binaryTree) {
		this.bt = binaryTree;
	}
	
	public boolean isLeftTree(int num) {
		int hi=-1;
		int hd=-1;
		BinaryTree<Integer> aux = buscarNodo(num,this.bt);
		if(aux.hasLeftChild())hi=contarSub(aux.getLeftChild());
		if(aux.hasRightChild())hd=contarSub(aux.getRightChild());
		if(hi>hd)return true;else return false;
	}
	
	private BinaryTree<Integer> buscarNodo(int num,BinaryTree<Integer> bint){
		if(bint.getData()==num)return bint;
		BinaryTree<Integer> aux = new BinaryTree<Integer>(null);
		//SI TIENE HIJO IZQUIERDO
		if(bint.hasLeftChild())aux = buscarNodo(num,bint.getLeftChild());
		//SI TIENE HIJO DERECHO Y NO ENCONTRE EL VALOR POR LA RAMA IZQUIERDA
		if((bint.hasRightChild())&&(aux.isEmpty()))aux = buscarNodo(num,bint.getRightChild());
		return aux;
	}
	
	private int contarSub(BinaryTree<Integer> bint){
		 int cant = 0;
	     if(bint.hasLeftChild()) cant += contarSub(bint.getLeftChild());
	     if(bint.hasRightChild()) cant += contarSub(bint.getRightChild());
	     if((bint.hasLeftChild() && !bint.hasRightChild()) || (!bint.hasLeftChild() && bint.hasRightChild())) cant++;
	     return cant;
	}
	
	public static void main(String[] args) {
		
		BinaryTree<Integer> bt = new BinaryTree<Integer>(1);
		bt.addLeftChild(new BinaryTree<Integer>(2));
		bt.addRightChild(new BinaryTree<Integer>(3));
		bt.getLeftChild().addLeftChild(new BinaryTree<Integer>(4));
		bt.getLeftChild().addRightChild(new BinaryTree<Integer>(7));
		bt.getRightChild().addLeftChild(new BinaryTree<Integer>(5));
		//bt.getRightChild().addRightChild(new BinaryTree<Integer>(6));
		
		ParcialArboles pa= new ParcialArboles(bt);
		System.out.println(pa.isLeftTree(3));
	}
}
