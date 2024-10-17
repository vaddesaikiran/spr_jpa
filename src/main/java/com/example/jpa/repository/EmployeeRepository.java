package com.example.jpa.repository;

import com.example.jpa.entity.Employee;
import com.example.jpa.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    @Query("SELECT new com.example.jpa.projection.EmployeeProjection(e.name, e.email, e.salary) FROM Employee e")
    List<EmployeeProjection> findAllEmployeeProjections();
}
