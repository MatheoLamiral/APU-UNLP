package Practica2.ejercicio3;

import java.util.*;

import Practica2.ejercicio1y2.BinaryTree;

public class ContadorArbol {
	private BinaryTree<Integer> bt;
	
	 public ContadorArbol(BinaryTree<Integer> unArbol) {
	        this.bt = unArbol;
	    }
	
	public List<Integer> numerosPares(){
		List <Integer> pair_list = new LinkedList<Integer>();
		if(!this.bt.isEmpty()) {
			//this.agregarPares(pair_list,this.bt);
			this.agregarParesInOrden(pair_list, bt);
			//this.agregarParesPostOrden(pair_list, bt);
		}
		return pair_list;
	}
	
	private void agregarPares(List<Integer> pair_list,BinaryTree<Integer> bint) {
		if(bint.getData()%2==0) {
			pair_list.add(bint.getData());
		}
		if(bint.hasLeftChild()) {
			this.agregarPares(pair_list,bint.getLeftChild());
		}
		if(bint.hasRightChild()) {
			this.agregarPares(pair_list,bint.getRightChild());
		}
	}
	
	private void agregarParesInOrden(List<Integer> pair_list,BinaryTree<Integer> bint) {
		if(bint.hasLeftChild()) {
			this.agregarParesInOrden(pair_list,this.bt.getLeftChild());
		}
		if(bint.getData()%2==0) {
			pair_list.add(bint.getData());
		}
		if(bint.hasRightChild()) {
			this.agregarParesInOrden(pair_list,this.bt.getRightChild());
		}
	}
	
	private void agregarParesPostOrden(List<Integer> pair_list,BinaryTree<Integer> bint) {
		if(bint.hasLeftChild()) {
			this.agregarParesPostOrden(pair_list,this.bt.getLeftChild());
		}
		if(bint.hasRightChild()) {
			this.agregarParesPostOrden(pair_list,this.bt.getRightChild());
		}
		if(bint.getData()%2==0) {
			pair_list.add(bint.getData());
		}
	}
	
	public static void main(String[] args) {
		BinaryTree<Integer> bt = new BinaryTree<Integer>(20);
		bt.addLeftChild(new BinaryTree<Integer>(18));
        bt.addRightChild(new BinaryTree<Integer>(29));
        bt.getLeftChild().addLeftChild(new BinaryTree<Integer>(15));
        bt.getLeftChild().addRightChild(new BinaryTree<Integer>(19));
        bt.getRightChild().addLeftChild(new BinaryTree<Integer>(26));
        ContadorArbol cont = new ContadorArbol(bt);
        List<Integer> pair_list = cont.numerosPares();
        System.out.println(pair_list.toString());
	}
}
