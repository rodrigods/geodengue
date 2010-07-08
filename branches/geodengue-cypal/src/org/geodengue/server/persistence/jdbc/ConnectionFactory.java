package org.geodengue.server.persistence.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    
    static int connCount = 0;
    
    public static Connection newConnection() throws SQLException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(JdbcConstants.PROPERTIES_PATH));
		
        try {
        	String urlDriver = properties.getProperty(JdbcConstants.PROP_URL_DRIVER);
            Class.forName( urlDriver ).newInstance();
            
            String username = properties.getProperty(JdbcConstants.PROP_LOGIN);
            String password = properties.getProperty(JdbcConstants.PROP_PASSWORD);

            //Abre conexão
            Connection c = null;
            Properties connInfo = new Properties();
            if ( username != null && password != null ) {
                connInfo.put("user", username);
                connInfo.put("password", password);
            }
            
            String urlDB = properties.getProperty(JdbcConstants.PROP_URL_DB);
            
            c = DriverManager.getConnection(urlDB, connInfo);            
            
            return c;
        } catch ( Exception e ) {        	
            throw new SQLException(e.getMessage());
        }
    }
    
}
