package ar.edu.unlp.info.oo2.ejercicio15_ArmadoDePC;

public class GamerBuilder extends PCBuilder {
	
	public GamerBuilder() {
		this.pc = new PC();
	}

	@Override
	public void agregarProcesador() {
		// TODO Auto-generated method stub
		this.getPC().agregarComponente(Catalogo.getComponente("Procesador gamer"));
	}

	@Override
	public void agregarRAM() {
		// TODO Auto-generated method stub
		this.getPC().agregarComponente(Catalogo.getComponente("RAM gamer"));
		this.getPC().agregarComponente(Catalogo.getComponente("RAM gamer"));
	}

	@Override
	public void agregarDisco() {
		// TODO Auto-generated method stub
		this.getPC().agregarComponente(Catalogo.getComponente("Disco intermedio"));
		this.getPC().agregarComponente(Catalogo.getComponente("Disco gamer"));
	}

	@Override
	public void agregarGrafica() {
		// TODO Auto-generated method stub
		this.getPC().agregarComponente(Catalogo.getComponente("Gráfica gamer"));
	}

	@Override
	public void agregarGabinete() {
		// TODO Auto-generated method stub
		double consumo = this.getPC().calcularConsumo();
		consumo += consumo*0.50;
		String consumoFormateado = String.format("%.0f", consumo);
		this.getPC().agregarComponente(Catalogo.getComponente("Fuente "+ consumoFormateado +" w"));
		System.out.print(consumo);
	}
	
}
