package ar.edu.unlp.info.oo2.ejercicio15_ArmadoDePC;

public class Director {
	private PCBuilder builder;
	
	public Director(PCBuilder builder) {
		this.builder = builder;
	}
	
	public void buildPC() {
		this.builder.agregarProcesador();
		this.builder.agregarRAM();
		this.builder.agregarDisco();
		this.builder.agregarGrafica();
		this.builder.agregarGabinete();
	}
}
