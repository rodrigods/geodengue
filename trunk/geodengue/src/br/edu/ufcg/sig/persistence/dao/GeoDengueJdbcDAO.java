package br.edu.ufcg.sig.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


import org.postgis.PGgeometry;
import org.postgis.Point;
import org.postgis.Polygon;

import br.edu.ufcg.sig.beans.Agente;
import br.edu.ufcg.sig.beans.Ponto;
import br.edu.ufcg.sig.beans.PontoType;
import br.edu.ufcg.sig.persistence.jdbc.ConnectionFactory;
import br.edu.ufcg.sig.persistence.jdbc.Querys;

public class GeoDengueJdbcDAO implements GeoDengueDAO {
	
	private Connection dbConn;
	
	
	public GeoDengueJdbcDAO(){
		try {
			dbConn = ConnectionFactory.newConnection();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	
	public void saveAgente(Agente agente) {
		try {
            String sql = Querys.SAVE_AGENTE; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);
            s.setInt(1, agente.getMatricula());
            s.setString(2, agente.getNome());
            s.setString(3, agente.getAreaCobertura().toString());
            s.setString(4, agente.getRota().toString());

            s.execute();
            s.close();            
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public void savePonto(Ponto ponto) {
		try {
            String sql = Querys.SAVE_PONTO; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);
            
            int type = 0;
            if (ponto.getType().equals(PontoType.PESSOA_CONTAMINADA)) {
            	type = 1;
            }
            
            s.setInt(1, type);
            s.setString(2, ponto.getLocation().toString());

            s.execute();
            s.close();            
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public List<Ponto> consultaDistanciaDeFocosAUmPonto(Point p1, int x) {
		List<Ponto> focos = new ArrayList<Ponto>();
		try {
            String sql = Querys.QUERY_1; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            s.setString(1, p1.toString());
            s.setInt(2, x);
            ResultSet rs = s.executeQuery();
            Ponto p;
            while(rs.next()){
            	p = new Ponto();
           		p.setType(PontoType.FOCO);
           		PGgeometry pg = (PGgeometry)(rs.getObject(3));
           		p.setLocation(getPointByPGgeometry(pg));
           		focos.add(p);
            }
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 	
		return focos;
	}

	private Point getPointByPGgeometry(PGgeometry pg) throws SQLException{		
    	String aux[] = pg.toString().split(" ");
    	String coordinates = aux[0].substring(6) + " " + aux[1].substring(0, aux[1].length() - 1);
    	//creates a point with the string:
    	//
    	Point p = new Point(coordinates); 
    	return p;
	}

	
	public List<Ponto> focosNaAreaDoAgente(int matricula) {
		List<Ponto> focos = new ArrayList<Ponto>();
		try {
            String sql = Querys.QUERY_2; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            s.setInt(1, matricula);
            
            ResultSet rs = s.executeQuery();
            while(rs.next()){
            	Ponto p = new Ponto();
           		p.setType(PontoType.FOCO);
           		PGgeometry pg = (PGgeometry)(rs.getObject(3));
           		p.setLocation(getPointByPGgeometry(pg));
           		focos.add(p);
            }
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 	
		return focos;
	}


	
	public int pessoasContaminadasEmUmRaio(Point p, int x) {
		int counter = 0;
		try {
            String sql = Querys.QUERY_3; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            s.setString(1, p.toString());
            s.setInt(2, x);
            
            ResultSet rs = s.executeQuery();
            if (rs.next()){
            	counter = rs.getInt(1);
            }
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 	
        
		return counter;
	}


	
	public int qtdFocosEmUmaRota(int matricula) {
		int counter = 0;
		try {
            String sql = Querys.QUERY_4; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            s.setInt(1, matricula);
            
            ResultSet rs = s.executeQuery();
            if (rs.next()){
            	counter = rs.getInt(1);
            }
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 	
        
		return counter;
	}
	
	public double distanciaEntreFocos(Point f1, Point f2) {
		double distancia = 0;
		try {
            String sql = Querys.QUERY_5; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            s.setString(1, f1.toString());
            s.setString(2, f2.toString());
            ResultSet rs = s.executeQuery();
            while(rs.next()){
            	distancia = rs.getDouble(1);
            }
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 	
		return distancia;

	}
	
	public List<Integer> responsaveisPelosFocos(int matricula) {
		List<Integer> agentes = new ArrayList<Integer>();
		try {
            String sql = Querys.QUERY_6; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            s.setInt(1, matricula);
            ResultSet rs = s.executeQuery();
            while(rs.next()){
            	agentes.add(rs.getInt(1));
            }
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 	
		return agentes;
	}

	public double areaDoAgente(int matricula) {
		double area = 0;
		try {
            String sql = Querys.QUERY_7; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            s.setInt(1, matricula);
            ResultSet rs = s.executeQuery();
            while(rs.next()){
            	area = rs.getDouble(1);
            }
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 	
		return area;
	}

	public double comprimentoDaRotaDoAgente(int matricula) {
		double comprimento = 0;
		try {
            String sql = Querys.QUERY_8; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            s.setInt(1, matricula);
            ResultSet rs = s.executeQuery();
            while(rs.next()){
            	comprimento = rs.getDouble(1);
            }
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 	
		return comprimento;
	}


	@Override
	public List<Point> getPointsInsidePolygon(Polygon polygon) {
		List<Point> pontos = new ArrayList<Point>();
		try {
            String sql = Querys.QUERY_9; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            s.setString(1, polygon.toString());
            ResultSet rs = s.executeQuery();
            while(rs.next()){
            	PGgeometry pg = (PGgeometry)(rs.getObject(1));
            	pontos.add(getPointByPGgeometry(pg));
            }
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 	
		return pontos;
	}
}
