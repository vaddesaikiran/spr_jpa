package com.example.sjpa.service;

import com.example.sjpa.entity.Student;
import com.example.sjpa.repository.StudentRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
    
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = getStudentById(id);
        if (existingStudent != null) {
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setAddress(updatedStudent.getAddress());
            return studentRepository.save(existingStudent);
        } else {
            return null;
        }
    }


    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    
    public List<Student> getStudentsByEmailDomain(String domain) {
        return studentRepository.findStudentsByEmailDomain(domain);
    }
    
    public Student getTopStudentInCity(String city) {
        List<Student> students = studentRepository.findTopStudentInCity(city);
        return students.isEmpty() ? null : students.get(0);
    }

    public List<Student> getStudentsAbove90PercentFromEachCity() {
        return studentRepository.findStudentsAbove90PercentFromEachCity();
    }
}
