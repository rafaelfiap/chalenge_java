-- ==============================
-- 1. Criação das Tabelas (DDL)
-- ==============================


-- Criação da tabela CLIENTE
CREATE TABLE T_CLIENTE (
                           id_cliente NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                           nr_cpf VARCHAR2(15) NOT NULL UNIQUE,
                           nm_cliente VARCHAR2(100) NOT NULL,
                           ds_email VARCHAR2(100),
                           sx_sexo CHAR(1) NOT NULL CHECK (sx_sexo IN ('M', 'F'))
);

-- Criação da tabela TELEFONE_CLIENTE associada a CLIENTE
CREATE TABLE T_TELEFONE_CLIENTE (
                                    id_telefone_cliente NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                    nr_telefone VARCHAR2(15) NOT NULL,
                                    tp_telefone VARCHAR2(11) CHECK (tp_telefone IN ('Residencial', 'Comercial', 'Celular')),
                                    id_cliente NUMBER NOT NULL,  -- Chave estrangeira referenciando CLIENTE
                                    FOREIGN KEY (id_cliente) REFERENCES T_CLIENTE(id_cliente)
);

-- Criação da tabela ENDERECO_CLIENTE
CREATE TABLE T_ENDERECO_CLIENTE (
                                    id_endereco_cliente NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                    ds_logradouro VARCHAR2(100) NOT NULL,
                                    nr_numero NUMBER NOT NULL CHECK (nr_numero > 0),
                                    nr_cep VARCHAR2(10) NOT NULL,
                                    nm_bairro VARCHAR2(50) NOT NULL,
                                    nm_cidade VARCHAR2(50) NOT NULL,
                                    sg_uf VARCHAR2(2) NOT NULL CHECK (sg_uf IN ('AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO')),
                                    id_cliente NUMBER NOT NULL,
                                    FOREIGN KEY (id_cliente) REFERENCES T_CLIENTE(id_cliente)
);

-- Criação da tabela VEICULO
CREATE TABLE T_VEICULO (
                           id_veiculo NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                           id_placa VARCHAR2(10) NOT NULL UNIQUE,
                           nm_marca VARCHAR2(50) NOT NULL,
                           nm_modelo VARCHAR2(50) NOT NULL,
                           nr_ano NUMBER NOT NULL CHECK (nr_ano >= 1980 AND nr_ano <= 2024),
                           ds_cor VARCHAR2(20),
                           st_combustivel VARCHAR2(20) NOT NULL CHECK (st_combustivel IN ('Gasolina', 'Diesel', 'Álcool', 'Elétrico')),
                           id_cliente NUMBER NOT NULL,
                           FOREIGN KEY (id_cliente) REFERENCES T_CLIENTE(id_cliente)
);

-- Criação da tabela OFICINA com id_oficina como chave primária
CREATE TABLE T_OFICINA (
                           id_oficina NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                           nr_cnpj VARCHAR2(18) NOT NULL UNIQUE,
                           nm_oficina VARCHAR2(100) NOT NULL,
                           ds_email VARCHAR2(100) NOT NULL
);

-- Criação da tabela TELEFONE_OFICINA associada a OFICINA
CREATE TABLE T_TELEFONE_OFICINA (
                                    id_telefone_oficina NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                    nr_telefone VARCHAR2(15) NOT NULL,
                                    tp_telefone VARCHAR2(11) CHECK (tp_telefone IN ('Residencial', 'Comercial', 'Celular')),
                                    id_oficina NUMBER NOT NULL,
                                    FOREIGN KEY (id_oficina) REFERENCES T_OFICINA(id_oficina)
);

-- Criação da tabela ENDERECO_OFICINA referenciando id_oficina
CREATE TABLE T_ENDERECO_OFICINA (
                                    id_endereco_oficina NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                    ds_logradouro VARCHAR2(100) NOT NULL,
                                    nr_numero NUMBER NOT NULL CHECK (nr_numero > 0),
                                    nr_cep VARCHAR2(10) NOT NULL,
                                    nm_bairro VARCHAR2(50) NOT NULL,
                                    nm_cidade VARCHAR2(50) NOT NULL,
                                    sg_uf VARCHAR2(2) NOT NULL CHECK (sg_uf IN ('AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO')),
                                    id_oficina NUMBER NOT NULL,
                                    FOREIGN KEY (id_oficina) REFERENCES T_OFICINA(id_oficina)
);

-- Criação da tabela FUNCIONARIO com referência a OFICINA
CREATE TABLE T_FUNCIONARIO (
                               id_funcionario NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                               nr_cpf VARCHAR2(15) NOT NULL UNIQUE,
                               nm_funcionario VARCHAR2(100) NOT NULL,
                               sx_sexo CHAR(1) NOT NULL CHECK (sx_sexo IN ('M', 'F')),
                               ds_funcao VARCHAR2(50) NOT NULL,
                               id_oficina NUMBER NOT NULL,
                               FOREIGN KEY (id_oficina) REFERENCES T_OFICINA(id_oficina)
);

-- Criação da tabela AGENDAMENTO com referência a CLIENTE e OFICINA
CREATE TABLE T_AGENDAMENTO (
                               id_agendamento NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                               dt_agendamento DATE NOT NULL,
                               hr_agendamento TIMESTAMP NOT NULL,
                               id_cliente NUMBER NOT NULL,
                               id_oficina NUMBER NOT NULL,
                               FOREIGN KEY (id_cliente) REFERENCES T_CLIENTE(id_cliente),
                               FOREIGN KEY (id_oficina) REFERENCES T_OFICINA(id_oficina)
);

-- Criação da tabela ORCAMENTO com referência a VEICULO e OFICINA
CREATE TABLE T_ORCAMENTO (
                             id_orcamento NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                             vl_orcamento NUMBER(10, 2) NOT NULL CHECK (vl_orcamento > 0),
                             st_situacao VARCHAR2(20) NOT NULL,
                             id_veiculo NUMBER NOT NULL,
                             id_oficina NUMBER NOT NULL,
                             FOREIGN KEY (id_veiculo) REFERENCES T_VEICULO(id_veiculo),
                             FOREIGN KEY (id_oficina) REFERENCES T_OFICINA(id_oficina)
);

-- Criação da tabela SERVICOS com referência a ORCAMENTO e VEICULO
CREATE TABLE T_SERVICOS (
                            id_servico NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            st_tipo_servico VARCHAR2(50) NOT NULL,
                            ds_servico VARCHAR2(200) NOT NULL,
                            hr_tempo_estimado TIMESTAMP,
                            vl_custo NUMBER(10, 2) NOT NULL CHECK (vl_custo > 0),
                            id_orcamento NUMBER NOT NULL,
                            FOREIGN KEY (id_orcamento) REFERENCES T_ORCAMENTO(id_orcamento)
);

-- Criação da tabela FALHAS com gravidade categorizada, id_orcamento e id_veiculo
CREATE TABLE T_FALHAS (
                          id_falha NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                          ds_falha VARCHAR2(200) NOT NULL,
                          ds_solucao VARCHAR2(200),
                          id_orcamento NUMBER NOT NULL,
                          id_veiculo NUMBER NOT NULL,
                          st_gravidade VARCHAR2(10) NOT NULL CHECK (st_gravidade IN ('Baixa', 'Média', 'Alta')),
                          FOREIGN KEY (id_orcamento) REFERENCES T_ORCAMENTO(id_orcamento),
                          FOREIGN KEY (id_veiculo) REFERENCES T_VEICULO(id_veiculo)
);

