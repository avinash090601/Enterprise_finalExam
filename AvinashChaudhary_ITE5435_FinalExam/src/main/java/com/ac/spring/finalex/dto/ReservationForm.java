package com.ac.spring.finalex.dto;

import com.ac.spring.finalex.model.SeatClass;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ReservationForm {
    // Customer
    @NotBlank private String customerName;
    @Email @NotBlank private String customerEmail;
    @NotBlank private String customerPhone;

    // Ticket
    @NotBlank private String flightNumber;
    @NotBlank private String fromAirport;
    @NotBlank private String toAirport;
    @FutureOrPresent private LocalDate departureDate;
    private SeatClass seatClass = SeatClass.ECONOMY;

    @Min(0) private double price;

    // Reservation
    @Min(0) private double amountPaid;

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
    public SeatClass getSeatClass(){return seatClass;}
    public void setSeatClass(SeatClass s){this.seatClass=s;}
    public double getPrice(){return price;}
    public void setPrice(double p){this.price=p;}

    public double getAmountPaid(){return amountPaid;}
    public void setAmountPaid(double a){this.amountPaid=a;}
}
