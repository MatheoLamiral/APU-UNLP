package ar.edu.unlp.info.oo2.ejercicio20_PersonajesJuegosDeRol;

import java.util.List;

public class Personaje {
	private  String nombre;
	private Armadura armadura;
	private Arma arma;
	private List<String> habilidades;
	private int vida = 100;
	
	
	
	public void recibirImpacto(Arma arma){
		int impacto = this.armadura.recibirImpacto(arma);
		if (this.vida - impacto < 0) {
			this.vida = 0;
		}
		else {
			this.vida -= impacto;
		}
	}
	
	protected int getVida() {
		return this.vida;
	}
	protected Arma getArma() {
		return this.arma;
	}
	protected String getNombre() {
		return nombre;
	}
	protected Armadura getArmadura() {
		return armadura;
	}
	protected List<String> getHabilidades() {
		return habilidades;
	}
	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}
	protected void setArmadura(Armadura armadura) {
		this.armadura = armadura;
	}
	protected void setArma(Arma arma) {
		this.arma = arma;
	}
	protected void setHabilidades(List<String> habilidades) {
		this.habilidades = habilidades;
	}
	protected void setVida(int vida) {
		this.vida = vida;
	}
	
	
}
