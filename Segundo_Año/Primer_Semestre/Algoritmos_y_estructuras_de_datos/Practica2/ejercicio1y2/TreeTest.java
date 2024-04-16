package Practica2.ejercicio1y2;

public class TreeTest {

	public static void main(String[] args) {
		
		BinaryTree<Integer> bt = new BinaryTree<Integer>(1);
		bt.addLeftChild(new BinaryTree<Integer>(2));
        bt.addRightChild(new BinaryTree<Integer>(3));
        bt.getLeftChild().addLeftChild(new BinaryTree<Integer>(4));
        bt.getLeftChild().addRightChild(new BinaryTree<Integer>(5));
        bt.getRightChild().addLeftChild(new BinaryTree<Integer>(6));
		System.out.println("La cantidad de nodos del arbol es "+bt.contarNodos());
		System.out.println("La cantidad hojas del arbol es "+bt.contarHojas());
		System.out.println("Arbol:");
		bt.entreNiveles(0, 2);
		System.out.println("\nArbol espejo:");
		bt.espejo().entreNiveles(0, 2);
	}

}
