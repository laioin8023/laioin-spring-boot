package com.laioin.boot.web.config;

import com.laioin.boot.web.base.BootDispatcherServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * web  server  配置类
 */
@Configuration
public class WebConfig {

    /**
     * 把 默认 DispatcherServlet  改为自己的 DispatcherServlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean dispatcherServletRegistration() {
        //注解扫描上下文
        AnnotationConfigWebApplicationContext applicationContext
                = new AnnotationConfigWebApplicationContext();
        //base package
        applicationContext.scan("com.laioin.boot");
        //通过构造函数指定dispatcherServlet的上下文
        BootDispatcherServlet rest_dispatcherServlet = new BootDispatcherServlet(applicationContext);

        //用ServletRegistrationBean包装servlet
        ServletRegistrationBean registrationBean
                = new ServletRegistrationBean(rest_dispatcherServlet);
        registrationBean.setLoadOnStartup(1);
        //指定urlmapping
        registrationBean.addUrlMappings("/*");
        //指定name，如果不指定默认为dispatcherServlet
        registrationBean.setName("rest");
        return registrationBean;
    }
}
