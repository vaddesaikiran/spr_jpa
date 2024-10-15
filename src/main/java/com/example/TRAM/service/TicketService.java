package com.example.TRAM.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public void bookTicket(UserInfo userInfo, Tickets ticket, Payments payment) {
        // Save ticket first
        logger.info("Saving ticket information...");
        ticket.setUserInfo(userInfo);  // Set the userInfo in ticket
        Tickets savedTicket = ticketsRepository.save(ticket);
        logger.info("Saved ticket: {}", savedTicket.getTicketId());

        // Simulate a payment operation
        BankAccount account = userInfo.getBankAccount();  // Get the user's bank account

        if (account.getBalance() < payment.getAmount()) {
            logger.error("Insufficient funds for user: {}", userInfo.getUsername());
            throw new RuntimeException("Insufficient funds");
        }

        // Deduct balance from the account
        logger.info("Deducting amount: {} from account ID: {}", payment.getAmount(), account.getId());
        account.setBalance(account.getBalance() - payment.getAmount());
        bankAccountRepository.save(account);

        // Set payment details
        payment.setTransactionId(payment.getTransactionId());  // Ensure the transaction ID is set
        payment.setUserInfo(userInfo);  // Set the userInfo in payment
        logger.info("Saving payment information...");
        paymentsRepository.save(payment);
        logger.info("Payment recorded with Transaction ID: {}", payment.getTransactionId());
    }

}
