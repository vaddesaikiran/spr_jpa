package com.example.jpa.controller;

import com.example.jpa.dto.EmployeeDTO;
import com.example.jpa.dto.EmployeeMinimalDTO;
import com.example.jpa.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

	@Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    
    @GetMapping("/profile")
    public List<EmployeeMinimalDTO> getAllEmployeesManualMapping(){
    	return employeeService.getAllEmployeesManualMapping();
    }
}
