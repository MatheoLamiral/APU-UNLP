package ar.edu.unlp.info.oo2.ejercicio08_DispositivoMovilYConexiones;

public class Dispositivo {
	private CRCCalculatorStrategy crcCalculator;
	private Connection connection;
	private Ringer ringer;
	private Display display;
	
	public Dispositivo() {
		this.crcCalculator = new CRC16Calculator();
		this.connection = new ConnectionWifi();
		this.display = new Display();
		this.ringer = new Ringer();
	}

	public String send(String data) {
		return this.connection.sendData(data, this.crcCalculator.crcFor(data));
	}
	
	public void setStrategy(CRCCalculatorStrategy strategy) {
		this.crcCalculator = strategy;;
	}
	
	protected void setConnection(Connection connection) {
		this.connection = connection;
		this.display.showBanner(this.connection.pict());
		this.ringer.ring();
	}

	protected Ringer getRinger() {
		return this.ringer;
	}

	protected Display getDisplay() {
		return display;
	}
	
	protected Connection getConnection() {
		return this.connection;
	}

	protected CRCCalculatorStrategy getCrcCalculator() {
		return this.crcCalculator;
	}
	
	
	
	
}
