# Getting Started

### Tecnologias e Ferramentas Utilizadas


- IDE: IntelliJ IDEA
- Linguagens: Kotlin e Java
- Framework: Spring Boot
- Gerenciamento de Dependências: Gradle
- Banco de Dados: MySQL
- Migrações de Banco de Dados: Flyway
- Contêinerização: Docker e Docker Compose
- Documentação: Swagger
- Testes: JUnit e Mockito
- Controle de Versão: Git
- Dbeaver para gerenciamento do banco de dados

![office](https://miro.medium.com/v2/resize:fit:2000/0*eIhVp0KXrXSSHORN.gif)


### Descrição do Projeto
A aplicação é um sistema de pedidos de pizza (pizzacode) que permite gerenciar clientes e pedidos de pizza. A aplicação utiliza Spring Boot para criar uma API RESTful e se conecta a um banco de dados MySQL para armazenar os dados.


Estrutura do Projeto
Modelos: As classes de modelo representam as entidades do sistema, como Pedido, Cliente e Pizza.
Controladores: As classes de controlador gerenciam as requisições HTTP e mapeiam os endpoints da API.
Repositórios: As interfaces de repositório são usadas para interagir com o banco de dados.
Configurações: Arquivos de configuração, como application.yml, definem as propriedades do Spring Boot e a conexão com o banco de dados.
Configuração do Banco de Dados
O banco de dados MySQL é configurado usando Docker Compose. O arquivo docker-compose.yml define o serviço MySQL, incluindo a imagem Docker, variáveis de ambiente e volumes.

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

