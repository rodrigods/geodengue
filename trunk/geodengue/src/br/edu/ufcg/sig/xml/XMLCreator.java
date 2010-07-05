package br.edu.ufcg.sig.xml;

public interface XMLCreator {
	String SEP = System.getProperty("line.separator");
	
	String HEADER = "<wfs:Transaction service=\"WFS\" version=\"1.0.0\"" + SEP +
		  "xmlns:wfs=\"http://www.opengis.net/wfs\"" + SEP +
		  "xmlns:geodengue=\"http://geodengue.org/\"" + SEP + 
		  "xmlns:gml=\"http://www.opengis.net/gml\"" + SEP +
		  "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" + SEP +
		  "xsi:schemaLocation=\"http://www.opengis.net/wfs http://schemas.opengis.net/wfs/1.0.0/WFS-transaction.xsd " +
		  "http://www.openplans.org/topp http://localhost:8080/geoserver/wfs/DescribeFeatureType?typename=topp:tasmania_roads\">" + SEP +
		  "<wfs:Insert>" + SEP;
	
	String FOOTER = "</wfs:Insert>" + SEP +
		  "</wfs:Transaction>";
	
}
