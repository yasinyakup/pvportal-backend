package com.kalyon.pvportalbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.basePackage("package com.kalyon.pvportalbackend.controller"))
               .apis(RequestHandlerSelectors.any())
               // .paths(PathSelectors.regex("/api.*"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo(){
        return new ApiInfo(
                "PV Portal user management api",
                "user login and register management api",
                "1.0",
                "www.kalyonpv.com",
                new Contact("yasin","www.kalyonpv.com", "yyakufu@kalyonpv.com"),
                "free",
                "www.kalyonpv.com",
                Arrays.asList(new StringVendorExtension("kalyon", "PV"))
        );
    }
}
