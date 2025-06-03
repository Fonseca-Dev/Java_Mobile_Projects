
# 🍽️ APPRestaurante Server

Servidor RESTful desenvolvido em **Java com Spring Boot** para gerenciar o sistema de mesas de um restaurante. Permite cadastrar, consultar, atualizar e remover mesas, além de listar os produtos disponíveis para pedidos.

> Projeto desenvolvido por **Kauê Rocha da Fonseca**

---

## 🚀 Funcionalidades

- 🔎 Listar todas as mesas
- 🔍 Consultar uma mesa pelo ID
- ➕ Cadastrar nova mesa
- ✏️ Atualizar dados de uma mesa (status, cliente, pedidos, valor total)
- ❌ Remover/Resetar uma mesa
- 📦 Listar os produtos disponíveis

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Lombok
- Maven

---

## 📁 Estrutura do Projeto

```
src/
├── com.example.Aula8RestauranteServer/
│   ├── Aula8RestauranteServerApplication.java
│   ├── controller/
│   │   └── MesaController.java
│   ├── dao/
│   │   ├── MesaDao.java
│   │   └── ProdutoDao.java
│   └── model/
│       ├── Mesa.java
│       ├── Pedido.java
│       └── Produto.java
```

---

## 🔗 Endpoints da API

### 🪑 Mesas

#### GET `/restaurante/mesas`
Retorna a lista de todas as mesas.

#### GET `/restaurante/mesas/{id}`
Retorna os dados da mesa com o ID especificado.

#### POST `/restaurante/mesas`
Cadastra uma nova mesa.

**Exemplo de requisição:**
```json
{
  "status": "Livre",
  "cliente": "",
  "pedidos": [],
  "valorConta": 0.0
}
```

#### PUT `/restaurante/mesas/{id}`
Atualiza os dados de uma mesa existente.

**Exemplo de requisição:**
```json
{
  "status": "Ocupada",
  "cliente": "João",
  "pedidos": [],
  "valorConta": 0.0
}
```

#### DELETE `/restaurante/mesas/{id}`
Reseta a mesa com o ID especificado, tornando-a livre novamente.

---

### 🍔 Produtos

#### GET `/restaurante/produtos`
Retorna a lista de produtos disponíveis com nome e preço.

---

## ▶️ Como Rodar o Projeto

1. **Clone este repositório:**
   ```bash
   git clone https://github.com/Fonseca-Dev/RestauranteAPP/APPRestauranteServer
   cd APPRestaurante-Server
   ```

2. **Compile e execute com o Maven:**
   ```bash
   ./mvnw spring-boot:run
   ```

3. **A aplicação estará disponível em:**
   ```
   http://localhost:8080
   ```

---

## 📫 Contato

- **Nome:** Kauê Rocha da Fonseca  
- **E-mail:** fonseca.kaue@outlook.com

---

