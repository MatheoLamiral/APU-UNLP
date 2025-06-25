package ar.edu.unlp.info.oo2.ejercicio18_FileManager;

import java.time.LocalDate;

public abstract class FileDecorator implements FileComponent {
	private FileComponent component;
	
	public FileDecorator(FileComponent component) {
		this.component = component;
	}
	
	public String prettyPrint() {
		return this.component.prettyPrint();
	}
	
	public String getNombre() {
		return this.component.getNombre();
	}
	public String getExtension() {
		return this.component.getExtension();
	}
	public String getTam() {
		return this.component.getTam();
	}
	public LocalDate getFechaCreacion() {
		return this.component.getFechaCreacion();		
	}
	public LocalDate getFechaModificacion() {
		return this.component.getFechaModificacion();
	}
	public String getPermisos() {
		return this.component.getPermisos();
	}
}
