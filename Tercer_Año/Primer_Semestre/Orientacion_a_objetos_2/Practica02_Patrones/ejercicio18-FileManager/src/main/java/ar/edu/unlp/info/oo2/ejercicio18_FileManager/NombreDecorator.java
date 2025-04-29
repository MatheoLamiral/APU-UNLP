package ar.edu.unlp.info.oo2.ejercicio18_FileManager;

public class NombreDecorator extends FileDecorator {

	public NombreDecorator(FileComponent component) {
		super(component);
		// TODO Auto-generated constructor stub
	}

	public String prettyPrint() {
		//return this.getComponent().prettyPrint() + " - " + this.getNombre();
		return super.prettyPrint();
	}
}
