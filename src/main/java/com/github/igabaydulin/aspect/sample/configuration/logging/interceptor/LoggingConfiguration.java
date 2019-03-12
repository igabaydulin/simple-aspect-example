package com.github.igabaydulin.aspect.sample.configuration.logging.interceptor;

import com.github.igabaydulin.aspect.sample.configuration.logging.LogMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Configuration
public class LoggingConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor());
    }

    @Bean
    public HandlerInterceptorAdapter loggingInterceptor() {
        return new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

                if (HandlerMethod.class.isAssignableFrom(handler.getClass())) {
                    LogMapping logMapping = ((HandlerMethod) handler).getMethodAnnotation(LogMapping.class);
                    if (Objects.nonNull(logMapping)) {
                        log.info("log interceptor is executed");
                        if (logMapping.value().equals("logging")) {
                            request
                                    .getParameterMap()
                                    .forEach((key, value) -> log
                                            .info("Request param {}: {}", key,
                                                    Stream
                                                            .of(value)
                                                            .collect(Collectors.joining(",", "[", "]"))));
                        }
                    }
                }

                return super.preHandle(request, response, handler);
            }
        };
    }
}
