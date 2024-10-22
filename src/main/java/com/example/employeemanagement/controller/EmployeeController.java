package com.example.employeemanagement.controller;

import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
 @Autowired
 private EmployeeService employeeService;

 @PostMapping
 public void addEmployee(@RequestBody Employee employee) {
     employeeService.addEmployee(employee);
 }

 @GetMapping
 public List<Employee> getAllEmployees() {
     return employeeService.getAllEmployees();
 }

 @GetMapping("/{id}")
 public Employee getEmployeeById(@PathVariable Long id) {
     return employeeService.getEmployeeById(id);
 }
}
