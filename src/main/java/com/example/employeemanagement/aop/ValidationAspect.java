package com.example.employeemanagement.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {
    
    public static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.example.employeemanagement.service.EmployeeService.getEmployeeById(..)) && args(id)")
    public Object validateAndUpdate(ProceedingJoinPoint jp, Long id) throws Throwable {
        if (id < 0) {
            LOGGER.info("Employee ID is negative, updating it");
            id = -id;
            LOGGER.info("New Value: {}", id);
        }
        
        Object obj = jp.proceed(new Object[]{id});
        return obj;
    }
}
