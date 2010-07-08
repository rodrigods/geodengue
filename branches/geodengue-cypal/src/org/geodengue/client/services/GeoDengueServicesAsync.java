package org.geodengue.client.services;

import java.util.List;

import org.geodengue.client.data.AgenteData;
import org.geodengue.client.data.PontoData;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GeoDengueServicesAsync {
	
	public void getAllPontoDatas(AsyncCallback<List<PontoData>> callback);
	
	public void getAllAgenteDatas(AsyncCallback<List<AgenteData>> callback);
}
