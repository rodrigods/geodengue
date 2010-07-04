package xml;

public class AgenteXMLCreator implements XMLCreator {
	
	private static final String XML_1 = "<geodengue:agente>" + SEP +
      		"<geodengue:rota>" + SEP +
            "<gml:LineString srsName=\"http://www.opengis.net/gml/srs/epsg.xml#4326\">" + SEP +
            "<gml:coordinates decimal=\".\" cs=\",\" ts=\" \">" + SEP;
	
	private static final String XML_2 = SEP + "</gml:coordinates> " + SEP +
            "</gml:LineString>" + SEP +
            "</geodengue:rota>" + SEP +
	        "<geodengue:areaCobertura>" + SEP +
            "<gml:Polygon srsName=\"http://www.opengis.net/gml/srs/epsg.xml#4326\">" + SEP +
            "<gml:outerBoundaryIs>" + SEP +
            "<gml:LinearRing>" + SEP +
            "<gml:coordinates decimal=\".\" cs=\",\" ts=\" \">" + SEP;
	
	private static final String XML_3 = SEP + "</gml:coordinates>" + SEP +
            "</gml:LinearRing>" + SEP +
            "</gml:outerBoundaryIs>" + SEP +
            "</gml:Polygon>" + SEP +
            "</geodengue:areaCobertura>" + SEP +
            "<geodengue:nome>";
	
	private static final String XML_4 = "</geodengue:nome>" + SEP +
    		"</geodengue:agente>";
	
	
	public static String getXML(String nome, String lineString, String polygon) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(HEADER);
		buffer.append(XML_1);
		buffer.append(lineString);
		buffer.append(XML_2);
		buffer.append(polygon);
		buffer.append(XML_3);
		buffer.append(nome);
		buffer.append(XML_4);
		
		return buffer.toString();
	}
}
