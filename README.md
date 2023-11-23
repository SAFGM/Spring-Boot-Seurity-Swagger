<h1 align="center">
  Spring Boot - Arquitetura em Camadas
</h1>

## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [MAVEN](https://maven.apache.org/docs/3.9.5/release-notes.html)
- [JAVA](https://spring.io/projects/java-version-17)
- 
- [H2](https://www.h2database.com)

- Para obter maior proveito das funcionalidades do Swagger recomendo:
  -   manter a verão 2.6.0 do spring-boot
  -   manter a versão do swagger em 2.9.2

## Funcionalidades Implementadas
- Cadastro de beneficiário junto com seus documentos;
- Listar todos os beneficiários cadastrados;
- Listar todos os documentos de um beneficiário a partir de seu id;
- Atualizar os dados cadastrais de um beneficiário;
- Remover um beneficiário.
```

## Como Executar

- Clonar repositório git:
```
git clone https://github.com/SAFGM/Spring-Boot-Seurity-Swagger.git
```
- Construir o projeto:
```
./mvnw clean package
```
- Executar:
```
java -jar ./target/beneficiarios-1.0-SNAPSHOT.jar
```
- Testar :
```
http://localhost:8080/swagger-ui.html

01 passo: Cadastrar Beneficiário:
   Endpoint:  Beneficiario Controller > POST/api/benef 

   informe JSON  com esses atributos do beneficiarios:
   {
     "dtNascimento": "dd-MM-yyyy",
     "nome": "Nome do beneficiario"
   }

02 Passo: Cadastrar informações dos documentos relacionados ao beneficiario
   Endpoint:  Documento Controller > POST/api/doc

   informe JSON  com esses atributos:
   {
     "beneficiarioId": {
     "id": 0  // informe o numero do beneficiario cadastrado para associar os documentos

   },
      "descDocumento": "String", // Descrição do documento
      "tpDocumento": "String" // Tipo de documento - RG, CPF, CNH, entre outros...
   }

03 Passo: 
   Execute os demais endpoints para listar, atualizar e remover
    
```

