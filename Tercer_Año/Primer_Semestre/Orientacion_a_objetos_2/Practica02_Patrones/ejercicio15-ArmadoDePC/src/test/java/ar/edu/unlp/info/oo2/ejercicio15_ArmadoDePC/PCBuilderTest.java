package ar.edu.unlp.info.oo2.ejercicio15_ArmadoDePC;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PCBuilderTest {
	private Director director;
	private BasicaBuilder basica;
	private IntermediaBuilder intermedia;
	private GamerBuilder gamer;
	private Catalogo catalogo;

	@BeforeEach
	void setUp() throws Exception {
		List<Componente> catalogo = new ArrayList();
		catalogo.add(new Componente("ryzen 3","Procesador básico",50000,70));
		catalogo.add(new Componente("ryzen 5","Procesador intermedio",100000,120));
		catalogo.add(new Componente("ryzen 9","Procesador gamer",300000,230));
		catalogo.add(new Componente("Kingston 8","RAM básica",30000,3));
		catalogo.add(new Componente("Kingston 16","RAM intermedia",50000,6));
		catalogo.add(new Componente("Kingston 32","RAM gamer",90000,12));
		catalogo.add(new Componente("HDD 500GB","Disco básico",60000,1.6));
		catalogo.add(new Componente("SSD 500GB","Disco intermedio",90000,4));
		catalogo.add(new Componente("SSD 1TB","Disco gamer",120000,8));
		catalogo.add(new Componente("GTX 1650","Gráfica intermedia",250000,75));
		catalogo.add(new Componente("RTX 4090","Gráfica gamer",800000,450));
		catalogo.add(new Componente("Gabinete básico","Fuente 300 w",50000,10));
		catalogo.add(new Componente("Gabinete intermedio","Fuente 800 w",50000,10));
		catalogo.add(new Componente("Gabinete gamer","Fuente 1074 w",50000,10));
		this.catalogo = new Catalogo(catalogo);
		this.basica = new BasicaBuilder();
		this.intermedia = new IntermediaBuilder();
		this.gamer = new GamerBuilder();
	}

	@Test
	void testBasica() {
		List<Componente> esperado = new ArrayList();
		esperado.add(new Componente("ryzen 3","Procesador básico",50000,70));
		esperado.add(new Componente("Kingston 8","RAM básica",30000,3));
		esperado.add(new Componente("HDD 500GB","Disco básico",60000,1.6));
		esperado.add(new Componente("Gabinete básico","Fuente 300 w",50000,10));
		this.director = new Director(this.basica);
		this.director.buildPC();
		assertEquals(esperado,this.basica.getPC().getComponentes());
		assertEquals(229900,this.basica.getPC().calcularPrecio());
		assertEquals(84.6,this.basica.getPC().calcularConsumo());
	}

	@Test
	void testIntermedia() {
		List<Componente> esperado = new ArrayList();
		esperado.add(new Componente("ryzen 5","Procesador intermedio",100000,120));
		esperado.add(new Componente("Kingston 16","RAM intermedia",50000,6));
		esperado.add(new Componente("SSD 500GB","Disco intermedio",90000,4));
		esperado.add(new Componente("GTX 1650","Gráfica intermedia",250000,75));
		esperado.add(new Componente("Gabinete intermedio","Fuente 800 w",50000,10));
		this.director = new Director(this.intermedia);
		this.director.buildPC();
		assertEquals(esperado,this.intermedia.getPC().getComponentes());
		assertEquals(653400,this.intermedia.getPC().calcularPrecio());
		assertEquals(215,this.intermedia.getPC().calcularConsumo());
	}
	
	@Test
	void testGamer() {
		Componente c1= new Componente("ryzen 9","Procesador gamer",300000,230);
		Componente c2= new Componente("Kingston 32","RAM gamer",90000,12);
		Componente c3= new Componente("Kingston 32","RAM gamer",90000,12);
		Componente c4= new Componente("SSD 500GB","Disco intermedio",90000,4);
		Componente c5= new Componente("SSD 1TB","Disco gamer",120000,8);
		Componente c6= new Componente("RTX 4090","Gráfica gamer",800000,450);
		Componente c7= new Componente("Gabinete gamer","Fuente 1074 w",50000,10);

		List<Componente> esperado = Arrays.asList(c1,c2,c3,c4,c5,c6,c7);

		this.director = new Director(this.gamer);
		this.director.buildPC();
		assertEquals(esperado,this.gamer.getPC().getComponentes());
		assertEquals(1863400,this.gamer.getPC().calcularPrecio());
		assertEquals(726,this.gamer.getPC().calcularConsumo());
	}
}
