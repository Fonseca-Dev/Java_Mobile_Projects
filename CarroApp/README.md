# Aula 4 - App Cadastro de Carros 🚗📱

Este projeto Android registra carros com seus respectivos dados (placa, modelo, cor, ano de fabricação e estado de conservação) e permite visualizar, listar e excluir os carros cadastrados.

## 👨‍🎓 Desenvolvedor
- **Nome:** Kauê Rocha da Fonseca

---

## 📱 Funcionalidades

- Cadastro de carros com validações de entrada (placa, modelo, ano)
- Lista de carros com seleção múltipla
- Exclusão de carros selecionados
- Navegação entre telas com `Intent`
- Utilização de `Spinner` com dados de arrays XML

---

## 🧱 Estrutura de Pastas e Arquivos

```
├── java/com/example/aula4carroapp/
│   ├── MainActivity.java
│   ├── Carros_Cadastrados.java
│   └── Carros.java
├── res/
│   ├── layout/
│   │   ├── activity_main.xml
│   │   ├── activity_carros_cadastrados.xml
│   │   └── item_carro_checked.xml
│   ├── values/
│   │   ├── strings.xml
│   │   ├── cores.xml
│   │   └── estado_conservacao.xml
```

---

## 🧩 Componentes Utilizados

| Componente              | Função                                                   |
|-------------------------|-----------------------------------------------------------|
| `EditText`              | Entrada de dados (placa, modelo, ano)                     |
| `Spinner`               | Escolha de cor e estado de conservação                    |
| `ListView`              | Exibição da lista de carros                               |
| `CheckedTextView`       | Item customizado com marcação para exclusão              |
| `Button`                | Aciona funções (cadastrar, excluir, voltar)               |
| `Intent`                | Navegação entre `MainActivity` e `Carros_Cadastrados`     |

---

## 📌 Validações implementadas

- Placa no formato `ABC-1234`
- Modelo não pode estar vazio
- Ano de fabricação não pode estar vazio, maior que o ano atual, ou não numérico

---

## 🗂️ Descrição dos Arquivos XML importantes

- `cores.xml` → Array de cores do spinner
- `estado_conservacao.xml` → Array de estados do spinner
- `activity_main.xml` → Tela de cadastro de carros
- `activity_carros_cadastrados.xml` → Tela de listagem e exclusão
- `item_carro_checked.xml` → Layout customizado da ListView com `CheckedTextView`

---

## 🚀 Execução

1. Importe o projeto no Android Studio.
2. Conecte um emulador ou dispositivo físico.
3. Execute o app.
4. Cadastre carros, visualize e exclua da lista conforme desejado.

---

## 🧠 Observações Finais

- Projeto ideal para prática com layouts, intents, ListView com seleção e validação de inputs em Android nativo.
- Código organizado por responsabilidade, com boa separação entre lógica e interface.

---
