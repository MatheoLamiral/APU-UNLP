package ar.edu.unlp.info.oo2.ejercicio07_Calculadora;

public abstract class State {

    public String getResultado(Calculadora c) {
    	return Double.toString(c.getValorAcumulado());
    }

    public void borrar(Calculadora c) {
    	c.setState(new InicialState());
    	c.setValorAcumulado(0);
    }

    public abstract void setValor(double unValor, Calculadora c);
    public abstract void mas(Calculadora c);
    public abstract void menos(Calculadora c);
    public abstract void div(Calculadora c);
    public abstract void mult(Calculadora c);



}
