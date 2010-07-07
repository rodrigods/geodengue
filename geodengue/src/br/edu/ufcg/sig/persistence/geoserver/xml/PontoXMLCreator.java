package br.edu.ufcg.sig.persistence.geoserver.xml;

public class PontoXMLCreator implements XMLCreator {
	
	private static final String XML_1 = "<geodengue:ponto>" + SEP +
      		"<geodengue:geom>" + SEP +
            "<gml:Point srsName=\"http://www.opengis.net/gml/srs/epsg.xml#4326\">" + SEP +
            "<gml:coordinates decimal=\".\" cs=\",\" ts=\" \">" + SEP;
	
	private static final String XML_2 =SEP + "</gml:coordinates> " + SEP +
            "</gml:Point>" + SEP +
            "</geodengue:geom>" + SEP +
	        "<geodengue:tipo>";
	
	private static final String XML_3 = "</geodengue:tipo>" + SEP +
    		"</geodengue:descricao>";
	
	private static final String XML_4 = "</geodengue:descricao>" + SEP +
	"</geodengue:ponto>" + SEP;
	
	public static String getXML(String geom, String tipo, String descricao) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(HEADER);
		buffer.append(XML_1);
		buffer.append(geom);
		buffer.append(XML_2);
		buffer.append(tipo);
		buffer.append(XML_3);
		buffer.append(descricao);
		buffer.append(XML_4);
		buffer.append(FOOTER);
		
		return buffer.toString();
	}
}
