package org.geodengue.server.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.geodengue.server.beans.Agente;
import org.geodengue.server.beans.Ponto;
import org.geodengue.server.beans.PontoType;
import org.geodengue.server.persistence.jdbc.ConnectionFactory;
import org.geodengue.server.persistence.jdbc.Queries;
import org.postgis.LineString;
import org.postgis.PGgeometry;
import org.postgis.Point;
import org.postgis.Polygon;

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
            String sql = Queries.SAVE_AGENTE; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);
            s.setInt(1, agente.getMatricula());
            s.setString(2, agente.getNome());
            s.setString(3, agente.getAreaCobertura().toString());
            
            updateRotaAgente(agente);
            if (!agente.getRota().isEmpty()) {
            	s.setString(4, agente.getRota().toString());
            } else {
            	s.setString(4, null);
            }

            s.execute();
            s.close();            
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	private void updateRotaAgente(Agente agente) {
		List<Point> pontos = getPointsInsidePolygon(agente.getAreaCobertura());

		Point[] pArray = new Point[pontos.size()];
		
		int index = 0;
		for (Point p : pontos) {
			pArray[index++] = p;
		}
		
		agente.setRota(new LineString(pArray));
	}

	public void savePonto(Ponto ponto) {
		try {
            String sql = Queries.SAVE_PONTO; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);
            
            String type = ponto.getType().equals(PontoType.FOCO) ? "F" : "P";
            
            s.setString(1, type);
            s.setString(2, ponto.getDescricao());
            s.setString(3, ponto.getGeometria().toString());

            s.execute();
            s.close();            
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public List<Ponto> getFocosFromDistance(Point p1, int x) {
		List<Ponto> focos = new ArrayList<Ponto>();
		try {
            String sql = Queries.QUERY_1; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            s.setString(1, p1.toString());
            s.setInt(2, x);
            ResultSet rs = s.executeQuery();
            Ponto p;
            while(rs.next()){
            	p = new Ponto();
           		p.setType(PontoType.FOCO);
           		PGgeometry pg = (PGgeometry)(rs.getObject(3));
           		p.setGeometria(getPointByPGgeometry(pg));
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

    	Point p = new Point(coordinates); 
    	return p;
	}

	public List<Ponto> getFocosOnAgenteArea(int mat) {
		List<Ponto> focos = new ArrayList<Ponto>();
		try {
            String sql = Queries.QUERY_2; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            s.setInt(1, mat);
            
            ResultSet rs = s.executeQuery();
            while(rs.next()){
            	Ponto p = new Ponto();
           		p.setType(PontoType.FOCO);
           		PGgeometry pg = (PGgeometry)(rs.getObject(3));
           		p.setGeometria(getPointByPGgeometry(pg));
           		focos.add(p);
            }
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 	
		return focos;
	}

	public int getPessoasContaminadasInArea(Point p, int x) {
		int counter = 0;
		try {
            String sql = Queries.QUERY_3; 
           
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

	public int countFocosInAgenteArea(int mat) {
		int out = 0;
		
		try {
            String sql = Queries.QUERY_4; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            s.setInt(1, mat);
            
            ResultSet rs = s.executeQuery();
            if (rs.next()){
            	out = rs.getInt(1);
            }
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 	
        
		return out;
	}
	
	public double getDistanceBetweenFocos(Ponto f1, Ponto f2) {
		double distancia = 0;
		try {
            String sql = Queries.QUERY_5; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            s.setString(1, f1.getGeometria().toString());
            s.setString(2, f2.getGeometria().toString());
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
	
	public List<Integer> getNewResponsibleAgentes(int mat) {
//		Agente agente = getAgente(mat);
//		
//		List<Point> pontos = getPointsInsidePolygon(agente.getAreaCobertura());
//		
//		int matAgente;
//		try {
//            String sql = Querys.QUERY_6; 
//           
//            PreparedStatement s = dbConn.prepareStatement(sql);      
//            s.setInt(1, mat);
//            ResultSet rs = s.executeQuery();
//            while(rs.next()){
//            	agentes.add(rs.getInt(1));
//            }
//            s.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } 	
//		return agentes;
		
		return null;
	}
	
	public double getAgenteArea(int mat) {
		double area = 0;
		try {
            String sql = Queries.QUERY_7; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            s.setInt(1, mat);
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

	public double getAgenteRotaLength(int mat) {
		double length = 0;
		try {
            String sql = Queries.QUERY_8; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            s.setInt(1, mat);
            ResultSet rs = s.executeQuery();
            while(rs.next()){
            	length = rs.getDouble(1);
            }
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 	
		return length;
	}
	
	public List<Ponto> getAllPontos() {
		List<Ponto> pontos = new ArrayList<Ponto>();
		try {
            String sql = Queries.GET_ALL_PONTOS; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            
            ResultSet rs = s.executeQuery();
            while(rs.next()){
            	Ponto p = new Ponto();
            	
            	String type = rs.getString(2);
           		p.setType(PontoType.valueOf(type));

           		p.setDescricao(rs.getString(3));
           		
           		PGgeometry pg = (PGgeometry)(rs.getObject(4));
           		p.setGeometria(getPointByPGgeometry(pg));
           		
           		pontos.add(p);
            }
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 	
        
		return pontos;
	}
	
	public List<Agente> getAllAgentes() {
		List<Agente> agentes = new ArrayList<Agente>();
		try {
            String sql = Queries.GET_ALL_AGENTES; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            
            ResultSet rs = s.executeQuery();
            while(rs.next()){
            	Agente a = new Agente();
            	
            	a.setMatricula(rs.getInt(2));
            	a.setNome(rs.getString(3));
           		
           		PGgeometry pg = (PGgeometry)(rs.getObject(4));
//           		p.setGeometria(getPointByPGgeometry(pg));
           		
           		agentes.add(a);
            }
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 	
        
		return agentes;
	}

	public List<Point> getPointsInsidePolygon(Polygon polygon) {
		List<Point> pontos = new ArrayList<Point>();
		try {
            String sql = Queries.GET_POINTS_INSIDE_POLYGON; 
           
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


	public void deleteAgente(int mat) {
		try {
            String sql = Queries.DELETE_AGENTE; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            s.setInt(1, mat);
            s.execute();
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 	
	}
	
	public void deletePonto(int codigo) {
		try {
            String sql = Queries.DELETE_PONTO; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            s.setInt(1, codigo);
            s.execute();
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } 	
	}
}
