package Practica3.ejercicio04;

public class AreaEmpresa {
	private String ident;
	private int tardanza;
	
	
	public AreaEmpresa() {
	}
	
	public AreaEmpresa(String unaIdent, int unaTardanza) {
		this.setIdent(unaIdent);
		this.setTardanza(unaTardanza);;
	}
	//GETTERS AND SETTERS
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public int getTardanza() {
		return tardanza;
	}
	public void setTardanza(int tardanza) {
		this.tardanza = tardanza;
	}
	
	
	
}
