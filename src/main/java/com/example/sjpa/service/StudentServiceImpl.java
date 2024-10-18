package com.example.sjpa.service;

import com.example.sjpa.entity.Student;
import com.example.sjpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl {

    @Autowired
    private StudentRepository studentRepository;

    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public Iterable<Student> getAllStudentsSorted(Sort sort) {
        return studentRepository.findAll(sort);
    }
    
    public Slice<Student> getAllStudentsSlice(Pageable pageable) {
        return studentRepository.findAllBy(pageable);
    }

}
