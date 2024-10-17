package com.example.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.entity.Employee;
import com.example.jpa.service.EmployeeService;

import java.util.List;

@RestController
public class EmployeeController {

	@Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
