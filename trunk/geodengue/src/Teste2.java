import java.io.File;
import java.io.IOException;

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
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getCredentialsProvider().setCredentials(
                new AuthScope("localhost", 9090, AuthScope.ANY_REALM), 
                new UsernamePasswordCredentials("admin", "admin"));
		
		String projectDirPath = System.getProperty("user.dir");
		File file = new File(projectDirPath + "/wfs_files/insertAgente.xml");
		FileEntity entity = new FileEntity(file, "text/xml");
		
		HttpPost httppost = new HttpPost("http://localhost:9090/geoserver/wfs");
		httppost.setHeader("Content-type", "text/xml");
		httppost.setEntity(entity);

		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entityResp = response.getEntity();
		String entityString = EntityUtils.toString(entityResp);
		System.out.println(entityString);

	}

}
