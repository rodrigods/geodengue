package br.edu.ufcg.sig.persistence.jdbc;

public interface Querys {

	public static final String SAVE_PONTO = "INSERT INTO ponto" +
			" (tipo, geometria)" +
			" VALUES(?, GeometryFromText(?,-1))";
	
	public static final String SAVE_AGENTE = "INSERT INTO agente" +
			" (matricula, nome, areaCobertura, rota)" +
			" VALUES(?, ?, GeometryFromText(?,-1), GeometryFromText(?,-1))";
	
	/**
	 * Quais focos est�o a uma dist�ncia de X metros de um ponto clicado? 
	 */
	public static final String QUERY_1 = "SELECT p.*" + 
										   " FROM ponto p" + 
										   " WHERE p.tipo = 0 AND" +
										   " distance(p.geometria, GeometryFromText(?, -1)) = ?";
	
	
	/**
	 * Quais focos est�o contidos na �rea do agente de sa�de X? Quantos s�o?
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
	
	/**
	 * Qual a dist�ncia entre dois focos selecionados no mapa? 
	 */
	public static final String QUERY_5 = " SELECT distance(p1.geometria, p2.geometria) " +
										   " FROM ponto p1, ponto p2 " +
										   " WHERE p1.tipo = 0 AND p2.tipo = 0 AND " +
										   " p1.geometria = GeometryFromText(?, -1) AND " +
										   " p2.geometria = GeometryFromText(?, -1) ";

	/**
	 * Caso o agente X seja eliminado, quem ser�o os respons�veis pelos focos que eram dele? (Alocar cada foco para o agente com a �rea mais pr�xima).
	 */
	public static final String QUERY_6 = " SELECT a1.matricula " + 
										   " FROM agente x, a1, a2 " +
										   " WHERE distance(x.areaCobertura,a1.areaCobertura) < " +
										   " distance(elim.areaCobertura,a2.areaCobertura) " + 
										   " AND x.matricula = ? ";	
	
	/**
	 *  Qual a �rea da �rea respons�vel por um agente de sa�de 
	 */
	public static final String QUERY_7 = " SELECT area(a.areacobertura) " +
										   " FROM agente a " +
										   " WHERE a.matricula = ? ";

	
	/**
	 *  Qual o comprimento da rota seguida pelo agente de sa�de para visitar todos os focos da sua �rea
	 */
	public static final String QUERY_8 = " SELECT length(a.rota) " +
										   " FROM agente a " +
										   " WHERE a.matricula = ? ";
	
	/**
	 * Recupera os pontos que ent�o dentro de uma geometria dada
	 */
	public static final String QUERY_9 = "SELECT p.geometria" +
										   " FROM ponto p" +
										   " WHERE contains(GeometryFromText(?, -1), p.geometria)";
}
