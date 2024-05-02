package Practica3.ejercicio6;

import java.util.LinkedList;
import java.util.List;
import Practica3.ejercicio1_3_5.GeneralTree;

public class RedAguaPotable {
	private GeneralTree<Character> gt;
	
	
	public RedAguaPotable(GeneralTree<Character> gt) {
		this.gt = gt;
	}

	public double minimoCaudal(double caudal) {
		if(this.gt.isEmpty())return caudal;
		else if(!this.gt.isEmpty()) {
			return minimoCaudal(this.gt,caudal);
		}else return caudal;
	}
	
	private double minimoCaudal(GeneralTree<Character> gt ,double caudal) {
		if(gt.isLeaf())return caudal;
		else {
			List<GeneralTree<Character>> children=gt.getChildren();
			int cant_hijos = children.size();
			caudal = caudal/cant_hijos;
			double caudal_min = caudal;
			for(GeneralTree<Character> child: children){
				double caudal_child = minimoCaudal(child,caudal);
				caudal_min=Math.min(caudal_min, caudal_child);
			}
			return caudal_min;
		}
	}


	public static void main(String[] args) {
		GeneralTree<Character> ab1 = new GeneralTree<Character>('B');
        
        List<GeneralTree<Character>> subChildren1 = new LinkedList<GeneralTree<Character>>();
        subChildren1.add(new GeneralTree<Character>('L'));
        GeneralTree<Character> subAb1 = new GeneralTree<Character>('G', subChildren1);
        
        List<GeneralTree<Character>> subChildren2 = new LinkedList<GeneralTree<Character>>();
        subChildren2.add(new GeneralTree<Character>('F'));
        subChildren2.add(subAb1);
        GeneralTree<Character> ab2 = new GeneralTree<Character>('C', subChildren2);
        
        List<GeneralTree<Character>> subChildren3 = new LinkedList<GeneralTree<Character>>();
        subChildren3.add(new GeneralTree<Character>('M'));
        subChildren3.add(new GeneralTree<Character>('N'));
        GeneralTree<Character> subAb2 = new GeneralTree<Character>('J', subChildren3);
        
        List<GeneralTree<Character>> subChildren4 = new LinkedList<GeneralTree<Character>>();
        subChildren4.add(new GeneralTree<Character>('H'));
        subChildren4.add(new GeneralTree<Character>('I'));
        subChildren4.add(subAb2);
        subChildren4.add(new GeneralTree<Character>('K'));
        subChildren4.add(new GeneralTree<Character>('P'));
        GeneralTree<Character> ab3 = new GeneralTree<Character>('D', subChildren4);
        
        GeneralTree<Character> ab4 = new GeneralTree<Character>('E');
        
        List<GeneralTree<Character>> arbol = new LinkedList<GeneralTree<Character>>();
        arbol.add(ab1);
        arbol.add(ab2);
        arbol.add(ab3);
        arbol.add(ab4);
        GeneralTree<Character> ab = new GeneralTree<Character>('A', arbol);
        
		RedAguaPotable red = new RedAguaPotable(ab);
		System.out.println(red.minimoCaudal(1000));
		
		
	}
}
