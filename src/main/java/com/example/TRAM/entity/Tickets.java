package com.example.TRAM.entity;

import jakarta.persistence.*;
import java.util.Random;

@Entity
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique ID for each ticket

    @Column(unique = true, nullable = false)
    private String ticketId;  // Unique ticket ID

    private String username;  // Username of the person booking the ticket
    private String startPlace;  // Starting place
    private String destination;  // Destination
    private double price;  // Price of the ticket

    // Constructor
    public Tickets() {
        this.ticketId = generateTicketId();  // Generate a random ticket ID
    }

    // Method to generate a random ticket ID (5-6 digits)
    private String generateTicketId() {
        Random random = new Random();
        int ticketIdValue = 10000 + random.nextInt(900000); // Generates a number between 10000 and 999999
        return String.valueOf(ticketIdValue);  // Convert to String
    }

    // Getters and Setters

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
