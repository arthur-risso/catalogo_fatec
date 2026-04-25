🚀 Configuração do Banco de Dados - Catálogo FATEC
Este projeto utiliza Java 17+, Spring Boot 3 e PostgreSQL como banco de dados. Siga os passos abaixo para configurar o ambiente e rodar a aplicação.

🛠️ Passo 1: Preparar o PostgreSQL (PgAdmin)
Abra o seu PgAdmin 4.

Crie um novo banco de dados (Database) com o nome de sua preferência (ex: db_catalogo_fatec).

Certifique-se de que o serviço do PostgreSQL está rodando na porta padrão 5432.

⚙️ Passo 2: Configurar o application.properties
Localize o arquivo em: src/main/resources/application.properties e configure as credenciais de acordo com o seu ambiente local:

# URL de conexão: troque 'db_catalogo_fatec' pelo nome que você criou no PgAdmin
spring.datasource.url=jdbc:postgresql://localhost:5432/db_catalogo_fatec
spring.datasource.username=postgres
spring.datasource.password=sua_senha_aqui

# Driver do PostgreSQL
spring.datasource.driver-class-name=org.postgresql.Driver

# Configurações do Hibernate / JPA
# 'update' cria as tabelas automaticamente caso elas não existam
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

🏗️ Estrutura do Banco (Parte 1 e 4)
A aplicação está configurada para gerar as tabelas automaticamente. Ao rodar o projeto pela primeira vez, o Hibernate criará:

tb_categorias: Armazena as categorias dos produtos.

TB_PRODUTO: Armazena os itens do catálogo com relacionamento @ManyToOne para categorias.

🚦 Como Rodar
Clone o repositório.

Certifique-se de que as dependências do Maven foram baixadas.

Execute a classe principal: CatalogoApplication.java.

Acesse no navegador: http://localhost:8080/dashboard.
