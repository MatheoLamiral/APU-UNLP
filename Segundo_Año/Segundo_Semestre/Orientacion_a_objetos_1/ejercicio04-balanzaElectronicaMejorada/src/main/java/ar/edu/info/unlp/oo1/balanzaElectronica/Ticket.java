package ar.edu.info.unlp.oo1.balanzaElectronica;

import java.time.LocalDate;
import java.util.*;

public class Ticket {
	private LocalDate fecha;
	private List<Producto> productos; 
	private int cantidadDeProductos;
	private double pesoTotal;
	private double precioTotal;
	
	public Ticket(double pesoTotal,double precioTotal,int cantidadDeProductos, List<Producto> productos) {
		this.cantidadDeProductos = cantidadDeProductos;
		this.pesoTotal = pesoTotal;
		this.precioTotal = precioTotal;
		this.fecha = LocalDate.now();
		this.productos = productos;
	}
	public double getPesoTotal() {
		return this.pesoTotal;
	}

	public double getPrecioTotal() {
		return this.precioTotal;
	}

	public int getCantidadDeProductos() {
		return this.cantidadDeProductos;
	}

	public double impuesto() {
		return this.precioTotal * 0.21;
	}

	public LocalDate getFecha() {
		return this.fecha;
	}
	public List<Producto> getProductos() {
		return productos;
	}
	
	

}
