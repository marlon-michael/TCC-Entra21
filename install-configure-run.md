
# _Configurando_

__banco de dados__

- requerimentos: MySql (8.0.30)
- opcional: DBeaver (interface de manipulação SQL)

    após configurar o MySql e suas variaveis de ambiente rode os comandos em "database/db-script.txt", ou carregue o dump em "database/database.sql" utilizando DBeaver

__backend (Java JDK 14.0.2)__
- opcional: IntelliJ IDEA Community

    instale no IntelliJ por meio do Maven (gerenciador de dependencias) o SptringBoot Framework, JPA(mysql), e lombok

__frontend (Angular 14.2.6)__

- quererimentos: Node (18.12)

    em "web-application/" rode o comando:

    `$ npm install`

<br>
<br>

# _Rodando_

__banco de dados__

- para iniciar o banco de dados no terminal de comando rode:
    
    `$ mysqld --console`
    
__backend (Java JDK 14.0.2)__

- para iniciar o backend rode classe java principal: TransportadoraApplication utilizando Springboot via IDE ou terminal de comando

    _API ficará disponível em localhost:8080_

__frontend (Angular 14.2.6)__

- para iniciar a aplicação web no terminal de comando, vá até "web-application/" e rode:
    
    `$ npm start`
    
    _Aplicação ficará disponível em localhost:4200_