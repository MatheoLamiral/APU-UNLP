package ar.edu.unlp.info.oo1.ejercicio9_cuentaConGanchos;

public class CuentaCorriente extends Cuenta{
	private double descubierto;
	
	public CuentaCorriente() {
		super();
		this.descubierto = 0;
	}
	
	@Override
	protected boolean puedeExtraer(double monto) {
		return ((this.getSaldo() - monto) >= (descubierto));
	}	
	
	//Getters and Setters
	public double getDescubierto() {
		return descubierto;
	}

	public void setDescubierto(double descubierto) {
		this.descubierto = descubierto;
	}

    
    



}
