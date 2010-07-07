package br.edu.ufcg.sig.persistence.jdbc;

public interface Queries {

	public static final String SAVE_PONTO = "INSERT INTO ponto" +
			" (tipo, descricao, geom)" +
			" VALUES(?, ?, GeometryFromText(?,-1))";
	
	public static final String SAVE_AGENTE = "INSERT INTO agente" +
			" (matricula, nome, areaCobertura, rota)" +
			" VALUES(?, ?, GeometryFromText(?,-1), GeometryFromText(?,-1))";
	
	/**
	 * Quais focos estão a uma distância de X metros de um ponto clicado? 
	 */
	public static final String QUERY_1 = "SELECT p.*" + 
										   " FROM ponto p" + 
										   " WHERE p.tipo = 'F' AND" +
										   " distance(p.geo, GeometryFromText(?, -1)) = ?";
	
	
	/**
	 * Quais focos estão contidos na área do agente de saúde X? Quantos são?
	 */
	public static final String QUERY_2 = "SELECT p.*" +
										   " FROM ponto p, agente x" +
										   " WHERE p.tipo = 'F' AND" +
										   " Contains(x.areacobertura, p.geo) AND" +
										   " x.matricula = ?";
	
	/**
	 * Quantas pessoas foram contaminadas num raio de X metros de um ponto clicado?
	 */
	public static final String QUERY_3 = "SELECT COUNT(*)" +
										   " FROM ponto p" +
										   " WHERE p.tipo = 'P' AND" +
										   " Contains(Buffer(GeometryFromText(?, -1), ?), p.geo)";
	
	/**
	 * Por quantos focos uma rota de uma agente atravessa?
	 */
	public static final String QUERY_4 = "SELECT COUNT(*)" +
										   " FROM ponto p, agente a" +
	 								       " WHERE Contains(a.rota, p.geo) AND" +
	 								       " p.tipo = 'F' AND" +
	 								       " a.matricula = ?";
	
	/**
	 * Qual a distância entre dois focos selecionados no mapa? 
	 */
	public static final String QUERY_5 = " SELECT distance(p1.geometria, p2.geometria) " +
										   " FROM ponto p1, ponto p2 " +
										   " WHERE p1.tipo = 'F' AND p2.tipo = 'F' AND " +
										   " p1.geometria = GeometryFromText(?, -1) AND " +
										   " p2.geometria = GeometryFromText(?, -1) ";

	/**
	 * Caso o agente X seja eliminado, quem serão os responsáveis pelos focos que eram dele? (Alocar cada foco para o agente com a área mais próxima).
	 */
	public static final String QUERY_6 = " SELECT a1.matricula " + 
										   " FROM agente a1 " +
										   " WHERE distance(a1.areacobertura, GeometryFromText(?, -1)) < " +
										   " ALL (SELECT distance(a2.areacobertura, GeometryFromText(?, -1))" +
										   "      FROM agente a2" +
										   "      WHERE a2.codigo <> a2.codigo)"; 
	
	/**
	 *  Qual a área da área responsável por um agente de saúde 
	 */
	public static final String QUERY_7 = " SELECT area(a.areacobertura) " +
										   " FROM agente a " +
										   " WHERE a.matricula = ? ";

	
	/**
	 *  Qual o comprimento da rota seguida pelo agente de saúde para visitar todos os focos da sua área
	 */
	public static final String QUERY_8 = " SELECT length(a.rota) " +
										   " FROM agente a " +
										   " WHERE a.matricula = ? ";
	
	/**
	 * Recupera os pontos que então dentro de uma geometria dada
	 */
	public static final String GET_POINTS_INSIDE_POLYGON = "SELECT p.geo" +
										   " FROM ponto p" +
										   " WHERE contains(GeometryFromText(?, -1), p.geo)";
	
	public static final String DELETE_AGENTE = " DELETE " +
										   " FROM agente a " +
										   " WHERE a.matricula = ?";
	
	public static final String DELETE_PONTO = " DELETE " +
										   " FROM ponto p " +
										   " WHERE p.codigo = ?";
}
