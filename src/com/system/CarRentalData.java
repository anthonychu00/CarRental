package com.system;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

//Data class for the car rental system (Model)
public class CarRentalData {
    private HashMap<String, Integer> carStock = new HashMap<>();
    private ArrayList<Rental> rentals = new ArrayList<Rental>();

    public CarRentalData(int numberOfSedans, int numberOfSUVs, int numberOfVans){
        if(numberOfSedans < 0 || numberOfSUVs < 0 || numberOfVans < 0){
            JOptionPane.showMessageDialog(null, "Can't have negative amount of cars.", "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(1);
        }
        if(numberOfSedans == 0 && numberOfSUVs == 0 && numberOfVans == 0){
            JOptionPane.showMessageDialog(null, "Need at least 1 car in stock.", "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(1);
        }
        carStock.put("Sedan", numberOfSedans);
        carStock.put("SUV", numberOfSUVs);
        carStock.put("Van", numberOfVans);
    }

    public HashMap<String, Integer> getStock(){
        return carStock;
    }

    public void updateStock(String type, int number){
        carStock.replace(type, number);
    }

    public void updateRentals(Rental r){
        rentals.add(r);
    }

    public ArrayList<Rental> getRentals(){
        return rentals;
    }
}
