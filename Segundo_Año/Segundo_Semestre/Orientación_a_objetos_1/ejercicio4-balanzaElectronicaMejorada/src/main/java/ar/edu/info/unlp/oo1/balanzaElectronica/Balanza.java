package ar.edu.info.unlp.oo1.balanzaElectronica;

import java.util.*;

public class Balanza {
	private List<Producto> productos;
	
	public Balanza () {
		this.productos = new LinkedList<Producto>();
	}
	
	public void agregarProducto(Producto producto) {
		this.productos.add(producto);
	}

	public double getPesoTotal() {
		return this.productos.stream().mapToDouble(producto -> producto.getPeso()).sum();
	}

	public double getPrecioTotal() {
		return this.productos.stream().mapToDouble(producto -> producto.getPrecio()).sum();
	}

	public int getCantidadDeProductos() {
		return this.productos.size();
	}

	public Ticket emitirTicket() {
		Ticket t = new Ticket(this.getPesoTotal(),this.getPrecioTotal(),this.getCantidadDeProductos(),this.productos);
		return t;
	}

	public void ponerEnCero() {
		this.productos.removeAll(productos);
	}

}
