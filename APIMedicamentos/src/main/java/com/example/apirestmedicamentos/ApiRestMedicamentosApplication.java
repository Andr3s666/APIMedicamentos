// 📁 ApiRestMedicamentosApplication.java
package com.example.apirestmedicamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync; // 🆕 Import nuevo

@SpringBootApplication
@EnableAsync // 🆕 ACTIVAR FUNCIONALIDAD ASÍNCRONA (para que los eventos no bloqueen)
public class ApiRestMedicamentosApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiRestMedicamentosApplication.class, args);
    }
}