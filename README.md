# 💸 PicPay Simplificado

## 📌 Visão Geral

**PicPay Simplificado** é uma plataforma de pagamentos básica inspirada no funcionamento do PicPay. Nela, usuários podem **depositar** valores em suas carteiras e realizar **transferências de dinheiro entre contas**.  

Existem dois tipos de usuários no sistema:

- 👤 **Usuário Comum**: pode enviar e receber dinheiro.  
- 🏪 **Lojista**: pode apenas **receber** transferências.

Ambos os tipos de usuários possuem carteiras com saldo e interagem com o sistema de acordo com suas permissões.

---

## 🎯 Finalidade do Projeto

Este projeto foi desenvolvido com o objetivo de **aprimorar meus conhecimentos em desenvolvimento de APIs com Spring Boot**, bem como explorar conceitos fundamentais como:

- Programação orientada a objetos com **Java**
- Operações **CRUD**
- Boas práticas com **DTOs**, **camadas de serviço** e **controllers**
- Integração com banco de dados utilizando **JPA / Hibernate**
- Validações com **Bean Validation**
- Testes de lógica de negócio e fluxo
- Consumo de APIs externas (mock de autorização)

---

## 🛠️ Tecnologias Utilizadas

### 📦 Backend
- **Java 17**
- **Spring Boot 3.x**
  - Spring Web
  - Spring Data JPA
  - Spring Validation
- **H2 Database** (banco de dados em memória para testes locais)
- **Lombok**
- **RestTemplate** (para chamadas HTTP simuladas)

---

## 📚 Funcionalidades

- ✅ Cadastro de usuários (comuns ou lojistas)
- ✅ Depósito inicial na carteira
- ✅ Transferência de valores entre usuários
- ✅ Regras de validação:
  - Lojistas **não podem transferir** valores
  - Usuários **não podem transferir para si mesmos**
  - Transferências só ocorrem se houver **saldo suficiente**
  - Transações devem ser **autorizadas externamente** (simulado via API)

---

## 🚀 Como Executar


### Clone o repositório
```bash
git clone https://github.com/seu-usuario/picpay-simplificado.git
```
### Acesse o diretório
```bash
cd picpay-simplificado
```

### Compile e execute a aplicação
```bash
./mvnw spring-boot:run
```
A aplicação estará disponível em: http://localhost:8080


## 🧠 Aprendizados
Durante o desenvolvimento deste projeto, pude aprofundar meus conhecimentos em:
- Arquitetura MVC com Spring Boot

- Mapeamento objeto-relacional com JPA

- Validações robustas com DTOs

- Boas práticas na separação de responsabilidades

- Tratamento de exceções e lógica de negócios