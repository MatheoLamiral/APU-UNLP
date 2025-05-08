package ar.edu.unlp.info.oo2.ejercicio15_ArmadoDePC;

public class BasicaBuilder extends PCBuilder {
	
	public BasicaBuilder() {
		this.pc = new PC();
	}

	@Override
	public void agregarProcesador() {
		// TODO Auto-generated method stub
		this.getPC().agregarComponente(Catalogo.getComponente("Procesador básico"));
	}

	@Override
	public void agregarRAM() {
		// TODO Auto-generated method stub
		this.getPC().agregarComponente(Catalogo.getComponente("RAM básica"));
	}

	@Override
	public void agregarDisco() {
		// TODO Auto-generated method stub
		this.getPC().agregarComponente(Catalogo.getComponente("Disco básico"));
	}

	@Override
	public void agregarGrafica() {
		// TODO Auto-generated method stub
	}

	@Override
	public void agregarGabinete() {
		// TODO Auto-generated method stub
		this.getPC().agregarComponente(Catalogo.getComponente("Fuente 300 w"));
	}

}
