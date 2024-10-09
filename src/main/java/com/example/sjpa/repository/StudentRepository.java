package com.example.sjpa.repository;

import com.example.sjpa.entity.Student;

import org.springframework.data.repository.PagingAndSortingRepository;
public interface StudentRepository extends PagingAndSortingRepository<Student, Long>{
	
}

