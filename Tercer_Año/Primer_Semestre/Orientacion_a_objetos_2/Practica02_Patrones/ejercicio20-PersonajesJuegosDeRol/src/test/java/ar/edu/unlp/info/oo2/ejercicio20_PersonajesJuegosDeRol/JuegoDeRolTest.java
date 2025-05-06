package ar.edu.unlp.info.oo2.ejercicio20_PersonajesJuegosDeRol;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JuegoDeRolTest {
	private Director director;
	private MagoBuilder magoBuilder;
	private GuerreroBuilder guerreroBuilder;
	private ArqueroBuilder arqueroBuilder;
	private ThoorBuilder thoorBuilder;
	private Enfrentamiento enfrentamiento;
	
	@BeforeEach
	void setUp() throws Exception {
		this.arqueroBuilder = new ArqueroBuilder();
		this.guerreroBuilder = new GuerreroBuilder();
		this.magoBuilder = new MagoBuilder();
		this.thoorBuilder = new ThoorBuilder();
	}

	@Test
	void testArquero() {
		this.director = new Director(this.arqueroBuilder);
		this.director.crearPersonaje();
		List<String> esperado = new ArrayList();
		esperado.add("combate a distancia");
		assertEquals(esperado, this.arqueroBuilder.getPersonaje().getHabilidades());
		assertEquals(5,this.arqueroBuilder.getPersonaje().getArma().impactoContraCuero());
		assertEquals(3,this.arqueroBuilder.getPersonaje().getArma().impactoContraHierro());
		assertEquals(2,this.arqueroBuilder.getPersonaje().getArma().impactoContraAcero());
		assertEquals(8, this.arqueroBuilder.getPersonaje().getArmadura().recibirImpacto(new Espada()));
		assertEquals(5,this.arqueroBuilder.getPersonaje().getArmadura().recibirImpacto(new Arco()));
		assertEquals(2,this.arqueroBuilder.getPersonaje().getArmadura().recibirImpacto(new BastonMagico()));
	}
	
	@Test
	void testGuerrero() {
		this.director = new Director(this.guerreroBuilder);
		this.director.crearPersonaje();
		List<String> esperado = new ArrayList();
		esperado.add("combate cuerpo a cuerpo");
		assertEquals(esperado, this.guerreroBuilder.getPersonaje().getHabilidades());
		assertEquals(8,this.guerreroBuilder.getPersonaje().getArma().impactoContraCuero());
		assertEquals(5,this.guerreroBuilder.getPersonaje().getArma().impactoContraHierro());
		assertEquals(3,this.guerreroBuilder.getPersonaje().getArma().impactoContraAcero());
		assertEquals(3, this.guerreroBuilder.getPersonaje().getArmadura().recibirImpacto(new Espada()));
		assertEquals(2,this.guerreroBuilder.getPersonaje().getArmadura().recibirImpacto(new Arco()));
		assertEquals(1,this.guerreroBuilder.getPersonaje().getArmadura().recibirImpacto(new BastonMagico()));
	}
	
	@Test
	void testMago() {
		this.director = new Director(this.magoBuilder);
		this.director.crearPersonaje();
		List<String> esperado = new ArrayList();
		esperado.add("magia");
		esperado.add("combate a distancia");
		assertEquals(esperado, this.magoBuilder.getPersonaje().getHabilidades());
		assertEquals(2,this.magoBuilder.getPersonaje().getArma().impactoContraCuero());
		assertEquals(1,this.magoBuilder.getPersonaje().getArma().impactoContraHierro());
		assertEquals(1,this.magoBuilder.getPersonaje().getArma().impactoContraAcero());
		assertEquals(8, this.magoBuilder.getPersonaje().getArmadura().recibirImpacto(new Espada()));
		assertEquals(5,this.magoBuilder.getPersonaje().getArmadura().recibirImpacto(new Arco()));
		assertEquals(2,this.magoBuilder.getPersonaje().getArmadura().recibirImpacto(new BastonMagico()));
	}
	
	@Test
	void testThoor() {
		this.director = new Director(this.thoorBuilder);
		this.director.crearPersonaje();
		List<String> esperado = new ArrayList();
		esperado.add("lanzar rayos");
		esperado.add("combate a distancia");
		assertEquals(esperado, this.thoorBuilder.getPersonaje().getHabilidades());
		assertEquals(16,this.thoorBuilder.getPersonaje().getArma().impactoContraCuero());
		assertEquals(10,this.thoorBuilder.getPersonaje().getArma().impactoContraHierro());
		assertEquals(6,this.thoorBuilder.getPersonaje().getArma().impactoContraAcero());
		assertEquals(5, this.thoorBuilder.getPersonaje().getArmadura().recibirImpacto(new Espada()));
		assertEquals(3,this.thoorBuilder.getPersonaje().getArmadura().recibirImpacto(new Arco()));
		assertEquals(1,this.thoorBuilder.getPersonaje().getArmadura().recibirImpacto(new BastonMagico()));
	}
	
	@Test
	void testEnfrentamiento() {
		this.director = new Director(this.magoBuilder);
		Director director1 = new Director(this.arqueroBuilder);
		Director director2 = new Director(this.guerreroBuilder);
		this.director.crearPersonaje();
		director1.crearPersonaje();
		director2.crearPersonaje();
		this.enfrentamiento = new Enfrentamiento(this.magoBuilder.getPersonaje(),this.guerreroBuilder.getPersonaje());
		this.enfrentamiento.enfrentar();
		//baston vs acero = 1 daño
		assertEquals(99,this.guerreroBuilder.getPersonaje().getVida());
		//espada vs cuero = 8 daño
		assertEquals(92,this.magoBuilder.getPersonaje().getVida());
		this.enfrentamiento = new Enfrentamiento(this.magoBuilder.getPersonaje(),this.arqueroBuilder.getPersonaje());
		this.enfrentamiento.enfrentar();
		//baston vs cuero = 2 daño
		assertEquals(87,this.magoBuilder.getPersonaje().getVida());
		//arco vs cuero = 5 daño
		assertEquals(98,this.arqueroBuilder.getPersonaje().getVida());
		//dejo a el mago sin vida
		this.enfrentamiento = new Enfrentamiento(this.magoBuilder.getPersonaje(),this.guerreroBuilder.getPersonaje());
		this.enfrentamiento.enfrentar();
		this.enfrentamiento.enfrentar();
		this.enfrentamiento.enfrentar();
		this.enfrentamiento.enfrentar();
		this.enfrentamiento.enfrentar();
		this.enfrentamiento.enfrentar();
		this.enfrentamiento.enfrentar();
		this.enfrentamiento.enfrentar();
		this.enfrentamiento.enfrentar();
		this.enfrentamiento.enfrentar();
		this.enfrentamiento = new Enfrentamiento(this.magoBuilder.getPersonaje(),this.arqueroBuilder.getPersonaje());
		this.enfrentamiento.enfrentar();
		this.enfrentamiento = new Enfrentamiento(this.magoBuilder.getPersonaje(),this.magoBuilder.getPersonaje());
		this.enfrentamiento.enfrentar();
		assertThrows(RuntimeException.class, () -> this.enfrentamiento.enfrentar());
	}

}
