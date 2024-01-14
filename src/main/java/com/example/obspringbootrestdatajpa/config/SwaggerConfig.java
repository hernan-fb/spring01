package com.example.obspringbootrestdatajpa.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/*
Configuración Swagger para la generación de la documentación de la api rest
* */
@Configuration
public class SwaggerConfig {
//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiDetails())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                //.apis(RequestHandlerSelectors.basePackage("com.example.obspringbootrestdatajpa.controllers"))
//                .paths(PathSelectors.any())
//                .build();
//
//        //.BookController.class)
//    }
//    public ApiInfo apiDetails() {
//        return new ApiInfo("Spring boot Hello Books API REST",
//                "Library API REST",
//                "1.0",
//                "https://github.com/hernan-fb",
//                new Contact("Hernán",
//                        "https://github.com/hernan-fb",
//                        "hfbrando@gmail.com"),
//                "MIT",
//                "https://github.com/hernan-fb",
//                Collections.emptyList());
//    }

    @Bean
    public OpenAPI customApi() {
        return new OpenAPI()
                .info(new Info().title("asdf")
                                .version("101010")
                                .description("Explicado")
                                .termsOfService("www.url.com")
                                .license(new License()
                                        .name("Apache 2.0")
                                        .url("licenseurl.com"))
                        );
    }
}
