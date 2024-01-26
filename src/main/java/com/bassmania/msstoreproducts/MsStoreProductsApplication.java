package com.bassmania.msstoreproducts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@OpenAPIDefinition(info = @Info(
        title = "E-Bass Store Products API",
        description = "API para la gestión del catálogo de productos de la tienda de bajos eléctricos y accesorios.",
        version = "1.0.0"))
public class MsStoreProductsApplication {
    public static void main(String[] args) {
        // Retrieve execution profile from environment variable. If not present, default profile is selected.
        String profile = System.getenv("PROFILE");
        System.setProperty("spring.profiles.active", profile != null ? profile : "default");
        SpringApplication.run(MsStoreProductsApplication.class, args);
    }

}
