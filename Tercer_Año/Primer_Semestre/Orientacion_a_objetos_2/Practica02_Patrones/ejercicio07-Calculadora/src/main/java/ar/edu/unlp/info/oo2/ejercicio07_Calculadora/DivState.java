package ar.edu.unlp.info.oo2.ejercicio07_Calculadora;

public class DivState extends OperandoState{

	@Override
	public void setValor(double unValor, Calculadora c) {
		// TODO Auto-generated method stub
		if(unValor != 0) {
			c.setValorAcumulado(c.getValorAcumulado() / unValor);
			c.setState(new InicialState());
		}
		else {
			throw new RuntimeException("No se puede dividir por 0");
		}
	}

}
