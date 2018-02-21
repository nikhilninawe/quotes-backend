package com.nikhilninawe.quotes.main;

import com.nikhilninawe.quotes.model.Quote;
import com.nikhilninawe.quotes.repository.QuoteRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.nikhilninawe.quotes.model")
@EnableJpaRepositories(basePackages = "com.nikhilninawe.quotes")
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(QuoteRepo repository) {
        return (args) -> {
            // save a couple of customers
            Quote q = new Quote();
            q.setApproved(false);
            q.setAuthor("Nikhi111");
//            q.setLanguage(Language.en);
            q.setText("Howdy");
            q.setUrl("Test");
            repository.save(q);
        };
    }
}
