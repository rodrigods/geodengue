import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Teste2 {
	
	private static final String PROPERTIES_PATH = "geodengue.properties";
	private static final String PROP_USER = "username";
	private static final String PROP_HOST = "host";
	private static final String PROP_PORT = "port";
	private static final String PROP_PASSWORD = "password";
	private static final String PROP_POST = "httppost";
	
	
	private static final String HEADER_NAME = "Content-type";
	private static final String HEADER_VALUE = "text/xml";
	
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(PROPERTIES_PATH));
		
		String host = properties.getProperty(PROP_HOST);
		String port = properties.getProperty(PROP_PORT);
		String userName = properties.getProperty(PROP_USER);
		String password = properties.getProperty(PROP_PASSWORD);
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getCredentialsProvider().setCredentials(
                new AuthScope(host, Integer.valueOf(port), AuthScope.ANY_REALM), 
                new UsernamePasswordCredentials(userName, password));
		
//		String projectDirPath = System.getProperty("user.dir");
		File file = new File("wfs_files/insertAgente.xml");
		FileEntity entity = new FileEntity(file, "text/xml");
		
		String httpPost = properties.getProperty(PROP_POST);
		
		HttpPost httppost = new HttpPost(httpPost);
		httppost.setHeader(HEADER_NAME, HEADER_VALUE);
		httppost.setEntity(entity);

		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entityResp = response.getEntity();
		String entityString = EntityUtils.toString(entityResp);
		System.out.println(entityString);

	}

}
