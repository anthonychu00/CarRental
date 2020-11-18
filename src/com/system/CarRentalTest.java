package com.system;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class CarRentalTest {
    private CarRentalData crd;
    private CarRentalSystem crs;
    private CarRentalLogic crl;
    @Before
    public void setUp(){
        this. crs = new CarRentalSystem();
        this. crd = new CarRentalData(1, 2, 3);
        this.crl = new CarRentalLogic(crs, crd);
        crl.initLogic();
    }

    @Test
    public void testCarRentalDataStock(){

        int count = 0;
        HashMap<String, Integer> testMap = crd.getStock();
        for(Map.Entry entry : testMap.entrySet()){
            count += (int)entry.getValue();
        }

        Assert.assertEquals(6, count, 0);
    }

    @Test
    public void testCarRentalDataUpdateStock(){

        int count = 0;
        HashMap<String, Integer> testMap = crd.getStock();
        crd.updateStock("Sedan", testMap.get("Sedan") - 1);
        for(Map.Entry entry : testMap.entrySet()){
            count += (int)entry.getValue();
        }

        Assert.assertEquals(5, count, 0);
    }

    @Test
    public void testRentalAdd(){
        Customer cust = new Customer("Steve", "Miller", "asdf@gmail.com", "123 Eureka Ave");
        Car car = new Car("Sedan");
        LocalDateTime rentalStart = LocalDateTime.of(2020, 12, 25, 9, 30);
        int daysToRent = 7;
        crd.updateRentals(new Rental(rentalStart, car, cust, daysToRent));
        Assert.assertEquals(1, crd.getRentals().size());
    }

    @Test
    public void testDateConversion(){
        String meridiem = "AM";

        Date zeroDate = new GregorianCalendar(2020, Calendar.DECEMBER, 25).getTime();
        //dummy date, only care about hours and minutes
        Date date = new GregorianCalendar(2020, Calendar.DECEMBER, 25, 5, 30).getTime();
        LocalDateTime expected = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime ldt = crl.dateToLDT(zeroDate, date, date, meridiem);

        Assert.assertTrue(ldt.equals(expected));

    }
}
