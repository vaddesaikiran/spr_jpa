package com.example.sjpa.controller;

import com.example.sjpa.entity.Student;
import com.example.sjpa.service.StudentServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @PostMapping("")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Student existingStudent = studentService.updateStudent(id, updatedStudent);
        if (existingStudent != null) {
            return ResponseEntity.ok(existingStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Student>> getAllStudents() {
        Iterable<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @GetMapping("/by-email-domain")
    public ResponseEntity<List<Student>> getStudentsByEmailDomain(@RequestParam String domain) {
        List<Student> students = studentService.getStudentsByEmailDomain(domain);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    
    @GetMapping("/top-in-city")
    public ResponseEntity<Student> getTopStudentInCity(@RequestParam String city) {
        Student topStudent = studentService.getTopStudentInCity(city);
        return topStudent == null 
            ? new ResponseEntity<>(HttpStatus.NOT_FOUND) 
            : new ResponseEntity<>(topStudent, HttpStatus.OK);
    }

    @GetMapping("/above-90-percent-each-city")
    public ResponseEntity<List<Student>> getStudentsAbove90PercentFromEachCity() {
        List<Student> students = studentService.getStudentsAbove90PercentFromEachCity();
        return students.isEmpty() 
            ? new ResponseEntity<>(HttpStatus.NOT_FOUND) 
            : new ResponseEntity<>(students, HttpStatus.OK);
    }
}


