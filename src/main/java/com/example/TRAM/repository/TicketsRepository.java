package com.example.TRAM.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TRAM.entity.Tickets;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Long> {

	Optional<Tickets> findByTicketId(String ticketId);
}