package ar.edu.unlp.info.oo2.ejercicio18_FileManager;

import java.time.LocalDate;

public interface FileComponent {
	
	public String prettyPrint();
	public String getNombre();
	public String getExtension();
	public String getTam();
	public LocalDate getFechaCreacion();
	public LocalDate getFechaModificacion();
	public String getPermisos();
	
}
