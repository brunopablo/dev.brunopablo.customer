# CustomerConnect

Bem-vindo ao **CustomerConnect**, um projeto envolvente e prático projetado para ajudá-lo a dominar os fundamentos do **Spring Boot** e do **Spring Data JPA**.  
Neste projeto, você criará um robusto **Sistema de Gerenciamento de Clientes** que executa operações CRUD em uma entidade **Cliente**.

---

## 🚀 Tecnologias Utilizadas
- **Java 25**
- **Spring Boot**
- **Spring Data JPA**
- **H2 Database** (banco em memória para desenvolvimento e testes)
- **Maven** (gerenciamento de dependências)

---

## 📚 Objetivos do Projeto
Ao final deste módulo, você terá:
- Uma compreensão sólida de como conectar uma aplicação Spring Boot a um banco de dados usando Spring Data JPA.
- Experiência prática na implementação de operações CRUD.
- Conhecimento sobre boas práticas de desenvolvimento backend com Spring.

---

## 📋 Regras de Negócio

### Dados Cadastrais
- Nome Completo
- CPF
- Email
- Telefone Celular
- Data de registro e atualização do cliente no sistema (auditoria)

### Cadastro Único
- Garantir que **id**, **cpf** e **email** não sejam duplicados.

### Busca Flexível
- Paginação e ordenação de resultados.
- Busca por CPF e/ou Email.

---

## 🌐 Endpoints REST

### Criar Cliente
`POST /customers`  
**Parâmetros requeridos:**
- `fullName`
- `cpf`
- `email`
- `phoneNumber`

**Retorno:**
- `customerId`

---

### Consultar Clientes
`GET /customers`  
**Parâmetros de consulta:**
- `page`
- `pageSize`
- `orderBy`
- `email`
- `cpf`

**Retorno:**
- Dados do cliente + paginação

---

### Atualizar Cliente
`PUT /customers/{customerId}`  
**Parâmetros requeridos:**
- `fullName`
- `email`
- `phoneNumber`

---

### Deletar Cliente
`DELETE /customers/{customerId}`

---

## 🛠️ Como Executar

### Pré-requisitos
- Git
- JDK 25 instalado

### Passos
```bash
# Clonar o repositório
git clone https://github.com/brunopablo/dev.brunopablo.customer.git

# Entrar na pasta
# Executar a aplicação com o Java
jar -jar customer-0.0.1-SNAPSHOT.jar


### 💡 Oportunidades de Melhoria

## 1. Queries nativas com `@Query`
- Evitar múltiplos métodos de consulta no repositório.
- Centralizar lógica de busca complexa em queries otimizadas.
- Melhorar performance e reduzir redundância de código.

---

## 2. Validações com Spring Validation (`@Valid`)
- Substituir validações manuais redundantes na camada de serviço.
- Utilizar anotações como `@NotNull`, `@Email`, `@Size` diretamente nos DTOs.
- Garantir consistência e padronização das regras de validação.

---

## 3. Tratamento global de exceções com `@RestControllerAdvice`
- Padronizar respostas de erro para toda a aplicação.
- Evitar duplicação de lógica de tratamento em cada controller.
- Melhorar a experiência do consumidor da API com mensagens claras e consistentes.

---

## 4. Banco de dados relacional em produção
- Substituir o H2 por **PostgreSQL** ou **MySQL** em ambientes reais.
- Garantir persistência, escalabilidade e suporte a recursos avançados de bancos relacionais.
- Facilitar integração com ferramentas de monitoramento e administração.

---

## 5. Uso de Docker
- Containerizar a aplicação (`customer-0.0.1-SNAPSHOT.jar`) para facilitar deploy e integração contínua.
- Criar um `Dockerfile` e usar `docker-compose` para subir a aplicação junto com PostgreSQL ou MySQL.
- Benefícios:
  - Portabilidade entre ambientes.
  - Consistência no desenvolvimento e produção.
  - Facilidade de integração em pipelines CI/CD.