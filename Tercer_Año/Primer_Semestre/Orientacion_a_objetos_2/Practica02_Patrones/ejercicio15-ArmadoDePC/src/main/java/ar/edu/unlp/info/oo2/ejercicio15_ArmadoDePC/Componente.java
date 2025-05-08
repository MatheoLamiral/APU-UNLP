package ar.edu.unlp.info.oo2.ejercicio15_ArmadoDePC;

public class Componente {
	private String nombre;
	private String desc;
	private double precio;
	private double consumo;
	
	public Componente(String nombre, String desc, double precio, double consumo) {
		this.nombre = nombre;
		this.desc = desc;
		this.precio = precio;
		this.consumo = consumo;
	}
	
	protected String getNombre() {
		return nombre;
	}
	protected String getDesc() {
		return desc;
	}
	protected double getPrecio() {
		return precio;
	}
	protected double getConsumo() {
		return consumo;
	}
	
	public String toString() {
		return this.getNombre();
	}
	
	public boolean equals(Object o) {
		return o.toString().equals(this.toString());
		
	}
	
	
}
