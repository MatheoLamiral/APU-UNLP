package Practica2.ejercicio1y2;


public class BinaryTree <T> {
	
	private T data;
	private BinaryTree<T> leftChild;   
	private BinaryTree<T> rightChild; 

	
	public BinaryTree() {
		super();
	}

	public BinaryTree(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	/**
	 * Preguntar antes de invocar si hasLeftChild()
	 * @return
	 */
	public BinaryTree<T> getLeftChild() {
		return leftChild;
	}
	/**
	 * Preguntar antes de invocar si hasRightChild()
	 * @return
	 */
	public BinaryTree<T> getRightChild() {
		return this.rightChild;
	}

	public void addLeftChild(BinaryTree<T> child) {
		this.leftChild = child;
	}

	public void addRightChild(BinaryTree<T> child) {
		this.rightChild = child;
	}

	public void removeLeftChild() {
		this.leftChild = null;
	}

	public void removeRightChild() {
		this.rightChild = null;
	}

	public boolean isEmpty(){
		return (this.isLeaf() && this.getData() == null);
	}

	public boolean isLeaf() {
		return (!this.hasLeftChild() && !this.hasRightChild());

	}
		
	public boolean hasLeftChild() {
		return this.leftChild!=null;
	}

	public boolean hasRightChild() {
		return this.rightChild!=null;
	}
	@Override
	public String toString() {
		return this.getData().toString();
	}

	public  int contarHojas() {
		int hojas = 0;
		if(this.isLeaf()) {
			hojas+=1;
		}
		else {
			if(this.hasLeftChild()) {
				hojas += this.getLeftChild().contarHojas();
			}
			if(this.hasRightChild()) {
				hojas += this.getRightChild().contarHojas();
			}
		}
		return hojas;
	}
		
		
    	 
    public BinaryTree<T> espejo(){
    	BinaryTree<T> bte = new BinaryTree<T> (this.getData());
    	if(this.hasLeftChild()) {
    		bte.addRightChild(this.getLeftChild().espejo());
    	}
    	if(this.hasRightChild()) {
    		bte.addLeftChild(this.getRightChild().espejo());
    	}	
 	   return bte;
    }

	// 0<=n<=m
	public void entreNiveles(int n, int m){
		BinaryTree<T> bt = null;
		int nivelActual = 0;
		Queue<BinaryTree<T>> cola = new Queue<BinaryTree<T>>();
		cola.enqueue(this);
		cola.enqueue(null);
		while((!cola.isEmpty())&&(n<=nivelActual)&&(nivelActual<=m)) {
			bt = cola.dequeue();
			if(bt!=null) {
				System.out.print(bt.getData()+ " ");
				if(bt.hasLeftChild()) {
					cola.enqueue(bt.getLeftChild());
				}
				if(bt.hasRightChild()) {
					cola.enqueue(bt.getRightChild());
				}
			}
			else {
				nivelActual++;
				if(!cola.isEmpty()) { 
					System.out.println();
					cola.enqueue(null);
				}
			}
		}
   }
	
	public int contarNodos() {
		int aux = 0;
		if(!this.isEmpty()) {
			if (this.hasLeftChild()) {
				aux+= this.getLeftChild().contarNodos();
			}
			if (this.hasRightChild()) {
				aux+= this.getRightChild().contarNodos();
			}
			return aux + 1;
		}
		else
			return 0;
	}
	
	public void printTree() {
        if(this.hasLeftChild()) this.getLeftChild().printTree();
        System.out.print(this.getData() + " ");
        if(this.hasRightChild()) this.getRightChild().printTree();
    }
		
}

