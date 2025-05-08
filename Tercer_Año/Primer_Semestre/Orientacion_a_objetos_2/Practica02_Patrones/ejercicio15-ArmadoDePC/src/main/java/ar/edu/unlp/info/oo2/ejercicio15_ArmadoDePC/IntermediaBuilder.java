package ar.edu.unlp.info.oo2.ejercicio15_ArmadoDePC;

public class IntermediaBuilder extends PCBuilder {
	
	public IntermediaBuilder() {
		this.pc = new PC();
	}
	
	@Override
	public void agregarProcesador() {
		// TODO Auto-generated method stub
		this.getPC().agregarComponente(Catalogo.getComponente("Procesador intermedio"));
	}

	@Override
	public void agregarRAM() {
		// TODO Auto-generated method stub
		this.getPC().agregarComponente(Catalogo.getComponente("RAM intermedia"));
	}

	@Override
	public void agregarDisco() {
		// TODO Auto-generated method stub
		this.getPC().agregarComponente(Catalogo.getComponente("Disco intermedio"));
	}

	@Override
	public void agregarGrafica() {
		// TODO Auto-generated method stub
		this.getPC().agregarComponente(Catalogo.getComponente("Gráfica intermedia"));
	}

	@Override
	public void agregarGabinete() {
		// TODO Auto-generated method stub
		this.getPC().agregarComponente(Catalogo.getComponente("Fuente 800 w"));
	}

}
