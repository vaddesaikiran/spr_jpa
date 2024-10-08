package com.example.sjpa.repository;

import com.example.sjpa.entity.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);

	List<Student> findByAddress_City(String city);
}
