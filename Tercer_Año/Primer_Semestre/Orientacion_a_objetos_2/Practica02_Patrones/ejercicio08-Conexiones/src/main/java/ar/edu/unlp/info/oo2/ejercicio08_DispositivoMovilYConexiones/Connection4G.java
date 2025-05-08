package ar.edu.unlp.info.oo2.ejercicio08_DispositivoMovilYConexiones;

public class Connection4G  {
	private String symb;

	
	public Connection4G() {
		this.symb = "4G";
	}


	public String transmit(String data, int crc) {
		// TODO Auto-generated method stub
		return "4G: "+ data + " " + crc;
	}

	
	public String symb() {
		// TODO Auto-generated method stub
		return this.symb;
	}
	
	
}
