package com.laioin.boot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/***
 * @ComponentScan("com.laioin.boot")
 * 解决多模块，自动注入不成功
 * 多个模块，共有包：com.laioin.boot
 */
@ComponentScan("com.laioin.boot")
@SpringBootApplication
public class ApiApplication {

    @Autowired
    private Environment env;
    private final Logger LGR = LoggerFactory.getLogger(ApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            LGR.debug("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                LGR.debug(beanName);
            }

            LGR.info(String.format("\n----------------------------------------------------------\n\t" +
                            "Application '%s' is running! Access URLs:\n\t" +
                            "Local: \t\thttp://localhost:%s\n\t" +
                            "The following profiles are active: %s\n" +
                            "----------------------------------------------------------",
                    env.getProperty("spring.application.name"),
                    env.getProperty("server.port", "8080"),
                    env.getProperty("spring.profiles.active")));

        };
    }
}
