package com.example.TRAM.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique ID for each user

    @Column(unique = true, nullable = false)
    private String username;  // Unique username

    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private BankAccount bankAccount;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private List<Tickets> tickets;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public List<Tickets> getTickets() {
        return tickets;
    }

    public void setTickets(List<Tickets> tickets) {
        this.tickets = tickets;
    }
}