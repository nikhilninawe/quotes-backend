package com.nikhilninawe.quotes.main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.nikhilninawe.quotes.constants.Language;
import com.nikhilninawe.quotes.model.Quote;
import com.nikhilninawe.quotes.model.QuoteWrapper;
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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
@EntityScan("com.nikhilninawe.quotes.model")
@EnableJpaRepositories(basePackages = "com.nikhilninawe.quotes")
@ComponentScan("com.nikhilninawe.quotes.controller")
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
