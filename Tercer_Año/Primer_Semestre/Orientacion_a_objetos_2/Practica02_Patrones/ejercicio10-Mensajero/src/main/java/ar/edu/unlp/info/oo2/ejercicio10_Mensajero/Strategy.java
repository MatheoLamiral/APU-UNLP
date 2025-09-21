package ar.edu.unlp.info.oo2.ejercicio10_Mensajero;

public abstract  class Strategy {
	private String clave;
	
	public abstract String encriptar(String mensaje);
	
	public abstract String descencriptar(String mensaje);
	
	protected String getClave() {
		return this.clave;
	}

}
