package com.ac.spring.finalex.service;

import com.ac.spring.finalex.dto.ReservationForm;
import com.ac.spring.finalex.dto.ReservationView;
import com.ac.spring.finalex.model.*;
import com.ac.spring.finalex.repo.AirlineTicketRepository;
import com.ac.spring.finalex.repo.CustomerRepository;
import com.ac.spring.finalex.repo.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepo;
    private final CustomerRepository customerRepo;
    private final AirlineTicketRepository ticketRepo;

    public ReservationService(ReservationRepository reservationRepo,
                              CustomerRepository customerRepo,
                              AirlineTicketRepository ticketRepo) {
        this.reservationRepo = reservationRepo;
        this.customerRepo = customerRepo;
        this.ticketRepo = ticketRepo;
    }

    public Reservation createReservationFromForm(ReservationForm form) {
        Assert.notNull(form, "form is required");

        Customer c = customerRepo.save(new Customer(
            form.getCustomerName(), form.getCustomerEmail(), form.getCustomerPhone()
        ));

        AirlineTicket t = ticketRepo.save(new AirlineTicket(
            form.getFlightNumber(), form.getFromAirport(), form.getToAirport(),
            form.getDepartureDate(), form.getSeatClass(), form.getPrice()
        ));

        Reservation r = new Reservation(c.getId(), t.getId(), ReservationStatus.CONFIRMED, form.getAmountPaid());
        return reservationRepo.save(r);
    }

    public List<ReservationView> getAllReservationViews() {
        return reservationRepo.findAll().stream().map(r -> {
            ReservationView v = new ReservationView();
            v.setId(r.getId());
            v.setAmountPaid(r.getAmountPaid());
            v.setStatus(r.getStatus());
            v.setCreatedAt(r.getCreatedAt());

            customerRepo.findById(r.getCustomerId()).ifPresent(c -> {
                v.setCustomerName(c.getName());
                v.setCustomerEmail(c.getEmail());
                v.setCustomerPhone(c.getPhone());
            });

            ticketRepo.findById(r.getTicketId()).ifPresent(t -> {
                v.setFlightNumber(t.getFlightNumber());
                v.setFromAirport(t.getFromAirport());
                v.setToAirport(t.getToAirport());
                v.setDepartureDate(t.getDepartureDate());
                v.setPrice(t.getPrice());
            });

            return v;
        }).toList();
    }
}
