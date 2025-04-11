package ar.edu.unlp.info.oo1.ejercicio23_mercadoDeObjetos;


public class Pedido {
	private Producto producto;
	private int cantidad;
	private FormaDePago formaDePago;
	private FormaDeEnvio formaDeEnvio;
		
	
	public Pedido(Producto producto, int cantidad, FormaDePago formaDePago, FormaDeEnvio formaDeEnvio) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.formaDePago = formaDePago;
		this.formaDeEnvio = formaDeEnvio;
	}


	public double calcularCosto() {
		double precio = this.producto.getPrecio()*this.cantidad;
		return this.formaDePago.calcularPrecioFinal(precio)+this.formaDeEnvio.calcularCosto();
	}
	
	public String getCategoriaDeProducto() {
		return this.producto.getCategoria();
	}
}
