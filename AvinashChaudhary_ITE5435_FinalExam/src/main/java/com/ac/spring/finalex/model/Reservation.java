package com.ac.spring.finalex.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("reservations")
public class Reservation {
    @Id
    private String id;

    @NotBlank private String customerId;
    @NotBlank private String ticketId;

    private ReservationStatus status = ReservationStatus.PENDING;

    @Min(0) private double amountPaid = 0;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Reservation() {}

    public Reservation(String customerId, String ticketId, ReservationStatus status, double amountPaid) {
        this.customerId = customerId;
        this.ticketId = ticketId;
        this.status = status;
        this.amountPaid = amountPaid;
    }

    public String getId(){return id;}
    public void setId(String id){this.id=id;}

    public String getCustomerId(){return customerId;}
    public void setCustomerId(String c){this.customerId=c;}

    public String getTicketId(){return ticketId;}
    public void setTicketId(String t){this.ticketId=t;}

    public ReservationStatus getStatus(){return status;}
    public void setStatus(ReservationStatus s){this.status=s;}

    public double getAmountPaid(){return amountPaid;}
    public void setAmountPaid(double a){this.amountPaid=a;}

    public LocalDateTime getCreatedAt(){return createdAt;}
    public void setCreatedAt(LocalDateTime c){this.createdAt=c;}
}
