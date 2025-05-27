package ar.edu.unlp.info.oo2.ejercicio07_Calculadora;

public class InicialState extends State{

	@Override
	public void setValor(double unValor, Calculadora c) {
		// TODO Auto-generated method stub
		c.setValorAcumulado(unValor);
	}

	@Override
	public void mas(Calculadora c) {
		// TODO Auto-generated method stub
		c.setState(new MasState());
	}

	@Override
	public void menos(Calculadora c) {
		// TODO Auto-generated method stub
		c.setState(new MenosState());
	}

	@Override
	public void div(Calculadora c) {
		// TODO Auto-generated method stub
		c.setState(new DivState());
	}

	@Override
	public void mult(Calculadora c) {
		// TODO Auto-generated method stub
		c.setState(new MultState());
	}

}
