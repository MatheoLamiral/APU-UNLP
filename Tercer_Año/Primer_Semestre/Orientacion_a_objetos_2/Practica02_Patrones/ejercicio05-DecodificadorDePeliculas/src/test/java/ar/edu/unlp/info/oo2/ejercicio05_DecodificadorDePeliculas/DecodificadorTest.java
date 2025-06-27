package ar.edu.unlp.info.oo2.ejercicio05_DecodificadorDePeliculas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DecodificadorTest {
	private Decodificador decodificador;
	private Pelicula thor;
	private Pelicula capitanAmerica;
	private Pelicula ironMan;
	private Pelicula rocky;
	private Pelicula dunkirk;
	private Pelicula rambo;

	@BeforeEach
	void setUp() throws Exception {
		this.thor = new Pelicula(new ArrayList(), "Thor", 2007, 7.9);
		this.capitanAmerica = new Pelicula(new ArrayList(), "Capitan America", 2016, 7.8);
		this.ironMan = new Pelicula(new ArrayList(), "Iron-Man", 2010, 7.9);
		this.rocky = new Pelicula(new ArrayList(), "Rocky", 1976, 8.1);
		this.rambo = new Pelicula(new ArrayList(), "Rambo", 1979, 7.8);
		this.dunkirk = new Pelicula(new ArrayList(), "Dunkirk", 2017, 7.9);
		
		this.thor.getSimilares().add(capitanAmerica);
		this.thor.getSimilares().add(ironMan);
		this.capitanAmerica.getSimilares().add(thor);
		this.capitanAmerica.getSimilares().add(ironMan);
		this.ironMan.getSimilares().add(thor);
		this.ironMan.getSimilares().add(capitanAmerica);
		this.rocky.getSimilares().add(rambo);
		this.rambo.getSimilares().add(rocky);
		
		List<Pelicula> reproducidas = new ArrayList();
		reproducidas.add(thor);
		reproducidas.add(rocky);
		List<Pelicula> grilla = new ArrayList();
		grilla.add(capitanAmerica);
		grilla.add(ironMan);
		grilla.add(thor);
		grilla.add(rocky);
		grilla.add(dunkirk);
		grilla.add(rambo);
		this.decodificador =  new Decodificador(grilla, reproducidas, null);
	}

	@Test
	void testNovedad() {
		this.decodificador.setStrategy(new Novedad());
		List<Pelicula> esperado = new ArrayList();
		esperado.add(dunkirk);
		esperado.add(capitanAmerica);
		esperado.add(ironMan);
		assertEquals(esperado,this.decodificador.sugerir());
	}
	
	@Test
	void testPuntaje() {
		this.decodificador.setStrategy(new Puntaje());
		List<Pelicula> esperado = new ArrayList();
		esperado.add(dunkirk);
		esperado.add(ironMan);
		esperado.add(capitanAmerica);
		List<Pelicula> res = this.decodificador.sugerir();
		assertEquals(esperado,this.decodificador.sugerir());
	}

	@Test
	void testSimilaridad() {
		this.decodificador.setStrategy(new Similaridad());
		List<Pelicula> esperado = new ArrayList();
		esperado.add(capitanAmerica);
		esperado.add(ironMan);
		esperado.add(rambo);
		assertEquals(esperado,this.decodificador.sugerir());
	}

}
