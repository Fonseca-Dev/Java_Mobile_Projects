
# 📱 Aplicativo Android - Gerenciador de Séries

Este é um aplicativo Android desenvolvido em Java com o Android Studio. O objetivo é permitir que o usuário cadastre e valide informações sobre suas séries favoritas.

---

## 🎯 Funcionalidades

- 📝 Entrada de dados para uma série:
  - Título
  - Temporada
  - Avaliação (0 a 10)
  - Gênero
  - Finalizada (Sim/Não)
- ✅ Validação de dados com mensagens de erro informativas
- 📢 Exibição de informações validadas por meio de mensagens `Toast`
- 👤 Exibição do nome e RA do desenvolvedor

---

## 🧱 Estrutura do Projeto

### 🔹 `MainActivity.java`
Contém a lógica principal de interação com o usuário, incluindo:

- Leitura dos dados inseridos nos campos de texto
- Validação dos campos com mensagens claras de erro
- Exibição dos dados validados via `Toast`
- Exibição de identificação pessoal (RA e nome)

### 🔹 `Serie.java`
Classe simples que representa uma série com os seguintes atributos:

```java
String titulo;
int temporada;
double avaliacao;
String genero;
String finalizada;
```

---

## 🧪 Regras de Validação

- **Título**: Não pode ser vazio
- **Temporada**: Deve ser maior que 0
- **Avaliação**: Valor entre 0 e 10
- **Gênero**: Não pode ser vazio
- **Finalizada**: Aceita apenas "Sim", "Não", "S", "N" ou "Nao"

Mensagens `Toast` são usadas para informar o usuário sobre falhas ou sucesso na validação.

---

## 🧰 Requisitos Técnicos

- Android Studio (recomendado: versão mais recente)
- SDK mínimo compatível com API usada no projeto (ex: API 21 ou superior)
- Dispositivo ou emulador Android

---

## 📲 Como Usar

1. Clone o repositório ou importe o projeto no Android Studio
2. Conecte um dispositivo Android ou inicie um emulador
3. Compile e execute o app
4. Preencha os campos da interface e clique nos botões para exibir/validar os dados

---

## 👨‍💻 Autor

Desenvolvido por **Kauê Rocha da Fonseca**  
Curso de Ciência da Computação – UNIEDUK

---

## ✨ Melhorias Futuras

- 💾 Persistência de dados com SQLite ou Room
- 🎨 Melhorias na interface com Material Design
- ☁️ Integração com API para buscar séries reais
- 🔄 Lista de séries com histórico e edição

---
