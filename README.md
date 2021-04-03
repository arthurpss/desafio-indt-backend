# Markdown Desafio

## Descrição
    Aplicação WEB em arquitetura RESTful onde é possível:
    - Listar produtos (vitrine)
    - Cadastrar produto
    - Editar produto
    - Deletar produto
## Tecnologias utilizadas
    Angular 11, Bootstrap, Java 11, Spring Framework, Docker e Swagger

## Requisitos:
- Ter o maven instalado
- Ter o docker instalado
- Ter o node(versão 10) instalado
- Ter as portas 3306, 8080 e 4200 disponíveis
 
## Passo a passo:
- Clonar repositórios:
    - backend: https://github.com/arthurpss/desafio-indt-backend.git
    - frontend: https://github.com/arthurpss/desafio-indt.git
- Rodar no diretório onde o **backend** foi clonado:
    - docker-compose up -d
    - mvn spring:boot run
- Rodar no diretório onde o **frontend** foi clonado:
    - npm install
    - npm start

## Swagger:
    Após realizar o passo a passo informado acima, acesse a documentação da API em: [Swagger](http://localhost:8080/swagger-ui.html)