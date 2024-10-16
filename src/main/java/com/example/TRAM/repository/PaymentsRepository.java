package com.example.TRAM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TRAM.entity.Payments;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Long> {
    // Additional query methods can be defined here if needed
}