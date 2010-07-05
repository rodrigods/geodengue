package br.edu.ufcg.sig.persistence.geoserver;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import br.edu.ufcg.sig.persistence.geoserver.util.FileUtil;

public class GeoServerManager {
	
	public static void insert(String xml) throws IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(GeoServerManagerConstants.PROPERTIES_PATH));
		
		String host = properties.getProperty(GeoServerManagerConstants.PROP_HOST);
		String port = properties.getProperty(GeoServerManagerConstants.PROP_PORT);
		String userName = properties.getProperty(GeoServerManagerConstants.PROP_USER);
		String password = properties.getProperty(GeoServerManagerConstants.PROP_PASSWORD);
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getCredentialsProvider().setCredentials(
                new AuthScope(host, Integer.valueOf(port), AuthScope.ANY_REALM), 
                new UsernamePasswordCredentials(userName, password));
		
		File file = FileUtil.writeXMLToFile(GeoServerManagerConstants.DEFAULT_FILE_PATH, xml);
		FileEntity entity = new FileEntity(file, GeoServerManagerConstants.HEADER_VALUE);
		
		String httpPost = properties.getProperty(GeoServerManagerConstants.PROP_POST);
		
		HttpPost httppost = new HttpPost(httpPost);
		httppost.setHeader(GeoServerManagerConstants.HEADER_NAME, GeoServerManagerConstants.HEADER_VALUE);
		httppost.setEntity(entity);

		HttpResponse response = httpclient.execute(httppost);
		
		//DEBUG
		HttpEntity entityResp = response.getEntity();
		String entityString = EntityUtils.toString(entityResp);
		System.out.println(entityString);
	}
}
