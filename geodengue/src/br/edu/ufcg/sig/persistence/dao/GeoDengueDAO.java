package br.edu.ufcg.sig.persistence.dao;

import br.edu.ufcg.sig.beans.Agente;
import br.edu.ufcg.sig.beans.Ponto;

public interface GeoDengueDAO {
	public void saveAgente(Agente agente);
	
	public void savePonto(Ponto ponto);
}
