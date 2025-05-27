package ar.edu.unlp.info.oo2.ejercicio02_ExtensionAccesoBD;

import java.util.Collection;
import java.util.List;
import java.util.logging.*;

public class ProxyAutenticationDatabase implements DatabaseAccess {
	private static final Logger logger = Logger.getLogger(ProxyAutenticationDatabase.class.getName());
	private DatabaseRealAccess realSubject;
	private String password;
	private Boolean logged;
	
	public ProxyAutenticationDatabase(String password) {
		this.realSubject = new DatabaseRealAccess();
		this.password = password;
		this.logged = false;
	}
	
	public void loggin(String password) {
		if (this.password.equals(password)) {
			this.logged = true;
		}else {
			logger.severe("failed login");
			throw new RuntimeException("User is not logged in to the system");
		}
	}

	@Override
	public Collection<String> getSearchResults(String queryString) {
		// TODO Auto-generated method stub
		if(this.logged) {
			logger.info("Successful search access");
			return this.realSubject.getSearchResults(queryString);
		}
		else {
			logger.severe("failed search acces");
			throw new RuntimeException("User is not logged in to the system");
		}
	}

	@Override
	public int insertNewRow(List<String> rowData) {
		// TODO Auto-generated method stub
		if(this.logged) {
			logger.warning("Successful insertion access");
			return this.realSubject.insertNewRow(rowData);
		}
		else {
			logger.severe("failed insertion acces");
			throw new RuntimeException("User is not logged in to the system");
		}
	}

	public Boolean isLogged() {
		return this.logged;
	}
	
	

}
