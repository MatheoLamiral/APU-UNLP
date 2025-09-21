package ar.edu.unlp.info.oo2.ejercicio12_FileSystem;

import java.time.LocalDate;
import java.util.List;

public class Archivo extends Elemento {
	private double tam;
	
	public Archivo(String nombre, LocalDate fechaCreacion, double tam) {
		super(nombre, fechaCreacion);
		this.tam = tam;
	}

	@Override
	public double calcularTam() {
		// TODO Auto-generated method stub
		return this.tam;
	}
	
	@Override
	public Archivo archivoMasGrande() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Archivo archivoMasNuevo() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Elemento buscar(String nombre) {
		// TODO Auto-generated method stub
		return this.coincide(nombre) ? this : null;
	}

	@Override
	public void buscarEnHijos(String nombre, List<Elemento> res) {
		// TODO Auto-generated method stub
	}

	@Override
	public String listadoDeContenido(String path) {
		// TODO Auto-generated method stub
		return path+this.getNombre()+"\n";
	}


}
