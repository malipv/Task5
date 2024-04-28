package ru.inno.task5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "ru.inno.task5")
@EnableJpaRepositories(basePackages = "ru.inno.task5.repository")
@EntityScan(basePackages = "ru.inno.task5.entity")
public class Main {
    public static void main(String[] args) {
        System.out.println("Стартуем...");
        ApplicationContext ctx = SpringApplication.run(Main.class);
        System.out.println("Полетели...");
    }
}