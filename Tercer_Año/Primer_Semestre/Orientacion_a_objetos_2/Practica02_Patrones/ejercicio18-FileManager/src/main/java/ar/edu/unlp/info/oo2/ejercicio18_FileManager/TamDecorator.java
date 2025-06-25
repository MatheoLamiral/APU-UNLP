package ar.edu.unlp.info.oo2.ejercicio18_FileManager;

public class TamDecorator extends FileDecorator {

	public TamDecorator(FileComponent component) {
		super(component);
		// TODO Auto-generated constructor stub
	}

	public String prettyPrint() {
		return super.prettyPrint() + " - " + this.getTam();
	}
}
