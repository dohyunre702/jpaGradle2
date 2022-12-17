package com.springboot.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JpaGradle2Application {

    public static void main(String[] args) {
        SpringApplication.run(JpaGradle2Application.class, args);
    }

}
