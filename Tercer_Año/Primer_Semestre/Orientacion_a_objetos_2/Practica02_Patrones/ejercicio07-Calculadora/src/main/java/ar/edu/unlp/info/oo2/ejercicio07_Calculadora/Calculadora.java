package ar.edu.unlp.info.oo2.ejercicio07_Calculadora;

public class Calculadora {
	private double valorAcumulado;
	private State state;
	
	public Calculadora() {
		this.valorAcumulado=0;
		this.state = new InicialState();
	}
	
	public String getResultado() {
    	return this.state.getResultado(this);
    }

    public void borrar() {
    	this.state.borrar(this);
    }

    public void setValor(double unValor) {
    	this.state.setValor(unValor, this);
    }
    public void mas() {
    	this.state.mas(this);
    }
    public void menos() {
    	this.state.menos(this);
    }
    public void div() {
    	this.state.div(this);
    }
    public void mult() {
    	this.state.mult(this);
    }
	
	protected double getValorAcumulado() {
		return valorAcumulado;
	}
	protected void setValorAcumulado(double valorAcumulado) {
		this.valorAcumulado = valorAcumulado;
	}
	protected State getState() {
		return state;
	}
	protected void setState(State state) {
		this.state = state;
	}
	
	

}
