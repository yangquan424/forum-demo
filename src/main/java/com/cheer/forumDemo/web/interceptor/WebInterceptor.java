package com.cheer.forumDemo.web.interceptor;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

;

//拦截器
@Component
@Log4j2
public class WebInterceptor implements HandlerInterceptor {
    //前置处理，返回false表示请求到此结束，不再往下执行
    //如果返回true，则继续执行（交给下一个拦截器前置处理方法或者请求目标处理方法）
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.traceEntry();
        return true;
    }

    //后置处理
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //在完后才能之后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
