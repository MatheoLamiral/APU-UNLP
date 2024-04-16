package Practica1.ejercicio7;

import java.util.*;

public class TestArrayList {

	//inciso D
	public static void metodo(){
		
		List <Estudiante> listEst = new LinkedList <Estudiante>();
		
		//CREO UNA LISTA DE 3 ESTUDIANTES
		Estudiante e1= new Estudiante("nombre 1","apellido 1", 1);
		Estudiante e2= new Estudiante("nombre 2","apellido 2", 2);
		Estudiante e3= new Estudiante("nombre 3","apellido 3", 3);
		listEst.add(e1);
		listEst.add(e2);
		listEst.add(e3);
		
		//GENERO UNA COPIA DE LA LISTA DE ESTUDIANTES
		List <Estudiante> listEstCopy=new LinkedList<Estudiante>(listEst);
		
		//IMPRIMO LA LISTA ORIGINAL
		System.out.println("LISTA ORIGINAL: ");
		System.out.println(listEst.toString());
		//for(Estudiante est:listEst) {
			//System.out.println("["+est.toString()+"]");  *OTRA FORMA*
		//}
		
		//IMPRIMO LA COPIA
		System.out.println("LISTA COPIA: ");
		System.out.println(listEstCopy.toString());
		//for(Estudiante est_copy:listEstCopy) {
			//System.out.println("["+est_copy.toString()+"]"); *OTRA FORMA*
		//}
		
		//MODIFICO UN DATO DE LOS ESTUDIANTES
		e1.setNombre("nuevo nombre");
		e2.setNombre("nuevo nombre");
		e3.setNombre("nuevo nombre");
		
		//IMPRIMO DE NUEVO AMBAS LISTAS
		System.out.println("LISTA ORIGINIAL DESPUES DE MODIFICAR COPIA: ");
		System.out.println(listEst.toString());
		System.out.println("LISTA COPIA MODIFICADA: ");
		System.out.println(listEstCopy.toString());

		
		//INCISO E
		//VERIFICO SI UN ESTUDIANTE ESTA EN LA LISTA, SI NO ESTA LO AGREGO
		Estudiante e4=new Estudiante("nuevo","nuevo",111);
		if(!listEst.contains(e4)) {
			listEst.add(e4);

		}
		System.out.println(listEst);
		
		
	}
	
	public static void main(String[] args) {
		
		//INCISO A
		List <Integer> list= new ArrayList<Integer>();
		System.out.println("ingrese los numeros para agregar a la lista:");
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		
		while(n!=-1){
			list.add(n);
			n = s.nextInt();
		}
		
		for(int i: list){
			System.out.println(i);
		}
		
		//INCISO B
		List<Integer> list_num = new LinkedList<Integer>();
		n= s.nextInt();
		
		while (n!=-1) {
			list_num.add(n);
			n = s.nextInt();
		}
		
		for(int j: list_num){
			System.out.println(j);
		}
		
		s.close();
		
		//INCISO D y E
		metodo();
	
		//INCISO F
		//capicua();


		
	}

}
