# Itau Backend Challenge
Backend challenge for Itaú Digital

## Desafio

Implementar uma API que retorna se uma senha é valida ou não, de acordo com uma série de regras listadas abaixo:
- Nove ou mais caracteres
- Ao menos 1 dígito
- Ao menos 1 letra minúscula
- Ao menos 1 letra maiúscula
- Ao menos 1 caractere especial
- Considere como especial os seguintes caracteres: !@#$%^&*()-+
- Não possuir caracteres repetidos dentro do conjunto

## Solução

Para a solução do desafio e desenvolvimento da API, foram utilizadas tecnologias como Java 11, Spring Boot, Lombok, Swagger, JUnit, Exception Handling.
No aspecto de validação das regras estabelicidades, foi implementado o design pattern Chain of Responsability, onde cada input (password à ser validado) passa por todas as regras (handlers) seguindo uma ordem estabelecida até que não haja mais regras para processar. O padrão foi implementado de duas maneiras diferentes, onde na primeira implementação, quando um input não atende a validação de uma regra, as regras sequentes não são processadas e o retorno já é dado ao usuário. Na segunda implementação, todas as regras são processadas independente da sua validadação, e na(s) regra(s) onde o input não atende o critério de validação, um detalhe é repassado para que, na resposta, o usuário visualize os critérios que não foram aceitos. Diante disso, a adição e remoção de uma regra de validação se torna um procedimento simples, bastando adicionar/retirar a classe responsável pela regra da cadeia de processamento.
Além do pattern citado acima, para a construção de objetos necessários para a execução da API, foi utilizado o padrão Builder, assim como DTOs para realizar o trânsito entre camadas de entrada/negócio/saída. 

Foram desenvolvidos testes unitários para cada validação de regra individualmente, e o fluxo de trabalho de todo o projeto seguiu a cultura TDD. Para os testes da camada de serviço e controller, foram implementados testes de integração, validando todo o fluxo e, no caso do controller, as respostas ao usuário da aplicação.

Para o tratamento de exceções, foi utilizado o Exception Handling, padrozinando o retorno em caso de exceção em um formato único e com detalhes e mensagens personalizadas ao consumidor da API.
Para monitoramento e sustentação, foi utilizado o framework Logback para adicionar logs de informação e de erros nos fluxos da aplicação.
Toda a API documentada utilizando Swagger e OpenAPI e pode ser consultada na seguinte URL: http://localhost:8080/swagger-ui/

A API expôe dois serviços (POST) para validação das senhas, um retornando apenas o resultado da validação e o segundo adicionando os detalhes dos critérios que não foram aceitos à resposta. 

## Execução
Para a execução do projeto, é necessário a instalação prévia do Java 11 e Maven 3.6.3.

Abaixo os comandos para execução da aplicação via CMD.
``` cURL Config
cd /{PROJECT_FOLDER}
mvn clean install
java -jar target/backend-challenge-0.0.1-SNAPSHOT.jar
mvn spring-boot:stop (parar aplicação)
```

Caso desejado, comandos para execução apenas dos testes.
``` cURL Config
cd /{PROJECT_FOLDER}
mvn test
mvn spring-boot:stop
```

## Testes de API
Request:
``` cURL Config
curl --location --request POST 'http://localhost:8080/password/validate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "password": "Abcdefag1A+@"
}'
```
Response:
```JSON 
{
    "isValid": true,    
}
```
Request:
``` cURL Config
curl --location --request POST 'http://localhost:8080/password/validate/details' \
--header 'Content-Type: application/json' \
--header 'password: aa' \
--data-raw '{
    "password": "Abcdefag1+@"
}'
```
Response:
```JSON 
{
    "isValid": false,
    "details": [
        "Password must not contains repeated characters"
    ]
}
```
