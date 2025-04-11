package ar.edu.unlp.info.oo1.ejercicio23_mercadoDeObjetos;


import java.util.LinkedList;
import java.util.List;

import ar.edu.unlp.info.oo1.ejercicio21_bag.BagImpl;

public class Usuario {
	private String nombre;
	private String direccion;
	private List<Producto> productos;
	private List<Pedido> pedidos;
	
		
	public Usuario(String nombre, String direccion) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.productos = new LinkedList<Producto>();
		this.pedidos = new LinkedList<Pedido>();
	}
	
	public void crearPedido(FormaDePago fp, FormaDeEnvio fe, Producto producto, int cantidad) {
		if(producto.comprar(cantidad)) {
			this.pedidos.add(new Pedido(producto,cantidad,fp,fe));
		}
	}

	public BagImpl<String> productoPorCategoria() {
		BagImpl<String> bag = new BagImpl<String>();
		this.pedidos.stream().forEach(pedido -> bag.add(pedido.getCategoriaDeProducto()));
		return bag;
	}
	
	

}
