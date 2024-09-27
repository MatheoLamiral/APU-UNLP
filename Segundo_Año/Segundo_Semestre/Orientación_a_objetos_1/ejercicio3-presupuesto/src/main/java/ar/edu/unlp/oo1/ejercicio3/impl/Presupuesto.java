package ar.edu.unlp.oo1.ejercicio3.impl;

import java.time.LocalDate;
import java.util.*;

public class Presupuesto {
	private LocalDate fecha;
	private String cliente;
	private List<Item> items;
	
	public Presupuesto(String cliente) {
		this.fecha = LocalDate.now();
		this.cliente = cliente;
		this.items = new LinkedList<Item>();
	}
	
	public void agregarItem(Item item) {
		this.items.add(item);
	}
	
	public double calcularTotal() {
		return items.stream()
				.mapToDouble(item -> item.costo()).sum();
	}
	
	
	//GETTERS AND SETTERS
	public LocalDate getFecha() {
		return fecha;
	}

	public String getCliente() {
		return cliente;
	}
}
