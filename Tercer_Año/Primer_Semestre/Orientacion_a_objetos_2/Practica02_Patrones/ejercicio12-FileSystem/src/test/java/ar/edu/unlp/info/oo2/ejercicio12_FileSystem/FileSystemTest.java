package ar.edu.unlp.info.oo2.ejercicio12_FileSystem;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileSystemTest {
	private FileSystem fs;
	private Directorio dir1;
	private Directorio dir2;
	private Directorio dir3;
	private Archivo arch1;
	private Archivo arch2;
	private Archivo arch3;
	private Archivo arch4;
	private Archivo arch5;
	

	@BeforeEach
	void setUp() throws Exception {
		this.fs = new FileSystem();
		this.arch1 = new Archivo("archivo 1",LocalDate.of(2019, 12, 2),125);
		this.arch2 = new Archivo("archivo 2",LocalDate.of(2020, 2, 19),124);
		this.arch3 = new Archivo("archivo 3",LocalDate.of(2017, 12, 2),70);
		this.arch4 = new Archivo("archivo 4",LocalDate.of(2021, 7, 6),70);
		this.arch5 = new Archivo("archivo 5",LocalDate.of(2021, 7, 7),90);
		this.dir1 = new Directorio("directorio 1", LocalDate.of(2024, 12, 12));
		this.dir2 = new Directorio("directorio 2", LocalDate.of(2023, 10, 5));
		this.dir3 = new Directorio("directorio 3",LocalDate.of(2024, 12, 11));
		this.dir1.agregarElemento(arch1);
		this.dir1.agregarElemento(arch2);
		this.dir2.agregarElemento(arch3);
		this.dir2.agregarElemento(arch4);
		this.dir3.agregarElemento(arch5);
		this.dir2.agregarElemento(dir3);
		this.fs.getRoot().agregarElemento(this.dir1);
		this.fs.getRoot().agregarElemento(this.dir2);
	}

	@Test
	void testBuscarExitoso() {
		System.out.println(this.fs.listadoDeContenido());
		assertEquals(arch5, this.fs.buscar("archivo 5"));
		assertEquals(dir3, this.fs.buscar("directorio 3"));
	}
	@Test
	void testBuscarFallido() {
		assertEquals(null, this.fs.buscar("archivo inexistente"));
	}
	
	@Test
	void testBuscarTodosExitoso() {
		Archivo arch = new Archivo("archivo 5",LocalDate.of(2021, 7, 7),90);
		this.dir2.agregarElemento(arch);
		List<Elemento> esp = new ArrayList();
		esp.add(arch);
		esp.add(this.arch5);
		List<Elemento> res = new ArrayList();
		this.fs.buscarTodos("archivo 5", res);
		assertTrue(esp.containsAll(res) && res.containsAll(esp));
	}
	
	@Test
	void testBuscarTodosFallido() {
		List<Elemento> res = new ArrayList();
		this.fs.buscarTodos("archivo inexistente", res);
		assertTrue(res.isEmpty());
	}
	
	@Test
	void testArchivoMasNuevo() {
		assertEquals(this.arch5,this.fs.archivoMasNuevo());
		assertNotEquals(this.arch4, this.fs.archivoMasNuevo());
	}
	
	@Test
	void testArchivoMasGrande() {
		assertEquals(this.arch1,this.fs.archivoMasGrande());
		assertNotEquals(this.arch2, this.fs.archivoMasGrande());
	}
	
	@Test
	void testCalcularTamTolal() {
		assertEquals(607,this.fs.tamTotalOcupado());
		assertNotEquals(607.1,this.fs.tamTotalOcupado());
		assertNotEquals(606.9,this.fs.tamTotalOcupado());
	}

}
