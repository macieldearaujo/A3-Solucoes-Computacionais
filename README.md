# A3---Solucoes-Computacionais

O <strong>Ulife Health</strong> é uma aplicação desenvolvida para gerenciar informações relacionadas a pacientes, médicos, consultas e exames em um ambiente hospitalar. A aplicação permite o cadastro, atualização, exclusão e visualização de dados de usuários, pacientes e médicos, além de facilitar o agendamento de consultas e exames.


Funcionalidades
Gestão de Usuários:
- Cadastro de novos usuários.
- Atualização e exclusão de dados de usuários existentes.
- Autenticação de usuários (login/logout).

Gestão de Pacientes:
- Cadastro automático de pacientes ao criar um novo usuário não médico.
- Visualização de informações de pacientes.

Gestão de Médicos:
- Cadastro automático de médicos ao criar um novo usuário médico.
- Atualização de informações médicas, como CRM e especialidades.

Gestão de Consultas e Exames:
- Marcação de consultas e exames.
- Visualização de histórico de consultas e exames.

Estrutura do Banco de Dados
O banco de dados é composto pelas seguintes tabelas:

- tb_usuarios
- tb_pacientes
- tb_medicos
- tb_exames
- tb_consultas
- tb_tipo_exames


Requisitos 
- MySQL 8.0.36
- JDK 21.0
- JCalendar 1.4
- JFreeChart 1.5.2
- JavaFX Version 22.0.1
- <strong> Power BI Desktop *</strong>


Para rodar utilizando netbeans
- Realize o clone desse repositório;
- Faça o download de todos os requisitos necessários;
- Faça a importação dos requisitos para a parte de Libraries utilizando 
JAR/Folder;
- No arquivo CreateConnection no package database, altere as informações
com as suas relacionadas ao banco de dados:
    serverName = "localhost";
    mydatabase = "db_hospital";
    usuario = "root";
    senha = "root";
    url = "jdbc:mysql://" + serverName + "/" + mydatabase;
- Abra o Mysql e execute o script de criação do banco e das tabelas contido nesse repositório.

<br>

<footer><strong>* Para a correta visualização do dashboard que foi desenvolvido com POWER BI, por favor, realize o download do Power BI:
https://powerbi.microsoft.com/pt-br/desktop/
<br>
Siga os passos de conexão com o banco de dados contidos neste link: https://cursos.alura.com.br/forum/topico-sugestao-mysql-unable-to-connect-to-any-of-the-specified-mysql-hosts-317229</strong>
</footer>


