import geoserver.Facade;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import xml.AgenteXMLCreator;
import xml.PontoXMLCreator;

public class Main {
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		String agenteXML = AgenteXMLCreator.getXML("Rodrigo", "10.71056415,12.8189323 15.70115662,30.95096618", 
				"1 1, 2 2, 2 1, 1 1");
		String pontoXML = PontoXMLCreator.getXML("-7.0, -35,0", 0);

		Facade.insert(agenteXML);
		Facade.insert(pontoXML);
	}

}
