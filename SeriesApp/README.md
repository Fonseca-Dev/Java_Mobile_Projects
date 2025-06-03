
# 📺 Sistema de Gerenciamento de Séries

Este é um aplicativo de console desenvolvido em **Java** para gerenciar suas séries favoritas!  
Você pode **listar**, **incluir**, **atualizar** e **excluir** informações de séries, como título, temporada, avaliação e gênero.

---

## 🚀 Funcionalidades

- 📄 **Listar Séries**: Exibe todas as séries cadastradas.
- ➕ **Incluir Série**: Permite adicionar uma nova série com os seguintes dados:
  - Título
  - Temporada
  - Avaliação
  - Gênero
- 🔁 **Atualizar Série**: Permite atualizar os dados de uma série existente, identificada pelo título.
- ❌ **Excluir Série**: Remove uma série da lista, também pelo título.
- 🚪 **Sair do Sistema**: Encerra a aplicação.

---

## 💻 Como Usar

1. Compile os arquivos:

```bash
javac Serie.java MenuSerie.java
```

2. Execute o programa:

```bash
java MenuSerie
```

3. Use o menu interativo no terminal para gerenciar suas séries favoritas!

---

## 🧱 Estrutura do Código

### 📂 `Serie.java`

Classe que representa uma série com os seguintes atributos:

```java
private String titulo;
private int temporada;
private double avaliacao;
private String genero;
```

Todos os campos possuem seus respectivos **getters**.  
O construtor é usado para instanciar novos objetos do tipo `Serie`.

---

### 📂 `MenuSerie.java`

Contém o menu principal e as funções para:

- 📋 Listar (`listar`)
- ✍️ Incluir (`incluir`)
- 🔁 Atualizar (`atualizar`)
- 🗑️ Excluir (`excluir`)

Utiliza um `ArrayList<Serie>` para armazenar dinamicamente os dados durante a execução.

---

## 🎮 Exemplo de Uso

```text
Sistema de Séries
1 - Listar
2 - Incluir
3 - Atualizar
4 - Excluir
5 - Sair
```

### Exemplo de Inclusão:
```text
Incluir uma nova série
Título
Breaking Bad
Temporada:
5
Avaliação:
9.5
Genero:
Drama/Crime
```

---

## 🛠️ Requisitos

- Java JDK 8 ou superior
- Terminal/Console para execução

---

## 📌 Observações

- Os dados **não são persistidos** ao encerrar o programa.
- O título da série é utilizado como identificador único para atualização e exclusão.
- Este projeto pode ser facilmente adaptado para um sistema com **interface gráfica** ou com **banco de dados**.

---

## ✨ Melhorias Futuras

- 📁 Salvamento dos dados em arquivo ou banco de dados.
- 🖼️ Interface gráfica com JavaFX ou Swing.
- 🔍 Busca de séries por gênero ou avaliação.
- ✅ Validações de entrada (ex: evitar avaliações negativas).

---

## 👨‍💻 Autor

Desenvolvido por [**Kauê Rocha da Fonseca**]  
Curso de Ciência da Computação – UNIEDUK  
Projeto de prática com Java – Controle de Séries no Console.

---
