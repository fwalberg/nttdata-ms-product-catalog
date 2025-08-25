Este Módulo implementa um serviço completo de gerenciamento de pedidos (Order Service) para a arquitetura de microserviços, incluindo funcionalidades para criação de pedidos padrão e expressos com integração ao Product Service via Feign Client.
##  Principais Funcionalidades Implementadas
### ️ Arquitetura & Configuração
- **Configuração do microserviço** com Spring Boot, Discovery Client e Feign
- **Habilitação de JPA Repositories** para persistência de dados
- **Configuração de beans** para injeção de dependência

### Modelos de Domínio
- **Order**: Entidade principal com suporte a múltiplos itens
- **OrderItem**: Representação de itens individuais do pedido
- **Product**: Modelo para integração com Product Service
- **Enums**: OrderStatus e OrderType para tipagem forte

### Padrão Factory
- **OrderFactory Interface**: Abstração para criação de pedidos
- **StandardOrderFactory**: Criação de pedidos padrão com validação de produtos
- **ExpressOrderFactory**: Criação de pedidos expressos com taxa adicional
- **OrderFactoryProvider**: Provedor centralizado de factories

### Camada de Serviços
- **OrderService**: Serviço principal para criação de pedidos
- **OrderBusinessService**: Validações e regras de negócio
- **ProductService**: Integração com Product Service via Feign
- **OrderFactoryProvider**: Gerenciamento de diferentes tipos de pedidos

### API REST
- **OrderController**: Endpoints para criação de pedidos
    - `POST /orders/standard` - Criação de pedidos padrão
    - `POST /orders/express` - Criação de pedidos expressos
  
- **Exemplo de chamada: POST**
```
curl -X POST "http://localhost:8200/orders/standard" \
  -H "Content-Type: application/json" \
  -d '{
    "customerId": "000001",
    "orderType": "STANDARD",
    "items": [
      { "productId": 1, "quantity": 2 },
      { "productId": 2, "quantity": 1 },
      { "productId": 3, "quantity": 1 },
      { "productId": 4, "quantity": 4 }
    ]
  }'
```

### Integração Externa
- **ProductFeignClient**: Cliente para comunicação com Product Service
- **ProductMapper**: Mapeamento entre DTOs e entidades usando MapStruct

### DTOs & Contratos
- **CreateOrderRequest**: Payload para criação de pedidos
- **OrderItemRequest/Response**: Representação de itens
- **ProductDTO**: DTO para integração com Product Service

### Tratamento de Erros
- **GlobalExceptionHandler**: Tratamento centralizado de exceções
- **Custom Exceptions**:
    - `OrderProductNotFoundException`
    - `OrderProductInvalidArgumentException`

- **Problem Detail**: Padronização de respostas de erro

## Tecnologias Utilizadas
- Spring Boot (Jakarta EE)
- Spring Data JPA
- Spring Cloud OpenFeign
- Spring Cloud Discovery Client
- MapStruct
- Lombok
- Java SDK 17

## Melhorias de Arquitetura
- **Separação clara de responsabilidades** entre camadas
- **Padrão Factory** para diferentes tipos de pedidos
- **Validação robusta** de dados de entrada
- **Tratamento de exceções** padronizado
- **Comunicação entre microserviços** via Feign Client

## Pontos de Atenção
- Integração configurada para Product Service em `localhost:8100`
- Suporte a pedidos padrão e expressos com diferentes comportamentos
- Validação completa de dados antes da criação de pedidos
