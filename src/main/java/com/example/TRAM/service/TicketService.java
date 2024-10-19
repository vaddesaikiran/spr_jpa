package com.example.TRAM.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.TRAM.entity.BankAccount;
import com.example.TRAM.entity.Payments;
import com.example.TRAM.entity.Tickets;
import com.example.TRAM.entity.UserInfo;
import com.example.TRAM.repository.BankAccountRepository;
import com.example.TRAM.repository.PaymentsRepository;
import com.example.TRAM.repository.TicketsRepository;

@Service
public class TicketService {

    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private TicketsRepository ticketsRepository;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void bookTicket(UserInfo userInfo, Tickets ticket, Payments payment) {
        logger.info("Starting ticket booking process...");

        payment.setUserInfo(userInfo);
        ticket.setUserInfo(userInfo);
        Tickets savedTicket = ticketsRepository.save(ticket);
        logger.info("Saved ticket: {} with ID: {}", savedTicket.getTicketId(), savedTicket.getId());
        
        Tickets retrievedTicket = getTicketDetails(savedTicket.getTicketId());
        logger.info("Retrieved ticket details within transaction: {} (Ticket ID: {})", retrievedTicket, retrievedTicket.getTicketId());

        
        
        try {
            logger.info("Simulating delay before completing the transaction...");
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            logger.error("Thread sleep interrupted: {}", e.getMessage());
            Thread.currentThread().interrupt();
        }

        try {
            processPayment(userInfo, payment);
        } catch (RuntimeException e) {
            logger.error("Payment processing failed: {}", e.getMessage());
            payment.setStatus("FAILURE");
            paymentsRepository.save(payment);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processPayment(UserInfo userInfo, Payments payment) {
        logger.info("Starting payment processing...");


        BankAccount account = userInfo.getBankAccount();

        if (account.getBalance() < payment.getAmount()) {
            logger.error("Insufficient funds for user: {}", userInfo.getUsername());
            payment.setStatus("FAILURE");
            paymentsRepository.save(payment);
            throw new RuntimeException("Insufficient funds");
        }

        logger.info("Deducting amount: {} from account ID: {}", payment.getAmount(), account.getId());
        account.setBalance(account.getBalance() - payment.getAmount());
        bankAccountRepository.save(account);

        
        payment.setTransactionId(payment.getTransactionId());
        payment.setUserInfo(userInfo);
        logger.info("Saving payment information...");
        payment.setStatus("SUCCESS");
        paymentsRepository.save(payment);
        logger.info("Payment recorded with Transaction ID: {}", payment.getTransactionId());
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public Tickets getTicketDetails(String ticketId) {
        logger.info("Fetching ticket details for ticket ID: {}", ticketId);
        return ticketsRepository.findByTicketId(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }
    
}
