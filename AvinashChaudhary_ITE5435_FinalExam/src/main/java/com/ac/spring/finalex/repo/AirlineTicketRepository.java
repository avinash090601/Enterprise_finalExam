package com.ac.spring.finalex.repo;

import com.ac.spring.finalex.model.AirlineTicket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AirlineTicketRepository extends MongoRepository<AirlineTicket, String> {}
