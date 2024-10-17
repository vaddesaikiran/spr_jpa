package com.example.jpa.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.jpa.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
}
