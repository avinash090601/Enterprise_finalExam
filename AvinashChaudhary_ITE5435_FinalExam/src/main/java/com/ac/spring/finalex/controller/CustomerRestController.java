package com.ac.spring.finalex.controller;

import com.ac.spring.finalex.model.Customer;
import com.ac.spring.finalex.repo.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
    private final CustomerRepository repo;
    public CustomerRestController(CustomerRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Customer> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> one(@PathVariable String id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer c) {
        return ResponseEntity.ok(repo.save(c));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable String id, @Valid @RequestBody Customer c) {
        return repo.findById(id)
            .map(ex -> {
                ex.setName(c.getName()); ex.setEmail(c.getEmail()); ex.setPhone(c.getPhone());
                return ResponseEntity.ok(repo.save(ex));
            }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
