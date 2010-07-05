
import java.io.IOException;
import java.sql.SQLException;

import org.apache.http.client.ClientProtocolException;
import org.postgis.LinearRing;
import org.postgis.Point;
import org.postgis.Polygon;

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
		
		
		Point[] ps = new Point[4];
		ps[0] = new Point(1, 1);
		ps[1] = new Point(2, 2);
		ps[2] = new Point(2, 1);
		ps[3] = new Point(1, 1);
		
		LinearRing[] lrs = new LinearRing[1];
		lrs[0] = new LinearRing(ps);
		
		Polygon polygon = new Polygon(lrs);
		
		Point p = new Point(-7, -35);
		Ponto ponto = new Ponto();
		ponto.setLocation(p);
		ponto.setType(PontoType.FOCO);
		
		PersistenceFacade.getInstance().savePonto(ponto);
	}

}