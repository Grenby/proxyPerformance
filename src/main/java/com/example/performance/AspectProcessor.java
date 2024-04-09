package com.example.performance;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectProcessor {

    @Around("@annotation(annotation)")
    public Object asp(ProceedingJoinPoint join, AspectAnnotation annotation) throws Throwable {
        return join.proceed();
    }

}
