package org.geodengue.server;

import org.geodengue.server.beans.Ponto;
import org.geodengue.server.beans.PontoType;
import org.geodengue.server.persistence.PersistenceFacade;
import org.postgis.Point;

public class Main {
	public static void main(String[] args) {
		//FOCO 1
		Ponto p1 = new Ponto();
		p1.setDescricao("Foco 1");
		p1.setType(PontoType.FOCO);
		p1.setGeometria(new Point(-7.234891744199769, -35.879974365234375));
		
		PersistenceFacade.getInstance().savePonto(p1);
		
		//FOCO 2
		Ponto p2 = new Ponto();
		p2.setDescricao("Foco 2");
		p2.setType(PontoType.FOCO);
		p2.setGeometria(new Point(-7.233316516011592, -35.879759788513184));
		
		PersistenceFacade.getInstance().savePonto(p2);
		
		
	}
}
