package com.example.TRAM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TRAM.entity.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    // Additional query methods can be defined here if needed
}