package com.laioin.boot.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 跨域 设置
 */
@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 所有路径
        registry.addMapping("/**")
                // 所有UA
                .allowedOrigins("*")
                .allowCredentials(true)
                // 所有 请求方式，get ，post 等等
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(7200);
    }

}