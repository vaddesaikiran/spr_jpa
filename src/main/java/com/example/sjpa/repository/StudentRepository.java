package com.example.sjpa.repository;

import com.example.sjpa.entity.Student;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long>{

	Slice<Student> findAllBy(Pageable pageable);
	
}

