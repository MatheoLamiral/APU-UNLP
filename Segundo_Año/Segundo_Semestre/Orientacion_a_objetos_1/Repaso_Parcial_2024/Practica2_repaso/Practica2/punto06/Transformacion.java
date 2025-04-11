package Practica2.punto06;
import Practica2.ejercicio1y2.*;

public class Transformacion {
	BinaryTree<Integer> bt;
	
	public Transformacion(BinaryTree<Integer> binaryTree) {
		this.bt = binaryTree;
	}
	
	public BinaryTree<Integer> suma(){
		if (this.bt.isEmpty()){
			return null;
		}
		else {
			sumaHelper(this.bt);
			return this.bt;
		}
	}
	
	private int sumaHelper(BinaryTree<Integer> bt){
		int aux = 0;
		//SI ES HOJA
		if(bt.isLeaf()) {
			//TOMO EL DATO
			aux = bt.getData();
			//SETEO EL NODO A 0
			bt.setData(0);
			//RETORNO EL DATO
			return aux;
		}
		else {
			//SI TIENE HIJO IZQUIERDO
			if(bt.hasLeftChild()) {
				//RECURSION CON HI
				aux += sumaHelper(bt.getLeftChild());
			}
			//SI TIENE HIJO DERECHO
			if(bt.hasRightChild()) {
				//RECURSION CON HD
				aux += sumaHelper(bt.getRightChild());
			}
			//ME GURADO EL DATO DEL NODO
			int act = bt.getData();
			//ESCRIBO LO QUE SE RETORNO
			bt.setData(aux);
			//RETORNO LO QUE SE ME RETORNO + LO DE ESTE NODO 
			return act + aux;
		}
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
		t.suma().printTree();
		
	}
}
