package org.geodengue.server.persistence.dao;

import java.util.List;

import org.geodengue.server.beans.Agente;
import org.geodengue.server.beans.Ponto;
import org.postgis.Point;
import org.postgis.Polygon;

public interface GeoDengueDAO {
	public void saveAgente(Agente agente);
	
	public void savePonto(Ponto ponto);
	
	public List<Ponto> getFocosFromDistance(Point p1, int x);
	
	public List<Ponto> getFocosOnAgenteArea(int mat);
	
	public int getPessoasContaminadasInArea(Point p,int x);
	
	public int countFocosInAgenteArea(int mat);
	
	public double getDistanceBetweenFocos(Ponto f1, Ponto f2);
	
	public List<Integer> getNewResponsibleAgentes(int mat);
	
	public double getAgenteArea(int mat);
	
	public double getAgenteRotaLength(int mat);
	
	public List<Point> getPointsInsidePolygon(Polygon polygon);
	
	public void deleteAgente(int mat);
	
	public void deletePonto(int codigo);
}
