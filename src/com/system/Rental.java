package com.system;

import java.time.LocalDateTime;
import java.util.Random;

class Rental {

    private int id;
    private int daysRented;
    private LocalDateTime rentalStart;
    private Car c;
    private Customer cust;

    public Rental(LocalDateTime rentalStart, Car c, Customer cust, int daysRented){
        Random rnd = new Random();
        this.id = 100000 + rnd.nextInt(900000);
        this.rentalStart = rentalStart;
        this.c = c;
        this.cust = cust;
        this.daysRented = daysRented;
    }

    public int getId(){
        return id;
    }

    public int getDaysRented(){
        return daysRented;
    }

    public LocalDateTime getStartDate(){
        return rentalStart;
    }

    public Car getCar(){
        return c;
    }

    public Customer getCustomer(){
        return cust;
    }

}
