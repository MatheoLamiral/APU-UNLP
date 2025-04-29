package ar.edu.unlp.info.oo2.ejercicio13_SubteWay;

public class SinTaccBuilder implements SandwichBuilder{
	private Sandwich product;

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		this.product = new Sandwich();
	}

	@Override
	public void AgregarPan() {
		// TODO Auto-generated method stub
		this.product.setPan("chipá");
		this.product.setPrecioPan(150);
	}

	@Override
	public void AgregarAderezo() {
		// TODO Auto-generated method stub
		this.product.setAderezo("salsa tártara");
		this.product.setPrecioAderezo(18);
	}

	@Override
	public void AgregarPrincipal() {
		// TODO Auto-generated method stub
		this.product.setPrincipal("carne de pollo");
		this.product.setPrecioPrincipal(250);
	}

	@Override
	public void AgregarAdicional() {
		// TODO Auto-generated method stub
		this.product.setAdicional("verduras grilladas");
		this.product.setPrecioAdicional(200);
	}
	@Override
	public double getPrecio() {
		// TODO Auto-generated method stub
		return this.product.calcularPrecio();
	}

}
