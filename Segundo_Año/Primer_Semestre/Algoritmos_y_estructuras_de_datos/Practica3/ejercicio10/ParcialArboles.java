package Practica3.ejercicio10;

import java.util.*;
import Practica3.ejercicio01_03_05.*;

public class ParcialArboles {

	public static List<Integer> resolver(GeneralTree<Integer> arbol){
		Valores max = new Valores(new LinkedList<Integer>(),-1);
		if(!arbol.isEmpty()) {
			Valores act = new Valores(new LinkedList<Integer>(),0);
			//SI LA RAIZ ES DISTINTA DE 0 LA AGREGO A LA LISTA 
			if(arbol.getData().equals(1)) {
				act.getList().add(arbol.getData());
			}
			resolver(arbol,0,max,act);
		}
		return max.getList();
	}
	
	private static void resolver(GeneralTree<Integer> arbol,int nivel,Valores max,Valores act){

		if(!arbol.isLeaf()) {
			for(GeneralTree<Integer> child:arbol.getChildren()) {
				if(arbol.getData().equals(1)) {
					//AGREGGO A LA LISTA
					act.setVal(act.getVal()+nivel+1);
					act.getList().add(arbol.getData());
					//LLAMADO RECURSIVO 
					resolver(child,nivel+1,max,act);
					//ELIMINO EL ULTIMO ELEMENTO 
					act.setVal(act.getVal()-(nivel+1));
					act.getList().remove(act.getList().size()-1);
				}else resolver(child,nivel+1,max,act);
			}
		}else {
			//SI EL ACTUAL ES MAYOR QUE EL MAXIMO
			if(act.getVal()>max.getVal()) {
				//SETEO EL VALOR
				max.setVal(act.getVal());
				//ACTUALIZO LA LISTA
				max.getList().removeAll(max.getList());
				max.getList().addAll(act.getList());
			}
			
		}
	}
	
	public static void main(String[] args) {
		List<GeneralTree<Integer>> subChildren1 = new LinkedList<GeneralTree<Integer>>();
        subChildren1.add(new GeneralTree<Integer>(1));
        subChildren1.add(new GeneralTree<Integer>(1));
        subChildren1.add(new GeneralTree<Integer>(1));
        GeneralTree<Integer> subA = new GeneralTree<Integer>(1, subChildren1);
        List<GeneralTree<Integer>> subChildren2 = new LinkedList<GeneralTree<Integer>>();
        subChildren2.add(subA);
        subChildren2.add(new GeneralTree<Integer>(1));
        GeneralTree<Integer> a1 = new GeneralTree<Integer>(0, subChildren2);
        
        List<GeneralTree<Integer>> subChildren3 = new LinkedList<GeneralTree<Integer>>();
        subChildren3.add(new GeneralTree<Integer>(1));
        GeneralTree<Integer> subB = new GeneralTree<Integer>(0, subChildren3);
        List<GeneralTree<Integer>> subChildren4 = new LinkedList<GeneralTree<Integer>>();
        subChildren4.add(subB);
        GeneralTree<Integer> subC = new GeneralTree<Integer>(0, subChildren4);
        List<GeneralTree<Integer>> subChildren5 = new LinkedList<GeneralTree<Integer>>();
        subChildren5.add(new GeneralTree<Integer>(1));
        subChildren5.add(subC);
        GeneralTree<Integer> a2 = new GeneralTree<Integer>(1, subChildren5);
        
        List<GeneralTree<Integer>> subChildren6 = new LinkedList<GeneralTree<Integer>>();
        subChildren6.add(new GeneralTree<Integer>(0));
        subChildren6.add(new GeneralTree<Integer>(0));
        GeneralTree<Integer> subD = new GeneralTree<Integer>(0, subChildren6);
        List<GeneralTree<Integer>> subChildren7 = new LinkedList<GeneralTree<Integer>>();
        subChildren7.add(subD);
        GeneralTree<Integer> subE = new GeneralTree<Integer>(0, subChildren7);
        List<GeneralTree<Integer>> subChildren8 = new LinkedList<GeneralTree<Integer>>();
        subChildren8.add(subE);
        GeneralTree<Integer> a3 = new GeneralTree<Integer>(1, subChildren8);
        
        List<GeneralTree<Integer>> arbol = new LinkedList<GeneralTree<Integer>>();
        arbol.add(a1);
        arbol.add(a2);
        arbol.add(a3);
        GeneralTree<Integer> a = new GeneralTree<Integer>(1, arbol);
        
        System.out.println(resolver(a));
	}

}
