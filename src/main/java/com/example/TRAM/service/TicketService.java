package com.example.TRAM.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.TRAM.entity.BankAccount;
import com.example.TRAM.entity.Payments;
import com.example.TRAM.entity.Tickets;
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

    @Transactional
    public void bookTicket(Tickets ticket, Payments payment) {
        // Save the ticket
        logger.info("Saving ticket information...");
        Tickets savedTicket = ticketsRepository.save(ticket);
        logger.info("Saved ticket: {}", savedTicket.getTicketId());

        // Simulate a payment operation
        BankAccount account = bankAccountRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Bank account not found"));

        // Check if the account has sufficient balance
        if (account.getBalance() < payment.getAmount()) {
            logger.error("Insufficient funds for user: {}", ticket.getUsername());
            throw new RuntimeException("Insufficient funds");  // Exception triggers rollback
        }

        // Deduct balance from the account
        logger.info("Deducting amount: {} from account ID: {}", payment.getAmount(), account.getId());
        account.setBalance(account.getBalance() - payment.getAmount());
        bankAccountRepository.save(account);

        // Save payment transaction after successful balance deduction
        payment.setTransactionId(payment.getTransactionId());  // Ensure the transaction ID is set
        logger.info("Saving payment information...");
        paymentsRepository.save(payment);  // Payment saved only if all steps succeed
        logger.info("Payment recorded with Transaction ID: {}", payment.getTransactionId());
    }
}