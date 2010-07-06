package br.edu.ufcg.sig.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            s.setInt(2, agente.getMatricula());
            s.setString(2, agente.getRota().toString());
            s.setString(3, agente.getAreaCobertura().toString());

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
}
