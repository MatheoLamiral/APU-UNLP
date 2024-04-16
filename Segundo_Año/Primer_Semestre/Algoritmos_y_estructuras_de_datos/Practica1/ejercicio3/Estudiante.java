package Practica1.ejercicio3;

public class Estudiante extends Persona {
	private int comision;
	private int direc;
	
	public String tusDatos () {
		String aux = super.tusDatos();
		aux += " Comisión: "+this.getComision();
		aux += " Dirección: "+this.getDirec();
		return aux;
	}

	public int getComision() {
		return comision;
	}

	public void setComision(int comision) {
		this.comision = comision;
	}

	public int getDirec() {
		return direc;
	}

	public void setDirec(int direc) {
		this.direc = direc;
	}
	
	
}
