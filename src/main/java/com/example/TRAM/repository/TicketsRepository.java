package com.example.TRAM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TRAM.entity.Tickets;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Long> {
    // Additional query methods can be defined here if needed
}