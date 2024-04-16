package Practica1.ejercicio7;

public class Estudiante {
	private String nombre;
	private String apellido;
	private int dni;
	
	public Estudiante(String unNombre,String unApellido, int unDni) {
		this.setNombre(unNombre);
		this.setApellido(unApellido);
		this.setDni(unDni);
	}

	
	@Override
	public String toString() {
		return "nombre:" + this.getNombre() + ", apellido:" + this.getApellido() + ", DNI: " + this.getDni();
	}


	//GETTERS AND SETTERS 
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}
	
	
}
