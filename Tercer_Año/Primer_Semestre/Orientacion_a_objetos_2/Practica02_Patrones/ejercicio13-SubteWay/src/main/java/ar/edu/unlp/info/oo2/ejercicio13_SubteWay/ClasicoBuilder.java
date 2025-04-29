package ar.edu.unlp.info.oo2.ejercicio13_SubteWay;

public class ClasicoBuilder implements SandwichBuilder {
	private Sandwich product;

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		this.product = new Sandwich();
	}

	@Override
	public void AgregarPan() {
		// TODO Auto-generated method stub
		this.product.setPan("brioche");
		this.product.setPrecioPan(100);
	}

	@Override
	public void AgregarAderezo() {
		// TODO Auto-generated method stub
		this.product.setAderezo("aderezo a base de mayonesa");
		this.product.setPrecioAderezo(20);
	}

	@Override
	public void AgregarPrincipal() {
		// TODO Auto-generated method stub
		this.product.setPrincipal("carne de ternera");
		this.product.setPrecioPrincipal(300);
	}

	@Override
	public void AgregarAdicional() {
		// TODO Auto-generated method stub
		this.product.setAdicional("tomate");
		this.product.setPrecioAdicional(80);
	}
	@Override
	public double getPrecio() {
		// TODO Auto-generated method stub
		return this.product.calcularPrecio();
	}

}
