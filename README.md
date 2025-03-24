# A3 - Soluções Computacionais  

## Ulife Health  

O **Ulife Health** é uma aplicação desenvolvida para gerenciar e analisar dados relacionados a pacientes, médicos, consultas e exames em um ambiente hospitalar. Além de facilitar o cadastro, atualização e agendamento, a aplicação conta com integração ao **Power BI**, permitindo a criação de **dashboards interativos** para análise de dados clínicos, acompanhamento de atendimentos e otimização da gestão hospitalar.  

![Login](https://github.com/user-attachments/assets/6c3f07d7-494c-4a28-a8ea-6754a13aad38)  

---

## Funcionalidades  

### 🔹 Gestão de Usuários  
- Cadastro de novos usuários.  
- Atualização e exclusão de dados de usuários existentes.  
- Autenticação de usuários (login/logout).  

### 🔹 Gestão de Pacientes  
- Cadastro automático de pacientes ao criar um novo usuário não médico.  
- Visualização de informações de pacientes.  

![Tabela de Consultas](https://github.com/user-attachments/assets/5fa45105-83c3-4e9f-87d7-1ac61f9c5355)  

### 🔹 Gestão de Médicos  
- Cadastro automático de médicos ao criar um novo usuário médico.  
- Atualização de informações médicas, como CRM e especialidades.  

![Cadastro de Usuários](https://github.com/user-attachments/assets/541917ef-3a93-47cc-abb3-0b7a20d769ea)  

### 🔹 Gestão de Consultas e Exames  
- Marcação de consultas e exames.  
- Visualização de histórico de consultas e exames.  

![Cadastro de Exames](https://github.com/user-attachments/assets/deefb1a2-f2a7-418d-81d4-2985306668a8)  

---

## 📊 Estrutura do Banco de Dados  

O banco de dados é composto pelas seguintes tabelas:  
- `tb_usuarios`  
- `tb_pacientes`  
- `tb_medicos`  
- `tb_exames`  
- `tb_consultas`  
- `tb_tipo_exames`  

---

## 🛠 Requisitos  

- **MySQL** 8.0.36  
- **JDK** 21.0  
- **JCalendar** 1.4  
- **JFreeChart** 1.5.2  
- **JavaFX** 22.0.1  
- **Power BI Desktop**  

---

## 🚀 Como Rodar o Projeto no NetBeans  

1. Clone este repositório:  
   ```bash
   git clone https://github.com/seu-usuario/A3---Solucoes-Computacionais.git
