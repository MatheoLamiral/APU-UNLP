package ar.edu.unlp.info.oo2.ejercicio18_FileManager;

public class FechaCreacionDecorator extends FileDecorator {

	public FechaCreacionDecorator(FileComponent component) {
		super(component);
		// TODO Auto-generated constructor stub
	}
	
	public String prettyPrint() {
		return this.getComponent().prettyPrint() + " - " + this.getFechaCreacion().toString();
	}

}
