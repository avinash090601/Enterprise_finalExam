package com.ac.spring.finalex.model;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("tickets")
public class AirlineTicket {
    @Id
    private String id;

    @NotBlank private String flightNumber;
    @NotBlank private String fromAirport;
    @NotBlank private String toAirport;

    @FutureOrPresent private LocalDate departureDate;
    private SeatClass seatClass = SeatClass.ECONOMY;

    @Min(0) private double price;

    public AirlineTicket() {}

    public AirlineTicket(String flightNumber, String fromAirport, String toAirport,
                         LocalDate departureDate, SeatClass seatClass, double price) {
        this.flightNumber = flightNumber;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.departureDate = departureDate;
        this.seatClass = seatClass;
        this.price = price;
    }

    public String getId(){return id;}
    public void setId(String id){this.id = id;}

    public String getFlightNumber(){return flightNumber;}
    public void setFlightNumber(String f){this.flightNumber = f;}

    public String getFromAirport(){return fromAirport;}
    public void setFromAirport(String f){this.fromAirport=f;}

    public String getToAirport(){return toAirport;}
    public void setToAirport(String t){this.toAirport=t;}

    public LocalDate getDepartureDate(){return departureDate;}
    public void setDepartureDate(LocalDate d){this.departureDate=d;}

    public SeatClass getSeatClass(){return seatClass;}
    public void setSeatClass(SeatClass s){this.seatClass=s;}

    public double getPrice(){return price;}
    public void setPrice(double p){this.price=p;}
}
