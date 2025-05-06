package ar.edu.unlp.info.oo2.ejercicio20_PersonajesJuegosDeRol;

public class Enfrentamiento {
	private Personaje personaje1;
	private Personaje personaje2;
	
	
	
	public Enfrentamiento(Personaje personaje1, Personaje personaje2) {
		this.personaje1 = personaje1;
		this.personaje2 = personaje2;
	}



	public void enfrentar() {
		if ((this.personaje1.getVida() > 0)&&(this.personaje2.getVida() > 0)) {
			this.personaje1.recibirImpacto(this.personaje2.getArma());
			this.personaje2.recibirImpacto(this.personaje1.getArma());
		}
		else {
			throw new RuntimeException("Personaje con vida insuficiente para el combate");
		}
	}
}
