package ar.edu.info.unlp.oo1.balanzaElectronica;

public class Balanza {
	private int cantidadDeProductos;
	private double precioTotal;
	private double pesoTotal;
	
	public Balanza () {
		this.cantidadDeProductos = 0;
		this.precioTotal = 0;
		this.pesoTotal = 0;
	}
	
	public void agregarProducto(Producto producto) {
		this.cantidadDeProductos ++;
		this.pesoTotal += producto.getPeso();
		this.precioTotal += producto.getPrecio();
	}

	public double getPesoTotal() {
		return this.pesoTotal;
	}

	public double getPrecioTotal() {
		return this.precioTotal;
	}

	public int getCantidadDeProductos() {
		return this.cantidadDeProductos;
	}

	public Ticket emitirTicket() {
		Ticket t = new Ticket(this.pesoTotal,this.precioTotal,this.cantidadDeProductos);
		return t;
	}

	public void ponerEnCero() {
		this.pesoTotal = 0;
		this.cantidadDeProductos = 0;
		this.precioTotal = 0;
	}

}
