package ar.edu.unlp.info.oo2.ejercicio20_PersonajesJuegosDeRol;

public class Director {
	private PersonajeBuilder builder;
	
	public Director(PersonajeBuilder builder) {
		this.builder = builder;
	}

	public void crearPersonaje() {
		this.builder.reset();
		this.builder.setearNombre();
		this.builder.setearVida();
		this.builder.agregarArmadura();
		this.builder.agregarArma();
		this.builder.agregarHabilidades();
	}
}
