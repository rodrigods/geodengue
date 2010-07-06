package br.edu.ufcg.sig.persistence.jdbc;

public interface Querys {

	public static final String SAVE_PONTO = "INSERT INTO ponto" +
			" (tipo, geometria)" +
			" VALUES(?, GeometryFromText(?,-1))";
	
	public static final String SAVE_AGENTE = "INSERT INTO agente" +
			" (matricula, nome, areaCobertura, rota)" +
			" VALUES(?, ?, GeometryFromText(?,-1), GeometryFromText(?,-1))";
	
	/**
	 * Quais focos estão a uma distância de X metros de um ponto clicado? 
	 */
	public static final String QUERY_1 = "SELECT p.*" + 
										   " FROM ponto p" + 
										   " WHERE p.tipo = 0 AND" +
										   " distance(p.geometria, GeometryFromText(?, -1)) = ?";
	
	
	/**
	 * Quais focos estão contidos na área do agente de saúde X? Quantos são?
	 */
	public static final String QUERY_2 = "SELECT p.*" +
										   " FROM ponto p, agente x" +
										   " WHERE p.tipo = 0 AND" +
										   " Contains(x.areacobertura, p.geometria) AND" +
										   " x.matricula = ?";
	
	/**
	 * Quantas pessoas foram contaminadas num raio de X metros de um ponto clicado?
	 */
	public static final String QUERY_3 = "SELECT COUNT(*)" +
										   " FROM ponto p" +
										   " WHERE p.tipo = 1 AND" +
										   " Contains(Buffer(GeometryFromText(?, -1), ?), p.geometria)";
	
	/**
	 * Por quantos focos uma rota de uma agente atravessa?
	 */
	public static final String QUERY_4 = "SELECT COUNT(*)" +
										   " FROM ponto p, agente a" +
	 								       " WHERE Contains(a.rota, p.geometria) AND" +
	 								       " p.tipo = 0 AND" +
	 								       " a.matricula = ?";
	
	public static final String QUERY_5 = "SELECT (p1.geometria, p2.geometria)" +
										   "FROM ponto p1, ponto p2" +
										   "WHERE p1.tipo = 0 AND p2.tipo = 0 AND" +
										   "p1.geometria = GeometryFromText(?, -1) AND" +
										   "p2.geometria = GeometryFromText(?, -1)";

	public static final String QUERY_6 = "SELECT a1.matricula" + 
										   "FROM agente x, a1, a2" +
										   "WHERE ST_distance(x.areaCobertura,a1.areaCobertura) < " +
										   "ST_distance(elim.areaCobertura,a2.areaCobertura)" + 
										   "AND x.matricula = ?";	
	
	public static final String QUERY_7 = "SELECT ST_area(a.areaCobertura)" +
										   "FROM agente a" +
										   "WHERE a.matricula = ?";

	public static final String QUERY_8 = "SELECT ST_length2d (a.rota)" +
										   "FROM agente a" +
										   "WHERE a.matricula = ?";
	
	public static final String QUERY_9 = "SELECT p.*" +
										   " FROM ponto p, agente a" +
										   " WHERE a.matricula = ? AND" +
										   " Contains(a.geometria, p.geometria)";
}
