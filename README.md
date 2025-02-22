# API Restful - Agendamento em Clínica Médica

## Descrição
Esta API Restful simula um sistema de agendamento de consultas médicas, permitindo o cadastro e gerenciamento de médicos e pacientes, além da autenticação e controle de acesso por meio de roles.

## Tecnologias Utilizadas
- **Spring Boot**
- **Spring Security**
- **Flyway** (Migrations para gerenciamento do banco de dados)

## Estrutura da API
A API apresenta os seguintes controllers:

### 1. **Agendamento de Consultas**
Responsável por permitir que pacientes agendem consultas médicas.

### 2. **Médicos**
Gerencia o cadastro e as informações dos médicos disponíveis na clínica.

### 3. **Pacientes**
Gerencia o cadastro e as informações dos pacientes da clínica.

### 4. **Autenticação**
Realiza a autenticação de usuários e controle de acesso por meio de roles, garantindo a segurança da aplicação.

## Como Executar o Projeto
1. Certifique-se de ter o **Java 17** ou superior instalado.
2. Clone este repositório.
   ```sh
   git clone <URL_DO_REPOSITORIO>
   ```
3. Configure o banco de dados conforme as migrations do Flyway.
4. Execute o projeto com o Maven ou diretamente pelo Spring Boot.
   ```sh
   mvn spring-boot:run
   ```
5. Acesse a API pelos endpoints definidos.

## Considerações
Esta API foi desenvolvida com o objetivo de demonstrar a implementação de um sistema de agendamento médico utilizando boas práticas do Spring Boot e segurança com autenticação e controle de acessos.

Fique à vontade para contribuir e sugerir melhorias!


