-- Criação do Banco de Dados
CREATE DATABASE IF NOT EXISTS db_hospital;

-- Uso do Banco de Dados
USE db_hospital;

-- Tabela Usuario
DROP TABLE IF EXISTS tb_usuarios;
CREATE TABLE tb_usuarios (
    usuario_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    dataNascimento DATE NOT NULL,
    genero VARCHAR(10),
    telefone VARCHAR(20),
    cep VARCHAR(20),
    senha VARCHAR(255) NOT NULL,
    fl_medico BOOLEAN DEFAULT FALSE,
    admin BOOLEAN DEFAULT FALSE,
    tipo VARCHAR(10) NOT NULL 
);

-- Tabela Paciente
DROP TABLE IF EXISTS tb_pacientes;
CREATE TABLE tb_pacientes (
    pacientes_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT UNIQUE,
    FOREIGN KEY (usuario_id) REFERENCES tb_usuarios(usuario_id) ON DELETE CASCADE
);

-- Tabela Medico
DROP TABLE IF EXISTS tb_medicos;
CREATE TABLE tb_medicos (
    medico_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT UNIQUE,
    FOREIGN KEY (usuario_id) REFERENCES tb_usuarios(usuario_id) ON DELETE CASCADE,
    cpf VARCHAR(11) UNIQUE,
    crm VARCHAR(20) UNIQUE,
    especialidade VARCHAR(50),
    consulta_medica BOOLEAN,
    exam_medico BOOLEAN
);

-- Tabela Exame
DROP TABLE IF EXISTS tb_exames;
CREATE TABLE tb_exames (
    exame_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(100) NOT NULL,
    paciente_id BIGINT,
    FOREIGN KEY (paciente_id) REFERENCES tb_pacientes(pacientes_id) ON DELETE CASCADE,
    medico_id BIGINT,
    FOREIGN KEY (medico_id) REFERENCES tb_medicos(medico_id),
    data DATE NOT NULL,
    hora TIME NOT NULL,
    especialidade VARCHAR(100) NOT NULL
);

-- Tabela Tipo de Exames
DROP TABLE IF EXISTS tb_tipo_exames;
CREATE TABLE tb_tipo_exames (
    tipo_exame_id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(70) UNIQUE NOT NULL,
    especialidade VARCHAR(50) NOT NULL
);

-- Tabela Consulta
DROP TABLE IF EXISTS tb_consultas;
CREATE TABLE tb_consultas (
    consulta_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT,
    FOREIGN KEY (paciente_id) REFERENCES tb_pacientes(pacientes_id) ON DELETE CASCADE,
    medico_id BIGINT,
    FOREIGN KEY (medico_id) REFERENCES tb_medicos(medico_id),
    data DATE NOT NULL,
    hora TIME NOT NULL,
    especialidade VARCHAR(100) NOT NULL
);

-- Trigger para inserir automaticamente um usuário como paciente ou médico
DELIMITER //

CREATE TRIGGER after_usuario_insert
AFTER INSERT ON tb_usuarios
FOR EACH ROW
BEGIN
    IF NEW.fl_medico THEN
        INSERT INTO tb_medicos (usuario_id, cpf) VALUES (NEW.usuario_id, NEW.cpf);
    ELSE
        INSERT INTO tb_pacientes (usuario_id) VALUES (NEW.usuario_id);
    END IF;
END //

DELIMITER ;


-- Inserção de tipos de exames
INSERT INTO tb_tipo_exames (nome, especialidade)
VALUES ('Exame de sangue', 'Hematologista'),
		('Eletrocardiograma', 'Cardiologista'),
		('Raio - X', 'Radiologista'),
        ('Ressonância magnética', 'Radiologista');

		


-- Inserção de um médico como exemplo
INSERT INTO tb_usuarios (cpf, nome, email, dataNascimento, genero, telefone, cep, senha, fl_medico, tipo) 
VALUES ('14752365992','Dr. Henrique', 'henrique@gmail.com', '1990-07-28', 'H', '11999999999', '12345678', 'senha123', TRUE, 'medico'),
('14752361272','Dr. Marcio', 'marcioa@gmail.com', '1990-07-28', 'H', '11999999999', '12345678', 'senha123', TRUE, 'medico'),
('12345678901', 'Dr. José', 'jose@gmail.com', '1970-07-20', 'H', '11999999999', '12345678', 'senha123', TRUE, 'medico'),
('14752365842','Dra. Adriana', 'adriana@gmail.com', '1990-07-28', 'M', '11999999999', '12345678', 'senha123', TRUE, 'medico'),
('14752365123','Dra. Bruna', 'brunaa@gmail.com', '1990-07-28', 'M', '11999999999', '12345678', 'senha123', TRUE, 'medico'),
('14752333214','Dr. Eduardo', 'eduardo@gmail.com', '1990-07-28', 'M', '11999999999', '12345678', 'senha123', TRUE, 'medico');


-- Update nas informações do médico
UPDATE tb_medicos
SET 
    crm = '29898',
    especialidade = 'Hematologista',
    consulta_medica = FALSE,
    exam_medico = TRUE
WHERE cpf = '12345678901';
UPDATE tb_medicos
SET 
    crm = '74125',
    especialidade = 'Hematologista',
    consulta_medica = FALSE,
    exam_medico = TRUE
WHERE cpf = '14752365842';
UPDATE tb_medicos
SET 
    crm = '74335',
    especialidade = 'Cardiologista',
    consulta_medica = FALSE,
    exam_medico = TRUE
WHERE cpf = '14752365123';

UPDATE tb_medicos
SET 
    crm = '44471',
    especialidade = 'Radiologista',
    consulta_medica = FALSE,
    exam_medico = TRUE
WHERE cpf = '14752365992';

UPDATE tb_medicos
SET 
    crm = '70345',
    especialidade = 'Radiologista',
    consulta_medica = FALSE,
    exam_medico = TRUE
WHERE cpf = '14752333214';

-- Inserção de um usuário administrador como exemplo
INSERT INTO tb_usuarios (cpf, nome, email, dataNascimento, genero, telefone, cep, senha, admin, tipo) 
VALUES ('12345678554', 'root', 'root@gmail.com', '2000-01-01','M', '11969363562', '12345678', 'root', TRUE, 'admin');


-- Inserção de um paciente como exemplo
INSERT INTO tb_usuarios (cpf, nome, email, dataNascimento, genero, telefone, cep, senha, tipo) 
VALUES ('65656565578', 'Marcos Castro', 'Marcos@gmail.com', '1996-05-04', 'M', '11999999999', '85487878', 'senha1234', 'paciente');


SELECT 
 A.* 
FROM tb_usuarios A