package org.geodengue.client.data;

import java.io.Serializable;

public class PontoData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String location;
	private boolean isFoco;
	private String descricao;
	
	public PontoData() {}
	
	public PontoData(String location, String descricao, boolean isFoco) {
		this.location = location;
		this.descricao = descricao;
		this.isFoco = isFoco;
	}
	
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setAsFoco() {
		this.isFoco = true;
	}
	
	public void setAsPessoaContaminada() {
		this.isFoco = false;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isFoco() {
		return this.isFoco;
	}
}
