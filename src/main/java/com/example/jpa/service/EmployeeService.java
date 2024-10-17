package com.example.jpa.service;

import com.example.jpa.projection.EmployeeProjection;
import com.example.jpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeProjection> getAllEmployees() {
        return employeeRepository.findAllEmployees();
    }
}
