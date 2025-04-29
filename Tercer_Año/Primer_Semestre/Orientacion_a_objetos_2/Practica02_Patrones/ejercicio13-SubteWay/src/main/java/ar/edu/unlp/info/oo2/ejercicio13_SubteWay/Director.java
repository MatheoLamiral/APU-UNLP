package ar.edu.unlp.info.oo2.ejercicio13_SubteWay;

public class Director {
	private SandwichBuilder builder;
	
	public Director(SandwichBuilder builder) {
		this.builder = builder;
	}

	public void crearSandwich() {
		this.builder.reset();
		this.builder.AgregarPan();
		this.builder.AgregarAderezo();
		this.builder.AgregarPrincipal();
		this.builder.AgregarAdicional();
	}
}
