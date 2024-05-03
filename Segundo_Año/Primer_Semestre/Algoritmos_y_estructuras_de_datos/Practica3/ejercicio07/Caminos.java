package Practica3.ejercicio07;

import java.util.*;

import Practica3.ejercicio01_03_05.GeneralTree;

public class Caminos {
	private GeneralTree<Integer> gt;
	
	
	public Caminos(GeneralTree<Integer> gt) {
		this.gt = gt;
	}

	public List<Integer> caminoAHojaMasLejana() {
        List<Integer> list_max = new LinkedList<Integer>();
        List<Integer> list_act = new LinkedList<Integer>();
        if(!this.gt.isEmpty()) caminoHelper(this.gt,list_act,list_max);
        return list_max;
    }
    
	
	private void caminoHelper(GeneralTree<Integer> gt, List<Integer> list_act,List<Integer> list_max) {
		list_act.add(gt.getData());
		if(!gt.isLeaf()) {
			for(GeneralTree<Integer> child: gt.getChildren()) {
				
				caminoHelper(child,list_act,list_max);
			}
		}
		else if(list_act.size()>list_max.size()){
			list_max.removeAll(list_max);
			list_max.addAll(list_act);
		}
		list_act.remove(list_act.size()-1);
		
			
	}
	 
	 public static void main(String[] args) {
		 
		 List<GeneralTree<Integer>> children1 = new LinkedList<GeneralTree<Integer>>();
		 children1.add(new GeneralTree<Integer>(1));
		 GeneralTree<Integer> subAb1 = new GeneralTree<Integer>(6,children1);
		 List<GeneralTree<Integer>> children2 = new LinkedList<GeneralTree<Integer>>();
		 children2.add(new GeneralTree<Integer>(16));
		 children2.add(new GeneralTree<Integer>(7));
		 children2.add(new GeneralTree<Integer>(25));
		 GeneralTree<Integer> subAb2 = new GeneralTree<Integer>(14,children2);
		 
		 List<GeneralTree<Integer>> children3 = new LinkedList<GeneralTree<Integer>>();
		 children3.add(new GeneralTree<Integer>(10));
		 children3.add(subAb1);
		 GeneralTree<Integer> subAb3 = new GeneralTree<Integer>(17,children3);
		 
		 List<GeneralTree<Integer>> children4 = new LinkedList<GeneralTree<Integer>>();
		 children4.add(subAb2);
		 children4.add(new GeneralTree<Integer>(18));
		 GeneralTree<Integer> subAb4 = new GeneralTree<Integer>(15,children4);
		 
		 List<GeneralTree<Integer>> children5 = new LinkedList<GeneralTree<Integer>>();
		 children5.add(new GeneralTree<Integer>(8));
		 GeneralTree<Integer> subAb5 = new GeneralTree<Integer>(9,children5);
		 
		 List<GeneralTree<Integer>> children = new LinkedList<GeneralTree<Integer>>();
		 children.add(subAb3);
		 children.add(subAb5);
		 children.add(subAb4);
		 GeneralTree<Integer> arbolGeneral = new GeneralTree<Integer>(12,children);
		 
		 
		 Caminos c = new Caminos(arbolGeneral);
		 
		 System.out.println(c.caminoAHojaMasLejana());
	 }
	 
	 }


