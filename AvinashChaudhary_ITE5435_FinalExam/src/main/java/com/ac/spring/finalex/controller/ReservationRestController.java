package com.ac.spring.finalex.controller;

import com.ac.spring.finalex.dto.ReservationForm;
import com.ac.spring.finalex.model.*;
import com.ac.spring.finalex.repo.AirlineTicketRepository;
import com.ac.spring.finalex.repo.CustomerRepository;
import com.ac.spring.finalex.repo.ReservationRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {
    private final ReservationRepository reservationRepo;
    private final CustomerRepository customerRepo;
    private final AirlineTicketRepository ticketRepo;

    public ReservationRestController(ReservationRepository r, CustomerRepository c, AirlineTicketRepository t) {
        this.reservationRepo = r; this.customerRepo = c; this.ticketRepo = t;
    }

    @GetMapping
    public List<Reservation> all() { return reservationRepo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> one(@PathVariable String id) {
        return reservationRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reservation> create(@Valid @RequestBody ReservationForm form) {
        Customer c = customerRepo.save(new Customer(form.getCustomerName(), form.getCustomerEmail(), form.getCustomerPhone()));
        AirlineTicket t = ticketRepo.save(new AirlineTicket(
                form.getFlightNumber(), form.getFromAirport(), form.getToAirport(),
                form.getDepartureDate(), form.getSeatClass(), form.getPrice()
        ));
        Reservation r = reservationRepo.save(new Reservation(c.getId(), t.getId(), ReservationStatus.CONFIRMED, form.getAmountPaid()));
        return ResponseEntity.ok(r);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Reservation> updateStatus(@PathVariable String id, @RequestParam ReservationStatus status) {
        return reservationRepo.findById(id).map(r -> {
            r.setStatus(status);
            return ResponseEntity.ok(reservationRepo.save(r));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (!reservationRepo.existsById(id)) return ResponseEntity.notFound().build();
        reservationRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
