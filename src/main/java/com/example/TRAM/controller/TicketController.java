package com.example.TRAM.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.TRAM.entity.Payments;
import com.example.TRAM.entity.Tickets;
import com.example.TRAM.repository.PaymentsRepository;
import com.example.TRAM.service.TicketService;

// Main controller for ticket operations
@RestController
@RequestMapping("/tickets")
public class TicketController {

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PaymentsRepository paymentsRepository; // Inject the payment repository

    // Endpoint to buy tickets
    @PostMapping("/buy")
    public ResponseEntity<String> buyTicket(@RequestBody BuyTicketRequest request) {
        Tickets ticket = new Tickets();
        ticket.setUsername(request.getUsername());
        ticket.setStartPlace(request.getStartPlace());
        ticket.setDestination(request.getDestination());
        ticket.setPrice(request.getPrice());

        Payments payment = new Payments();
        payment.setAmount(request.getPrice());
        payment.setStatus("PENDING");  // Set initial status to PENDING

        try {
            logger.info("Attempting to book ticket for user: {}", request.getUsername());
            ticketService.bookTicket(ticket, payment);  // Call the service method

            // Update payment status after successful ticket booking
            payment.setStatus("SUCCESS");
            paymentsRepository.save(payment);  // Save the updated payment to the database

            logger.info("Ticket booked successfully! Ticket ID: {}", ticket.getTicketId());
            return ResponseEntity.ok("Ticket booked successfully! Ticket ID: " + ticket.getTicketId());
        } catch (RuntimeException e) {
//            payment.setStatus("FAILURE");
//            paymentsRepository.save(payment);  // Save the failed payment status
            logger.error("Error booking ticket: {}", e.getMessage());
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }
}


// DTO for buying a ticket
class BuyTicketRequest {
    private String username;
    private String startPlace;
    private String destination;
    private double price;

    // Getters and Setters
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