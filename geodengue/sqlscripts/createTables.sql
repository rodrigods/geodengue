-- Ponto (foco ou pessoa contaminada)

CREATE TABLE ponto (
codigo SERIAL,
tipo INT,
PRIMARY KEY(codigo));

SELECT AddGeometryColumn('ponto','geometria',4326,'POINT',2);

-- Agente de Saúde

CREATE TABLE agente (
matricula SERIAL,
nome varchar(30),
PRIMARY KEY (matricula));

SELECT AddGeometryColumn('agente','areaCobertura',4326,'POLYGON',2);
SELECT AddGeometryColumn('agente','rota',4326,'LINESTRING',2);