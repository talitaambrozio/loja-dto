CREATE TABLE tb_fornecedor(
id bigint AUTO_INCREMENT PRIMARY KEY,
cnpj varchar(14) not null,
nome varchar(60) not null,
telefone varchar(10) not null,
email varchar(255) not null,
logradouro VARCHAR(255) NOT NULL,
numero VARCHAR(255) NOT NULL,
complemento VARCHAR(255),
cep VARCHAR(8) NOT NULL );