-- Criação da tabela PECAS com referência a ORCAMENTO e SERVICO
CREATE TABLE T_PECAS (
                         id_peca NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         nm_marca VARCHAR2(50) NOT NULL,
                         qt_quantidade NUMBER NOT NULL CHECK (qt_quantidade > 0),
                         vl_valor NUMBER(10, 2) NOT NULL CHECK (vl_valor > 0),
                         ds_descricao VARCHAR2(200),
                         id_orcamento NUMBER NOT NULL,
                         id_servico NUMBER NOT NULL,
                         FOREIGN KEY (id_orcamento) REFERENCES T_ORCAMENTO(id_orcamento),
                         FOREIGN KEY (id_servico) REFERENCES T_SERVICOS(id_servico)
);

-- Criação da tabela ORDEM_DE_SERVICO com referência a ORCAMENTO, FUNCIONARIO e VEICULO
CREATE TABLE T_ORDEM_DE_SERVICO (
                                    id_os NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                    dt_inicio DATE NOT NULL,
                                    dt_fim DATE,
                                    hr_inicio TIMESTAMP NOT NULL,
                                    hr_fim TIMESTAMP,
                                    st_status VARCHAR2(20) NOT NULL,
                                    id_orcamento NUMBER NOT NULL,
                                    id_funcionario NUMBER NOT NULL,
                                    id_veiculo NUMBER NOT NULL,
                                    FOREIGN KEY (id_orcamento) REFERENCES T_ORCAMENTO(id_orcamento),
                                    FOREIGN KEY (id_funcionario) REFERENCES T_FUNCIONARIO(id_funcionario),
                                    FOREIGN KEY (id_veiculo) REFERENCES T_VEICULO(id_veiculo)
);

-- Criação da tabela METODO_PAGAMENTO com referência a ORDEM_DE_SERVICO
CREATE TABLE T_METODO_PAGAMENTO (
                                    id_pagamento NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                    st_forma_pagamento VARCHAR2(50) NOT NULL,
                                    st_tipo_pagamento VARCHAR2(20) NOT NULL,
                                    vl_desconto NUMBER(5, 2) CHECK (vl_desconto >= 0 AND vl_desconto <= 100),
                                    id_os NUMBER NOT NULL,
                                    FOREIGN KEY (id_os) REFERENCES T_ORDEM_DE_SERVICO(id_os)
);



-- ====================================
-- 2. Inserção de Dados (DML) - Exemplo
-- ====================================


-- Inserção de Dados na Tabela T_CLIENTE (10 registros)

INSERT INTO T_CLIENTE (nr_cpf, nm_cliente, ds_email, sx_sexo) VALUES ('12345678901', 'Carlos Henrique da Silva', 'carlos.henrique@exemplo.com', 'M');
INSERT INTO T_CLIENTE (nr_cpf, nm_cliente, ds_email, sx_sexo) VALUES ('23456789012', 'Ana Paula Santos', 'ana.santos@exemplo.com', 'F');
INSERT INTO T_CLIENTE (nr_cpf, nm_cliente, ds_email, sx_sexo) VALUES ('34567890123', 'João Roberto Pereira', 'joao.pereira@exemplo.com', 'M');
INSERT INTO T_CLIENTE (nr_cpf, nm_cliente, ds_email, sx_sexo) VALUES ('45678901234', 'Mariana Souza Lima', 'mariana.souza@exemplo.com', 'F');
INSERT INTO T_CLIENTE (nr_cpf, nm_cliente, ds_email, sx_sexo) VALUES ('56789012345', 'Ricardo de Azevedo', 'ricardo.azevedo@exemplo.com', 'M');
INSERT INTO T_CLIENTE (nr_cpf, nm_cliente, ds_email, sx_sexo) VALUES ('67890123456', 'Fernanda Alves Costa', 'fernanda.costa@exemplo.com', 'F');
INSERT INTO T_CLIENTE (nr_cpf, nm_cliente, ds_email, sx_sexo) VALUES ('78901234567', 'Gustavo Henrique Rocha', 'gustavo.rocha@exemplo.com', 'M');
INSERT INTO T_CLIENTE (nr_cpf, nm_cliente, ds_email, sx_sexo) VALUES ('89012345678', 'Juliana Oliveira Dias', 'juliana.dias@exemplo.com', 'F');
INSERT INTO T_CLIENTE (nr_cpf, nm_cliente, ds_email, sx_sexo) VALUES ('90123456789', 'Lucas Almeida Souza', 'lucas.almeida@exemplo.com', 'M');
INSERT INTO T_CLIENTE (nr_cpf, nm_cliente, ds_email, sx_sexo) VALUES ('01234567890', 'Beatriz Ramos', 'beatriz.ramos@exemplo.com', 'F');


-- Inserção de Dados na Tabela T_TELEFONE_CLIENTE (20 registros)

INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('11987654321', 'Celular', 1);
INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('1145678901', 'Residencial', 1);
INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('1167890123', 'Comercial', 1);

INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('11923456789', 'Celular', 2);
INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('1133445566', 'Residencial', 2);
INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('1165432109', 'Comercial', 2);

INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('11976543210', 'Celular', 3);
INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('1123456789', 'Residencial', 3);
INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('1187654321', 'Comercial', 3);

INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('11987654322', 'Celular', 4);
INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('1134567891', 'Residencial', 4);
INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('1156789012', 'Comercial', 4);

INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('11923456780', 'Celular', 5);
INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('1122233344', 'Residencial', 5);
INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('1176543211', 'Comercial', 5);

INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('11976543211', 'Celular', 6);
INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('1123456789', 'Residencial', 6);
INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('1187654321', 'Comercial', 6);

INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('11987654323', 'Celular', 7);
INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('1134567891', 'Residencial', 7);
INSERT INTO T_TELEFONE_CLIENTE (nr_telefone, tp_telefone, id_cliente) VALUES ('1156789012', 'Comercial', 7);


-- Inserção de Dados na Tabela T_ENDERECO_CLIENTE (10 registros)

INSERT INTO T_ENDERECO_CLIENTE (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_cliente)
VALUES ('Rua das Flores', 123, '01001000', 'Centro', 'São Paulo', 'SP', 1);

INSERT INTO T_ENDERECO_CLIENTE (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_cliente)
VALUES ('Avenida Paulista', 456, '01310000', 'Bela Vista', 'São Paulo', 'SP', 2);

INSERT INTO T_ENDERECO_CLIENTE (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_cliente)
VALUES ('Rua das Margaridas', 789, '05422000', 'Pinheiros', 'São Paulo', 'SP', 3);

INSERT INTO T_ENDERECO_CLIENTE (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_cliente)
VALUES ('Rua dos Lírios', 101, '04546000', 'Jardim América', 'São Paulo', 'SP', 4);

INSERT INTO T_ENDERECO_CLIENTE (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_cliente)
VALUES ('Avenida Brasil', 202, '01412000', 'Itaim Bibi', 'São Paulo', 'SP', 5);

INSERT INTO T_ENDERECO_CLIENTE (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_cliente)
VALUES ('Rua XV de Novembro', 303, '80020310', 'Centro', 'Curitiba', 'PR', 6);

INSERT INTO T_ENDERECO_CLIENTE (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_cliente)
VALUES ('Avenida das Nações', 404, '89010001', 'Pioneiros', 'Balneário Camboriú', 'SC', 7);

