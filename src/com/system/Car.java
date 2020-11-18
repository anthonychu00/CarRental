package com.system;

import java.util.Random;

public class Car {
    private int id;//if this were implemented for real, you'd pull a random car ID from a database that matches the type
    private CarType type;
    //would have other variables such as mileage, year, make etc...

    public enum CarType {
        SEDAN,
        SUV,
        VAN
    }

    public Car(String carString) {
        Random rnd = new Random();
        this.id = 100000 + rnd.nextInt(900000);
        this.type = CarType.valueOf(carString.toUpperCase());
    }

    public CarType getType(){
        return this.type;
    }

    public int getId(){
        return this.id;
    }

}
