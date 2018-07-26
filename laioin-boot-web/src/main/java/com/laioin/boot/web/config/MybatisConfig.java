package com.laioin.boot.web.config;

import com.laioin.boot.common.pagination.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis 配置文件
 * MapperScan -->  注入 mapper 接口
 * EnableTransactionManagement  -->  开启事务管理
 * AutoConfigureAfter -->  在什么config 之后加载
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.laioin.boot.server.mapper")
public class MybatisConfig {

    /**
     * 注入 mybatis 分页，过滤器
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}