INSERT INTO T_ENDERECO_CLIENTE (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_cliente)
VALUES ('Rua Amazonas', 505, '30130010', 'Funcionários', 'Belo Horizonte', 'MG', 8);

INSERT INTO T_ENDERECO_CLIENTE (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_cliente)
VALUES ('Rua Sete de Setembro', 606, '20050002', 'Centro', 'Rio de Janeiro', 'RJ', 9);

INSERT INTO T_ENDERECO_CLIENTE (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_cliente)
VALUES ('Rua Vitória', 707, '50030230', 'Boa Vista', 'Recife', 'PE', 10);


-- Inserção de Dados na Tabela T_VEICULO (20 registros)

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('ABC1234', 'Toyota', 'Corolla', 2020, 'Branco', 'Gasolina', 1);

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('DEF5678', 'Honda', 'Civic', 2019, 'Preto', 'Álcool', 2);

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('GHI9101', 'Ford', 'Focus', 2018, 'Prata', 'Diesel', 3);

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('JKL1122', 'Volkswagen', 'Golf', 2021, 'Azul', 'Elétrico', 4);

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('MNO3344', 'Chevrolet', 'Cruze', 2017, 'Vermelho', 'Gasolina', 5);

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('PQR5566', 'Hyundai', 'Elantra', 2016, 'Cinza', 'Diesel', 6);

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('STU7788', 'Nissan', 'Sentra', 2020, 'Preto', 'Gasolina', 7);

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('VWX9900', 'Kia', 'Cerato', 2019, 'Branco', 'Álcool', 8);

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('YZA1123', 'Renault', 'Megane', 2015, 'Prata', 'Gasolina', 9);

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('BCD4456', 'Peugeot', '308', 2018, 'Azul', 'Diesel', 10);

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('EFG6677', 'Fiat', 'Palio', 2017, 'Vermelho', 'Gasolina', 1);

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('HIJ8899', 'Jeep', 'Renegade', 2021, 'Verde', 'Diesel', 2);

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('KLM0011', 'Chevrolet', 'Onix', 2018, 'Azul', 'Gasolina', 3);

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('NOP2233', 'Hyundai', 'HB20', 2019, 'Cinza', 'Álcool', 4);

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('QRS4455', 'Ford', 'EcoSport', 2020, 'Branco', 'Diesel', 5);

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('TUV6677', 'Honda', 'Fit', 2016, 'Preto', 'Gasolina', 6);

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('WXY8899', 'Volkswagen', 'Polo', 2022, 'Prata', 'Elétrico', 7);

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('ZAB1234', 'Toyota', 'Yaris', 2019, 'Vermelho', 'Gasolina', 8);

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('CDE5678', 'Renault', 'Sandero', 2020, 'Branco', 'Álcool', 9);

INSERT INTO T_VEICULO (id_placa, nm_marca, nm_modelo, nr_ano, ds_cor, st_combustivel, id_cliente)
VALUES ('FGH9012', 'Peugeot', '208', 2021, 'Azul', 'Diesel', 10);


-- Inserção de Dados na Tabela T_OFICINA (10 registros)

INSERT INTO T_OFICINA (nr_cnpj, nm_oficina, ds_email)
VALUES ('12345678000101', 'Oficina São José', 'contato@saojoseoficina.com.br');

INSERT INTO T_OFICINA (nr_cnpj, nm_oficina, ds_email)
VALUES ('23456789000102', 'Mecânica Silva', 'atendimento@mecanicasilva.com');

INSERT INTO T_OFICINA (nr_cnpj, nm_oficina, ds_email)
VALUES ('34567890000103', 'Auto Center Central', 'contato@autocentercentral.com.br');

INSERT INTO T_OFICINA (nr_cnpj, nm_oficina, ds_email)
VALUES ('45678901000104', 'Oficina Rápida', 'suporte@oficinarapida.com.br');

INSERT INTO T_OFICINA (nr_cnpj, nm_oficina, ds_email)
VALUES ('56789012000105', 'Reparos e Cia', 'info@reparosecia.com.br');

INSERT INTO T_OFICINA (nr_cnpj, nm_oficina, ds_email)
VALUES ('67890123000106', 'CarFix', 'servico@carfix.com.br');

INSERT INTO T_OFICINA (nr_cnpj, nm_oficina, ds_email)
VALUES ('78901234000107', 'FastCar Serviços', 'contato@fastcarservicos.com.br');

INSERT INTO T_OFICINA (nr_cnpj, nm_oficina, ds_email)
VALUES ('89012345000108', 'Mecanicar', 'contato@mecanicar.com.br');

INSERT INTO T_OFICINA (nr_cnpj, nm_oficina, ds_email)
VALUES ('90123456000109', 'AutoPeças Plus', 'suporte@autopecasplus.com.br');

INSERT INTO T_OFICINA (nr_cnpj, nm_oficina, ds_email)
VALUES ('01234567000110', 'Oficina Super', 'super@oficinasuper.com.br');

-- Inserção de Dados na Tabela T_TELEFONE_OFICINA (20 registros)

INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1132114455', 'Comercial', 1);
INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1132115566', 'Comercial', 1);

INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1122343344', 'Comercial', 2);
INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1122344455', 'Comercial', 2);

INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1154326677', 'Comercial', 3);
INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1154327788', 'Comercial', 3);

INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1143565566', 'Comercial', 4);
INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1143566677', 'Comercial', 4);

INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1167897788', 'Comercial', 5);
INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1167898899', 'Comercial', 5);

INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1171238899', 'Comercial', 6);
INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1171239900', 'Comercial', 6);

INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1189879900', 'Comercial', 7);
INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1189870011', 'Comercial', 7);

INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1198760011', 'Comercial', 8);
INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1198761122', 'Comercial', 8);

INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1101231122', 'Comercial', 9);
INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1101232233', 'Comercial', 9);

INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1115552233', 'Comercial', 10);
INSERT INTO T_TELEFONE_OFICINA (nr_telefone, tp_telefone, id_oficina) VALUES ('1115553344', 'Comercial', 10);


-- Inserção de Dados na Tabela T_ENDERECO_OFICINA (10 registros)

INSERT INTO T_ENDERECO_OFICINA (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_oficina)
VALUES ('Rua Vergueiro', 1000, '01504001', 'Vila Mariana', 'São Paulo', 'SP', 1);

INSERT INTO T_ENDERECO_OFICINA (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_oficina)
VALUES ('Avenida Ipiranga', 200, '01046010', 'República', 'São Paulo', 'SP', 2);

INSERT INTO T_ENDERECO_OFICINA (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_oficina)
VALUES ('Rua Augusta', 1500, '01304001', 'Consolação', 'São Paulo', 'SP', 3);

INSERT INTO T_ENDERECO_OFICINA (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_oficina)
VALUES ('Avenida Paulista', 3000, '01310300', 'Bela Vista', 'São Paulo', 'SP', 4);

INSERT INTO T_ENDERECO_OFICINA (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_oficina)
VALUES ('Rua da Consolação', 500, '01302000', 'Consolação', 'São Paulo', 'SP', 5);

INSERT INTO T_ENDERECO_OFICINA (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_oficina)
VALUES ('Avenida Cândido de Abreu', 800, '80530000', 'Centro Cívico', 'Curitiba', 'PR', 6);

