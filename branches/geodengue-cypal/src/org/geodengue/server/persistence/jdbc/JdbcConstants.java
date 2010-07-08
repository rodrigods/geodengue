package org.geodengue.server.persistence.jdbc;

public interface JdbcConstants {
	public static final String PROPERTIES_PATH = "geodengue.properties";
	public static final String PROP_LOGIN = "postgis.login";
	public static final String PROP_PASSWORD = "postgis.password";
	public static final String PROP_URL_DRIVER = "postgis.driver.url";
	public static final String PROP_URL_DB = "postgis.db.url";
	
	public static final int PONTO_TYPE_FOCO = 0;
	public static final int PONTO_TYPE_PESSOA = 1;
}
