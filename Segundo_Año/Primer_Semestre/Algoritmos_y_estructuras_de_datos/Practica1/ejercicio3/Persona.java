package Practica1.ejercicio3;

public abstract class Persona {
	private String nom;
	private String ape;
	private String email;
	
	public String tusDatos() {
		String aux ="Nombre: "+this.getNom();
		aux += " Apellido: "+this.getApe();
		aux += " Email"+this.getEmail();
		return aux;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getApe() {
		return ape;
	}

	public void setApe(String ape) {
		this.ape = ape;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
