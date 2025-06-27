package ar.edu.unlp.info.oo2.ejercicio06_Excursiones;

public class Definitiva extends ExcursionState {

	@Override
	public void inscribir(Excursion context, Usuario usuario) {
		// TODO Auto-generated method stub
		context.agregarInscripo(usuario);
		if(context.cumpleMaximo()) context.switchState(new Completa());
	}

	@Override
	protected String informacionAdicional(Excursion context) {
		// TODO Auto-generated method stub
		return "mails de inscriptos: " 
				+ context.mailInscriptos().toString()
				+ "\n"
				+ "inscripciones faltantes para el cupo máximo: "
				+ context.usuariosFaltantesCupoMax();
		
	}

}
