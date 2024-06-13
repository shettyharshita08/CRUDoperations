package com.operations.catoperations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Cat Operations",
                version = "1.0.0",
                description = "Cat Operations API Documentation"
        ),
        servers = @Server(
                url = "http://localhost:8080",
                description = "Cat Operations Local Server"
        )
)
public class CatOperationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatOperationsApplication.class, args);
    }

}
