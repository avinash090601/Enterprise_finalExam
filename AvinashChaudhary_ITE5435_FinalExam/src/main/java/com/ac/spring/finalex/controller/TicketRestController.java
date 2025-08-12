package com.ac.spring.finalex.controller;

import com.ac.spring.finalex.model.AirlineTicket;
import com.ac.spring.finalex.repo.AirlineTicketRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {
    private final AirlineTicketRepository repo;
    public TicketRestController(AirlineTicketRepository repo){this.repo = repo;}

    @GetMapping
    public List<AirlineTicket> all(){return repo.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<AirlineTicket> one(@PathVariable String id){
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AirlineTicket> create(@Valid @RequestBody AirlineTicket t){
        return ResponseEntity.ok(repo.save(t));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirlineTicket> update(@PathVariable String id, @Valid @RequestBody AirlineTicket t){
        return repo.findById(id)
            .map(ex -> {
                ex.setFlightNumber(t.getFlightNumber());
                ex.setFromAirport(t.getFromAirport());
                ex.setToAirport(t.getToAirport());
                ex.setDepartureDate(t.getDepartureDate());
                ex.setSeatClass(t.getSeatClass());
                ex.setPrice(t.getPrice());
                return ResponseEntity.ok(repo.save(ex));
            }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
