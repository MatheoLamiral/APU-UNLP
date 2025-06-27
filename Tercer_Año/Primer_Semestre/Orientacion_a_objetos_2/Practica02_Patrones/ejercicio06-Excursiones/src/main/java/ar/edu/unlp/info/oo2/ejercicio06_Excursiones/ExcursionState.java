package ar.edu.unlp.info.oo2.ejercicio06_Excursiones;

public abstract class ExcursionState {


	public abstract void inscribir(Excursion context, Usuario usuario);
	
	public String obtenerInformacion(Excursion context) {
		return context.toString() + "\n" + this.informacionAdicional(context);
	}

	protected abstract String informacionAdicional(Excursion context);
}
