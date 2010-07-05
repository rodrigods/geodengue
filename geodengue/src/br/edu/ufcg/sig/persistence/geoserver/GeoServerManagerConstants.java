package br.edu.ufcg.sig.persistence.geoserver;

import java.io.File;

public interface GeoServerManagerConstants {
	public static final String PROPERTIES_PATH = "geodengue.properties";
	public static final String PROP_USER = "geoserver.username";
	public static final String PROP_HOST = "geoserver.host";
	public static final String PROP_PORT = "geoserver.port";
	public static final String PROP_PASSWORD = "geoserver.password";
	public static final String PROP_POST = "geoserver.httppost";
	
	
	public static final String HEADER_NAME = "Content-type";
	public static final String HEADER_VALUE = "text/xml";
	
	
	public static final String DEFAULT_FILE_PATH =  
						"wfs_files" + File.separator + "transaction.xml";
}
