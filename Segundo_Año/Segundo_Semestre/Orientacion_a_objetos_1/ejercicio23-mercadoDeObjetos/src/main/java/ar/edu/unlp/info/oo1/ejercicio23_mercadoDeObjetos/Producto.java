package ar.edu.unlp.info.oo1.ejercicio23_mercadoDeObjetos;

public class Producto {
	private String nombre;
	private String categoria;
	private double precio;
	private int unidadesDisponibles;
	
	public Producto(String nombre, String categoria, double precio, int unidadesDisponibles) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.unidadesDisponibles = unidadesDisponibles;
	}
	
	public boolean comprar(int unidades) {
		if (this.unidadesDisponibles >= unidades) {
			this.decrementar(unidades);
			return true;
		}
		return false;
	}
	
	private void decrementar(int unidades) {
		this.unidadesDisponibles -= unidades;
	}
	
	public double getPrecio() {
		return this.precio;
	}
	
	public String getCategoria() {
		return this.categoria;
	}
	

}
