package com.example.jpa.repository;

import com.example.jpa.dto.EmployeeDTO;
import com.example.jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT new com.example.jpa.dto.EmployeeDTO(e.name, e.email, e.department) FROM Employee e")
    List<EmployeeDTO> findAllEmployees();
}
