package com.example.employeemanagement.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
 private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

 @Before("execution(* com.example.employeemanagement.service.EmployeeService.*(..))")
 public void logBeforeMethod(JoinPoint joinPoint) {
     logger.info("Entering method: {}", joinPoint.getSignature().getName());
 }

 @After("execution(* com.example.employeemanagement.service.EmployeeService.*(..))")
 public void logAfterMethod(JoinPoint joinPoint) {
     logger.info("Exiting method: {}", joinPoint.getSignature().getName());
 }
 
 @AfterThrowing("execution(* com.example.employeemanagement.service.EmployeeService.getEmployeeById(..))")
 public void logMethodCrashed(JoinPoint joinPoint) {
     logger.info("Method has some issues: {}", joinPoint.getSignature().getName());
 }
 
 
 @AfterReturning("execution(* com.example.employeemanagement.service.EmployeeService.getEmployeeById(..))")
 public void logMethodExecutedSuccess(JoinPoint joinPoint) {
     logger.info("Method executed successfully: {}", joinPoint.getSignature().getName());
 }
 
}