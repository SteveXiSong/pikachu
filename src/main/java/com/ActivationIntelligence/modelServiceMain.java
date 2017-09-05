package com.ActivationIntelligence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by Steve on 2/13/17.
 */
@SpringBootApplication
public class modelServiceMain extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(modelServiceMain.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(modelServiceMain.class);
    }

}
