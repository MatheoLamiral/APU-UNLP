package Practica1.ejercicio3;

public class Profesor extends Persona{
	private String catedra;
	private String facultad;
	
	public String tusDatos() {
		String aux = super.tusDatos();
		aux += " Cátedra: "+this.getCatedra();
		aux += " Facultad: "+this.getFacultad();
		return aux;
	}
	public String getCatedra() {
		return catedra;
	}
	public void setCatedra(String catedra) {
		this.catedra = catedra;
	}
	public String getFacultad() {
		return facultad;
	}
	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}
	
	
}
