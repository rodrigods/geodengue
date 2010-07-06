package br.edu.ufcg.sig.persistence;

import java.util.List;

import org.postgis.Point;

import br.edu.ufcg.sig.beans.Agente;
import br.edu.ufcg.sig.beans.Ponto;
import br.edu.ufcg.sig.persistence.dao.GeoDengueDAO;
import br.edu.ufcg.sig.persistence.dao.GeoDengueJdbcDAO;

public class PersistenceFacade implements PersistenceFacadeInterface{
	
	private GeoDengueDAO dao;
	
	
	private static PersistenceFacade instance;
	
	
	private PersistenceFacade() {
		dao = new GeoDengueJdbcDAO();
	}
	
	
	public static PersistenceFacade getInstance() {
		if (instance == null) {
			instance = new PersistenceFacade();
		}
		
		return instance;
	}
	
	
	public void savePonto(Ponto ponto) {
		this.dao.savePonto(ponto);
	}
	
	public void saveAgente(Agente agente) {
		this.dao.saveAgente(agente);
	}


	public List<Ponto> consultaDistanciaDeFocosAUmPonto(String p1, int x) {
		return this.dao.consultaDistanciaDeFocosAUmPonto(p1, x);
	}


	public List<Ponto> focosNaAreaDoAgente(int matricula) {
		// TODO Auto-generated method stub
		return null;
	}


	
	public int pessoasContaminadasEmUmRaio(Point p, int x) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	public int qtdFocosEmUmaRota(int matricula) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	public double distanciaEntreFocos(Point f1, Point f2) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	public List<String> responsaveisPelosFocos(int matricula) {
		// TODO Auto-generated method stub
		return null;
	}


	
	public double areaDoAgente(int matricula) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	public double comprimentoDaRotaDoAgente(int matricula) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
