package ar.edu.unlp.info.oo2.ejercicio02_ExtensionAccesoBD;

import java.util.Collection;
import java.util.List;

public interface DatabaseAccess {
	
    public Collection<String> getSearchResults(String queryString);
    public int insertNewRow(List<String> rowData);
}