package com.github.jeffersonrolino.avaliacao_sprint_5_task_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class AvaliacaoSprint5Task1Application {

    public static void main(String[] args) {
        SpringApplication.run(AvaliacaoSprint5Task1Application.class, args);
        System.out.println(LocalDateTime.now());
    }

}
