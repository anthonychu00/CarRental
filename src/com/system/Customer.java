package com.system;

import java.util.Random;

public class Customer {
    private int id;
    private String firstName, lastName, email, address;

    public Customer(String firstName, String lastName, String email, String address){
        Random rnd = new Random();
        this.id = 100000 + rnd.nextInt(900000);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public String getName(){
        return firstName + " " + lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getAddress(){
        return address;
    }
}
