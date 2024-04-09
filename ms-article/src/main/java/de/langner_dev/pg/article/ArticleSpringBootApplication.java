package de.langner_dev.pg.article;

import de.langner_dev.pg.article.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Objects;

@SpringBootApplication
@AllArgsConstructor
public class ArticleSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleSpringBootApplication.class, args);
    }
}
