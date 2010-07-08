-- Equipe 2. GeoDenge
-- Descri��o: Cadastro dos focos do mosquito transmissor da dengue, de pessoas contaminadas e dos agente de sa�de respons�veis por uma determinada �rea. O sistema dever� prover uma rota para os agentes de sa�de dentro de sua �rea de atua��o, que passe por todos os focos e casas que possuem pessoa contaminadas. Dever�o ser exibidas camadas no mapa com as �reas de cada agente, dos focos e das pessoas contaminadas, sendo poss�vel ativar e desativar a visualiza��o de cada camada. 
-- As seguintes consultas espaciais dever�o ser permitidas pela aplica��o: 


-- 1) Quais focos est�o a uma dist�ncia de X metros de um ponto clicado?


SELECT p.geometria
FROM ponto p
WHERE tipo = 0 AND
ST_distance_sphere(p, GeometryFromText(?, 4326)) = x

-- 2) Quais focos est�o contidos na �rea do agente de sa�de X? Quantos s�o?

SELECT p.geometria
FROM ponto p, agente x 
WHERE p.tipo = 0 AND
ST_Contains(x.areaCobertura, p.geometria) AND
x.matricula = ?


-- 3) Quantas pessoas foram contaminadas num raio de X metros de um ponto clicado?

SELECT COUNT(*)
FROM ponto p
WHERE p.tipo = 1 AND
ST_Contains(ST_Buffer(GeometryFromText(?, 4326), X), p.geometria) 


-- 4) Por quantos focos uma rota de uma agente atravessa?

SELECT COUNT(*)
FROM ponto p, agente a
WHERE ST_Contains(a.rota, p.geometria)  AND
p.tipo = 0 AND
a.matricula = ?


-- 5) Qual a dist�ncia entre dois focos selecionados no mapa?

SELECT (p1.geometria, p2.geometria)
FROM ponto p1, ponto p2
WHERE p1.tipo = 0 AND p2.tipo = 0 AND
p1.geometria = GeometryFromText(?, 4326) AND
p2.geometria = GeometryFromText(?, 4326)



-- 6) Caso o agente X seja eliminado, quem ser�o os respons�veis pelos focos que eram dele? (Alocar cada foco para o agente com a �rea mais pr�xima).

SELECT a1.matricula
FROM agente x, a1, a2
WHERE ST_distance(x.areaCobertura,a1.areaCobertura) < ST_distance(elim.areaCobertura,a2.areaCobertura) 
AND x.matricula = ?


-- 7) Qual a �rea da �rea respons�vel por um agente de sa�de?

SELECT ST_area(a.areaCobertura)
FROM agente a 
WHERE a.matricula = ?

-- 8) Qual o comprimento da rota seguida pelo agente de sa�de para visitar todos os focos da sua �rea?

SELECT ST_length2d (a.rota)
FROM agente a
WHERE a.matricula = ?


-- A arquitetura do sistema dever� ser composta por Postgres+Postgis, Geoserver, Google Maps API e KML.