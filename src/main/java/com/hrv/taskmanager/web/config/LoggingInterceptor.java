package com.hrv.taskmanager.web.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, @Nullable HttpServletResponse response,
                             @Nullable Object handler) {
        log.info("---> {} - {}", request.getMethod(), request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        log.info("<--- {} - {}", request.getMethod(), request.getRequestURI());
    }

}
