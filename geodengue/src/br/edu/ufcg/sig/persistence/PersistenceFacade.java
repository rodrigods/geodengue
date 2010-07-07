package br.edu.ufcg.sig.persistence;

import java.util.List;

import org.postgis.Point;
import org.postgis.Polygon;

import br.edu.ufcg.sig.beans.Agente;
import br.edu.ufcg.sig.beans.Ponto;
import br.edu.ufcg.sig.persistence.dao.GeoDengueDAO;
import br.edu.ufcg.sig.persistence.dao.GeoDengueJdbcDAO;

public class PersistenceFacade {
	
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

	public List<Ponto> getFocosFromDistance(Point p1, int x) {
		return this.dao.getFocosFromDistance(p1, x);
	}

	public List<Ponto> getFocosOnAgenteArea(int mat) {
		return this.dao.getFocosOnAgenteArea(mat);
	}
	
	public int getPessoasContaminadasInArea(Point p, int x) {
		return this.dao.getPessoasContaminadasInArea(p, x);
	}

	public int countFocosInAgenteArea(int mat) {
		return this.dao.countFocosInAgenteArea(mat);
	}
	
	public double getDistanceBetweenFocos(Ponto f1, Ponto f2) {
		return this.dao.getDistanceBetweenFocos(f1, f2);
	}
	
	public List<Integer> getNewResponsibleAgentes(int mat) {
		return this.dao.getNewResponsibleAgentes(mat);
	}
	
	public double getAgenteArea(int mat) {
		return this.dao.getAgenteArea(mat);
	}

	public double getAgenteRotaLength(int mat) {
		return this.dao.getAgenteRotaLength(mat);
	}

	public List<Point> getPointsInsidePolygon(Polygon polygon) {
		return this.dao.getPointsInsidePolygon(polygon);
	}

	public void deleteAgente(int mat) {
		this.dao.deleteAgente(mat);
	}

	public void deletePonto(int cod) {
		this.dao.deletePonto(cod);
	}
}
