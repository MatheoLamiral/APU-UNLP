package Practica5.ejercicio2;

import java.util.*;
import Practica5.ejercicio1.*;
import Practica5.ejercicio1.adjList.*;
import Practica1.ejercicio8.Queue;

public class Recorridos<T> {

		public Recorridos() {
			
	    }	
		
		//O(|V|+|E|) V vertices y E aristas
		public List<T> dfs(Graph<T> grafo){
			//CREO EL VECTOR DE MARCAS
			boolean[] marca = new boolean[grafo.getSize()];
			//CREO LA LISTA QUE ALMACENARA EL CAMINO
			List<T> list = new LinkedList<T>();
			//POR CADA VERTICE DEL GRAFO
			for(int i = 0; i < grafo.getSize(); i ++) {
				//SI NO ESTA VISITADO
				if(!marca[i]) {
					//LLAMO AL HELPER
					dfs(i,grafo,list,marca);
				}
			}
			//RETORNO LA LISTA
			return list;
		}
		
		private void dfs(int i,Graph<T> grafo,List<T> list, boolean[]marca) {
			//MARCO EL VERTICE COMO VISITADO EN EL ARREGLO
			marca[i] = true;
			//TOMO EL VERTICE
			Vertex<T> v = grafo.getVertex(i);
			//AÑADO EL VERTICE A LA LISTA CON EL CAMINO
			list.add(v.getData());
			//PARA CADA UNO DE LOS ADYACENTES DEL VERTICE
			for (Edge<T> e: grafo.getEdges(v)) {
				//TOMO LA POSICION
				int j = e.getTarget().getPosition();
				//SI NO ESTA VISITADO
				if(!marca[j]) {
					//LLAMADO RECURSIVO
					dfs(j,grafo,list,marca);
				}
			}
		}
		
		//O(|V|+|E|) V vertices y E aristas
		public List<T> bfs(Graph<T> grafo){
			//CREO EL VECTOR DE MARCAS
			boolean[] marca = new boolean[grafo.getSize()];
			//CREO LA LISTA QUE ALMACENARA EL CAMINO
			List<T> list = new LinkedList<T>();
			//POR CADA VERTICE DEL GRAFO
			for(int i = 0; i < grafo.getSize(); i ++) {
				//SI NO ESTA VISITADO
				if(!marca[i]) {
					//LLAMO AL HELPER
					bfs(i,grafo,list,marca);
				}
			}
			//RETORNO LA LISTA
			return list;
		}
		
		private void bfs(int i,Graph<T> grafo,List<T> list, boolean[]marca) {
			//DECLARO LA COLA
			Queue<Vertex<T>> q = new Queue<Vertex<T>>();
			//ENCOLO EL VERTICE ENM LA POSICION I
			q.enqueue(grafo.getVertex(i));
			//LO MARCO COMO VISITADO
			marca[i] = true;
			//MIENTRAS LA COLA NO ESTE VACIA
			while(!q.isEmpty()) {
				//DESENCOLO EL PRIMER ELEMENTO
				Vertex<T> w = q.dequeue();
				//AGREGO A LA LISTA
				list.add(w.getData());
				//PARA TODOS LOS ADYACENTES DEL ELEMENTO QUE DESENCOLE
				for(Edge<T> e: grafo.getEdges(w)) {
					//ME GUARDO LA POSICION
					int j = e.getTarget().getPosition();
					//SI NO ESTA VISITADO
					if(!marca[j]) {
						//LO MARCO COMO VISITADO
						marca[j] = true;
						//LO ENCOLO
						q.enqueue(e.getTarget());
					}
				}
			}
		}
		
		public static void main(String[] args) {
	        Graph<String> ciudades = new AdjListGraph<String>();
	        Recorridos<String> rec = new Recorridos<String>();
	        Vertex<String> v1 = ciudades.createVertex("Buenos Aires");
	        Vertex<String> v2 = ciudades.createVertex("Santiago");
	        Vertex<String> v3 = ciudades.createVertex("Asunción");
	        Vertex<String> v4 = ciudades.createVertex("Tokio");
	        Vertex<String> v5 = ciudades.createVertex("Roma");
	        Vertex<String> v6 = ciudades.createVertex("Paris");
	        Vertex<String> v7 = ciudades.createVertex("Madrid");
	        Vertex<String> v8 = ciudades.createVertex("Caracas");
	        ciudades.connect(v1, v2);
	        ciudades.connect(v1, v3);
	        ciudades.connect(v2, v5);
	        ciudades.connect(v3, v7);
	        ciudades.connect(v3, v8);
	        ciudades.connect(v8, v7);
	        ciudades.connect(v8, v4);
	        ciudades.connect(v5, v4);
	        ciudades.connect(v7, v4);
	        ciudades.connect(v6, v5);
	        ciudades.connect(v6, v7);
	        ciudades.connect(v6, v4);
	        ciudades.connect(v4, v1);
	        
	        List<String> listDfs = rec.dfs(ciudades);
	        List<String> listBfs = rec.bfs(ciudades);
	        
	        System.out.print("Lista DFS: ");
	        System.out.print(listDfs);
	        
	        System.out.println("");
	        
	        System.out.print("Lista BFS: ");
	        System.out.print(listBfs);
	    }
}
