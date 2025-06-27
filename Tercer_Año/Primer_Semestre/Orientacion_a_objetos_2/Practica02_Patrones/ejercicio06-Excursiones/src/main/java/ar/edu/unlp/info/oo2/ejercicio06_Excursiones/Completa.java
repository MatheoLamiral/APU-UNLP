package ar.edu.unlp.info.oo2.ejercicio06_Excursiones;

public class Completa extends ExcursionState {

	@Override
	public void inscribir(Excursion context, Usuario usuario) {
		// TODO Auto-generated method stub
		context.agregarListaEspera(usuario);
	}

	@Override
	protected String informacionAdicional(Excursion context) {
		// TODO Auto-generated method stub
		return "";
	}

}
