package ar.edu.unlp.info.oo2.ejercicio08_DispositivoMovilYConexiones;

public class Connection4GAdapter implements Connection {
	private Connection4G adptee;

	public Connection4GAdapter() {
		this.adptee = new Connection4G();
	}

	@Override
	public String sendData(String data, int crc) {
		// TODO Auto-generated method stub
		return this.adptee.transmit(data, crc);
	}

	@Override
	public String pict() {
		// TODO Auto-generated method stub
		return this.adptee.symb();
	}

}
