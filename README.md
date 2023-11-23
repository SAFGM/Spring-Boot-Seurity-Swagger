<h1 align="center">
  Spring Boot - Arquitetura em Camadas
</h1>

## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JDBC](https://spring.io/projects/spring-data-jdbc)
- [H2](https://www.h2database.com)

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

01 passo: Beneficiario Controller > POST/api/benef Salva Beneficiário

Cadastrar dados do beneficiarios:
{
  "dtNascimento": "dd-MM-yyyy",
   "nome": "Nome do beneficiario"
}

02 - Passo: Documento Controller > POST/api/doc Salva Documento
Cadastrar informações dos documentos relacionados ao beneficiario

{
  "beneficiarioId": {
    "id": 0  // informe o numero do beneficiario cadastrado para associar os documentos

  },
    "descDocumento": "String", // Descrição do documento
    "tpDocumento": "String" // Tipo de documento - RG, CPF, CNH, entre outros...
}
```

