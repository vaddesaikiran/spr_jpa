package com.example.TRAM.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.TRAM.entity.BankAccount;
import com.example.TRAM.entity.Payments;
import com.example.TRAM.entity.Tickets;
import com.example.TRAM.entity.UserInfo;
import com.example.TRAM.repository.UserInfoRepository;
import com.example.TRAM.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @PostMapping("/buy")
    public ResponseEntity<String> buyTicket(@RequestBody BuyTicketRequest request) {
        UserInfo userInfo = userInfoRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Tickets ticket = new Tickets();
        ticket.setUserInfo(userInfo);
        ticket.setStartPlace(request.getStartPlace());
        ticket.setDestination(request.getDestination());
        ticket.setPrice(request.getPrice());

        Payments payment = new Payments();
        payment.setAmount(request.getPrice());
        payment.setStatus("PENDING");

        try {
            logger.info("Attempting to book ticket for user: {}", request.getUsername());
            ticketService.bookTicket(userInfo, ticket, payment);

            if (payment.getStatus().equals("SUCCESS")) {
                logger.info("Ticket booked successfully! Ticket ID: {}", ticket.getTicketId());
                return ResponseEntity.ok("Ticket booked successfully! Ticket ID: " + ticket.getTicketId());
            } else {
                logger.warn("Ticket saved with ID: {} but payment failed.", ticket.getTicketId());
                return ResponseEntity.ok("Ticket saved successfully! Ticket ID: " + ticket.getTicketId() + ". Payment failed.");
            }
        } catch (RuntimeException e) {
            logger.error("Error booking ticket: {}", e.getMessage());
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }
    
    
    @GetMapping("/getTicket/{ticketId}")
    public ResponseEntity<Tickets> getTicket(@PathVariable String ticketId) {
        try {
            Tickets ticket = ticketService.getTicketDetails(ticketId);
            return ResponseEntity.ok(ticket);
        } catch (RuntimeException e) {
            logger.error("Error fetching ticket details: {}", e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }
    
}


class BuyTicketRequest {
    private String username;
    private String startPlace;
    private String destination;
    private double price;

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
