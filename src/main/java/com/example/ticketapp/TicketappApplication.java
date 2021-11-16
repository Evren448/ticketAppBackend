package com.example.ticketapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TicketappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketappApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.example.ticketapp"))                                       
          .build();                                           
    }

}
