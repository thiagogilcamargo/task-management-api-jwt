# Sistema de Gestão de Tarefas com Autenticação JWT

## Integrantes do Grupo
- Thiago Gil Camargo - RM 551211

## Descrição
Este projeto é um Sistema de Gestão de Tarefas que utiliza autenticação baseada em JWT para proteger endpoints e gerenciar tarefas. O sistema permite o cadastro de usuários, login, criação, visualização, atualização e exclusão de tarefas.

## Instruções para Rodar o Projeto

1. Clone o Repositório

    ```bash
    git clone https://github.com/task-management-api-jwt.git
    ```

2. Navegue até o Diretório do Projeto

    ```bash
    cd seu-diretorio
    ```

3. Compile e Rode o Projeto

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4. Testes
   Utilize ferramentas como Insomnia ou Postman para testar os endpoints. Certifique-se de que o banco de dados está configurado corretamente e acessível.

## Endpoints

### Cadastro de Usuário
- **Método:** POST
- **URL:** /auth/register

**Corpo da Requisição:**
```json
{
"name": "Nome do Usuário",
"email": "usuario@exemplo.com",
"password": "senha123"
}
```

### Login
- **Método:** POST
- **URL:** /auth/login

**Corpo da Requisição:**
```json
{
"email": "usuario@exemplo.com",
"password": "senha123"
}
```

**Resposta Esperada:**
```json
{
"token": "seu-jwt-token-aqui"
}
```

### Status das Tarefas
- **Método:** GET
- **URL:** /public/status

### Criar Tarefa
- **Método:** POST
- **URL:** /tasks
- **Cabeçalho:** Authorization: Bearer seu-jwt-token-aqui

**Corpo da Requisição:**
```json
{
"title": "Título da Tarefa",
"description": "Descrição da Tarefa",
"dueDate": "2024-12-31T23:59:59",
"status": "Pendente"
}
```

### Visualizar Tarefas
- **Método:** GET
- **URL:** /tasks
- **Cabeçalho:** Authorization: Bearer seu-jwt-token-aqui

### Atualizar Tarefa
- **Método:** PUT
- **URL:** /tasks/{id}
- **Cabeçalho:** Authorization: Bearer seu-jwt-token-aqui

**Corpo da Requisição:**
```json
{
"title": "Novo Título",
"description": "Nova Descrição",
"dueDate": "2024-12-31T23:59:59",
"status": "Em Andamento"
}
```

### Excluir Tarefa
- **Método:** DELETE
- **URL:** /tasks/{id}
- **Cabeçalho:** Authorization: Bearer seu-jwt-token-aqui

## Como Configurar o Cabeçalho de Autorização

Para acessar os endpoints que requerem autenticação, é necessário incluir o token JWT no cabeçalho da requisição. Siga os passos abaixo para configurar o cabeçalho de autorização nas ferramentas Insomnia e Postman:

### No Insomnia
1. Selecione o Endpoint: Escolha o endpoint que deseja testar.
2. Vá para a Aba de Cabeçalhos: Na área de configuração da requisição, vá para a aba "Headers".
3. Adicione o Cabeçalho de Autorização:
    - **Chave:** Authorization
    - **Valor:** Bearer seu-jwt-token-aqui
4. Envie a Requisição: Clique em "Send" para enviar a requisição com o cabeçalho configurado.

### No Postman
1. Selecione o Endpoint: Escolha o endpoint que deseja testar.
2. Vá para a Aba de Cabeçalhos: Na área de configuração da requisição, vá para a aba "Headers".
3. Adicione o Cabeçalho de Autorização:
    - **Chave:** Authorization
    - **Valor:** Bearer seu-jwt-token-aqui
4. Envie a Requisição: Clique em "Send" para enviar a requisição com o cabeçalho configurado.

**Nota:** Substitua `seu-jwt-token-aqui` pelo token JWT real obtido após o login. Se o token estiver incorreto ou expirado, você receberá um erro de autenticação.
