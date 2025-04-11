package ar.edu.unlp.info.oo1.ejercicio9_cuentaConGanchos;

public class CajaDeAhorro extends Cuenta{

	public CajaDeAhorro() {
		super();		
	}

	public CajaDeAhorro(double monto) {
		super(monto);		
	}
	
	@Override
	protected boolean puedeExtraer(double monto) {
		return (this.getSaldo() >= monto*1.02);
	}
	
	public void depositar (double monto) {
		super.depositar(monto * 0.98);
	}
	
	protected void extraerSinControlar (double monto) {
		super.extraerSinControlar(monto * 1.02);
	}

}
