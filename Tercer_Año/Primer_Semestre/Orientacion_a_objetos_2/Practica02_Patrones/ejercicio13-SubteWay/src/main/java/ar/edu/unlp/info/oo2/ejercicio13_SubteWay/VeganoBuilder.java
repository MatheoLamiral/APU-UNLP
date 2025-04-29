package ar.edu.unlp.info.oo2.ejercicio13_SubteWay;

public class VeganoBuilder implements SandwichBuilder {
	private Sandwich product;

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		this.product = new Sandwich();
	}

	@Override
	public void AgregarPan() {
		// TODO Auto-generated method stub
		this.product.setPan("pan integral");
		this.product.setPrecioPan(100);
	}

	@Override
	public void AgregarAderezo() {
		// TODO Auto-generated method stub
		this.product.setAderezo("salsa criolla");
		this.product.setPrecioAderezo(20);
	}

	@Override
	public void AgregarPrincipal() {
		// TODO Auto-generated method stub
		this.product.setPrincipal("milanesa de girgolas");
		this.product.setPrecioPrincipal(500);
	}

	@Override
	public void AgregarAdicional() {
		// TODO Auto-generated method stub
		this.product.setAdicional("sin adicional");
		this.product.setPrecioAdicional(0);
	}
	@Override
	public double getPrecio() {
		// TODO Auto-generated method stub
		return this.product.calcularPrecio();
	}

}
