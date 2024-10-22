package com.example.employeemanagement.entity;

import jakarta.persistence.*;;

@Entity
public class Employee {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String name;
 private String department;
 private String role;
 private double salary;

 public Employee() {
 }

 public Employee(String name, String department, String role, double salary) {
     this.name = name;
     this.department = department;
     this.role = role;
     this.salary = salary;
 }

 public Long getId() {
     return id;
 }

 public void setId(Long id) {
     this.id = id;
 }

 public String getName() {
     return name;
 }

 public void setName(String name) {
     this.name = name;
 }

 public String getDepartment() {
     return department;
 }

 public void setDepartment(String department) {
     this.department = department;
 }

 public String getRole() {
     return role;
 }

 public void setRole(String role) {
     this.role = role;
 }

 public double getSalary() {
     return salary;
 }

 public void setSalary(double salary) {
     this.salary = salary;
 }
}
