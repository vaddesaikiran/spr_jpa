package com.example.employeemanagement.aop;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingAspect {
 private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

 @Before("execution(* *.*(..))")
 public void logBefore(JoinPoint joinPoint) {
     logger.info("Entering method");
 }
}

