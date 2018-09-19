package com.laioin.boot.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/***
 * @ComponentScan("com.laioin.boot")
 * 解决多模块，自动注入不成功
 * 多个模块，共有包：com.laioin.boot
 *
 * extends SpringBootServletInitializer 解决tomcat 中无法启动的情况
 *
 */
@ComponentScan("com.laioin.boot")
@SpringBootApplication
@EnableAutoConfiguration
public class ApiApplication  {

    @Autowired
    private Environment env;
    private final Logger LGR = LoggerFactory.getLogger(ApiApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }


}
