package ar.edu.unlp.info.oo2.ejercicio12_FileSystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public abstract class Elemento {
	private String nombre;
	private LocalDate fechaCreacion;
	
	public Elemento(String nombre, LocalDate fechaCreacion) {
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
	}
	
	public abstract double calcularTam();
	public abstract Archivo archivoMasGrande();
	public abstract Archivo archivoMasNuevo();
	public abstract Elemento buscar(String nombre);
	public abstract String listadoDeContenido(String path);
	
	public void buscarTodos(String nombre, List<Elemento> res){
		if(this.coincide(nombre))
			res.add(this);
		this.buscarEnHijos(nombre, res);
	}
	
	public abstract void buscarEnHijos(String nombre, List<Elemento> res);
	
	public boolean coincide(String nombre) {
		return this.getNombre().equals(nombre);
	}
	public int getAntiguedad() {
		return (int)ChronoUnit.DAYS.between(this.fechaCreacion,LocalDate.now());
	}
	public String getNombre() {
		return this.nombre;
	}
	public String toString() {
		return this.getNombre();
	}
}
