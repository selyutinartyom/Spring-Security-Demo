package org.demo;

import org.demo.utils.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Selutin_AV on 04.08.2015.
 */
@SpringBootApplication
@ComponentScan
@EnableJpaRepositories(basePackages = {"org.demo.repositories"})
public class Application {
    public static void main(String[] args) {
        // Генерация хэш-пароля для пользователя admin
//        System.out.println(Utils.GenerateHashedPassword("1"));

        SpringApplication.run(Application.class, args);
    }
}
