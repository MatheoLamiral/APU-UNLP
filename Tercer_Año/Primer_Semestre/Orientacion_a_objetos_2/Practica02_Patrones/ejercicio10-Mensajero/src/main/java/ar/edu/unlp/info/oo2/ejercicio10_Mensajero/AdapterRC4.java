package ar.edu.unlp.info.oo2.ejercicio10_Mensajero;

public class AdapterRC4 extends Strategy {
	private RC4 rc4;

	public AdapterRC4() {
		this.rc4 = new RC4();
	}

	@Override
	public String encriptar(String mensaje) {
		// TODO Auto-generated method stub
		return this.rc4.encriptar(mensaje, this.getClave());
	}

	@Override
	public String descencriptar(String mensaje) {
		// TODO Auto-generated method stub
		return this.rc4.desencriptar(mensaje, this.getClave());
	}

	

}
