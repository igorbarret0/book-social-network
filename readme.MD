<h1>Book Social Network</h1>

Book Social Network é uma aplicação web que permite aos usuários postar livros, avaliá-los, e interagir com outros leitores. A plataforma inclui funcionalidades como:

Postagem e avaliação de livros.
Autenticação e autorização de usuários com Spring Security e JWT.
Rotas protegidas para garantir a segurança e privacidade dos dados.
Tratamento de exceções para uma melhor experiência do usuário.
Organização por domínio para uma estrutura de código mais modular e escalável.

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [PostgreSQL](https://www.postgresql.org/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Security](https://spring.io/projects/spring-security)
- [JPA Auditing](https://docs.spring.io/spring-data/jpa/reference/auditing.html)
- [Docker](https://www.docker.com/)
- [Swagger](https://swagger.io/)


## Como Executar

- Clonar repositório git
```
git clone git@github.com:igorbarret0/book-social-network.git
```

- Construir o projeto:
```
./mvnw clean package
```

A API poderá ser acessada em [localhost:8888](http://localhost:8888).

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Postman](https://www.postman.com/)

-  USER
```
POST /auth/register -> Criar Conta
```

```
POST /auth/login -> Logar na Conta
```

- BOOK
```
POST /books -> Salvar um livro
```

```
GET /books/{book-id} -> Encontrar livro por ID
```

```
GET /books -> Encontrar todos os livros
```

```
POST /books/cover/{book-id} -> Fazer upload da capa do livro
```

```
GET /books/owner -> Encontrar todos os livros filtando pelo dono
```

```
GET /books/borrowed -> Encontrar todos os livros emprestados
```

```
GET /books/returned -> Encontrar todos os livros retornados
```

```
PATCH /books/shareable/{book-id} -> Atualizar shareable status do livro
```

```
PATCH /books/archived/{book-id} -> Atualizar archived status de um livro
```

```
POST /books/borrow/{book-id} -> Pegar um livro emprestado
```

```
PATCH /books/borrow/return/{book-id} -> Devolver um livro emprestado
```

```
PATCH /books/borrow/return/approved/{book-id} -> Aprovar a devolução de um livro
```

- FEEDBACKS
```
POST /feedbacks -> Avaliar um livro
```

```
GET /feedbacks/book/{book-id} -> Encontrar todos os feedbacks de um livro
```

