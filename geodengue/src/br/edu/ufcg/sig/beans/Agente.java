package br.edu.ufcg.sig.beans;

import org.postgis.LineString;
import org.postgis.Polygon;

public class Agente {
	
	private LineString rota;
	private Polygon areaCobertura;
	private String nome;

	
	public LineString getRota() {
		return rota;
	}
	
	public void setRota(LineString rota) {
		this.rota = rota;
	}
	
	public Polygon getAreaCobertura() {
		return areaCobertura;
	}
	
	public void setAreaCobertura(Polygon areaCobertura) {
		this.areaCobertura = areaCobertura;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
}
