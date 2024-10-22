package com.example.employeemanagement.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {
 private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

 @Before("execution(* com.example.employeemanagement.service.EmployeeService.getAllEmployees(..))")
 public void logBefore(JoinPoint joinPoint) {
     logger.info("Entering method: {}", joinPoint.getSignature().getName());
 }

 @After("execution(* com.example.employeemanagement.service.EmployeeService.getAllEmployees(..))")
 public void logAfter(JoinPoint joinPoint) {
     logger.info("Exiting method: {}", joinPoint.getSignature().getName());
 }
}

