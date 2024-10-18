package com.example.locking_mechanisms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.locking_mechanisms.entity.Account;
import com.example.locking_mechanisms.repository.AccountRepository;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void transferMoneyWithOptimisticLock(Long fromAccountId, Long toAccountId, Double amount) {
        try {
            Account fromAccount = accountRepository.findByIdWithOptimisticLock(fromAccountId)
                    .orElseThrow(() -> new RuntimeException("Account not found"));

            Thread.sleep(10000);

            Account toAccount = accountRepository.findById(toAccountId)
                    .orElseThrow(() -> new RuntimeException("Account not found"));

            if (fromAccount.getBalance() < amount) {
                throw new RuntimeException("Insufficient funds");
            }
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);

            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);

            System.out.println("Transaction completed.");
        } catch (ObjectOptimisticLockingFailureException e) {
            System.out.println("Optimistic lock failure: another transaction has updated this account.");
            throw e;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Transfer interrupted", e);
        }
    }
}
