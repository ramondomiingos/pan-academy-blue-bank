# BancoPan_GamaAulas_Projeto Final

<p align="center">
  <a href="https://bancopan.corporate.gama.academy/" target="_blank">
    <img align="center" width="300" src="https://github.com/Paulo-Ultra/Banco_Pan_Training/blob/main/Imagem%20Banco%20Pan.png" style="max-width:100%;">
     </a>
</p>
<div align="center">

![workflowStatus](https://github.com/ramondomiingos/pan-academy-blue-bank/actions/workflows/main.yml/badge.svg)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ramondomiingos_pan-academy-blue-bank&metric=coverage)](https://sonarcloud.io/summary/new_code?id=ramondomiingos_pan-academy-blue-bank)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=ramondomiingos_pan-academy-blue-bank&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=ramondomiingos_pan-academy-blue-bank)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=ramondomiingos_pan-academy-blue-bank&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=ramondomiingos_pan-academy-blue-bank)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=ramondomiingos_pan-academy-blue-bank&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=ramondomiingos_pan-academy-blue-bank)
</div>


<h3 align="center">
PAN ACADEMY É UM PROGRAMA DE FORMAÇÃO À ESPECIALIZAÇÃO EM <i>CLOUD</i> COM JAVA
</h3>



## ⚙️ Programa 

O programa dispõe de uma grade de estudos de cerca 390 horas de conteúdo, e é estruturado em 10 semanas de treinamento. Contendo aulas gravadas e também ao vivo, porém as aulas e
mentorias  foram assistidas nos horários pré determinados. 

* [Ementa Pan Academy](https://github.com/Paulo-Ultra/Banco_Pan_Training/blob/main/Ementa/%5BEmenta%5D%20Pan%20Academy%20-%20Java%20e%20AWS%20(Recupera%C3%A7%C3%A3o%20Autom%C3%A1tica).pdf)

 E o fechamento com um projeto final conforme o seguinte documento: 

* [Desafio Final - Projeto Blue Bank](https://github.com/Paulo-Ultra/Banco_Pan_Training/blob/main/Desafio%20final%20-%20BlueBank.docx.pdf)



## ⚙️ Projeto Final / Repositório 

- [Repositório do Projeto](https://github.com/ramondomiingos/pan-academy-blue-bank)

  

## ⚙️ Alunos participantes / Github

- [Brenda Pereira Ornelas](https://github.com/Brenda-pereira)

- [Iasmin Corrêa Araújo](https://github.com/iasminaraujoc)

- [Paulo Ricardo Freire Ultra](https://github.com/Paulo-Ultra)

- [Ramon Domingos Duarte Oliveira](https://github.com/ramondomiingos)

- [William Silva de Jesus](https://github.com/williamjesusdev)


## ⚙️ Dependências 

* [JDK 11: Para executar o Projeto](https://www.oracle.com/java/technologies/downloads/#java11)

* [Maven 3.8.3: Para executar build do Projeto](https://maven.apache.org/download.cgi)

## ⚙️ Executando o projeto
``` shell 
# CHECANDO A VERSÃO DO JAVA
java -version

# CHECANDO A VERSÃO DO MVN
mvn -v

# INICIANDO O PROJETO
mvn spring-boot:run

# BUILD O PROJETO
mvn clean install
```

## Metodologias ágeis e DevOps

Com o objetivo de integras contínuas de pequenas partes usáveis,optamos usar o [Scrum](https://www.atlassian.com/br/agile/scrum), para termos um feedback do que estava ficando pronto, no caso do projeto o cliente final, que avaliava e gerava feedbacks, para criação de novas features eramos nós mesmo da equipe. E visando um fluxo de entrega otimizado, adotamos práticas do DevOps, que serão expostos nos próximos tópicos, como fizemos cada etapa:
<p align="center">
    <img align="center" width="300" src="https://www.agitma.nl/wp/wp-content/uploads/2019/12/devops_scrum.png" style="max-width:100%;">
</p>


- Plan 

Após identificar todas os atores, funções que possuíamos, começamos a modelar a primeira versão do banco de dados. Usamos o Jira, organizando épicos, que eram as pequenas partes entregáveis e dentro delas as tasks, com extimativas de horas a a serem gastas, e responsáveis, possíveis impedimentos.

- Code

Utilizamos Java 11, SpringBoot, Mysql e docker, para termos como subir um ambiente totalmente configurado facilmente. Em seguida adicionamos o [FlyWay](https://flywaydb.org/) para termos liberdade em mudar algo do banco de dados de maneira fácil, como o scrum permite.

Precisamos adicionar os serviços da [AWS lambda](https://aws.amazon.com/pt/lambda/),  [AWS SNS](https://aws.amazon.com/pt/sns/), para notificações de transações.Utilizamos o [AWS RDS](https://aws.amazon.com/pt/rds/)  como serviço do banco de dados.

Optmaos para usar variáveis de ambiente para armazenamento de credenciais, aqui no github disponibilizado através do  [secrets](https://docs.github.com/pt/actions/security-guides/encrypted-secrets) 
- Build

Utilizamos o maven, e o [GitHubActions](https://github.com/features/actions) para realizar o o deploy de nossa aplicação.
- Test


A aplicação possui [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ramondomiingos_pan-academy-blue-bank&metric=coverage)](https://sonarcloud.io/summary/new_code?id=ramondomiingos_pan-academy-blue-bank) de cobertura de código, optamos por testar a camada mais alta da aplicação, os controllers, para também atingir as camadas inferiores, como os services e os repositorys.

- Release

Lançamos 3 versões, onde  0.0.1, ja contemplava entregáveis como as operações básicas, em seguida a 0.1.0 ja possuia a integração com a [AWS lambda](https://aws.amazon.com/pt/lambda/),  [AWS SNS](https://aws.amazon.com/pt/sns/), validações com [sonarCloud](https://sonarcloud.io/project/overview?id=ramondomiingos_pan-academy-blue-bank), e por último, a 1.0.0 com testes cobrindo 81% da aplicação, poucos code smell. 
- Deploy

Por questões de segurança na hora da apresentação, resolvemos subir com o [beanstalk](https://aws.amazon.com/pt/elasticbeanstalk/) , com o [ec2](https://aws.amazon.com/pt/ec2/), 
uma aplicação no [Heroku](https://www.heroku.com/), e configuramos um [API  Gateway](https://aws.amazon.com/pt/api-gateway/),para centralizar as url do beanstalk e lambda.

- Operate

Links úteis da aplicação: 

**Repositórios**

Projeto:  https://github.com/ramondomiingos/pan-academy-blue-bank

Repositório do Lambda: https://github.com/iasminaraujoc/lambda-blue-bank

**Aplicações**

Aplicação EBS: http://t2s7bluebank.us-east-1.elasticbeanstalk.com/

Aplicaçao EC2: http://ec2-54-175-137-20.compute-1.amazonaws.com:5005

API Gateway (Configuração): https://console.aws.amazon.com/apigateway/home?region=us-east-1#/apis/pzqujsr0ch/resources/er0o0zdtf8
API Gateway (Aplicação) : https://pzqujsr0ch.execute-api.us-east-1.amazonaws.com/final

Lambda: https://us-east-1.console.aws.amazon.com/lambda/home?region=us-east-1#/functions/t2-s7-details-blue-bank

**Organização**

Sonar: https://sonarcloud.io/project/overview?id=ramondomiingos_pan-academy-blue-bank

Jira: https://prf12.atlassian.net/jira/software/projects/BLUEB7/boards/2 


## 🌐 Endpoints
📄  As urls interativas, construídas através do [Swagger](https://swagger.io/tools/swagger-ui/), pode ser acessadas no link:
[http://t2s7bluebank.us-east-1.elasticbeanstalk.com/](http://t2s7bluebank.us-east-1.elasticbeanstalk.com/)

| Método | URL        | Finalidade       |   
|--------|------------|------------------|
| POST   |/auth/login | Fazer login na aplicação|
| GET    |/clients  | Lista todos os clientes|
| POST   |/clients  | Adiciona um novo cliente|
| GET    |/clients/{id} | Detalha um cliente pelo id|
| PUT    |/clients/{id} | Atualiza o cliente pelo id|
| PUT    |/clients/{id}/delete | Desativa o cliente pelo id|
| PUT    |/clients/{id}/block | Bloqueia o cliente pelo id|
| GET    |/accounts | Lista todas as contas|
| POST   |/accounts | Criar uma nova conta|   
| PUT    |/accounts/{id}  | Atualiza uma conta pelo id|
| GET    |/accounts/{id}  | Detalha uma conta pelo id|   
| DELETE |/accounts/{id}  | Desativar uma conta pelo id| 
| GET    |/accounts/{id}/extract  | Detalha um cliente e mostra o extrato de transações|
| GET    |/addresses  | Lista todos os endereços|
| POST   |/addresses  | Adiciona um novo endereço|
| GET    |/addresses/{id} | Busca um endereço por id|
| PUT    |/addresses/{id} | Atualiza um endereço por id|
| DELETE |/addresses/{id} | Deleta um endereço por id|
| GET    |/transactions | Lista todas as transações|
| GET    |/transactions{id} | Lista uma transação pelo id|
| POST   |/transactions/deposit | Realiza um depósito em uma conta|
| POST   |/transactions/transfer/{idAccount} | Realiza uma trasnferência de uma conta para outra|
| POST   |/transactions/withdraw/{idAccount} | Realiza um saque em uma conta|


## 📁 Diretórios e arquivos
```
.
|   .gitignore
|   buildspec.yml
|   docker-compose.yml
|   Dockerfile
|   mvnw
|   mvnw.cmd
|   pom.xml
|   README.md
|   system.properties
+---.github
|   \---workflows
|           main.yml
|                  
+---src
|   +---main
|   |   +---java
|   |   |   \---com
|   |   |       \---panacademy
|   |   |           \---squad7
|   |   |               \---bluebank
|   |   |                   |   BlueBankApplication.java
|   |   |                   |   
|   |   |                   +---configs
|   |   |                   |   |   AwsSnsConfig.java
|   |   |                   |   |   OpenApiConfig.java
|   |   |                   |   |   WebSecurityConfig.java
|   |   |                   |   |   
|   |   |                   |   \---security
|   |   |                   |           AuthTokenFilter.java
|   |   |                   |           JwtUtils.java
|   |   |                   |           
|   |   |                   +---domain
|   |   |                   |   +---enums
|   |   |                   |   |       AccountType.java
|   |   |                   |   |       ClaimType.java
|   |   |                   |   |       ClientType.java
|   |   |                   |   |       RoleType.java
|   |   |                   |   |       StatusType.java
|   |   |                   |   |       TransactionType.java
|   |   |                   |   |       
|   |   |                   |   +---models
|   |   |                   |   |       Account.java
|   |   |                   |   |       Address.java
|   |   |                   |   |       Client.java
|   |   |                   |   |       Transaction.java
|   |   |                   |   |       User.java
|   |   |                   |   |       
|   |   |                   |   \---repositories
|   |   |                   |           AccountsRepository.java
|   |   |                   |           AddressesRepository.java
|   |   |                   |           ClientsRepository.java
|   |   |                   |           TransactionsRepository.java
|   |   |                   |           UsersRepository.java
|   |   |                   |           
|   |   |                   +---exceptions
|   |   |                   |   |   BlueBankRunTimeExceptionHandler.java
|   |   |                   |   |   ContentNotFoundException.java
|   |   |                   |   |   InvalidInputException.java
|   |   |                   |   |   
|   |   |                   |   \---dtos
|   |   |                   |           ApiExceptionsDTO.java
|   |   |                   |           
|   |   |                   +---services
|   |   |                   |   |   AccountsService.java
|   |   |                   |   |   AddressesService.java
|   |   |                   |   |   ClientsService.java
|   |   |                   |   |   TransactionsService.java
|   |   |                   |   |   UsersService.java
|   |   |                   |   |   
|   |   |                   |   \---impl
|   |   |                   |           AccountsServiceImpl.java
|   |   |                   |           AddressesServiceImpl.java
|   |   |                   |           ClientsServiceImpl.java
|   |   |                   |           TransactionsServiceImpl.java
|   |   |                   |           UsersServiceImpl.java
|   |   |                   |           
|   |   |                   \---web
|   |   |                       +---controllers
|   |   |                       |       AccountsController.java
|   |   |                       |       AddressesController.java
|   |   |                       |       AuthController.java
|   |   |                       |       ClientsController.java
|   |   |                       |       TransactionsController.java
|   |   |                       |       
|   |   |                       +---dtos
|   |   |                       |   +---request
|   |   |                       |   |       AccountRequest.java
|   |   |                       |   |       AccountUpdateRequest.java
|   |   |                       |   |       AddressRequest.java
|   |   |                       |   |       ClientRequest.java
|   |   |                       |   |       DepositRequest.java
|   |   |                       |   |       LoginRequest.java
|   |   |                       |   |       TransferRequest.java
|   |   |                       |   |       WithdrawRequest.java
|   |   |                       |   |       
|   |   |                       |   \---response
|   |   |                       |           AccountResponse.java
|   |   |                       |           AddressResponse.java
|   |   |                       |           ClientResponse.java
|   |   |                       |           JwtResponse.java
|   |   |                       |           TransactionResponse.java
|   |   |                       |           
|   |   |                       \---helpers
|   |   |                           +---annotations
|   |   |                           |       CpfCnpj.java
|   |   |                           |       
|   |   |                           +---converters
|   |   |                           |       AccountConverter.java
|   |   |                           |       AddressConverter.java
|   |   |                           |       AuthConverter.java
|   |   |                           |       ClientConverter.java
|   |   |                           |       TransactionConverter.java
|   |   |                           |       UserConverter.java
|   |   |                           |       
|   |   |                           \---validators
|   |   |                                   CpfCnpjValidator.java
|   |   |                                   
|   |   \---resources
|   |       |   application.properties
|   |       |   openapi.properties
|   |       |   secret.properties
|   |       |   
|   |       \---db
|   |           \---migration
|   |                   V1__baseline_migration.sql
|   |                   V2__fix_transactions_table.sql
|   |                   V3__fix_enums_tables.sql
|   |                   V4__change_transactions_type.sql
|   |                   V5__change_account_table.sql
|   |                   
|   \---test
|       +---java
|       |   \---com
|       |       \---panacademy
|       |           \---squad7
|       |               \---bluebank
|       |                   |   BlueBankApplicationTests.java
|       |                   |   
|       |                   \---controllers
|       |                           AccountsControllerTests.java
|       |                           AddressesControllerTests.java
|       |                           AuthControllerTests.java
|       |                           ClientsControllerTests.java
|       |                           TransactionsControllerTests.java
|       |                           
|       \---resources
|               application.properties
|               secret.properties
|
```
<div align="center"> 

## Developed By _'password'_ 

</div>
