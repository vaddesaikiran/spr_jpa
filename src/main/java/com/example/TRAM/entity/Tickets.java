package com.example.TRAM.entity;

import jakarta.persistence.*;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String ticketId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

    private String startPlace;
    private String destination;
    private double price;

    public Tickets() {
        this.ticketId = generateTicketId();
    }


    private String generateTicketId() {
        Random random = new Random();
        int ticketIdValue = 10000 + random.nextInt(900000);
        return String.valueOf(ticketIdValue);
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}