package ar.edu.unlp.info.oo1.ejercicio25_veterinaria;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Mascota {
	private String nombre;
	private LocalDate fechaNacimiento;
	private String especie;
	private List<Servicio> servicios;
	
	public Mascota(String nombre, LocalDate fechaNacimiento, String especie) {
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.especie = especie;
		this.servicios = new LinkedList<Servicio>();
	}
	protected void agregarServicio(Servicio servicio) {
		this.servicios.add(servicio);
	}
	protected int cantidadDeServicios() {
		return this.servicios.size();
	}
}
