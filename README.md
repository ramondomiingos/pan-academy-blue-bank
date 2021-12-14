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
