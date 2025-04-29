package ar.edu.unlp.info.oo2.ejercicio13_SubteWay;

public interface SandwichBuilder {

	public abstract void reset();
	
	public abstract void AgregarPan();
	
	public abstract void AgregarAderezo();
	
	public abstract void AgregarPrincipal();
	
	public abstract void AgregarAdicional();
	
	public abstract double getPrecio();
	
	
}
