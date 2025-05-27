package ar.edu.unlp.info.oo2.ejercicio07_Calculadora;

public class MenosState extends OperandoState{

	@Override
	public void setValor(double unValor, Calculadora c) {
		// TODO Auto-generated method stub
		c.setValorAcumulado(c.getValorAcumulado() - unValor);
		c.setState(new InicialState());
	}

}
