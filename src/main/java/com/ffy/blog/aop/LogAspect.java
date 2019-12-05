package com.ffy.blog.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/*
    @Aspect 标记此类是通知类
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //切点
    @Pointcut("execution(* com.ffy.blog.controller.*.*.*(..))")
    public void log(){};
    //前置通知
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        //获得 HttpServletRequest 实例对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获得URL地址
        String url = request.getRequestURL().toString();
        //获得请求ID
        String id = request.getRemoteAddr();
        //获得请求方法
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        //获得请求参数
        Object[] args = joinPoint.getArgs();

        RequestLog requestLog = new RequestLog(url,id,classMethod,args);
        logger.info("RequestLog : {}" , requestLog);
    }
    //后置通知
    @After("log()")
    public void doAfter(){
//        logger.info("-----doAfter-----");
    }
    //收到返回值然后执行通知，result 是返回值
    @AfterReturning(returning = "result" , pointcut = "log()")
    public void doAfterReturning(Object result){
        logger.info("结果返回值：" + result);
    }

    class RequestLog{
        public String url ;
        public String id ;
        public String classMethod ;
        public Object[] args ;

        public RequestLog() {
        }

        public RequestLog(String url, String id, String classMethod, Object[] args) {
            this.url = url;
            this.id = id;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", id='" + id + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}
