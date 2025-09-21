package ar.edu.unlp.info.oo2.ejercicio17_AccesoABaseDeDatos;

import java.util.Collection;
import java.util.List;

public interface DatabaseAccess {
	
    public Collection<String> getSearchResults(String queryString);
    public int insertNewRow(List<String> rowData);
}