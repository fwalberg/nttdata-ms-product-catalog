O projeto é um sistema de catálogo de produtos baseado em microserviços,
desenvolvido em Java com Maven. Ele é composto por quatro principais módulos: 
api-gateway, 
eureka-server, 
order-service e 
product-service.
Cada microserviço possui responsabilidades específicas e se comunica via API REST.

## Estrutura do Projeto
- `api-gateway`: Responsável por rotear as requisições para os serviços apropriados.
- `eureka-server`: Serviço de descoberta que permite que os microserviços se registrem
e descubram uns aos outros.
- `order-service`: Gerencia as operações relacionadas a pedidos.
- `product-service`: Gerencia as operações relacionadas a produtos.

## Tecnologias Utilizadas
- Java
- Maven
- Spring Boot
- Spring Cloud Netflix
- RESTful APIs
- H2 Database (para desenvolvimento e testes)
- JUnit
- Spring Security
- OAuth2 (aceita qualquer token valido)
- Lombok
- MapStruct (para mapeamento de objetos)
- JPA/Hibernate (para mapeamento objeto-relacional)
- SonarQube (para análise de qualidade de código)

## Requisitos
- Java 17 ou superior
- Maven 3.6 ou superior
- Docker (opcional, para containerização)
- Git
- IDE de sua preferência (IntelliJ, Eclipse, VSCode, etc.)
- Postman ou qualquer ferramenta para testar APIs REST
- Navegador web (para acessar o Eureka Dashboard)
- Curl (opcional, para testar APIs via linha de comando)

## Ms product-service
O serviço de produtos é responsável por gerenciar o catálogo de produtos. 
Ele oferece endpoints para criar, ler, atualizar e deletar produtos.
### Endpoints Principais
- `GET /products`: Retorna uma lista de todos os produtos.
- `GET /products/{id}`: Retorna os detalhes de um produto específico.
- `POST /products`: Cria um novo produto.
- `PUT /products/{id}`: Atualiza um produto existente.
- `DELETE /products/{id}`: Deleta um produto específico.
- `GET /products/search?namePart={name}`: Busca produtos pelo nome (parcial ou completo).
### Exemplo de Requisição direta pelo modulo de produto:
- GET:
```bash 
  curl -X GET http://localhost:8100/products
```
- POST:
```bash
  curl -X POST "http://localhost:8100/products" \
     -H "Content-Type: application/json" \
     -d '{
           "name": "Mouse",
           "description": "Ergonomic wireless mouse",
           "price": 199.99
         }'
```
## Ms order-service
O serviço de pedidos é responsável por gerenciar os pedidos dos clientes. Ele oferece endpoints para criar, ler, atualizar e deletar pedidos.
### Endpoints Principais
- `POST` `/orders/standard`: Cria um novo pedido na modalidade padrão.
- `POST` `/orders/express`: Cria um novo pedido na modalidade expressa. **pendente de implementação!!!**

### Exemplo de Requisição direta pelo modulo de pedido:

**IMPORTANTE**: 
 - Certifique-se de que o serviço de produtos esteja em execução e que 
 - existam produtos cadastrados antes de criar um pedido.


- POST:
```bash
  curl -X POST "http://localhost:8200/orders/standard" \
        -H "Content-Type: application/json" \
        -d {
	        "customerId": "2",
            "items": [
                { "productId": 1, "quantity": 2 },
                { "productId": 2, "quantity": 1 }
            ]
        }'
```
- Resposta esperada:
```json
{
  "id": "ORD-2740d26a-dcd4-4df0-b458-21e7c47087b9",
  "customerId": "2",
  "items": [
    {
      "id": "ITM-a3c5ce62-d26a-448a-ae7b-f834844d0c40",
      "productId": 1,
      "productName": "Laptop",
      "quantity": 2,
      "unitPrice": 10500.0,
      "totalPrice": 21000.0
    },
    {
      "id": "ITM-0d25ab9c-ac78-4e0a-916f-1af6bc105f97",
      "productId": 2,
      "productName": "Web cam",
      "quantity": 1,
      "unitPrice": 1700.0,
      "totalPrice": 1700.0
    }
  ],
  "totalAmount": 22700.0,
  "createdAt": "2025-08-25T12:27:40.896533373-03:00",
  "status": "PENDING"
}
```
## Ms eureka-server
O Eureka Server é um serviço de descoberta que permite que os microserviços se registrem e
descubram uns aos outros. Ele é essencial para a arquitetura de microserviços, pois
facilita a comunicação entre os serviços.
### Acessando o Dashboard do Eureka
Após iniciar o Eureka Server, você pode acessar o dashboard através do navegador web:
```
    http://localhost:8761
```
## Ms  api-gateway  
O API Gateway atua como um ponto de entrada único para todos os microserviços. Ele roteia as requisições para os serviços apropriados e pode também realizar tarefas como autenticação, logging e rate limiting.
### Configuração do API Gateway
O API Gateway está configurado para rotear as requisições para os serviços de produtos e pedidos.
### Endpoints Principais
- `GET /products/**`: Roteia para o serviço de produtos.
- `POST /orders/**`: Roteia para o serviço de pedidos.
### Exemplo de Requisição via API Gateway
- GET: Não necessita de autenticação para este endpoint
- Os demais endpoints do product-service podem ser acessados via API Gateway da mesma forma porem existe a necessidade de autenticação(qualquer token Bearer) para os endpoints de modificação.
```bash
  curl -X GET http://localhost:8700/products
```
**IMPORTANTE**: Para fazer requisições ao endpoint de pedidos, é necessário incluir um token de autenticação válido no cabeçalho da requisição. O API Gateway está configurado para aceitar qualquer token do tipo Bearer.
- POST:
```bash
  curl -X POST "http://localhost:8700/orders/standard" \
        -H "Content-Type: application/json" \
        -d {
            "customerId": "2", 
            "items": [
                { "productId": 1, "quantity": 2 },
                { "productId": 2, "quantity": 1 }
            ]
        }'
```

### Exemplo de Token Bearer
```
Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikd1ZXN0IFVzZXIiLCJpYXQiOjE1MTYyMzkwMjJ9.w72B-R8s_oF5iT3nS5Jb1kO_H5P_E9d-vP5t-t-T-sA
```
### O projeto carece de muita melhoria, principalmente com validações, tratamento de erros e testes unitários. Sintam-se a vontade para contribuir.