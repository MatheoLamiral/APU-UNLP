package ar.edu.unlp.info.oo2.ejercicio06_Excursiones;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Excursion {
	private String nombre;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private String puntoDeEncuentro;
	private double costo;
	private int cupoMax;
	private int cupoMin;
	private List<Usuario> inscriptos;
	private List<Usuario> listaDeEspera;
	private ExcursionState state;
	
	public Excursion(String nombre, LocalDate fechaInicio, LocalDate fechaFin, String puntoDeEncuentro, double costo, int cupoMax, int cupoMin) {
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.puntoDeEncuentro = puntoDeEncuentro;
		this.costo = costo;
		this.cupoMax = cupoMax;
		this.cupoMin = cupoMin;
		this.inscriptos = new ArrayList();
		this.listaDeEspera = new ArrayList();
		this.state = new Provisoria();
	}

	public void inscribir(Usuario usuario) {
		this.state.inscribir(this, usuario);
	}
	
	public String obtenerInformacion() {
		return this.state.obtenerInformacion(this);
	}
	
	protected void agregarInscripo(Usuario usuario) {
		this.inscriptos.add(usuario);
	}
	
	protected void agregarListaEspera(Usuario usuario) {
		this.listaDeEspera.add(usuario);
	}
	
	protected boolean cumpleMinimo() {
		return this.inscriptos.size() == this.cupoMin;
	}
	protected boolean cumpleMaximo() {
		return this.inscriptos.size() == this.cupoMax;
	}
	
	protected void switchState(ExcursionState state) {
		this.state = state;		
	}
	
	protected int usuariosFaltantesCupoMin() {
		return this.cupoMin - this.inscriptos.size();
	}
	
	protected int usuariosFaltantesCupoMax() {
		return this.cupoMax - this.inscriptos.size();
	}
	
	protected List<String> mailInscriptos() {
		return this.inscriptos.stream().map(Usuario::getEmail).collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return this.getNombre() + this.getCosto() + this.getFechaInicio() + this.getFechaFin() + this.getPuntoDeEncuentro();
	}

	protected String getNombre() {
		return nombre;
	}

	protected LocalDate getFechaInicio() {
		return fechaInicio;
	}

	protected LocalDate getFechaFin() {
		return fechaFin;
	}

	protected String getPuntoDeEncuentro() {
		return puntoDeEncuentro;
	}

	protected double getCosto() {
		return costo;
	}
	
	
}
