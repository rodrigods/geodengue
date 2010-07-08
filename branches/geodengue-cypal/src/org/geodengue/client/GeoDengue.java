package org.geodengue.client;

import java.util.List;

import org.geodengue.client.data.PontoData;
import org.geodengue.client.services.GeoDengueServices;
import org.geodengue.client.services.GeoDengueServicesAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.SmallMapControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MarkerMouseOverHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class GeoDengue implements EntryPoint {
	
	private static final GeoDengueServicesAsync GEO_DENGUE_SERVICES = (GeoDengueServicesAsync) GWT.create(GeoDengueServices.class);
	
	
	private InfoWindow info = null;

	
	public void onModuleLoad() {
		if (!Maps.isLoaded()) {
			Window.alert("The Maps API is not installed."
					+ "  The <script> tag that loads the Maps API may be missing or your Maps key may be wrong.");
			return;
		}
		
		if (!Maps.isBrowserCompatible()) {
			Window.alert("The Maps API is not compatible with this browser.");
			return;
		}	
		
		MapWidget mapWidget = constructMapWidget();
		
		info = mapWidget.getInfoWindow();
		loadPontos(mapWidget);
		
		RootPanel.get().add(mapWidget);
	}
	
	private MapWidget constructMapWidget() {
		MapWidget mapWidget = new MapWidget(LatLng.newInstance(-7.230336339539009, -35.88109016418457), 15);
		mapWidget.setSize("1000px","550px");

	    mapWidget.addControl(new SmallMapControl());
	    mapWidget.addControl(new MapTypeControl());
	    
	    mapWidget.addMapClickHandler(new MapClickHandler() {
		      public void onClick(MapClickEvent e) {
		        MapWidget sender = e.getSender();
		        Overlay overlay = e.getOverlay();
		        LatLng point = e.getLatLng();

		        if (overlay != null && overlay instanceof Marker) {
		          sender.removeOverlay(overlay);
		        } else {
		          sender.addOverlay(new Marker(point));
		        }
		      }
		    });
		    
		mapWidget.setContinuousZoom(true);
		mapWidget.setScrollWheelZoomEnabled(true);
		
		return mapWidget;
	}
	
	private void loadPontos(final MapWidget mapWidget) {
		GEO_DENGUE_SERVICES.getAllPontoDatas(new AsyncCallback<List<PontoData>>(){

			public void onFailure(Throwable caught) {
				Window.alert("Erro ao carregar os dados!");
				
			}

			public void onSuccess(List<PontoData> result) {
				for (int i = 0; i < result.size(); i++) {
					String[] latlng = result.get(i).getLocation().split(" ");
					String lat = latlng[0];
					String lng = latlng[1];
					
					final LatLng position = LatLng.newInstance(Double.parseDouble(lat),
							Double.parseDouble(lng));
					
					final MarkerOptions options = makeMarkerOptions(result.get(i));		            
					final Marker marker = new Marker(position, options);
					
			        String title = result.get(i).isFoco() ? "Foco\n" : "Pessoa Contaminada\n";
			        title += "Descrição: " + result.get(i).getDescricao() + "\n";
			        
			        final InfoWindowContent content = new InfoWindowContent(title);
			        
			        marker.addMarkerMouseOverHandler(new MarkerMouseOverHandler(){

						public void onMouseOver(MarkerMouseOverEvent event) {
							info.open(position, content);
							
						}
			        	
			        });			        

			        mapWidget.addOverlay(marker);
				}
				
				
			}
			
			 private MarkerOptions makeMarkerOptions(final PontoData pontoData)
			    {
			        final MarkerOptions options = MarkerOptions.newInstance();
			        
			        String title = pontoData.isFoco() ? "Foco\n" : "Pessoa Contaminada\n";
			        title += "Descrição: " + pontoData.getDescricao() + "\n";
			        
			        options.setClickable(true);
			        //options.setIcon(makeIcon());        

			        return options;
			    }

			
		});
		
	}

}
