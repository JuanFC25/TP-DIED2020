CREATE SCHEMA trabajoPractico
	AUTHORIZATION root;
	
CREATE TABLE trabajoPractico.insumo
	(
		idInsumo integer,
		descripcion varchar(1000),
		undidad varchar(100),
		costoXinsumo numeric(20,2),
		CONSTRAINT pk_idInsumo PRIMARY KEY (idInsumo)
	);
	
CREATE TABLE trabajoPractico.general
	(
		idInsumoGeneral integer,
		peso numeric(20,2),
		CONSTRAINT pk_idInsumoGeneral PRIMARY KEY (idInsumoGeneral),
		CONSTRAINT fk_idInsumo FOREIGN KEY (idInsumoGeneral)
		REFERENCES trabajoPractico.insumo (idInsumo)
	);

CREATE TABLE trabajoPractico.liquido
	(
		idInsumoLiquido integer,
		densidad numeric(20,2),
		CONSTRAINT pk_idInsumoLiquido PRIMARY KEY (idInsumoLiquido),
		CONSTRAINT fk_idInsumo FOREIGN KEY (idInsumoLiquido)
		REFERENCES trabajoPractico.insumo (idInsumo)
	);
	
CREATE TABLE trabajoPractico.planta
	(
		idPlanta integer,
		nombrePlanta varchar(100),
		direccion varchar(200),
		telefono integer,
		CONSTRAINT pk_idPlanta PRIMARY KEY (idPlanta)
	);
	
CREATE TABLE trabajoPractico.stock
	(
		idRegistro integer,
		cantidad integer,
		puntoDePedido integer,
		idInsumoAsociado integer,
		idPlantaAsociada integer,
		CONSTRAINT pk_idRegistro PRIMARY KEY (idRegistro),
		CONSTRAINT fk_idInsumo FOREIGN KEY (idInsumoAsociado)
		REFERENCES trabajoPractico.insumo (idInsumo),
		CONSTRAINT fk_idPlanta FOREIGN KEY (idPlantaAsociada)
		REFERENCES trabajoPractico.planta (idPlanta)					   
	);
	

CREATE TABLE trabajoPractico.pedido
	(
		idPedido integer,
		idPlantaOrigen integer,
		idPlantaDestino integer,
		fechaSolicitud date,
		fechaEntrega date,
		estado varchar(100),
		CONSTRAINT pk_idPedido PRIMARY KEY (idPedido),
		CONSTRAINT fk_plantaOrigen FOREIGN KEY (idPlantaOrigen)
		REFERENCES trabajoPractico.planta (idPlanta),
		CONSTRAINT fk_plantaDestino FOREIGN KEY (idPlantaDestino)
		REFERENCES trabajoPractico.planta (idPlanta)
	);
	
	
CREATE TABLE trabajoPractico.camion
	(
		idCamion Integer,
		patente varchar(20),
		marca varchar(100),
		modelo varchar(100),
		kmRecorridos integer,
		costoXkm numeric (50,2),
		costoXhora numeric(50,2),
		fechaCompra date,
		CONSTRAINT pk_idCamion PRIMARY KEY (idCamion)
	);
	
CREATE TABLE trabajoPractico.ruta
	(
		idRuta integer,
		idPlantaOrigen integer,
		idPlantaDestino integer,
		distanciaEnKm integer,
		duracionEnMin integer,
		cantMaxPermitidaEnKilos integer,
		CONSTRAINT pk_idRuta PRIMARY KEY (idRuta),
		CONSTRAINT fk_plantaOrigen FOREIGN KEY (idPlantaOrigen)
		REFERENCES trabajoPractico.planta (idPlanta),
		CONSTRAINT fk_plantaDestino FOREIGN KEY (idPlantaDestino)
		REFERENCES trabajoPractico.planta (idPlanta)
	);
	
CREATE TABLE trabajoPractico.item
	(
		idItem integer,
		idPedido integer,
		idInsumo integer,
		cantidad numeric (50,2),
		costoItem numeric (50,2),
		CONSTRAINT pk_idItem PRIMARY KEY (idItem),
		CONSTRAINT fk_idPedido FOREIGN KEY (idPedido)
		REFERENCES trabajoPractico.pedido (idPedido),
		CONSTRAINT fk_idInsumo FOREIGN KEY (idInsumo)
		REFERENCES trabajoPractico.Insumo (idInsumo)
	);
	
CREATE TABLE trabajoPractico.envio
	(
		idEnvio integer,
		idPedido integer,
		idRutaAsignada integer,
		costoEnvio numeric(50,2),
		idCamion integer,
		CONSTRAINT pk_idEnvio PRIMARY KEY (idEnvio),
		CONSTRAINT fk_idPedido FOREIGN KEY (idPedido)
		REFERENCES trabajoPractico.pedido (idPedido),
		CONSTRAINT fk_idCamion FOREIGN KEY (idCamion)
		REFERENCES trabajoPractico.camion (idCamion)
	);