-- Ponto (foco ou pessoa contaminada)

CREATE TABLE ponto (
codigo SERIAL,
tipo CHAR,
descricao VARCHAR(200),
PRIMARY KEY(codigo));

SELECT AddGeometryColumn('ponto','geom',-1,'POINT',2);

-- Agente de Saúde

CREATE TABLE agente (
codigo SERIAL,
matricula INT NOT NULL,
nome varchar(30),
PRIMARY KEY (codigo));

SELECT AddGeometryColumn('agente','areacobertura',-1,'POLYGON',2);
SELECT AddGeometryColumn('agente','rota',-1,'LINESTRING',2);