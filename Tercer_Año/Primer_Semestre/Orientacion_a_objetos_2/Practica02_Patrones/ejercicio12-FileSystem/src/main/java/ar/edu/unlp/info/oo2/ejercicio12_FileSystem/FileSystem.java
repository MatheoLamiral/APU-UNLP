package ar.edu.unlp.info.oo2.ejercicio12_FileSystem;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class FileSystem {
	private Directorio root;
	
	public FileSystem() {
		this.root = new Directorio("",LocalDate.now());
	}
	
	public Double tamTotalOcupado() {
		return this.root.calcularTam();
	}
	
	public Archivo archivoMasGrande() {
		return this.root.archivoMasGrande();
	}
	
	public Archivo archivoMasNuevo() {
		return this.root.archivoMasNuevo();		
	}
	
	public Elemento buscar(String nombre) {
		return this.root.buscar(nombre);
	}
	
	public void buscarTodos(String nombre, List<Elemento> res){
		this.root.buscarTodos(nombre, res);			
	}
	
	public String listadoDeContenido() {
		return this.root.listadoDeContenido("");
	}
	
	public Directorio getRoot() {
		return this.root;
	}
}
