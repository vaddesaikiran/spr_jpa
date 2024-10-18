package com.example.locking_mechanisms.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.locking_mechanisms.entity.Account;
import com.example.locking_mechanisms.repository.AccountRepository;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void transferMoneyWithDelay(Long fromAccountId, Long toAccountId, Double amount) {
        // Fetch the "from" account with pessimistic write lock
        Account fromAccount = accountRepository.findByIdWithPessimisticWriteLock(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Fetch the "to" account
        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Perform the transfer
        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        // Save the updated accounts
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Simulate delay to hold the lock for testing purposes
        try {
            System.out.println("Simulating delay for pessimistic lock...");
            Thread.sleep(10000);  // 10 seconds delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Transaction completed.");
    }
}