INSERT INTO T_ENDERECO_OFICINA (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_oficina)
VALUES ('Rua 3300', 234, '88330202', 'Centro', 'Balneário Camboriú', 'SC', 7);

INSERT INTO T_ENDERECO_OFICINA (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_oficina)
VALUES ('Avenida Amazonas', 567, '30180110', 'Centro', 'Belo Horizonte', 'MG', 8);

INSERT INTO T_ENDERECO_OFICINA (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_oficina)
VALUES ('Avenida das Américas', 3500, '22640102', 'Barra da Tijuca', 'Rio de Janeiro', 'RJ', 9);

INSERT INTO T_ENDERECO_OFICINA (ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_oficina)
VALUES ('Rua da Aurora', 150, '50050000', 'Boa Vista', 'Recife', 'PE', 10);


-- Inserção de Dados na Tabela T_FUNCIONARIO (50 registros)

INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('12345678901', 'Paulo Roberto Silva', 'M', 'Mecânico', 1);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('23456789012', 'Ana Paula Ferreira', 'F', 'Atendente', 1);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('34567890123', 'Carlos Eduardo Martins', 'M', 'Eletricista', 1);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('45678901234', 'Mariana Souza Costa', 'F', 'Gerente', 1);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('56789012345', 'Roberto Lima', 'M', 'Mecânico', 1);

INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('67890123456', 'Fernanda Oliveira', 'F', 'Atendente', 2);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('78901234567', 'Gustavo Machado', 'M', 'Eletricista', 2);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('89012345678', 'Juliana Pereira', 'F', 'Gerente', 2);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('90123456789', 'Lucas Almeida', 'M', 'Mecânico', 2);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('01234567890', 'Beatriz Ramos', 'F', 'Atendente', 2);

INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('11223344556', 'Thiago Santos', 'M', 'Supervisor', 3);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('22334455667', 'Marcela Dias', 'F', 'Atendente', 3);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('33445566778', 'Fabio Costa', 'M', 'Mecânico', 3);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('44556677889', 'Patricia Silva', 'F', 'Gerente', 3);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('55667788990', 'Renato Oliveira', 'M', 'Eletricista', 3);

INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('66778899001', 'Larissa Fernandes', 'F', 'Atendente', 4);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('77889900112', 'Marcelo Souza', 'M', 'Mecânico', 4);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('88990011223', 'Flavia Rocha', 'F', 'Gerente', 4);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('99001122334', 'Bruno Azevedo', 'M', 'Supervisor', 4);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('10112233445', 'Cintia Santos', 'F', 'Atendente', 4);

INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('11122334456', 'Felipe Andrade', 'M', 'Mecânico', 5);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('12233445567', 'Raquel Nogueira', 'F', 'Atendente', 5);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('13344556678', 'Adriana Melo', 'F', 'Gerente', 5);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('14455667789', 'Alex Teixeira', 'M', 'Supervisor', 5);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('15566778890', 'Cristina Leite', 'F', 'Atendente', 5);

INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('16677889901', 'Daniel Moura', 'M', 'Mecânico', 6);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('17788990012', 'Aline Pires', 'F', 'Atendente', 6);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('18899001123', 'João Lucas Mendes', 'M', 'Eletricista', 6);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('19900112234', 'Nathalia Costa', 'F', 'Gerente', 6);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('21112233445', 'Ricardo Ferreira', 'M', 'Supervisor', 6);

INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('22223344556', 'Tatiane Ramos', 'F', 'Atendente', 7);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('23334455667', 'Geraldo Lopes', 'M', 'Mecânico', 7);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('24445566778', 'Elaine Costa', 'F', 'Gerente', 7);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('25556677889', 'Fernando Matos', 'M', 'Eletricista', 7);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('26667788990', 'Silvana Figueiredo', 'F', 'Supervisor', 7);

INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('27778899001', 'Roberta Nunes', 'F', 'Atendente', 8);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('28889900112', 'Eduardo Mendes', 'M', 'Mecânico', 8);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('29990011223', 'Sandra Freitas', 'F', 'Gerente', 8);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('31101122334', 'Ivan Marques', 'M', 'Eletricista', 8);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('32212233445', 'Débora Souza', 'F', 'Atendente', 8);

INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('33323344556', 'Bruna Oliveira', 'F', 'Gerente', 9);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('34434455667', 'José Marcos', 'M', 'Supervisor', 9);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('35545566778', 'Ana Lúcia Moraes', 'F', 'Atendente', 9);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('36656677889', 'Rafael Borges', 'M', 'Eletricista', 9);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('37767788990', 'Camila Lima', 'F', 'Mecânico', 9);

INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('38878899001', 'Luiz Henrique', 'M', 'Supervisor', 10);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('39989900112', 'Carolina Ribeiro', 'F', 'Atendente', 10);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('41101122334', 'Hugo Ferreira', 'M', 'Eletricista', 10);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('42212233445', 'Viviane Costa', 'F', 'Gerente', 10);
INSERT INTO T_FUNCIONARIO (nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES ('43323344556', 'Anderson Silva', 'M', 'Mecânico', 10);

-- Inserção de dados na tabela T_AGENDAMENTO (40 registros)

INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-01-10', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-01-10 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 1);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-01-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-01-15 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), 2, 1);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-01-20', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-01-20 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 3, 2);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-01-22', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-01-22 08:30:00', 'YYYY-MM-DD HH24:MI:SS'), 4, 2);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-01-25', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-01-25 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), 5, 3);

INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-02-01', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-02-01 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 6, 3);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-02-05', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-02-05 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 7, 4);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-02-07', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-02-07 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), 8, 4);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-02-10', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-02-10 08:30:00', 'YYYY-MM-DD HH24:MI:SS'), 9, 5);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-02-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-02-15 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), 10, 5);

INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-02-20', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-02-20 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 6);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-02-25', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-02-25 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2, 6);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-03-01', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-03-01 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), 3, 7);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-03-05', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-03-05 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 4, 7);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-03-10', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-03-10 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), 5, 8);

INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-03-12', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-03-12 14:30:00', 'YYYY-MM-DD HH24:MI:SS'), 6, 8);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-03-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-03-15 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), 7, 9);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-03-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-03-18 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 8, 9);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-03-20', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-03-20 08:30:00', 'YYYY-MM-DD HH24:MI:SS'), 9, 10);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-03-25', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-03-25 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 10, 10);

INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-04-01', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-04-01 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 1);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-04-03', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-04-03 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), 2, 2);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-04-07', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-04-07 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 3, 3);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-04-10', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-04-10 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), 4, 4);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-04-12', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-04-12 08:30:00', 'YYYY-MM-DD HH24:MI:SS'), 5, 5);

INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-04-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-04-15 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 6, 6);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-04-17', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-04-17 12:30:00', 'YYYY-MM-DD HH24:MI:SS'), 7, 7);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-04-20', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-04-20 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 8, 8);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-04-22', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-04-22 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), 9, 9);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-04-25', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-04-25 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), 10, 10);

INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-05-01', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-05-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 1);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-05-03', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-05-03 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2, 2);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-05-05', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-05-05 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 3, 3);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-05-07', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-05-07 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 4, 4);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-05-09', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-05-09 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), 5, 5);

INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-05-11', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-05-11 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 6, 6);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-05-13', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-05-13 08:30:00', 'YYYY-MM-DD HH24:MI:SS'), 7, 7);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-05-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-05-15 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), 8, 8);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-05-17', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-05-17 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 9, 9);
INSERT INTO T_AGENDAMENTO (dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (TO_DATE('2024-05-19', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-05-19 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), 10, 10);


-- Inserção de Dados na Tabela T_ORCAMENTO (40 registros)

INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (1500.00, 'Em Análise', 1, 1);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2500.00, 'Aprovado', 2, 1);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (3000.00, 'Finalizado', 3, 2);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (1200.00, 'Cancelado', 4, 2);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (1800.00, 'Em Análise', 5, 2);

INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2200.00, 'Aprovado', 6, 3);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2900.00, 'Finalizado', 7, 3);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (1600.00, 'Cancelado', 8, 4);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2000.00, 'Em Análise', 9, 4);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2300.00, 'Aprovado', 10, 5);

INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2750.00, 'Finalizado', 11, 5);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (1450.00, 'Cancelado', 12, 6);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (3100.00, 'Em Análise', 13, 6);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (1250.00, 'Aprovado', 14, 7);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2750.00, 'Finalizado', 15, 7);

INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (1600.00, 'Cancelado', 16, 8);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2050.00, 'Em Análise', 17, 8);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2950.00, 'Aprovado', 18, 9);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (1800.00, 'Finalizado', 19, 9);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (1500.00, 'Cancelado', 20, 10);

INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2400.00, 'Em Análise', 1, 10);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2750.00, 'Aprovado', 2, 1);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (3300.00, 'Finalizado', 3, 1);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (1950.00, 'Cancelado', 4, 2);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2200.00, 'Em Análise', 5, 2);

INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (1850.00, 'Aprovado', 6, 3);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2750.00, 'Finalizado', 7, 3);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (3400.00, 'Cancelado', 8, 4);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2100.00, 'Em Análise', 9, 4);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2900.00, 'Aprovado', 10, 5);

INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (3150.00, 'Finalizado', 11, 5);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (1950.00, 'Cancelado', 12, 6);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2550.00, 'Em Análise', 13, 6);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2650.00, 'Aprovado', 14, 7);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (3550.00, 'Finalizado', 15, 7);

INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2150.00, 'Cancelado', 16, 8);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2950.00, 'Em Análise', 17, 8);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2850.00, 'Aprovado', 18, 9);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2650.00, 'Finalizado', 19, 9);
INSERT INTO T_ORCAMENTO (vl_orcamento, st_situacao, id_veiculo, id_oficina) VALUES (2950.00, 'Cancelado', 20, 10);


-- Inserção de Dados na Tabela T_SERVICOS (40 registros)

INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Troca de Óleo', 'Substituição de óleo e filtro de óleo', TO_TIMESTAMP('01:00:00', 'HH24:MI:SS'), 150.00, 1);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Balanceamento', 'Balanceamento de rodas', TO_TIMESTAMP('00:30:00', 'HH24:MI:SS'), 90.00, 2);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Alinhamento', 'Alinhamento da suspensão dianteira', TO_TIMESTAMP('00:45:00', 'HH24:MI:SS'), 120.00, 3);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Troca de Pneu', 'Substituição dos pneus traseiros', TO_TIMESTAMP('01:00:00', 'HH24:MI:SS'), 320.00, 4);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Revisão Geral', 'Revisão completa do veículo', TO_TIMESTAMP('02:30:00', 'HH24:MI:SS'), 650.00, 5);

INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Freios', 'Substituição de pastilhas de freio traseiras', TO_TIMESTAMP('01:15:00', 'HH24:MI:SS'), 180.00, 6);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Suspensão', 'Reparo da suspensão traseira', TO_TIMESTAMP('01:45:00', 'HH24:MI:SS'), 400.00, 7);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Troca de Bateria', 'Substituição da bateria do veículo', TO_TIMESTAMP('00:20:00', 'HH24:MI:SS'), 220.00, 8);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Pintura Parcial', 'Pintura do para-lama direito', TO_TIMESTAMP('02:30:00', 'HH24:MI:SS'), 900.00, 9);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Escapamento', 'Reparo do sistema de escapamento', TO_TIMESTAMP('01:30:00', 'HH24:MI:SS'), 350.00, 10);

INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Revisão Elétrica', 'Revisão completa do sistema elétrico', TO_TIMESTAMP('02:00:00', 'HH24:MI:SS'), 500.00, 11);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Troca de Velas', 'Substituição de velas de ignição', TO_TIMESTAMP('00:30:00', 'HH24:MI:SS'), 100.00, 12);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Filtro de Ar', 'Troca de filtro de ar', TO_TIMESTAMP('00:20:00', 'HH24:MI:SS'), 80.00, 13);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Troca de Correia', 'Substituição de correia dentada', TO_TIMESTAMP('01:30:00', 'HH24:MI:SS'), 400.00, 14);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Ar Condicionado', 'Recarga do sistema de ar condicionado', TO_TIMESTAMP('00:45:00', 'HH24:MI:SS'), 200.00, 15);

INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Limpeza de Motor', 'Limpeza completa do motor', TO_TIMESTAMP('01:00:00', 'HH24:MI:SS'), 150.00, 16);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Revisão de Suspensão', 'Revisão da suspensão dianteira e traseira', TO_TIMESTAMP('02:30:00', 'HH24:MI:SS'), 450.00, 17);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Troca de Amortecedores', 'Substituição dos amortecedores traseiros', TO_TIMESTAMP('02:00:00', 'HH24:MI:SS'), 400.00, 18);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Faróis', 'Substituição das lâmpadas de farol', TO_TIMESTAMP('00:40:00', 'HH24:MI:SS'), 90.00, 19);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Luzes Traseiras', 'Troca das lâmpadas das luzes traseiras', TO_TIMESTAMP('00:25:00', 'HH24:MI:SS'), 70.00, 20);

INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Instalação de Som', 'Instalação de som automotivo', TO_TIMESTAMP('01:30:00', 'HH24:MI:SS'), 500.00, 21);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Troca de Vidro', 'Substituição do para-brisa', TO_TIMESTAMP('02:30:00', 'HH24:MI:SS'), 750.00, 22);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Troca de Filtro de Combustível', 'Substituição de filtro de combustível', TO_TIMESTAMP('00:30:00', 'HH24:MI:SS'), 110.00, 23);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Limpeza de Interior', 'Limpeza completa do interior do veículo', TO_TIMESTAMP('01:30:00', 'HH24:MI:SS'), 180.00, 24);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Pintura Total', 'Pintura completa do veículo', TO_TIMESTAMP('12:00:00', 'HH24:MI:SS'), 3000.00, 25);

INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Troca de Radiador', 'Substituição do radiador', TO_TIMESTAMP('02:00:00', 'HH24:MI:SS'), 800.00, 26);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Reparo no Para-choque', 'Reparo e pintura do para-choque traseiro', TO_TIMESTAMP('02:30:00', 'HH24:MI:SS'), 600.00, 27);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Troca de Palhetas', 'Substituição das palhetas do para-brisa', TO_TIMESTAMP('00:15:00', 'HH24:MI:SS'), 50.00, 28);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Revisão Completa', 'Revisão de todos os sistemas do veículo', TO_TIMESTAMP('06:00:00', 'HH24:MI:SS'), 1000.00, 29);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Reparo de Parabrisa', 'Reparo de trinca no para-brisa', TO_TIMESTAMP('01:00:00', 'HH24:MI:SS'), 120.00, 30);

INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Revisão de Faróis', 'Ajuste dos faróis', TO_TIMESTAMP('00:30:00', 'HH24:MI:SS'), 75.00, 31);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Substituição de Motor', 'Instalação de motor novo', TO_TIMESTAMP('16:00:00', 'HH24:MI:SS'), 4000.00, 32);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Limpeza de Para-lamas', 'Limpeza e polimento de para-lamas', TO_TIMESTAMP('01:00:00', 'HH24:MI:SS'), 150.00, 33);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Reparação de Airbag', 'Reparo no sistema de airbag', TO_TIMESTAMP('03:00:00', 'HH24:MI:SS'), 800.00, 34);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Troca de Buzina', 'Instalação de nova buzina', TO_TIMESTAMP('00:20:00', 'HH24:MI:SS'), 70.00, 35);

INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Revisão do Sistema de Freio ABS', 'Verificação e ajuste do sistema ABS', TO_TIMESTAMP('02:00:00', 'HH24:MI:SS'), 550.00, 36);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Polimento Completo', 'Polimento da lataria', TO_TIMESTAMP('03:00:00', 'HH24:MI:SS'), 600.00, 37);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Instalação de Acessórios', 'Instalação de acessórios externos', TO_TIMESTAMP('01:30:00', 'HH24:MI:SS'), 250.00, 38);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Reparo em Rodas', 'Restauração de rodas danificadas', TO_TIMESTAMP('02:00:00', 'HH24:MI:SS'), 320.00, 39);
INSERT INTO T_SERVICOS (st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES ('Troca de Sensor de Estacionamento', 'Substituição do sensor de estacionamento', TO_TIMESTAMP('00:40:00', 'HH24:MI:SS'), 180.00, 40);

-- Inserção de Dados na Tabela T_FALHAS (60 registros)

INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Falha no motor', 'Substituição de peças internas', 1, 1, 'Alta');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Desgaste nos freios', 'Troca de pastilhas de freio', 2, 2, 'Média');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Suspensão comprometida', 'Substituição de amortecedores', 3, 3, 'Alta');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Pneu furado', 'Substituição de pneu', 4, 4, 'Baixa');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Bateria descarregada', 'Instalação de nova bateria', 5, 5, 'Média');

INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Vazamento de óleo', 'Troca de juntas', 6, 6, 'Alta');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Farol queimado', 'Substituição de lâmpadas', 7, 7, 'Baixa');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Problema na embreagem', 'Ajuste e substituição de peças', 8, 8, 'Média');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Câmbio defeituoso', 'Reparação do sistema de câmbio', 9, 9, 'Alta');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Alinhamento fora de especificação', 'Realização de alinhamento', 10, 10, 'Baixa');

INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Sistema de freio ABS com falha', 'Correção no sistema ABS', 11, 11, 'Alta');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Problema no ar-condicionado', 'Troca do compressor', 12, 12, 'Média');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Correia dentada desgastada', 'Substituição da correia dentada', 13, 13, 'Alta');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Vidro trincado', 'Substituição do vidro', 14, 14, 'Baixa');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Excesso de poluentes no escapamento', 'Limpeza do catalisador', 15, 15, 'Média');

INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Escapamento furado', 'Reparo do escapamento', 16, 16, 'Média');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Sistema de som não funciona', 'Reparo no sistema elétrico', 17, 17, 'Baixa');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Travas elétricas falhando', 'Substituição de fusíveis', 18, 18, 'Baixa');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Motor superaquecendo', 'Verificação do radiador', 19, 19, 'Alta');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Luzes internas não acendem', 'Substituição de lâmpadas internas', 20, 20, 'Baixa');

INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Defeito na buzina', 'Troca da buzina', 1, 1, 'Baixa');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Avaria nos limpadores de para-brisa', 'Troca das palhetas', 2, 2, 'Baixa');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Problema no sistema de direção hidráulica', 'Reparo da bomba de direção', 3, 3, 'Alta');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Tranco na transmissão automática', 'Troca de fluidos', 4, 4, 'Média');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Ar quente não funciona', 'Reparo do sistema de aquecimento', 5, 5, 'Baixa');

INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Vazamento de fluido de freio', 'Substituição do cilindro mestre', 6, 6, 'Alta');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Painel com falhas elétricas', 'Reparo no chicote elétrico', 7, 7, 'Média');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Sensor de estacionamento inoperante', 'Troca do sensor', 8, 8, 'Baixa');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Perda de potência', 'Ajuste na injeção eletrônica', 9, 9, 'Alta');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Ruído no motor', 'Ajuste nas válvulas', 10, 10, 'Média');

INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Ar-condicionado com vazamento', 'Substituição de mangueiras', 11, 11, 'Média');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Amortecedor quebrado', 'Substituição do amortecedor traseiro', 12, 12, 'Alta');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Escapamento soltando fumaça', 'Limpeza do filtro de partículas', 13, 13, 'Alta');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Painel com falha no velocímetro', 'Troca do cabo do velocímetro', 14, 14, 'Média');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Infiltração no porta-malas', 'Vedação do porta-malas', 15, 15, 'Baixa');

INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Vidro elétrico não funciona', 'Troca do motor do vidro', 16, 16, 'Média');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Marcha lenta irregular', 'Ajuste do sistema de injeção', 17, 17, 'Média');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Vibração no volante', 'Balanceamento das rodas dianteiras', 18, 18, 'Baixa');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Barulho no sistema de exaustão', 'Fixação do escapamento', 19, 19, 'Baixa');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Aquecimento excessivo', 'Troca do termostato', 20, 20, 'Alta');

INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Mangueira de combustível vazando', 'Substituição da mangueira', 1, 1, 'Alta');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Capô com problemas para abrir', 'Lubrificação do cabo do capô', 2, 2, 'Baixa');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Farol de milha queimado', 'Troca do farol de milha', 3, 3, 'Baixa');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Rangido ao frear', 'Verificação dos discos de freio', 4, 4, 'Média');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Retrovisor não ajusta', 'Reparo no sistema de ajuste', 5, 5, 'Baixa');

INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Pintura descascando', 'Reparação da pintura', 6, 6, 'Baixa');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Somente ventilação, sem ar', 'Troca do compressor de ar', 7, 7, 'Média');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Vazamento no tanque', 'Reparo do tanque de combustível', 8, 8, 'Alta');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Porta não abre por dentro', 'Ajuste do mecanismo interno', 9, 9, 'Baixa');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Câmera de ré inoperante', 'Verificação do cabo', 10, 10, 'Média');

INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Avaria na lanterna traseira', 'Troca da lanterna', 11, 11, 'Baixa');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Problema no limpador traseiro', 'Substituição do motor', 12, 12, 'Baixa');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Cinto de segurança travando', 'Troca do cinto de segurança', 13, 13, 'Alta');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Ruído no rolamento', 'Substituição do rolamento dianteiro', 14, 14, 'Média');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Tremor no câmbio manual', 'Troca do suporte de câmbio', 15, 15, 'Média');

INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Freio de mão frouxo', 'Ajuste do cabo', 16, 16, 'Baixa');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Falta de potência em aclives', 'Verificação de filtro de combustível', 17, 17, 'Média');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Retrovisor eletrônico inoperante', 'Troca do motor do retrovisor', 18, 18, 'Baixa');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Pintura com manchas', 'Polimento da pintura', 19, 19, 'Baixa');
INSERT INTO T_FALHAS (ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES ('Sistema de tração com falha', 'Reparo na caixa de transferência', 20, 20, 'Alta');


-- Inserção de Dados na Tabela T_PECAS (40 registros)

INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Bosch', 2, 250.00, 'Pastilhas de freio dianteiras', 1, 1);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Monroe', 4, 120.00, 'Amortecedores traseiros', 2, 2);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Pirelli', 4, 600.00, 'Pneus radiais', 3, 3);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('NGK', 4, 100.00, 'Velas de ignição', 4, 4);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Hella', 1, 300.00, 'Farol de milha', 5, 5);

INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Bosch', 1, 350.00, 'Bateria 60Ah', 6, 6);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Valeo', 2, 80.00, 'Palhetas do para-brisa', 7, 7);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Fiat', 1, 50.00, 'Filtro de óleo', 8, 8);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Fram', 1, 35.00, 'Filtro de ar', 9, 9);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Mann', 1, 40.00, 'Filtro de combustível', 10, 10);

INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Shell', 5, 150.00, 'Óleo para motor 5W30', 11, 11);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Magneti Marelli', 1, 200.00, 'Bobina de ignição', 12, 12);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Continental', 1, 100.00, 'Correia dentada', 13, 13);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Goodyear', 4, 450.00, 'Pneus de alta performance', 14, 14);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Philips', 2, 60.00, 'Lâmpadas de farol', 15, 15);

INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Akebono', 1, 120.00, 'Disco de freio dianteiro', 16, 16);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Fiamm', 1, 280.00, 'Bateria 75Ah', 17, 17);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('3M', 1, 50.00, 'Líquido para radiador', 18, 18);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Pioneer', 1, 300.00, 'Alto-falante', 19, 19);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Sachs', 1, 200.00, 'Amortecedor dianteiro', 20, 20);

INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('TRW', 1, 150.00, 'Pastilha de freio traseira', 21, 21);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Wega', 1, 30.00, 'Filtro de cabine', 22, 22);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('K&N', 1, 250.00, 'Filtro de ar esportivo', 23, 23);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Osram', 2, 90.00, 'Lâmpada LED para faróis', 24, 24);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('NGK', 4, 110.00, 'Velas de ignição esportivas', 25, 25);

INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Delphi', 2, 80.00, 'Injetores de combustível', 26, 26);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Bridgestone', 4, 620.00, 'Pneus radiais reforçados', 27, 27);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Cofap', 1, 180.00, 'Amortecedor traseiro', 28, 28);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Philips', 2, 45.00, 'Lâmpadas halógenas', 29, 29);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Denso', 1, 150.00, 'Radiador de ar-condicionado', 30, 30);

INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Bosch', 1, 250.00, 'Centralina de ignição', 31, 31);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Mann', 1, 40.00, 'Filtro de óleo premium', 32, 32);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Brembo', 1, 450.00, 'Kit de freio completo', 33, 33);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('NGK', 1, 150.00, 'Kit de velas', 34, 34);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Valeo', 1, 100.00, 'Palhetas traseiras', 35, 35);

INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Cofap', 2, 200.00, 'Suporte do motor', 36, 36);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Fiat', 2, 140.00, 'Junta do cabeçote', 37, 37);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Volkswagen', 1, 100.00, 'Suporte do cárter', 38, 38);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Renault', 1, 320.00, 'Sistema de injeção eletrônica', 39, 39);
INSERT INTO T_PECAS (nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES ('Denso', 1, 200.00, 'Radiador', 40, 40);

-- Inserção de Dados na Tabela T_ORDEM_DE_SERVICO (20 registros)

-- Orçamentos Finalizados

INSERT INTO T_ORDEM_DE_SERVICO (dt_inicio, dt_fim, hr_inicio, hr_fim, st_status, id_orcamento, id_funcionario, id_veiculo)
VALUES (TO_DATE('2024-01-10', 'YYYY-MM-DD'), TO_DATE('2024-01-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-01-10 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-01-15 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Concluído', 3, 1, 3);

INSERT INTO T_ORDEM_DE_SERVICO (dt_inicio, dt_fim, hr_inicio, hr_fim, st_status, id_orcamento, id_funcionario, id_veiculo)
VALUES (TO_DATE('2024-02-01', 'YYYY-MM-DD'), TO_DATE('2024-02-05', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-02-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-02-05 15:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Concluído', 7, 2, 7);

INSERT INTO T_ORDEM_DE_SERVICO (dt_inicio, dt_fim, hr_inicio, hr_fim, st_status, id_orcamento, id_funcionario, id_veiculo)
VALUES (TO_DATE('2024-03-10', 'YYYY-MM-DD'), TO_DATE('2024-03-14', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-03-10 08:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-03-14 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Concluído', 11, 3, 11);

INSERT INTO T_ORDEM_DE_SERVICO (dt_inicio, dt_fim, hr_inicio, hr_fim, st_status, id_orcamento, id_funcionario, id_veiculo)
VALUES (TO_DATE('2024-04-05', 'YYYY-MM-DD'), TO_DATE('2024-04-08', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-04-05 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-04-08 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Concluído', 15, 4, 15);

INSERT INTO T_ORDEM_DE_SERVICO (dt_inicio, dt_fim, hr_inicio, hr_fim, st_status, id_orcamento, id_funcionario, id_veiculo)
VALUES (TO_DATE('2024-05-10', 'YYYY-MM-DD'), TO_DATE('2024-05-12', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-05-10 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-05-12 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Concluído', 19, 5, 19);

INSERT INTO T_ORDEM_DE_SERVICO (dt_inicio, dt_fim, hr_inicio, hr_fim, st_status, id_orcamento, id_funcionario, id_veiculo)
VALUES (TO_DATE('2024-06-01', 'YYYY-MM-DD'), TO_DATE('2024-06-05', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-06-01 08:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-06-05 17:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'Concluído', 20, 6, 20);

INSERT INTO T_ORDEM_DE_SERVICO (dt_inicio, dt_fim, hr_inicio, hr_fim, st_status, id_orcamento, id_funcionario, id_veiculo)
VALUES (TO_DATE('2024-06-12', 'YYYY-MM-DD'), TO_DATE('2024-06-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-06-12 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-06-15 16:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'Concluído', 9, 7, 9);

INSERT INTO T_ORDEM_DE_SERVICO (dt_inicio, dt_fim, hr_inicio, hr_fim, st_status, id_orcamento, id_funcionario, id_veiculo)
VALUES (TO_DATE('2024-07-08', 'YYYY-MM-DD'), TO_DATE('2024-07-10', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-07-08 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-07-10 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Concluído', 13, 8, 13);

INSERT INTO T_ORDEM_DE_SERVICO (dt_inicio, dt_fim, hr_inicio, hr_fim, st_status, id_orcamento, id_funcionario, id_veiculo)
VALUES (TO_DATE('2024-08-01', 'YYYY-MM-DD'), TO_DATE('2024-08-04', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-08-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-08-04 15:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Concluído', 17, 9, 17);

INSERT INTO T_ORDEM_DE_SERVICO (dt_inicio, dt_fim, hr_inicio, hr_fim, st_status, id_orcamento, id_funcionario, id_veiculo)
VALUES (TO_DATE('2024-08-15', 'YYYY-MM-DD'), TO_DATE('2024-08-20', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-08-15 07:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-08-20 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Concluído', 4, 10, 4);

-- Orçamentos Aprovados

INSERT INTO T_ORDEM_DE_SERVICO (dt_inicio, dt_fim, hr_inicio, hr_fim, st_status, id_orcamento, id_funcionario, id_veiculo)
VALUES (TO_DATE('2024-09-12', 'YYYY-MM-DD'), TO_DATE('2024-09-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-09-12 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-09-15 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Aprovado', 2, 11, 2);

INSERT INTO T_ORDEM_DE_SERVICO (dt_inicio, dt_fim, hr_inicio, hr_fim, st_status, id_orcamento, id_funcionario, id_veiculo)
VALUES (TO_DATE('2024-10-01', 'YYYY-MM-DD'), TO_DATE('2024-10-03', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-10-01 08:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-10-03 16:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'Aprovado', 6, 12, 6);

INSERT INTO T_ORDEM_DE_SERVICO (dt_inicio, dt_fim, hr_inicio, hr_fim, st_status, id_orcamento, id_funcionario, id_veiculo)
VALUES (TO_DATE('2024-10-15', 'YYYY-MM-DD'), TO_DATE('2024-10-20', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-10-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-10-20 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Aprovado', 10, 13, 10);

INSERT INTO T_ORDEM_DE_SERVICO (dt_inicio, dt_fim, hr_inicio, hr_fim, st_status, id_orcamento, id_funcionario, id_veiculo)
VALUES (TO_DATE('2024-11-02', 'YYYY-MM-DD'), TO_DATE('2024-11-07', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-11-02 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-11-07 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Aprovado', 14, 14, 14);

INSERT INTO T_ORDEM_DE_SERVICO (dt_inicio, dt_fim, hr_inicio, hr_fim, st_status, id_orcamento, id_funcionario, id_veiculo)
VALUES (TO_DATE('2024-11-15', 'YYYY-MM-DD'), TO_DATE('2024-11-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-11-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-11-18 16:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'Aprovado', 18, 15, 18);

INSERT INTO T_ORDEM_DE_SERVICO (dt_inicio, dt_fim, hr_inicio, hr_fim, st_status, id_orcamento, id_funcionario, id_veiculo)
VALUES (TO_DATE('2024-12-01', 'YYYY-MM-DD'), TO_DATE('2024-12-03', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-12-01 08:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-12-03 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Aprovado', 12, 16, 12);

INSERT INTO T_ORDEM_DE_SERVICO (dt_inicio, dt_fim, hr_inicio, hr_fim, st_status, id_orcamento, id_funcionario, id_veiculo)
VALUES (TO_DATE('2024-12-10', 'YYYY-MM-DD'), TO_DATE('2024-12-12', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-12-10 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-12-12 17:30:00', 'YYYY-MM-DD HH24:MI:SS'), 'Aprovado', 16, 17, 16);

INSERT INTO T_ORDEM_DE_SERVICO (dt_inicio, dt_fim, hr_inicio, hr_fim, st_status, id_orcamento, id_funcionario, id_veiculo)
VALUES (TO_DATE('2024-12-20', 'YYYY-MM-DD'), TO_DATE('2024-12-22', 'YYYY-MM-DD'), TO_TIMESTAMP('2024-12-20 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-12-22 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Aprovado', 20, 18, 20);

-- Inserção de Dados na Tabela T_METODO_PAGAMENTO com (20 registros)

INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Cartão de Crédito', 'Crédito', 5.00, 1);
INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Boleto Bancário', 'À Vista', 10.00, 2);
INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Pix', 'Imediato', 15.00, 3);
INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Cartão de Débito', 'Débito', 5.00, 4);
INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Dinheiro', 'À Vista', 0.00, 5);

INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Cartão de Crédito', 'Parcelado', 5.00, 6);
INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Boleto Bancário', 'À Vista', 10.00, 7);
INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Transferência Bancária', 'Imediato', 7.50, 8);
INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Cartão de Débito', 'Débito', 5.00, 9);
INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Pix', 'Imediato', 20.00, 10);

INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Dinheiro', 'À Vista', 0.00, 11);
INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Cartão de Crédito', 'Crédito', 5.00, 12);
INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Boleto Bancário', 'À Vista', 10.00, 13);
INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Pix', 'Imediato', 15.00, 14);
INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Cartão de Débito', 'Débito', 5.00, 15);

INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Transferência Bancária', 'Imediato', 7.50, 16);
INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Dinheiro', 'À Vista', 0.00, 17);
INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Cartão de Crédito', 'Parcelado', 5.00, 18);
INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Pix', 'Imediato', 10.00, 19);
INSERT INTO T_METODO_PAGAMENTO (st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES ('Boleto Bancário', 'À Vista', 8.00, 20);


-- ==============================
-- 3. Consultas para Relatórios (DQL)
-- ==============================

-- Relatórios com Classificação de Dados

-- 1. Listar todos os clientes ordenados por nome em ordem alfabética

SELECT * FROM T_CLIENTE
ORDER BY nm_cliente ASC;

-- 2. Listar todos os veículos ordenados por ano em ordem decrescente
SELECT * FROM T_VEICULO
ORDER BY nr_ano DESC;


-- Relatórios utilizando Funções Numéricas Simples

-- 1. Calcular o valor total dos orçamentos acima de R$ 1000
SELECT id_orcamento, vl_orcamento,
       vl_orcamento * 1.10 AS valor_com_acrescimo
FROM T_ORCAMENTO
WHERE vl_orcamento > 1000;

-- 2. Mostrar o custo dos serviços e aplicar um desconto de 5% para todos os serviços acima de R$ 500

SELECT id_servico, ds_servico, vl_custo,
       vl_custo * 0.95 AS valor_com_desconto
FROM T_SERVICOS
WHERE vl_custo > 500;


-- Relatórios utilizando Funções de Grupo

-- 1. Contar o número de clientes por sexo

SELECT sx_sexo, COUNT(*) AS total_clientes
FROM T_CLIENTE
GROUP BY sx_sexo;

-- 2. Contar o número de veículos por tipo de combustível

SELECT st_combustivel, COUNT(*) AS total_veiculos
FROM T_VEICULO
GROUP BY st_combustivel;

-- Relatórios com Subconsulta

-- 1. Listar clientes que possuem veículos cadastrados

SELECT * FROM T_CLIENTE
WHERE id_cliente IN (SELECT DISTINCT id_cliente FROM T_VEICULO);

-- 2. Listar oficinas que possuem funcionários cadastrados

SELECT * FROM T_OFICINA
WHERE id_oficina IN (SELECT DISTINCT id_oficina FROM T_FUNCIONARIO);

-- Relatórios utilizando Junção de Tabelas

-- 1. Listar todos os veículos e os dados dos seus respectivos proprietários (clientes)

SELECT v.id_veiculo, v.nm_marca, v.nm_modelo, c.nm_cliente, c.ds_email
FROM T_VEICULO v
         JOIN T_CLIENTE c ON v.id_cliente = c.id_cliente;

-- 2. Listar ordens de serviço com os dados do orçamento e do funcionário responsável

SELECT os.id_os, os.dt_inicio, os.dt_fim, o.vl_orcamento, f.nm_funcionario
FROM T_ORDEM_DE_SERVICO os
         JOIN T_ORCAMENTO o ON os.id_orcamento = o.id_orcamento
         JOIN T_FUNCIONARIO f ON os.id_funcionario = f.id_funcionario;





