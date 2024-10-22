package com.example.employeemanagement.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {
 private static final Logger logger = LoggerFactory.getLogger(PerformanceAspect.class);

 @Around("execution(* com.example.employeemanagement.service.EmployeeService.*(..))")
 public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
     long start = System.currentTimeMillis();
     logger.info("Starting method: {}", joinPoint.getSignature().getName());

     Object proceed = joinPoint.proceed();

     long executionTime = System.currentTimeMillis() - start;
     logger.info("Method {} executed in {} ms", joinPoint.getSignature().getName(), executionTime);
     return proceed;
 }
}

