package org.geodengue.client.panels;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.SmallMapControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Overlay;
import com.gwtext.client.widgets.Panel;


public class MapPanel extends Panel {
	
	private MapWidget mapWidget;
	
	public MapPanel() {
		super();
		initPanel();
	}

	private void initPanel() {
		this.setTitle("Map");
		this.setPaddings(10);
		
//		constructMap();
		
		this.add(mapWidget);
	}
	
	private void constructMap() {
		this.mapWidget = new MapWidget(LatLng.newInstance(-7.230336339539009, -35.88109016418457), 15);
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
	}
}
