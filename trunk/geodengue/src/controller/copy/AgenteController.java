package controller.copy;

import org.postgis.LinearRing;
import org.postgis.Point;
import org.postgis.Polygon;

import br.edu.ufcg.sig.beans.Agente;
import br.edu.ufcg.sig.persistence.PersistenceFacadeImpl;


public class AgenteController {
	
	public static void cadastraAgente(String nome, String matricula, String area){
		String[] split = area.split(",");
		
		Point[] pontos = new Point[split.length];
		
		for (int i = 0; i < split.length; i += 2) {
			String[] split2 = split[i].trim().split("[ ]+");
			pontos[i] = new Point(Double.valueOf(split2[0]), Double.valueOf(split2[1]));
		}
		
		LinearRing[] lr = new LinearRing[1];
		lr[0] = new LinearRing(pontos);
		
		Polygon areaCobertura = new Polygon(lr);
		
		Agente agente = new Agente();
		agente.setNome(nome);
		agente.setMatricula(Integer.valueOf(matricula));
		agente.setAreaCobertura(areaCobertura);
		
		PersistenceFacadeImpl.getInstance().saveAgente(agente);
	}

}
