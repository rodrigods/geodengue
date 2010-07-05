package br.edu.ufcg.sig.geoserver;

import java.io.File;

public interface ManagerConstants {
	public static final String PROPERTIES_PATH = "geodengue.properties";
	public static final String PROP_USER = "username";
	public static final String PROP_HOST = "host";
	public static final String PROP_PORT = "port";
	public static final String PROP_PASSWORD = "password";
	public static final String PROP_POST = "httppost";
	
	
	public static final String HEADER_NAME = "Content-type";
	public static final String HEADER_VALUE = "text/xml";
	
	
	public static final String DEFAULT_FILE_PATH =  
						"wfs_files" + File.separator + "transaction.xml";
}
