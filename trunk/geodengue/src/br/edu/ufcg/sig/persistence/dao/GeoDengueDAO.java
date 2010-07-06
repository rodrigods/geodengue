package br.edu.ufcg.sig.persistence.dao;

import java.util.List;

import org.postgis.Point;
import org.postgis.Polygon;

import br.edu.ufcg.sig.beans.Agente;
import br.edu.ufcg.sig.beans.Ponto;

public interface GeoDengueDAO {
	public void saveAgente(Agente agente);
	
	public void savePonto(Ponto ponto);
	
	public List<Ponto> consultaDistanciaDeFocosAUmPonto(Point p1, int x);
	
	public List<Ponto> focosNaAreaDoAgente(int matricula);
	
//	public int qtidadeFocosNaAreaDoAgente(int matricula);
	
	public int pessoasContaminadasEmUmRaio(Point p,int x);
	
	public int qtdFocosEmUmaRota(int matricula);
	
	public double distanciaEntreFocos(Point f1, Point f2);
	
	public List<Integer> responsaveisPelosFocos(int matricula);
	
	public double areaDoAgente(int matricula);
	
	public double comprimentoDaRotaDoAgente(int matricula);
	
	public List<Point> getPointsInsidePolygon(Polygon polygon);
}
