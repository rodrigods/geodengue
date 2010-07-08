package org.geodengue.server;

import java.util.ArrayList;
import java.util.List;

import org.geodengue.client.data.AgenteData;
import org.geodengue.client.data.PontoData;
import org.geodengue.client.services.GeoDengueServices;
import org.geodengue.server.beans.Ponto;
import org.geodengue.server.persistence.PersistenceFacade;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GeoDengueServicesImpl extends RemoteServiceServlet implements GeoDengueServices {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public List<PontoData> getAllPontoDatas() {
		List<Ponto> pontos = PersistenceFacade.getInstance().getAllPontos();
		
		List<PontoData> out = new ArrayList<PontoData>();
		
		for (Ponto p : pontos) {
			PontoData pontoData = new PontoData();
			
			pontoData.setDescricao(p.getDescricao());
			
			if (p.isFoco()) {
				pontoData.setAsFoco();
			} else {
				pontoData.setAsPessoaContaminada();
			}
			
			pontoData.setLocation(p.getGeometria().x + " " + p.getGeometria().y);
			
			out.add(pontoData);
		}
		
		return out;
	}

	public List<AgenteData> getAllAgenteDatas() {
		// TODO Auto-generated method stub
		return null;
	}

}
