package Practica3.ejercicio8;

import java.util.LinkedList;
import java.util.List;

import Practica3.ejercicio1_3_5.GeneralTree;

public class Navidad {
	private GeneralTree<Integer> gt;	
	
	 public Navidad(GeneralTree<Integer> unGt) {
		this.gt = unGt;
	}

	public String esAbetoNavidenio() {
		 if(this.gt.isEmpty())return "NO";
		 else if(esAbetoNavidenio(this.gt))return "YES";
		 else return "NO";
	 }
	 
	 public boolean esAbetoNavidenio(GeneralTree<Integer> gt) {
		 int cant_hojas=0;
		 boolean es_abeto=true;
		 for(GeneralTree<Integer> child:gt.getChildren()) {
			 if(child.isLeaf()) {
				 cant_hojas++;
			 }
			 else es_abeto = es_abeto && esAbetoNavidenio(child);
			 if(es_abeto==false)break;
		 }
		 if(cant_hojas<3)es_abeto=false;
		 return es_abeto;
	 }
	 
	 public static void main(String[] args) {
			List <GeneralTree <Integer>> childs2 = new LinkedList<GeneralTree <Integer>>();
			childs2.add(new GeneralTree<Integer>(7));
			childs2.add(new GeneralTree<Integer>(8));
			childs2.add(new GeneralTree<Integer>(9));
			childs2.add(new GeneralTree<Integer>(10));
			GeneralTree <Integer> subAb2 = new GeneralTree <Integer>(4, childs2);
			List <GeneralTree <Integer>> childs = new LinkedList<GeneralTree <Integer>>();
			childs.add(new GeneralTree<Integer>(2));
			childs.add(new GeneralTree<Integer>(3));
			childs.add(subAb2);
			childs.add(new GeneralTree<Integer>(5));
			GeneralTree <Integer> arbolGeneral = new GeneralTree <Integer>(1, childs);
			
			Navidad n = new Navidad(arbolGeneral);
			
			System.out.println("¿Es abeto navideño?");
			System.out.println(n.esAbetoNavidenio());
	 }
}
