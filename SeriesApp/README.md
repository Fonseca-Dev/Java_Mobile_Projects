# 📺 SérieApp (Android)

Este é um aplicativo Android simples desenvolvido em Java que permite ao usuário cadastrar, validar e visualizar informações sobre uma série.

## 🎯 Funcionalidades

- Inserção dos dados de uma série (título, temporada, avaliação, gênero e se foi finalizada).
- Validação completa dos campos com mensagens de erro personalizadas.
- Exibição dos dados validados por meio de `Toast`.
- Interface gráfica com `ConstraintLayout`.

## 🧩 Layout da Interface (XML)

A interface foi construída utilizando o `ConstraintLayout` e conta com os seguintes componentes:

| Componente | ID | Descrição |
|-----------|----|-----------|
| `TextView` | `Main_TextView_Titulo_Activity` | Título da activity |
| `EditText` | `Main_EditText_Titulo` | Campo para inserir o título da série |
| `EditText` | `Main_EditText_Temporada` | Campo para inserir a temporada |
| `EditText` | `Main_EditText_Avaliacao` | Campo para inserir a avaliação (0-10) |
| `EditText` | `Main_EditText_Genero` | Campo para inserir o gênero da série |
| `EditText` | `Main_EditText_Finalizada` | Campo para informar se a série foi finalizada ("Sim" ou "Não") |
| `Button` | `button` | Botão "Exibir" RA e nome |
| `Button` | `button2` | Botão "Validar" os dados inseridos |

## 📱 Lógica da Aplicação

### Classe `MainActivity`

- `exibir(View v)`: Exibe o nome e RA do autor com um `Toast`.
- `validar(View v)`: Faz validações rigorosas nos dados inseridos, como:
  - Título não pode ser vazio
  - Temporada deve ser maior que 0
  - Avaliação entre 0 e 10
  - Gênero e finalização obrigatórios
  - Campo finalizada aceita "Sim", "Não", "S", "N", "Nao"

- `obterSerie()`: Extrai dados da tela e cria uma instância da classe `Serie`.

### Classe `Serie`

```java
public class Serie {
    String titulo;
    int temporada;
    double avaliacao;
    String genero;
    String finalizada;
}
```

## 🙋‍♂️ Autor

**Kauê Rocha da Fonseca**  

---

Este projeto foi desenvolvido como parte dos estudos em Android Studio utilizando Java e layouts com XML.
