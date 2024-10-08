package com.example.sjpa.service;

import java.util.List;
import com.example.sjpa.entity.Student;

public interface StudentService {
    Student saveStudent(Student student);
    Student getStudentById(Long id);
    Student updateStudent(Long id, Student student);
    List<Student> getAllStudents();
    List<Student> findByCity(String city);
}
