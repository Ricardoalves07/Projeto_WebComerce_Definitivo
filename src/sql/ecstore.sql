-- Criação da base de dados
CREATE DATABASE ecstore
WITH 
    ENCODING = 'UTF8'
    LC_COLLATE = 'pt_BR.UTF-8'
    LC_CTYPE = 'pt_BR.UTF-8'
    TEMPLATE = template0;

-- Conexão com a base de dados
\c ecstore;

-- Tabelas
-- Tabela Usuário
CREATE TABLE Usuario (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    endereco TEXT,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

-- Tabela Produto
CREATE TABLE Produto (
    id SERIAL PRIMARY KEY,
    categoria VARCHAR(50),
    quantidade INTEGER NOT NULL,
    preco NUMERIC(10, 2) NOT NULL,
    descricao TEXT,
    foto BYTEA -- Campo ajustado para armazenar a imagem
);

-- Tabela Compra
CREATE TABLE Compra (
    id SERIAL PRIMARY KEY,
    data_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_id INTEGER NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES Usuario (id) ON DELETE CASCADE
);

-- Tabela Carrinho (associação entre Compra e Produto)
CREATE TABLE Carrinho (
    compra_id INTEGER NOT NULL,
    produto_id INTEGER NOT NULL,
    quantidade INTEGER NOT NULL,
    PRIMARY KEY (compra_id, produto_id),
    FOREIGN KEY (compra_id) REFERENCES Compra (id) ON DELETE CASCADE,
    FOREIGN KEY (produto_id) REFERENCES Produto (id) ON DELETE CASCADE
);
