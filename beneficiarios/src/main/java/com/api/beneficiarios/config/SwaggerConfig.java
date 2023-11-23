package com.api.beneficiarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api")
                .select()
                .paths(regex("/api/.*"))
                .build().apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API")
                .description("Service API")
                .contact(new Contact("Sávio Costa", "https://github.com/SAFGM", "Saviojfcosta@hotmail.com"))
                .license("License Version 2.0")
                .licenseUrl("https://github.com/SAFGM")
                .version("1.0")
                .build();
    }


}
