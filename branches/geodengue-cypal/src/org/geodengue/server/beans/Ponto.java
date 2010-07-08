package org.geodengue.server.beans;

import org.postgis.Point;

public class Ponto {
	
	private Point geometria;
	private PontoType type;
	private String descricao;
	
	
	public void setType(PontoType type) {
		this.type = type;
	}
	
	public PontoType getType() {
		return type;
	}
	
	public void setGeometria(Point geometria) {
		this.geometria = geometria;
	}
	
	public Point getGeometria() {
		return geometria;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
