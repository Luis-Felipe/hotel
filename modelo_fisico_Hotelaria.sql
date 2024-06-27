/* Logico_Hotelaria: */

CREATE TABLE Hotes (
    localozacao VARCHAR,
    id NUMERIC PRIMARY KEY,
    nome VARCHAR,
	comodidades VARCHAR,
    avaliacoes INTEGER
);

CREATE TABLE Reservas (
    id NUMERIC PRIMARY KEY,
    id_hotel NUMERIC,
    data_checkin TIMESTAMP,
    data_checkout TIMESTAMP,
    avaliacao_reserva INTEGER,
    fk_Quartos_id INTEGER,
    fk_hopedes_id INTEGER
);

CREATE TABLE Quartos (
    quantidade_hospodes INTEGER,
    id INTEGER PRIMARY KEY,
    preco DECIMAL,
    tipo VARCHAR,
    fk_Hotes_id NUMERIC
);

CREATE TABLE hopedes (
    id INTEGER PRIMARY KEY,
    nome VARCHAR,
    telefone VARCHAR,
    email VARCHAR
);

CREATE TABLE pagamento (
    id NUMERIC PRIMARY KEY,
    realizado BOOLEAN,
    data TIMESTAMP,
    meio_pagamento VARCHAR,
    comprovante VARCHAR,
    fk_Reservas_id NUMERIC
);
 
ALTER TABLE Reservas ADD CONSTRAINT FK_Reservas_2
    FOREIGN KEY (fk_Quartos_id)
    REFERENCES Quartos (id)
    ON DELETE CASCADE;
 
ALTER TABLE Reservas ADD CONSTRAINT FK_Reservas_3
    FOREIGN KEY (fk_hopedes_id)
    REFERENCES hopedes (id)
    ON DELETE CASCADE;
 
ALTER TABLE Quartos ADD CONSTRAINT FK_Quartos_2
    FOREIGN KEY (fk_Hotes_id)
    REFERENCES Hotes (id)
    ON DELETE CASCADE;
 
ALTER TABLE pagamento ADD CONSTRAINT FK_pagamento_2
    FOREIGN KEY (fk_Reservas_id)
    REFERENCES Reservas (id)
    ON DELETE CASCADE;