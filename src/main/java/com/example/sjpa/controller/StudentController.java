package com.example.sjpa.controller;

import com.example.sjpa.entity.Student;
import com.example.sjpa.service.StudentServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("")
    public ResponseEntity<Page<Student>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Student> students = studentService.getAllStudents(PageRequest.of(page, size));
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/sorted")
    public ResponseEntity<Iterable<Student>> getAllStudentsSorted(
            @RequestParam(defaultValue = "name") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Iterable<Student> students = studentService.getAllStudentsSorted(sort);
        return ResponseEntity.ok(students);
    }
    
    
    @GetMapping("/paged-sorted")
    public ResponseEntity<Page<Student>> getAllStudentsPagedAndSorted(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Page<Student> students = studentService.getAllStudents(PageRequest.of(page, size, sort));
        return ResponseEntity.ok(students);
    }
    
}


