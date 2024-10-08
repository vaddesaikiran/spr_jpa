package com.example.sjpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.sjpa.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
	
}

