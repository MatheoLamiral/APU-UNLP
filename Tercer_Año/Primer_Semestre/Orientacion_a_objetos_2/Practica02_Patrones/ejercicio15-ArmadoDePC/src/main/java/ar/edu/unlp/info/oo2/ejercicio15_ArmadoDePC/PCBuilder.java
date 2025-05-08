package ar.edu.unlp.info.oo2.ejercicio15_ArmadoDePC;

public abstract class PCBuilder {
	protected PC pc;
	
	public abstract void agregarProcesador();
	public abstract void agregarRAM();
	public abstract void agregarDisco();
	public abstract void agregarGrafica();
	public abstract void agregarGabinete();
	
	protected PC getPC() {
		return this.pc;
	}
}
