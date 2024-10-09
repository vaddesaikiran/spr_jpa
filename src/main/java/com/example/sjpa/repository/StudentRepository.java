package com.example.sjpa.repository;

import com.example.sjpa.entity.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("FROM Student s WHERE s.email LIKE CONCAT('%', :domain)")
    List<Student> findStudentsByEmailDomain(@Param("domain") String domain);
    
    @Query(value = "SELECT * FROM student WHERE city = :city ORDER BY percentage DESC LIMIT 1", nativeQuery = true)
    List<Student> findTopStudentInCity(@Param("city") String city);

    @Query("FROM Student s WHERE s.percentage > 90 AND s.address.city IN (" +
           "SELECT s2.address.city FROM Student s2 WHERE s2.percentage > 90 GROUP BY s2.address.city)")
    List<Student> findStudentsAbove90PercentFromEachCity();
}
