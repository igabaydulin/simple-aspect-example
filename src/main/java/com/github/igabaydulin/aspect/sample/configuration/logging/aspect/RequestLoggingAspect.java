package com.github.igabaydulin.aspect.sample.configuration.logging.aspect;

import com.github.igabaydulin.aspect.sample.configuration.logging.LogMapping;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Aspect
@Component
public class RequestLoggingAspect {

    @Pointcut("within(com.github.igabaydulin.aspect.sample.controller.*) && @annotation(logMapping)")
    public void controller(LogMapping logMapping) {
    }

    @Before(value = "controller(logMapping)", argNames = "logMapping")
    public void invoke(LogMapping logMapping) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        if (logMapping.value().equals("logging")) {
            log.info("log aspect is executed");
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
