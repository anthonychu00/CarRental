package com.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import javax.swing.*;
import java.awt.*;
import org.jdesktop.swingx.JXDatePicker;

//GUI class for the car rental system (View)
public class CarRentalSystem {
    //GUI components
    private JFrame frame;
    private JPanel panel;
    private JLabel title, carsLeft, firstNameLabel, lastNameLabel, emailLabel, addressLabel, typeLabel,
                    dateLabel, timeLabel, daysLabel, noteLabel;
    private JTextField firstName, lastName, email, address;
    private JComboBox typeList, timeList;
    private JXDatePicker picker;
    private JSpinner hours, minutes, daysSpinner;
    private JButton submitButton;


    public CarRentalSystem(){
        frame = new JFrame("Car Rental System");
        panel = new JPanel(new GridBagLayout());

        //defining parameters of the GridBagLayout
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.insets = new Insets(10, 10, 10, 10);

        //describing layout of window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400, 400, 550, 600);

        //title
        title = new JLabel("Welcome to Anthony's Car Rentals!", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        setLocation(0 , 0, 4, panel, title, cons);

        //cars remaining
        carsLeft = new JLabel("Placeholder", SwingConstants.CENTER);
        setLocation(0 , 1, 4, panel, carsLeft, cons);

        //Customer details
        firstNameLabel = new JLabel("First name: ");
        firstName = new JTextField(16);
        setLocation(0, 2, 1, panel, firstNameLabel, cons);
        setLocation(1, 2, 2, panel, firstName, cons);

        lastNameLabel = new JLabel("Last name: ");
        lastName = new JTextField(16);
        setLocation(0, 3, 1, panel, lastNameLabel, cons);
        setLocation(1, 3, 2, panel, lastName, cons);

        emailLabel = new JLabel("Email: ");
        email = new JTextField(30);
        setLocation(0, 4, 1, panel, emailLabel, cons);
        setLocation(1, 4, 2, panel, email, cons);

        addressLabel = new JLabel("Address");
        address = new JTextField(30);
        setLocation(0, 5, 1, panel, addressLabel, cons);
        setLocation(1, 5, 2, panel, address, cons);

        //choosing car type
        typeLabel = new JLabel("Car Type: ");
        String[] typeStrings = {"Sedan", "SUV", "Van"};
        typeList = new JComboBox(typeStrings);
        setLocation(0, 6, 1, panel, typeLabel, cons);
        setLocation(1, 6, 2, panel, typeList, cons);

        //date
        dateLabel = new JLabel("Date: ");
        picker = new JXDatePicker();
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        setLocation(0, 7, 1, panel, dateLabel, cons);
        setLocation(1, 7, 2, panel, picker, cons);

        //time
        //zeroing date on form
        timeLabel = new JLabel("Time: ");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 12);
        cal.set(Calendar.MINUTE, 0);
        Date zeroDate = cal.getTime();

        //JSpinner has compatibility issues with LocalDateTime, so need to get Date first and convert to LocalDateTime
        //Date representing hours selected
        SpinnerDateModel hourModel = new SpinnerDateModel();
        hourModel.setCalendarField(Calendar.HOUR);
        hours = new JSpinner(hourModel);
        hours.setEditor(new JSpinner.DateEditor(hours, "hh"));
        hours.setValue(zeroDate);

        //Date representing minutes selected
        SpinnerDateModel minuteModel = new SpinnerDateModel();
        minuteModel.setCalendarField(Calendar.MINUTE);
        minutes = new JSpinner(minuteModel);
        minutes.setEditor(new JSpinner.DateEditor(minutes, "mm"));
        minutes.setValue(zeroDate);

        String[] timeStrings = {"AM", "PM"};
        timeList = new JComboBox(timeStrings);
        setLocation(0, 8, 1, panel, timeLabel, cons);
        setLocation(1, 8, 1, panel, hours, cons);
        setLocation(2, 8, 1, panel, minutes, cons);
        setLocation(3, 8, 1, panel, timeList, cons);

        //days to rent
        daysLabel = new JLabel("Days to rent (max 90 days): ");
        daysSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 90, 1));
        setLocation(0, 9, 1, panel, daysLabel, cons);
        setLocation(1, 9, 1, panel, daysSpinner, cons);

        noteLabel = new JLabel("*Note that rentals must be made at least a 24 hours in advance.", SwingConstants.CENTER);
        setLocation(0, 10, 3, panel, noteLabel, cons);

        //submit button
        submitButton = new JButton("Submit");
        frame.getContentPane().add(BorderLayout.SOUTH, submitButton);

        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    //sets location of Swing component in GridBagLayout
    private void setLocation(int x, int y, int width, JPanel panel, JComponent component, GridBagConstraints cons){
        cons.gridx = x;
        cons.gridy = y;
        cons.gridwidth = width;
        panel.add(component, cons);
    }

    public JTextField getFirstNameField(){
        return firstName;
    }

    public JTextField getLastNameField(){
        return lastName;
    }

    public JTextField getEmailField(){
        return email;
    }

    public JTextField getAddressField(){
        return address;
    }

    public JLabel getCarsLeftLabel(){
        return carsLeft;
    }

    public JButton getSubmitButton(){
        return submitButton;
    }

    public JComboBox getTypeList(){
        return typeList;
    }

    public JComboBox getTimeList(){
        return timeList;
    }

    public JXDatePicker getPicker(){
        return picker;
    }

    public JSpinner getHours(){
        return hours;
    }

    public JSpinner getMinutes(){
        return minutes;
    }

    public JSpinner getDaysSpinner(){
        return daysSpinner;
    }
}
