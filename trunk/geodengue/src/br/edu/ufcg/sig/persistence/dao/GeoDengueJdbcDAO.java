package br.edu.ufcg.sig.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


import org.postgis.PGgeometry;
import org.postgis.Point;

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
            s.setString(1, agente.getNome());
            s.setString(2, agente.getAreaCobertura().toString());
            s.setString(3, agente.getRota().toString());

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


	@Override
	public List<Ponto> consultaDistanciaDeFocosAUmPonto(String p1, int x) {
		List<Ponto> focos = new ArrayList<Ponto>();
		try {
            String sql = Querys.QUERY_1; 
           
            PreparedStatement s = dbConn.prepareStatement(sql);      
            s.setString(1, p1);
            s.setInt(2, x);
            ResultSet rs = s.executeQuery();
            Ponto p;
            while(rs.next()){
            	p = new Ponto();
            	int type = rs.getInt(2);
            	if (type == 0) {
            		p.setType(PontoType.FOCO);
            		PGgeometry pg = (PGgeometry)(rs.getObject(3));
            		p.setLocation(getPointByPGgeometry(pg));
            		focos.add(p);
            	}
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

	@Override
	public List<Ponto> focosNaAreaDoAgente(int matricula) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int pessoasContaminadasEmUmRaio(Point p, int x) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int qtdFocosEmUmaRota(int matricula) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public double distanciaEntreFocos(Point f1, Point f2) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<String> responsaveisPelosFocos(int matricula) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public double areaDoAgente(int matricula) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public double comprimentoDaRotaDoAgente(int matricula) {
		// TODO Auto-generated method stub
		return 0;
	}
}
