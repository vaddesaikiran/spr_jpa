package com.example.TRAM.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique ID for each payment transaction

    @Column(unique = true, nullable = false)
    private String transactionId;  // Unique transaction ID

    private String status;  // Transaction status (PENDING, SUCCESS, FAILURE)
    private double amount;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

    // Constructor
    public Payments() {
        this.transactionId = generateTransactionId();  // Generate a random transaction ID
    }

    // Method to generate a random transaction ID
    private String generateTransactionId() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);  // Generate a random 10-character ID
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    
}