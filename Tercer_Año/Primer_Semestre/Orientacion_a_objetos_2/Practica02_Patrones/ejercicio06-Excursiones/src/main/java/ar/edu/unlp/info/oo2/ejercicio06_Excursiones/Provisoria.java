package ar.edu.unlp.info.oo2.ejercicio06_Excursiones;

public class Provisoria extends ExcursionState {

	@Override
	public void inscribir(Excursion context, Usuario usuario) {
		// TODO Auto-generated method stub
		context.agregarInscripo(usuario);
		if (context.cumpleMinimo()) context.switchState(new Definitiva());
	}

	@Override
	protected String informacionAdicional(Excursion context) {
		// TODO Auto-generated method stub
		return "inscripciones faltantes para completar el cupo minimo: " + context.usuariosFaltantesCupoMin();
	}

}
