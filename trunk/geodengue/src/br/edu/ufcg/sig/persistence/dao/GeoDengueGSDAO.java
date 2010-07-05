package br.edu.ufcg.sig.persistence.dao;

import java.io.IOException;

import br.edu.ufcg.sig.beans.Agente;
import br.edu.ufcg.sig.beans.Ponto;
import br.edu.ufcg.sig.beans.PontoType;
import br.edu.ufcg.sig.persistence.geoserver.GeoServerManager;
import br.edu.ufcg.sig.persistence.geoserver.xml.AgenteXMLCreator;
import br.edu.ufcg.sig.persistence.geoserver.xml.PontoXMLCreator;

public class GeoDengueGSDAO implements GeoDengueDAO {

	public void saveAgente(Agente agente) {
		String xml = AgenteXMLCreator.getXML(agente.getNome(), 
				agente.getRota().toString(), agente.getAreaCobertura().toString());
		
		try {
			GeoServerManager.insert(xml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void savePonto(Ponto ponto) {
		String geometria = ponto.getLocation().toString();
		
		int tipo = 0;
		
		if (ponto.getType().equals(PontoType.PESSOA_CONTAMINADA)) {
			tipo = 1;
		}
		
		String xml = PontoXMLCreator.getXML(geometria, tipo);
		
		try {
			GeoServerManager.insert(xml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
