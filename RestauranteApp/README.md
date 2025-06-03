# 🍽️ RestauranteApp

Um aplicativo Android desenvolvido em **Java** que se comunica com um servidor RESTful para **controle completo das mesas de um restaurante**. Ideal para fins acadêmicos, este projeto simula um sistema prático de atendimento, permitindo consultar, abrir, anotar pedidos e fechar mesas com sincronização em tempo real.

---

## 🚀 Funcionalidades Principais

- 🔍 **Consultar mesas** (livres, ocupadas ou fechadas) com indicação visual clara  
- ➕ **Abrir mesa** com cadastro do nome do cliente  
- 📝 **Registrar pedidos** por mesa, incluindo seleção de produtos e quantidades  
- ❌ **Fechar mesa**, zerando informações e liberando para novo atendimento  
- 🔄 **Sincronização via REST API** entre app Android e servidor backend Java Spring Boot  
- ⚙️ **Configuração dinâmica do IP do servidor** via SharedPreferences para flexibilidade no ambiente local  

---

## 🧰 Tecnologias Utilizadas

| Tecnologia       | Descrição                                      |
|------------------|------------------------------------------------|
| Java             | Linguagem principal para app Android e backend |
| Android Studio   | Ambiente de desenvolvimento integrado (IDE)    |
| Volley           | Biblioteca para requisições HTTP assíncronas    |
| Spring Boot      | Framework para criação da API RESTful backend   |
| JSON             | Formato leve e padronizado para troca de dados  |
| Material Design  | Componentes UI modernos para Android             |

---

## 📋 Pré-requisitos

- Android Studio instalado  
- Emulador Android ou dispositivo físico  
- Java JDK instalado para backend  
- Servidor REST ativo (rodando a API Java Spring Boot)  
- Conexão de rede local para comunicação entre app e servidor  

---

## 🛠️ Como Executar

### 🔧 Configurando e Rodando o Servidor REST (Backend)

1. Importe o projeto Spring Boot no IntelliJ IDEA, Eclipse ou outra IDE Java.  
2. Configure a porta HTTP (padrão 8080) caso necessário.  
3. Execute a aplicação para iniciar o servidor REST.  
4. Endpoints disponíveis:  
   - `GET /mesas` - lista todas as mesas e seus status  
   - `PUT /mesa/{id}` - abre ou atualiza dados de uma mesa  
   - `DELETE /mesa/{id}` - fecha e libera a mesa  

---

### 📱 Executando o Aplicativo Android (Frontend)

1. Abra o projeto no Android Studio.  
2. No app, configure o IP do servidor REST (via interface ou código, usando SharedPreferences).  
3. Inicie o app em um dispositivo físico ou emulador Android.  
4. Na tela principal, selecione a mesa desejada para abrir, anotar pedidos e gerenciar seu status.  
5. Todas as alterações serão sincronizadas em tempo real com o backend via API REST.  

---

## 🗂️ Estrutura do Projeto

- **Modelos:** Classes Java para representar `Mesa` e `Produto`.  
- **Views:** Layouts XML com Material Design para interação amigável.  
- **Controladores:** Activities Android para abrir mesa, anotar pedidos, fechar mesa e menu principal.  
- **Comunicação:** Implementação do Volley para chamadas REST e tratamento de JSON.  
- **Backend:** API REST Java Spring Boot para CRUD das mesas e controle de status.  

---

## 🎯 Objetivos do Projeto

- Demonstrar integração Android + Backend RESTful  
- Praticar manipulação de dados assíncronos e persistência local  
- Aplicar boas práticas em UI/UX para apps móveis  
- Estudar arquitetura cliente-servidor simples e eficiente  

---

## 🤝 Como Contribuir

- Envie sugestões de melhorias ou correções via issues  
- Faça fork e crie pull requests com novas funcionalidades  
- Compartilhe feedbacks sobre usabilidade e performance  

---

## 📞 Contato

**Desenvolvedor:** Kauê Rocha da Fonseca  
**Email:** fonseeca.kaue@outlook.com

---

### 🌟 Gostou do projeto?  
Deixe uma ⭐ no repositório para ajudar na divulgação!

---

Obrigado por visitar o projeto! 🚀
