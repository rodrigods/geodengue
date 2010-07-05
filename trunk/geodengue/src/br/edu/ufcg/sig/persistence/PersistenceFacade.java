package br.edu.ufcg.sig.persistence;

import br.edu.ufcg.sig.beans.Agente;
import br.edu.ufcg.sig.beans.Ponto;
import br.edu.ufcg.sig.persistence.dao.GeoDengueDAO;
import br.edu.ufcg.sig.persistence.dao.GeoDengueJdbcDAO;

public class PersistenceFacade {
	
	private GeoDengueDAO dao;
	
	
	private static PersistenceFacade instance;
	
	
	private PersistenceFacade() {
		dao = new GeoDengueJdbcDAO();
	}
	
	
	public static PersistenceFacade getInstance() {
		if (instance == null) {
			instance = new PersistenceFacade();
		}
		
		return instance;
	}
	
	
	public void savePonto(Ponto ponto) {
		this.dao.savePonto(ponto);
	}
	
	public void saveAgente(Agente agente) {
		this.dao.saveAgente(agente);
	}
}
