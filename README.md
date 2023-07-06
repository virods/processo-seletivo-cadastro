# Processo Seletivo Cadastro

Este projeto é uma API de cadastro de usuários (pessoa física ou jurídica) desenvolvida em Java com Spring Boot.

## Funcionalidades

- Cadastro de usuário com validação de CPF e CNPJ.
- Armazenamento dos usuários em um banco de dados MongoDB.

## Pré-requisitos

- JDK 17 ou superior
- Docker (para executar o MongoDB)

## Configuração

1. Clone o repositório:

- git clone https://github.com/virods/processo-seletivo-cadastro.git

2. Execute o comando `docker-compose up --build` para subir a aplicação e o banco de dados

## Uso

A API estará disponível em `http://localhost:8080/api/users`.

## Documentação

Para acessar a documentação (Feita via Swagger) acesse o link quando subir a app http://localhost:8080/swagger-ui/index.html#/Cadastro%20de%20usu%C3%A1rios


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
    "data": {
        "message": "Sucesso ao criar o usuario no banco de dados",
        "personType": "Pessoa Física"
    }
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







