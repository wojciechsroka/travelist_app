package com.mkyong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.text.MessageFormat;

@SpringBootApplication
public class WildflySpringBootApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WildflySpringBootApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WildflySpringBootApplication.class, args);
    }

}
