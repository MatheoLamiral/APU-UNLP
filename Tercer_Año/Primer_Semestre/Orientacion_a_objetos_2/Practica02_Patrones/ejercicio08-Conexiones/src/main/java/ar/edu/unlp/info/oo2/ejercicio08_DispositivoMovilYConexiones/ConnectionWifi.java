package ar.edu.unlp.info.oo2.ejercicio08_DispositivoMovilYConexiones;

public class ConnectionWifi implements Connection {
	private String pict;

	
	public ConnectionWifi() {
		this.pict = "wifi";
	}

	@Override
	public String sendData(String data, int crc) {
		// TODO Auto-generated method stub
		return "wifi: "+ data + " " + crc;
	}

	@Override
	public String pict() {
		// TODO Auto-generated method stub
		return this.pict;
	}
	
	
}
