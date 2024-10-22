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
     if (employee.getSalary() < 0) {
         throw new IllegalArgumentException("Salary cannot be negative");
     }
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
 
 
 public void updateEmployee(Long id, Employee updatedEmployee) {
     if (updatedEmployee.getSalary() < 0) {
         throw new IllegalArgumentException("Salary cannot be negative.");
     }
     Employee existingEmployee = getEmployeeById(id);
     if (existingEmployee == null) {
         throw new IllegalArgumentException("Employee not found with ID: " + id);
     }
     existingEmployee.setName(updatedEmployee.getName());
     existingEmployee.setDepartment(updatedEmployee.getDepartment());
     existingEmployee.setRole(updatedEmployee.getRole());
     existingEmployee.setSalary(updatedEmployee.getSalary());
     employeeRepository.save(existingEmployee);
 }
 
}
