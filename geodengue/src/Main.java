
import java.io.IOException;
import java.sql.SQLException;

import org.apache.http.client.ClientProtocolException;
import org.postgis.Point;

import br.edu.ufcg.sig.beans.Ponto;
import br.edu.ufcg.sig.beans.PontoType;
import br.edu.ufcg.sig.persistence.PersistenceFacade;


public class Main {
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException, SQLException {
		Ponto ponto = new Ponto();
		
		ponto.setGeometria(new Point(5, 5));
		ponto.setType(PontoType.FOCO);
		ponto.setDescricao("Foco no ponto 5, 5");
		
		PersistenceFacade.getInstance().savePonto(ponto);
	}

}
