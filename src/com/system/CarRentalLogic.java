package com.system;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

//class handling the logic for the car rental system (Controller)
public class CarRentalLogic {
    private CarRentalSystem crs;
    private CarRentalData crd;

    public CarRentalLogic(CarRentalSystem crs, CarRentalData crd){
        this.crs = crs;
        this.crd = crd;
        putDataIntoGUI();
    }

    private void putDataIntoGUI(){
        //updates cars left in stock after a rental has been made
        HashMap<String, Integer> carStock = crd.getStock();
        crs.getCarsLeftLabel().setText(" We currently have in stock - " + carStock.get("Sedan") + " sedans, "
                + carStock.get("SUV") + " SUVs, "
                + carStock.get("Van") + " vans");
    }

    //adds event listeners
    public void initLogic(){
        crs.getSubmitButton().addActionListener(e -> submitLogic());
    }

    private void submitLogic(){
        HashMap<String, Integer> carStock = crd.getStock();
        String firstName = crs.getFirstNameField().getText();
        String lastName = crs.getLastNameField().getText();
        String email = crs.getEmailField().getText();
        String address = crs.getAddressField().getText();
        //checks for empty text fields
        if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || address.isEmpty()){
            JOptionPane.showMessageDialog(null, "One or more fields is empty.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Customer cust = new Customer(firstName, lastName, email, address);

        //checks if car of selected type still in stock
        String carType = (String)crs.getTypeList().getSelectedItem();
        if(carStock.get(carType) <= 0){
            JOptionPane.showMessageDialog(null, "No more cars of that type.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Car car = new Car(carType);

        //checks for a valid date
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime rentalLDT = dateToLDT(crs.getPicker().getDate(), (Date)crs.getHours().getValue(), (Date)crs.getMinutes().getValue(), (String)crs.getTimeList().getSelectedItem());
        LocalDateTime oneDayAgo = rentalLDT.minusDays(1);
        if(!today.isBefore(oneDayAgo)){
            JOptionPane.showMessageDialog(null, "Rentals can not be made the day of or in the past.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        //create a new rental. This would be where it would be added to a database if one were implemented.
        Rental rent = new Rental(rentalLDT, car, cust, (int)crs.getDaysSpinner().getValue());
        crd.updateRentals(rent);

        //remove car from stock
        crd.updateStock(carType, carStock.get(carType) - 1);
        putDataIntoGUI();

        String thankYouMessage = "Thank you " + cust.getName() + "! Your rental ID is " + rent.getId() + ".";
        JOptionPane.showMessageDialog(null, thankYouMessage, "Thank you!", JOptionPane.INFORMATION_MESSAGE);
    }



    //converts Date objects representing date,hours, and minutes to a single LocalDateTime
    public LocalDateTime dateToLDT(Date date, Date hours, Date minutes, String meridiem){
        int h, m;

        Calendar cal = Calendar.getInstance();

        cal.setTime(hours);
        h = cal.get(Calendar.HOUR_OF_DAY);
        if(meridiem.equals("PM")){
            h += 12;
        }

        cal.setTime(minutes);
        m = cal.get(Calendar.MINUTE);

        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).plusHours(h).plusMinutes(m);
    }


}
