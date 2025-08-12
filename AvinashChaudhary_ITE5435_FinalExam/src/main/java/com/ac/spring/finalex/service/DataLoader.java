package com.ac.spring.finalex.service;

import com.ac.spring.finalex.model.*;
import com.ac.spring.finalex.repo.AirlineTicketRepository;
import com.ac.spring.finalex.repo.CustomerRepository;
import com.ac.spring.finalex.repo.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final CustomerRepository customerRepo;
    private final AirlineTicketRepository ticketRepo;
    private final ReservationRepository reservationRepo;

    public DataLoader(CustomerRepository c, AirlineTicketRepository t, ReservationRepository r) {
        this.customerRepo = c; this.ticketRepo = t; this.reservationRepo = r;
    }

    @Override
    public void run(String... args) {
        if (customerRepo.count() > 0 || ticketRepo.count() > 0 || reservationRepo.count() > 0) return;

        Customer c = customerRepo.save(new Customer("Demo User", "demo@huskyair.com", "437-000-0000"));
        AirlineTicket t = ticketRepo.save(new AirlineTicket("HA101", "YYZ", "YVR",
                LocalDate.now().plusDays(3), SeatClass.ECONOMY, 399.0));
        reservationRepo.save(new Reservation(c.getId(), t.getId(), ReservationStatus.CONFIRMED, 399.0));
    }
}
