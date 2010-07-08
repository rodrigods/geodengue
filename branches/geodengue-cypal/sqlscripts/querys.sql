-- Equipe 2. GeoDenge
-- Descrição: Cadastro dos focos do mosquito transmissor da dengue, de pessoas contaminadas e dos agente de saúde responsáveis por uma determinada área. O sistema deverá prover uma rota para os agentes de saúde dentro de sua área de atuação, que passe por todos os focos e casas que possuem pessoa contaminadas. Deverão ser exibidas camadas no mapa com as áreas de cada agente, dos focos e das pessoas contaminadas, sendo possível ativar e desativar a visualização de cada camada. 
-- As seguintes consultas espaciais deverão ser permitidas pela aplicação: 


-- 1) Quais focos estão a uma distância de X metros de um ponto clicado?


SELECT p.geometria
FROM ponto p
WHERE tipo = 0 AND
ST_distance_sphere(p, GeometryFromText(?, 4326)) = x

-- 2) Quais focos estão contidos na área do agente de saúde X? Quantos são?

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


-- 5) Qual a distância entre dois focos selecionados no mapa?

SELECT (p1.geometria, p2.geometria)
FROM ponto p1, ponto p2
WHERE p1.tipo = 0 AND p2.tipo = 0 AND
p1.geometria = GeometryFromText(?, 4326) AND
p2.geometria = GeometryFromText(?, 4326)



-- 6) Caso o agente X seja eliminado, quem serão os responsáveis pelos focos que eram dele? (Alocar cada foco para o agente com a área mais próxima).

SELECT a1.matricula
FROM agente x, a1, a2
WHERE ST_distance(x.areaCobertura,a1.areaCobertura) < ST_distance(elim.areaCobertura,a2.areaCobertura) 
AND x.matricula = ?


-- 7) Qual a área da área responsável por um agente de saúde?

SELECT ST_area(a.areaCobertura)
FROM agente a 
WHERE a.matricula = ?

-- 8) Qual o comprimento da rota seguida pelo agente de saúde para visitar todos os focos da sua área?

SELECT ST_length2d (a.rota)
FROM agente a
WHERE a.matricula = ?


-- A arquitetura do sistema deverá ser composta por Postgres+Postgis, Geoserver, Google Maps API e KML.