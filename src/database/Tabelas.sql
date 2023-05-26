create table TipoImovel(
    idTipoImovel INT NOT NULL AUTO_INCREMENT,
    descricao VARCHAR(500) NOT NULL,
    PRIMARY KEY (idTipoImovel)
);

create table Imovel (
    idImovel INT NOT NULL AUTO_INCREMENT,
    endereco VARCHAR(255) NOT NULL, 
    metragem DOUBLE NOT NULL,
    quantQuartos INT NOT NULL,
    quantBanheiros INT NOT NULL,
    descricaoDependencias VARCHAR(500) NOT NULL,
    valorLocacao DOUBLE NOT NULL,
    fotoImovel VARCHAR(500) NOT NULL,
    idTipoImovel INT NOT NULL,
    PRIMARY KEY (idImovel),
    FOREIGN KEY (idTipoImovel)
    REFERENCES TipoImovel(idTipoImovel) ON DELETE CASCADE);