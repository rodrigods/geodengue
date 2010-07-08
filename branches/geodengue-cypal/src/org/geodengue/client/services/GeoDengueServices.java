package org.geodengue.client.services;

import java.util.List;

import org.geodengue.client.data.AgenteData;
import org.geodengue.client.data.PontoData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("GeoDengueServices")
public interface GeoDengueServices extends RemoteService {
	
	public static class Util {

		public static GeoDengueServicesAsync getInstance() {

			return GWT.create(GeoDengueServicesAsync.class);
		}
	}
	
	
	public List<PontoData> getAllPontoDatas();
	
	public List<AgenteData> getAllAgenteDatas();
}
