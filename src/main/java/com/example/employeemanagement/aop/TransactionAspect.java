package com.example.employeemanagement.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
public class TransactionAspect {

    private static final Logger logger = LoggerFactory.getLogger(TransactionAspect.class);

    // Pointcut for methods in EmployeeService that modify employee data (add, update)
    @Pointcut("execution(* com.example.employeemanagement.service.EmployeeService.addEmployee(..)) || " +
              "execution(* com.example.employeemanagement.service.EmployeeService.updateEmployee(..))")
    public void employeeServiceMethods() {}

    // Log before method execution (optional, to track the start of transaction)
    @Before("employeeServiceMethods()")
    public void beforeMethodExecution() {
        logger.info("Starting transaction for employee data modification.");
    }

    // Transactional advice after successful execution of the service method
    @Transactional
    @AfterReturning(pointcut = "employeeServiceMethods()")
    public void afterSuccessfulExecution() {
        logger.info("Transaction committed successfully for employee data modification.");
    }

    // Advice to handle transaction rollback after an exception is thrown
    @AfterThrowing(pointcut = "employeeServiceMethods()", throwing = "ex")
    public void manageTransaction(Throwable ex) {
        // Handle transaction rollback on exception
        logger.error("Transaction rolled back due to an error: {}", ex.getMessage());
    }
}
