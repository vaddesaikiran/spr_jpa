package com.example.jpa.service;

import com.example.jpa.dto.EmployeeDTO;
import com.example.jpa.dto.EmployeeMinimalDTO;
import com.example.jpa.entity.Employee;
import com.example.jpa.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

	@Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAllEmployees();
    }
    
    public List<EmployeeMinimalDTO> getAllEmployeesManualMapping() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employee -> new EmployeeMinimalDTO(employee.getName(), employee.getDepartment()))
                .collect(Collectors.toList());
    }
}
