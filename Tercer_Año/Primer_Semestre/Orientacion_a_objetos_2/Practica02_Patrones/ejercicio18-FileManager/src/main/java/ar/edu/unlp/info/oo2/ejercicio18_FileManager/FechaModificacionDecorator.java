package ar.edu.unlp.info.oo2.ejercicio18_FileManager;

public class FechaModificacionDecorator extends FileDecorator {

	public FechaModificacionDecorator(FileComponent component) {
		super(component);
		// TODO Auto-generated constructor stub
	}

	public String prettyPrint() {
		return this.getComponent().prettyPrint() + " - " + this.getFechaModificacion().toString();
	}
}
