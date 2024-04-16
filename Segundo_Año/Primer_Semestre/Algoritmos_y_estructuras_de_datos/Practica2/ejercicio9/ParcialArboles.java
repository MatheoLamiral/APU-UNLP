package Practica2.ejercicio9;

import Practica2.ejercicio1y2.BinaryTree;

public class ParcialArboles {

	public static BinaryTree<?> sumAndDif(BinaryTree<Integer> bt){
		BinaryTree<NodoInfo> bint = new BinaryTree<NodoInfo>();
		createTree(bt,bint,0,0);
		return bint;
	}
	
	private static void createTree(BinaryTree<Integer> bt,BinaryTree<NodoInfo>bint,int sum,int padre){
		//AGREGO LA INFORMACION AL OBJETO
		NodoInfo n = new NodoInfo(sum+bt.getData(),bt.getData()-padre);
		//CARGO EL OBJETO EN EL NODO DEL ARBOL
		bint.setData(n);
		
		if(bt.hasLeftChild()) {
			bint.addLeftChild(new BinaryTree<NodoInfo>());//CREO UN NODO VACIO 
			createTree(bt.getLeftChild(),bint.getLeftChild(),sum+bt.getData(),bt.getData());
		}
		if(bt.hasRightChild()) {
			bint.addRightChild(new BinaryTree<NodoInfo>());//CREO UN NODO VACIO 
			createTree(bt.getRightChild(),bint.getRightChild(),sum+bt.getData(),bt.getData());
		}
	}
	
	public static void main(String[] args) {
		BinaryTree<Integer> bt = new BinaryTree<Integer>(2);
		bt.addLeftChild(new BinaryTree<Integer>(1));
		bt.addRightChild(new BinaryTree<Integer>(4));
		bt.getRightChild().addLeftChild(new BinaryTree<Integer>(3));
		bt.getRightChild().addRightChild(new BinaryTree<Integer>(6));
		
		System.out.println("Arbol: ");
		bt.printTree();System.out.println();
		System.out.println("Arbol con la suma y la diferencia: ");
		ParcialArboles.sumAndDif(bt).printTree();;
		
		
	}
}
