package ar.edu.unlp.info.oo2.ejercicio20_PersonajesJuegosDeRol;

public abstract class PersonajeBuilder {
	private Personaje personaje;

	public void reset() {
		this.personaje = new Personaje();
	}
	public void setearVida() {
		this.personaje.setVida(100);
	}
	public void setearNombre() {
		String nombre = System.in.toString();
		this.personaje.setNombre(nombre);
	}
	public abstract void agregarArmadura();
	public abstract void agregarArma();
	public abstract void agregarHabilidades();
	
	protected Personaje getPersonaje() {
		return this.personaje;
	}
}
