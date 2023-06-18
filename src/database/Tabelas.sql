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
    alocado BOOLEAN DEFAULT false,
    idTipoImovel INT NOT NULL,
    PRIMARY KEY (idImovel),
    FOREIGN KEY (idTipoImovel)
    REFERENCES TipoImovel(idTipoImovel) ON DELETE CASCADE);

create table Locatario (
    idLocatario INT NOT NULL AUTO_INCREMENT,
    cpf VARCHAR(14) NOT NULL, 
    nome VARCHAR(250) NOT NULL,
    estadoCivil VARCHAR(100) NOT NULL,
    dataNascimento DATE NOT NULL,
    contaBancaria VARCHAR(150) NOT NULL,
    PRIMARY KEY (idLocatario));


create table Locacao (
    idLocacao INT NOT NULL AUTO_INCREMENT,
    valorDesconto DOUBLE NOT NULL, 
    dataInicio DATE NOT NULL,
    dataTermino DATE NOT NULL,
    encerrado BOOLEAN NOT NULL,
    formaPgto INT NOT NULL,
    idImovel INT NOT NULL,
    idLocatario INT NOT NULL,
    PRIMARY KEY (idLocacao),
    FOREIGN KEY (idImovel) REFERENCES Imovel(idImovel) ON DELETE CASCADE,
    FOREIGN KEY (idLocatario) REFERENCES Locatario(idLocatario) ON DELETE CASCADE
);

CREATE VIEW relatorio AS
SELECT l.idImovel, l.idLocatario, lt.nome, l.dataTermino, i.valorLocacao, l.valorDesconto
FROM locacao l
JOIN imovel i ON l.idImovel = i.idImovel
JOIN locatario lt ON l.idLocatario = lt.idLocatario
WHERE l.encerrado=0;

