# Processo Seletivo Cadastro

Este projeto é uma API de cadastro de usuários (pessoa física ou jurídica) desenvolvida em Java com Spring Boot.

## Funcionalidades

- Cadastro de usuário com validação de CPF e CNPJ.
- Armazenamento dos usuários em um banco de dados MongoDB.

## Pré-requisitos

- JDK 11 ou superior
- Docker (para executar o MongoDB)

## Configuração

1. Clone o repositório:

- git clone [git@github.com:virods/processo-seletivo-cadastro.git](https://github.com/virods/processo-seletivo-cadastro.git)

2. Inicie o banco de dados MongoDB usando o Docker:
- docker run -d -p 27017:27017 --name mongodb mongo:latest

3. Importe o projeto em uma IDE de sua preferência (por exemplo, IntelliJ, Eclipse).

4. Aguarde as dependências serem baixadas automaticamente pelo Maven.

5. Execute a classe `ProcessoSeletivoCadastroApplication` para iniciar a aplicação.

## Uso

A API estará disponível em `http://localhost:8080/api/users`.

### Endpoint de criação de usuário

- `POST /api/users`: Cria um novo usuário.

O corpo da requisição deve conter os dados do usuário em formato JSON:

``{
"document": "43864009820",
"personType": "Pessoa Física",
"name": "João da Silva",
"age": 30,
"email": "joao@example.com",
"phone": "+55 11 99999-9999",
"gender": "Masculino"
}``

### Exemplos de Resposta

- Resposta de sucesso (HTTP 200):

``{
"id": "611d440eb1ec4f15210b4d56",
"document": "43864009820",
"personType": "Pessoa Física",
"name": "João da Silva",
"age": 30,
"email": "joao@example.com",
"phone": "+55 11 99999-9999",
"gender": "Masculino"
}``

- Resposta de erro de validação (HTTP 400):

``
"data": {
  "message": "CPF Inválido"
}``

- Resposta de erro do servidor (HTTP 500):

``"data": {
  "message": "Internal Server Error"
}``

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e enviar pull requests para melhorar este projeto.

## Licença

Este projeto está licenciado sob a MIT License.







