package com.example.jpa.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpa.entity.Employee;
import com.example.jpa.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

	@Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
