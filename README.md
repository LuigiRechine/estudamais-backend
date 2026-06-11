# EstudaMais - Backend

Backend da plataforma **EstudaMais** — Plataforma de cursos online gratuitos.

## Sobre o Projeto

**EstudaMais** é uma plataforma de cursos online onde **professores** podem cadastrar e gerenciar seus cursos, e **alunos** podem se inscrever e estudar gratuitamente.

Este repositório contém a **API REST** desenvolvida com Spring Boot, responsável por toda a lógica de negócio, autenticação e persistência de dados.

## Principais Funcionalidades

- Gerenciamento de usuários (Alunos e Professores)
- Autenticação e autorização
- Cadastro, edição e listagem de cursos (em desenvolvimento)
- Inscrição de alunos nos cursos
- API RESTful completa
- Integração com banco de dados MySQL

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring Validation**
- **MySQL**
- **Lombok**
- **Maven**

## Como Executar o Projeto

### Pré-requisitos

- Java 17 ou superior
- Maven
- MySQL instalado e rodando

### Configuração do Banco de Dados

1. Crie um banco de dados no MySQL:
   ```sql
   CREATE DATABASE estudamais;

2. Configure as credenciais no arquivo src/main/resources/application.properties:

    ```java
    spring.datasource.url=jdbc:mysql://localhost:3306/estudamais?createDatabaseIfNotExist=true&serverTimezone=America/Sao_Paulo
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true

### Passos para Rodar
1. Clone o repositório
git clone https://github.com/LuigiRechine/estudamais-backend.git
cd estudamais-backend

2. Compile e execute o projeto
./mvnw spring-boot:run

### Estrutura de Pastas Principais
Bashsrc/main/java/com/estudaMais/
├── EstudaMaisApplication.java
├── config/
├── controller/       # Controladores REST
├── entity/           # Entidades JPA
├── repository/       # Repositórios
└── service/          # Regras de negócio
