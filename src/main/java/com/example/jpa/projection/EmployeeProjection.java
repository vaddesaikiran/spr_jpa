package com.example.jpa.projection;

public class EmployeeProjection {
    private String name;
    private String email;

    public EmployeeProjection(String name, String email, double salary) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
