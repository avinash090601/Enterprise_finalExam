package com.ac.spring.finalex.dto;

import com.ac.spring.finalex.model.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationView {
    private String id;

    private String customerName;
    private String customerEmail;
    private String customerPhone;

    private String flightNumber;
    private String fromAirport;
    private String toAirport;
    private LocalDate departureDate;

    private ReservationStatus status;
    private double amountPaid;
    private double price;
    private LocalDateTime createdAt;

    public String getId(){return id;}
    public void setId(String id){this.id=id;}

    public String getCustomerName(){return customerName;}
    public void setCustomerName(String s){this.customerName=s;}
    public String getCustomerEmail(){return customerEmail;}
    public void setCustomerEmail(String s){this.customerEmail=s;}
    public String getCustomerPhone(){return customerPhone;}
    public void setCustomerPhone(String s){this.customerPhone=s;}

    public String getFlightNumber(){return flightNumber;}
    public void setFlightNumber(String s){this.flightNumber=s;}
    public String getFromAirport(){return fromAirport;}
    public void setFromAirport(String s){this.fromAirport=s;}
    public String getToAirport(){return toAirport;}
    public void setToAirport(String s){this.toAirport=s;}
    public LocalDate getDepartureDate(){return departureDate;}
    public void setDepartureDate(LocalDate d){this.departureDate=d;}

    public ReservationStatus getStatus(){return status;}
    public void setStatus(ReservationStatus s){this.status=s;}
    public double getAmountPaid(){return amountPaid;}
    public void setAmountPaid(double a){this.amountPaid=a;}
    public double getPrice(){return price;}
    public void setPrice(double p){this.price=p;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public void setCreatedAt(LocalDateTime t){this.createdAt=t;}
}
