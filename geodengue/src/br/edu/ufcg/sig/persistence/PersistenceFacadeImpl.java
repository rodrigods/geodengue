package br.edu.ufcg.sig.persistence;

import java.util.List;

import org.postgis.Point;
import org.postgis.Polygon;

import br.edu.ufcg.sig.beans.Agente;
import br.edu.ufcg.sig.beans.Ponto;
import br.edu.ufcg.sig.persistence.dao.GeoDengueDAO;
import br.edu.ufcg.sig.persistence.dao.GeoDengueJdbcDAO;

public class PersistenceFacadeImpl implements PersistenceFacade{
	
	private GeoDengueDAO dao;
	
	
	private static PersistenceFacadeImpl instance;
	
	
	private PersistenceFacadeImpl() {
		dao = new GeoDengueJdbcDAO();
	}
	
	
	public static PersistenceFacadeImpl getInstance() {
		if (instance == null) {
			instance = new PersistenceFacadeImpl();
		}
		
		return instance;
	}
	
	
	public void savePonto(Ponto ponto) {
		this.dao.savePonto(ponto);
	}
	
	public void saveAgente(Agente agente) {
		this.dao.saveAgente(agente);
	}

	public List<Ponto> consultaDistanciaDeFocosAUmPonto(Point p1, int x) {
		return this.dao.consultaDistanciaDeFocosAUmPonto(p1, x);
	}

	public List<Ponto> focosNaAreaDoAgente(int matricula) {
		return this.dao.focosNaAreaDoAgente(matricula);
	}

	public int pessoasContaminadasEmUmRaio(Point p, int x) {
		return this.dao.pessoasContaminadasEmUmRaio(p, x);
	}

	public int qtdFocosEmUmaRota(int matricula) {
		return this.dao.qtdFocosEmUmaRota(matricula);
	}

	public double distanciaEntreFocos(Point f1, Point f2) {
		return this.dao.distanciaEntreFocos(f1, f2);
	}
	
	public List<Integer> responsaveisPelosFocos(int matricula) {
		return this.dao.responsaveisPelosFocos(matricula);
	}
	
	public double areaDoAgente(int matricula) {
		return this.dao.areaDoAgente(matricula);
	}
	
	public double comprimentoDaRotaDoAgente(int matricula) {
		return this.dao.comprimentoDaRotaDoAgente(matricula);
	}

	public List<Point> getPointsInsidePolygon(Polygon polygon) {
		return this.dao.getPointsInsidePolygon(polygon);
	}
	
	public void deleteAgente(int matricula){
		this.dao.deleteAgente(matricula);
	}
}
