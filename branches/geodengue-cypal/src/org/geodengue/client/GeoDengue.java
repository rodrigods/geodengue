package org.geodengue.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.SmallMapControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.user.client.ui.RootPanel;

public class GeoDengue implements EntryPoint {

	public void onModuleLoad() {
		MapWidget mapWiget = new MapWidget(LatLng.newInstance(-7.230336339539009, -35.88109016418457), 15);
		mapWiget.setSize("500px","300px");

	    mapWiget.addControl(new SmallMapControl());
	    mapWiget.addControl(new MapTypeControl());

	    mapWiget.addMapClickHandler(new MapClickHandler() {
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

	    RootPanel.get().add(mapWiget);
	}

}
