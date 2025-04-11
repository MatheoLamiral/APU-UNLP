package Practica2.punto09;
import Practica2.ejercicio1y2.*;

public class ParcialArboles {

	public BinaryTree<Elemento> sumAndDif(BinaryTree<Integer> bt) {
		BinaryTree<Elemento> bt2 = new BinaryTree<Elemento> ();
		sumAndDifHelper(bt,bt2,0,0);
		return bt2;
		
	}
	
	private void sumAndDifHelper (BinaryTree<Integer> bt,BinaryTree<Elemento> bt2,int padre, int sum) {
		Elemento e = new Elemento(sum + bt.getData(),bt.getData()-padre);
		bt2.setData(e);
		sum += bt.getData();
		if(bt.hasLeftChild()) {
			bt2.addLeftChild(new BinaryTree<Elemento>());
			sumAndDifHelper(bt.getLeftChild(),bt2.getLeftChild(),bt.getData(),sum);
		}
		if(bt.hasRightChild()) {
			bt2.addRightChild(new BinaryTree<Elemento>());
			sumAndDifHelper(bt.getRightChild(),bt2.getRightChild(),bt.getData(),sum);
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
		ParcialArboles p = new ParcialArboles();
		p.sumAndDif(bt).printTree();
	}
}
