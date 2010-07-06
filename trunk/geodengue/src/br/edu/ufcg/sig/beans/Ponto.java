package br.edu.ufcg.sig.beans;

import org.postgis.Point;

public class Ponto {
	private Point location;
	private PontoType type;
	
	public void setType(PontoType type) {
		this.type = type;
	}
	
	public PontoType getType() {
		return type;
	}
	
	public void setLocation(Point location) {
		this.location = location;
	}
	
	public Point getLocation() {
		return location;
	}
}
