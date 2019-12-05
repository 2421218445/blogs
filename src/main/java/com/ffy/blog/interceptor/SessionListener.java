package com.ffy.blog.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("销毁："+se.getSession().getId());
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("创建："+se.getSession().getId());
    }
}
