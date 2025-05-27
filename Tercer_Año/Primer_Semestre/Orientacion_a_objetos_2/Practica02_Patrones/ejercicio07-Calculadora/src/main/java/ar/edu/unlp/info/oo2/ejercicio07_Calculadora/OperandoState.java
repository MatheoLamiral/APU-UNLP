package ar.edu.unlp.info.oo2.ejercicio07_Calculadora;

public abstract class OperandoState extends State{

	@Override
	public void mas(Calculadora c) {
		// TODO Auto-generated method stub
		c.setState(new ErrorState());
	}

	@Override
	public void menos(Calculadora c) {
		// TODO Auto-generated method stub
		c.setState(new ErrorState());
	}

	@Override
	public void div(Calculadora c) {
		// TODO Auto-generated method stub
		c.setState(new ErrorState());
	}

	@Override
	public void mult(Calculadora c) {
		// TODO Auto-generated method stub
		c.setState(new ErrorState());
	}
	
	

}
