package ar.edu.unlp.info.oo2.ejercicio12_FileSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Directorio extends Elemento {
	private List<Elemento> elementos;
	
	public Directorio(String nombre, LocalDate fechaCreacion) {
		super(nombre, fechaCreacion);
		this.elementos = new ArrayList();
	}

	@Override
	public double calcularTam() {
		// TODO Auto-generated method stub
		return 32 + this.elementos.stream().mapToDouble(e -> e.calcularTam()).sum();
	}
	
	@Override
	public Archivo archivoMasGrande() {
		// TODO Auto-generated method stub
		if(this.elementos.isEmpty())
			return null;
		return this.elementos.stream()
				.map(Elemento::archivoMasGrande)
				.filter(Objects::nonNull)
				.max(Comparator.comparingDouble(Archivo::calcularTam))
				.orElse(null);
	}
	
	@Override
	public Archivo archivoMasNuevo() {
		// TODO Auto-generated method stub
		if(this.elementos.isEmpty())
			return null;
		return this.elementos.stream()
				.map(Elemento::archivoMasNuevo)
				.filter(Objects::nonNull)
				.min(Comparator.comparingDouble(Archivo::getAntiguedad))
				.orElse(null);
	}
	
	public Elemento buscar(String nombre) {
		if(this.coincide(nombre)) {
			return this;
		}
		return this.elementos.stream()
				.map(e -> e.buscar(nombre))
				.filter(Objects::nonNull)
				.findFirst()
				.orElse(null);
	}

	@Override
	public void buscarEnHijos(String nombre, List<Elemento> res) {
		// TODO Auto-generated method stub
		this.elementos.stream().forEach(e -> e.buscarTodos(nombre, res));
	}

	@Override
	public String listadoDeContenido(String path) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		String pathActual = path+this.getNombre();
		sb.append(pathActual).append("\n")
		  .append(
			this.elementos.stream().map(e->e.listadoDeContenido(pathActual+"/")).collect(Collectors.joining())	  
		  );
		return sb.toString();
	}
	
	public void agregarElemento(Elemento elemento) {
		this.elementos.add(elemento);
	}
	

}
