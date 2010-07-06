
import java.io.IOException;
import java.sql.SQLException;

import org.apache.http.client.ClientProtocolException;
import org.postgis.LineString;
import org.postgis.LinearRing;
import org.postgis.Point;
import org.postgis.Polygon;

import br.edu.ufcg.sig.beans.Agente;
import br.edu.ufcg.sig.beans.Ponto;
import br.edu.ufcg.sig.beans.PontoType;
import br.edu.ufcg.sig.persistence.PersistenceFacade;
import br.edu.ufcg.sig.persistence.geoserver.GeoServerManager;
import br.edu.ufcg.sig.persistence.geoserver.xml.AgenteXMLCreator;
import br.edu.ufcg.sig.persistence.geoserver.xml.PontoXMLCreator;


public class Main {
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException, SQLException {
		/*String agenteXML = AgenteXMLCreator.getXML("Rodrigo", "10.71056415,12.8189323 15.70115662,30.95096618", 
				"1 1, 2 2, 2 1, 1 1");
		String pontoXML = PontoXMLCreator.getXML("-7.0,-35,0", 1);

		GeoServerManager.insert(agenteXML);
		GeoServerManager.insert(pontoXML);*/
		
		
//		Point[] ps = new Point[4];
//		ps[0] = new Point(1, 1);
//		ps[1] = new Point(2, 2);
//		ps[2] = new Point(2, 1);
//		ps[3] = new Point(1, 1);
//		
//		Point[] ps2 = new Point[4];
//		ps2[0] = new Point(0, 0);
//		ps2[1] = new Point(0, 10);
//		ps2[2] = new Point(10, 0);
//		ps2[3] = new Point(10, 10);
//		ps2[3] = new Point(0, 0);
//		
//		LinearRing[] lrs = new LinearRing[1];
//		lrs[0] = new LinearRing(ps2);
//		
//		Polygon polygon = new Polygon(lrs);
//		
//		LineString lineString = new LineString(ps);
//		
//		Agente agente = new Agente();
//		agente.setAreaCobertura(polygon);
//		agente.setMatricula(100);
//		agente.setNome("Rodrigo");
//		agente.setRota(lineString);
//		
//		PersistenceFacade.getInstance().saveAgente(agente);
		
//		System.out.println(polygon);
//		
//		Point p = new Point(5, 5);
//		Ponto ponto = new Ponto();
//		ponto.setLocation(p);
//		ponto.setType(PontoType.PESSOA_CONTAMINADA);
//		PersistenceFacade.getInstance().savePonto(ponto);
//		
//		Point p2 = new Point(4, 4);
//		Ponto ponto2 = new Ponto();
//		ponto2.setLocation(p2);
//		ponto2.setType(PontoType.PESSOA_CONTAMINADA);
//		PersistenceFacade.getInstance().savePonto(ponto2);
		
		Point p3 = new Point(3,3);
//		System.out.println(p3);
		System.out.println(PersistenceFacade.getInstance().consultaDistanciaDeFocosAUmPonto(p3, 1));
		System.out.println(PersistenceFacade.getInstance().focosNaAreaDoAgente(100));
		System.out.println(PersistenceFacade.getInstance().pessoasContaminadasEmUmRaio(p3, 10));
//		Agente agente = new Agente();
//		LineString rota = new LineString(ps);
//		agente.setRota(rota);
//		agente.setAreaCobertura(polygon);
//		agente.setNome("Creuza Maria");
//		PersistenceFacade.getInstance().saveAgente(agente);
	}

}
