package com.ac.spring.finalex.controller;

import com.ac.spring.finalex.dto.ReservationForm;
import com.ac.spring.finalex.dto.ReservationView;
import com.ac.spring.finalex.model.Reservation;
import com.ac.spring.finalex.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class PageController {
    private static final Logger log = LoggerFactory.getLogger(PageController.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ReservationService reservationService;

    public PageController(RestTemplate rt, ObjectMapper om, ReservationService reservationService) {
        this.restTemplate = rt; this.objectMapper = om; this.reservationService = reservationService;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/reservations/new")
    public String newReservation(Model model) {
        model.addAttribute("form", new ReservationForm());
        return "reservations/new";
    }

    @PostMapping("/reservations")
    public String submitReservation(@Valid @ModelAttribute("form") ReservationForm form,
                                    BindingResult binding, Model model) throws Exception {
        if (binding.hasErrors()) {
            model.addAttribute("msg", "Please fix the errors below.");
            return "reservations/new";
        }

        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(form);
        log.info("Submitting ReservationForm as JSON:\n{}", json);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);

        ResponseEntity<Reservation> response =
                restTemplate.postForEntity("http://localhost:8080/api/reservations", entity, Reservation.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            model.addAttribute("msg", "Reservation created! ID: " + response.getBody().getId());
            model.addAttribute("form", new ReservationForm());
        } else {
            model.addAttribute("msg", "Failed to create reservation.");
        }
        return "reservations/new";
    }

    @GetMapping("/reservations")
    public String listReservations(Model model) {
        List<ReservationView> list = reservationService.getAllReservationViews();
        model.addAttribute("reservations", list);
        return "reservations/list";
    }
}
