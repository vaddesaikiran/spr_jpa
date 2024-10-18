package com.example.locking_mechanisms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.locking_mechanisms.entity.Account;


import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT a FROM Account a WHERE a.id = :id")
    Optional<Account> findByIdWithOptimisticLock(Long id);
}
