package ar.edu.unlp.info.oo2.ejercicio13_SubteWay;

public class VegetarianoBuilder implements SandwichBuilder {
	private Sandwich product;

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		this.product = new Sandwich();
	}

	@Override
	public void AgregarPan() {
		// TODO Auto-generated method stub
		this.product.setPan("pan con semillas");
		this.product.setPrecioPan(120);
	}

	@Override
	public void AgregarAderezo() {
		// TODO Auto-generated method stub
		this.product.setAderezo("sin aderezo");
		this.product.setPrecioAderezo(0);
	}

	@Override
	public void AgregarPrincipal() {
		// TODO Auto-generated method stub
		this.product.setPrincipal("provoleta grillada");
		this.product.setPrecioPrincipal(200);
	}

	@Override
	public void AgregarAdicional() {
		// TODO Auto-generated method stub
		this.product.setAdicional("berenjenas al escabeche");
		this.product.setPrecioAdicional(100);
	}

	@Override
	public double getPrecio() {
		// TODO Auto-generated method stub
		return this.product.calcularPrecio();
	}

}
