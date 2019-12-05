package com.ffy.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StsticResourceConfig implements WebMvcConfigurer {

    //释放静态资源
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态文件
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //webjar文件
//        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
    }
}
