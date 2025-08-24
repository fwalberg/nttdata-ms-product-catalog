### Product Service

##### Visão Geral

O `Product Service` é um microserviço Spring Boot responsável por gerenciar informações de produtos. Ele se integra com o Eureka para descoberta de serviços e utiliza um banco de dados H2 para persistência de dados. Ele fornece endpoints REST para manipulação dos dados dos produtos, incluindo criação, leitura, atualização e exclusão. O projeto implementa DTOs, mapeadores, serviços, repositórios e tratamento de exceções, seguindo as melhores práticas de desenvolvimento de microsserviços.

##### Tecnologias Utilizadas

*   **Java**: Linguagem de programação
*   **Spring Boot**: Framework para construção de aplicações Java
*   **Spring Data JPA**: Facilitador para acesso a dados relacionais
*   **H2 Database**: Banco de dados embutido para desenvolvimento
*   **Eureka Client**: Cliente para registro e descoberta de serviços
*   **Lombok**: Biblioteca para reduzir código boilerplate
*   **MapStruct**: Biblioteca para conversão entre objetos (DTOs e entidades)
*   **Maven**: Ferramenta de gerenciamento de dependências

##### Arquitetura

O projeto segue uma arquitetura em camadas:

*   **Controller**: Camada responsável por receber as requisições HTTP e retornar as respostas.
*   **Service**: Camada responsável pela lógica de negócio.
*   **Repository**: Camada responsável pelo acesso aos dados.
*   **Model**: Camada responsável pelas entidades JPA.
*   **DTO**: Camada responsável pela transferência de dados entre as camadas.
*   **Mapper**: Camada responsável pela conversão entre entidades e DTOs.
*	**Exception**: Camada que gerencia as exceções da aplicação.

## Endpoints

O microserviço possui os seguintes endpoints REST:

*   **GET /products**: Retorna uma lista de todos os produtos.
*   **GET /products/{id}**: Retorna um produto com o ID especificado.
*   **GET /products/name/product_name**: Retorna um produto com o nome especificado.
*   **GET /products/search?namePart=term**: Retorna uma lista de produtos contendo o especificado.
*   **POST /products**: Cria um novo produto.
*   **PUT /products/{id}**: Atualiza um produto existente com o ID especificado.
*   **DELETE /products/{id}**: Deleta um produto com o ID especificado.

### Exemplo POST:
```
curl -X POST "http://localhost:8080/products" \
     -H "Content-Type: application/json" \
     -d '{
           "name": "Mouse",
           "description": "Ergonomic wireless mouse",
           "price": 199.99
         }'
```
### Exemplo PUT
```
curl -X PUT "http://localhost:8080/products/5" \
     -H "Content-Type: application/json" \
     -d '{
           "id": 5,
           "name": "Mouse",
           "description": "Ergonomic wireless mouse",
           "price": 199.99
         }'
```