# HubSpot Integration API

Este projeto é uma integração desenvolvida em Java (utilizando Spring Boot) que se conecta à API do HubSpot para realizar as seguintes funções:

- **Autenticação OAuth com o HubSpot:** Inicia o fluxo OAuth para obter um token de acesso.
- **Criação de contatos:** Permite a criação de um contato no CRM do HubSpot com as propriedades desejadas.
- **Recebimento de notificações via webhook:** Processa notificações de eventos enviados pelo HubSpot.

A documentação detalhada dos endpoints, parâmetros e exemplos de requisições está disponível via Swagger, o que evita a necessidade de expor exemplos diretamente neste arquivo.

---

## Estrutura do Projeto

A arquitetura da aplicação é baseada em REST e contempla os seguintes componentes:

- **Controllers:**  
  Responsáveis por expor os endpoints da API e orquestrar as chamadas para autenticação, criação de contatos e processamento de webhooks.

- **Modelos (Models):**  
  Classes que representam os dados de entrada e saída, como `HubspotModel`, `TokenModel` e `ContactModel`. Esses modelos definem as estruturas dos objetos manipulados pela aplicação.

- **Documentação Swagger (OpenAPI):**  
  Um arquivo YAML documenta todos os endpoints, parâmetros, schemas e exemplos de requisições/respostas. Esta documentação facilita a compreensão e o teste da API, dispensando a exposição de exemplos de requisições diretamente neste arquivo.

---

## Visão Geral dos Endpoints

A seguir, um resumo dos principais endpoints e seu funcionamento:

### 1. Configuração dos Parâmetros de Conexão

- **Endpoint:** `POST /hubspot/mappings`
- **Descrição:**  
  Define as credenciais e endpoints necessários para a integração com o HubSpot, armazenando os dados de conexão (como `clientId`, `redirectUri`, `clientSecret`, etc.) para uso futuro.
- **Detalhes:**  
  Consulte o Swagger para informações detalhadas sobre o payload e os exemplos de configuração.

### 2. Início do Fluxo OAuth

- **Endpoint:** `GET /hubspot/authorize`
- **Descrição:**  
  Inicia o fluxo de autorização OAuth, redirecionando o usuário para a página de autorização do HubSpot com os parâmetros configurados.
- **Detalhes:**  
  A URL de autorização é construída dinamicamente a partir dos parâmetros definidos em `HubspotModel`. Para maiores informações sobre o fluxo, consulte o Swagger.

### 3. Processamento do Callback OAuth

- **Endpoint:** `GET /hubspot/callback`
- **Descrição:**  
  Este endpoint é chamado pelo HubSpot após a autorização do usuário. Nele, a aplicação recebe o código de autorização e realiza a troca por um token de acesso.
- **Detalhes:**  
  O código recebido é utilizado para montar uma requisição que troca esse código por um token, armazenado no modelo `TokenModel`. Verifique o Swagger para detalhes completos do fluxo e exemplos.

### 4. Criação de Contato

- **Endpoint:** `POST /hubspot/contacts`
- **Descrição:**  
  Cria um novo contato no HubSpot com base nos dados informados no objeto `ContactModel`.
- **Detalhes:**  
  A requisição utiliza o token de acesso obtido previamente para autenticar a chamada. Caso o token esteja expirado ou inválido, o fluxo redireciona para a autorização novamente. Detalhes completos estão disponíveis no Swagger.

### 5. Recebimento de Notificações via Webhook

- **Endpoint:** `POST /hubspot/webhook`
- **Descrição:**  
  Recebe notificações de eventos (como a criação ou atualização de contatos) enviados pelo HubSpot.
- **Detalhes:**  
  O payload recebido é logado para posterior análise ou processamento. Para uma descrição completa do payload e dos exemplos, consulte o Swagger.

---

## Fluxo Geral da Integração

1. **Configuração Inicial:**  
   Configure os parâmetros de conexão chamando o endpoint `/hubspot/mappings`. Essa configuração engloba as credenciais e URLs necessárias para a comunicação com o HubSpot.

2. **Autorização:**  
   Ao acessar o endpoint `/hubspot/authorize`, o usuário é redirecionado para a página de autorização do HubSpot. Após conceder as permissões, o usuário é redirecionado para o endpoint `/hubspot/callback`.

3. **Troca do Código por Token:**  
   O endpoint `/hubspot/callback` recebe um código de autorização e o troca por um token de acesso, armazenado para uso nas operações seguintes.

4. **Criação de Contato:**  
   Com o token válido, a aplicação permite a criação de um contato via o endpoint `/hubspot/contacts`. Em caso de token expirado, o fluxo redireciona novamente para a autorização.

5. **Recebimento de Webhooks:**  
   O endpoint `/hubspot/webhook` processa as notificações de eventos enviados pelo HubSpot, permitindo monitorar a atividade e atualizar o sistema conforme necessário.

---

## Considerações Finais

- **Flexibilidade na Configuração:**  
  Através do endpoint `/hubspot/mappings`, é possível ajustar facilmente as configurações de conexão, facilitando a transição entre ambientes (desenvolvimento, homologação e produção).

- **Segurança no Fluxo OAuth:**  
  O fluxo de autorização garante que apenas usuários autenticados possam gerar tokens de acesso, mantendo a segurança da integração.

- **Documentação Completa via Swagger:**  
  O arquivo Swagger documenta detalhadamente cada endpoint, os modelos de dados e os fluxos de integração, dispensando a necessidade de expor exemplos de requisições neste arquivo.