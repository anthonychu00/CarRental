package com.system;

public class MainApp {
    public static void main(String[] args){
        CarRentalSystem crs = new CarRentalSystem();
        CarRentalData crd = new CarRentalData(1, 2, 3);
        CarRentalLogic crl = new CarRentalLogic(crs, crd);
        crl.initLogic();
    }
}
