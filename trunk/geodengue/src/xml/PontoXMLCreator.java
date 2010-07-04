package xml;

public class PontoXMLCreator implements XMLCreator {
	
	private static final String XML_1 = "<geodengue:ponto>" + SEP +
      		"<geodengue:geometria>" + SEP +
            "<gml:Point srsName=\"http://www.opengis.net/gml/srs/epsg.xml#4326\">" + SEP +
            "<gml:coordinates decimal=\".\" cs=\",\" ts=\" \">" + SEP;
	
	private static final String XML_2 = "</gml:coordinates> " + SEP +
            "</gml:Point>" + SEP +
            "</geodengue:geometria>" + SEP +
	        "<geodengue:tipo>";
	
	private static final String XML_3 = "</geodengue:tipo>" + SEP +
    		"</geodengue:ponto>";
	
	public static String getXML(String ponto, int tipo) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(HEADER);
		buffer.append(XML_1);
		buffer.append(ponto);
		buffer.append(XML_2);
		buffer.append(tipo);
		buffer.append(XML_3);
		
		return buffer.toString();
	}
}
