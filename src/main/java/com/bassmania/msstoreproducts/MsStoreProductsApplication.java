package com.bassmania.msstoreproducts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MsStoreProductsApplication {

    public static void main(String[] args) {
        // Retrieve execution profile from environment variable. If not present, default profile is selected.
        String profile = System.getenv("PROFILE");
        System.setProperty("spring.profiles.active", profile != null ? profile : "default");
        SpringApplication.run(MsStoreProductsApplication.class, args);
    }

}
