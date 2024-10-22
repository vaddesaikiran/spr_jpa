package com.example.employeemanagement.service;

import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
 @Autowired
 private EmployeeRepository employeeRepository;

 public void addEmployee(Employee employee) {
     employeeRepository.save(employee);
 }

 public List<Employee> getAllEmployees() {
     return employeeRepository.findAll();
 }

 public Employee getEmployeeById(Long id) {
	 
	 if (id <= 0) {
         throw new IllegalArgumentException("Invalid employee ID: " + id);
     }
     return employeeRepository.findById(id).orElse(null);
 }
}
