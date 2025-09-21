package ar.edu.unlp.info.oo2.ejercicio10_Mensajero;

public class AdapterFeistelCipher extends Strategy {
	private FeistelCipher fc;
	
	public AdapterFeistelCipher() {
		this.fc = new FeistelCipher(this.getClave());
	}

	@Override
	public String encriptar(String mensaje) {
		// TODO Auto-generated method stub
		return this.fc.encode(mensaje);
	}

	@Override
	public String descencriptar(String mensaje) {
		// TODO Auto-generated method stub
		return this.fc.encode(mensaje);
	}

	

}
