package br.edu.ufcg.sig.persistence;

import br.edu.ufcg.sig.persistence.dao.GeoDengueDAO;
import br.edu.ufcg.sig.persistence.dao.GeoDengueGSDAO;

public class PersistenceFacade {
	
	private GeoDengueDAO insertDAO;
	private GeoDengueDAO querysDAO;
	
	
	private static PersistenceFacade instance;
	
	
	private PersistenceFacade() {
		insertDAO = new GeoDengueGSDAO();
	}
	
	
	public static PersistenceFacade getInstance() {
		if (instance == null) {
			instance = new PersistenceFacade();
		}
		
		return instance;
	}
}
