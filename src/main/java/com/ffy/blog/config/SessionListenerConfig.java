package com.ffy.blog.config;

import com.ffy.blog.interceptor.SessionListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SessionListenerConfig  implements WebMvcConfigurer {

    @Bean
    public ServletListenerRegistrationBean<SessionListener> listenerRegist() {
        ServletListenerRegistrationBean<SessionListener> srb = new ServletListenerRegistrationBean<SessionListener>();
        srb.setListener(new SessionListener());
        System.out.println("listener");
        return srb;
    }

}
