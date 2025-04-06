CREATE TABLE clientes (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          nome VARCHAR(255) NOT NULL,
                          telefone VARCHAR(20),
                          endereco VARCHAR(255),
                          PRIMARY KEY (id)
);