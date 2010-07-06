package controller;

import org.postgis.Point;

import br.edu.ufcg.sig.beans.Ponto;
import br.edu.ufcg.sig.beans.PontoType;
import br.edu.ufcg.sig.persistence.PersistenceFacadeImpl;

public class PessoaController {

	public static void cadastraFoco(String x, String y){
		Point point = new Point(Double.valueOf(x), Double.valueOf(y));
		Ponto ponto = new Ponto();
		ponto.setLocation(point);
		ponto.setType(PontoType.PESSOA_CONTAMINADA);
		PersistenceFacadeImpl.getInstance().savePonto(ponto);
	}
}