package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Math;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SmartTermo extends JFrame{
    //panels
    private JPanel mainPanel;
    private JPanel StatusPanel;
    private JPanel TempPanel;
    private JPanel HumidPanel;
    private JPanel ChangeHumidFormatPanel;
    private JPanel DesiredHumidPanel;
    private JLabel DesiredHumidLabel;
    private JScrollPane LogScrollPanel;
    private JPanel DesiredTempPanel;
    private JPanel ChangeTempPanel;
    private JPanel ActualTempPanel;
    //textfields
    private JTextField actTempField;
    private JTextField DesiredTempField;
    private JTextField ActHumidField;
    private JTextField DesiredHumidField;
    private JTextField onOffTextField;
    private JTextField dateTextField;
    private JTextArea ScrollTextArea;
    //labels
    private JLabel ActTempLabel;
    private JLabel DesiredTempLabel;
    private JPanel ActHumidPanel;
    private JLabel ActHumidLabel;
    private JButton ChangeTempButton;
    private JButton IncreaseHumidButton;
    private JButton DecreaseTempButton;
    private JButton DecreaseHumidButton;
    private JButton IncreaseTempButton;
    private JLabel mainLabel;
    //attributes
    private float actualTemp;
    private float desiredTemp;
    private int tempFormat;
    private float actualHumidity;
    private float desiredHumidity;
    private Date date;
    private String loggedMessages;
    private Timer timer;
    //constructors
    public SmartTermo(String title) {
        super(title);
        this.setContentPane(mainPanel);
        this.pack();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                logEvents("Goodbye.");
                System.exit(0);
            }
        });
        //initializations
        actualTemp = 22;
        desiredTemp = 25;
        tempFormat = 0;  //0-> C 1->F
        updateTextFields();
        actualHumidity = 50;
        desiredHumidity = 50;
        updateHumidityFields();
        loggedMessages = "";
        logEvents("App started.");

        //timer Task
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                Random randNum = new Random();
                if (actualTemp < desiredTemp && randNum.nextDouble() <= 0.8) {
                    actualTemp += 0.5;
                }
                else if (actualTemp > desiredTemp && randNum.nextDouble() <= 0.3) {
                    actualTemp -= 0.5;
                }
                logEvents("temperatura atual alterada para " + actualTemp);
                updateTextFields();
            }
        };
        timer = new Timer("Timer for simulation");
        timer.schedule(tt, 1000,1000);
        //button actions
        ChangeTempButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tempFormat==1) {
                    tempFormat = 0;
                    ChangeTempButton.setText("F");
                    logEvents("Changed temperature format to Fortnite.");
                }
                else if(tempFormat==0) {
                    tempFormat=1;
                    ChangeTempButton.setText("C");
                    logEvents("Changed temperature format to Celsius.");
                }
                else {
                    actTempField.setText("How did you fuck this up?!");
                    DesiredTempField.setText("How did you fuck this up?!");
                    logEvents("Error 501.");
                }
                updateTextFields();
            }
        });
        IncreaseTempButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desiredTemp += 0.5;
                //actualTemp += 0.5;
                updateTextFields();
                logEvents("Increased temperature to " + actualTemp);

            }
        });
        DecreaseTempButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desiredTemp -= 0.5;
                //actualTemp -= 0.5;
                updateTextFields();
                logEvents("Decreased temperature to " + actualTemp);
            }
        });
        IncreaseHumidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desiredHumidity += 1;
                actualHumidity += 1;
                updateHumidityFields();
                logEvents("Increased humidity to " + actualHumidity);
            }
        });
        DecreaseHumidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desiredHumidity -= 1;
                actualHumidity -= 1;
                updateHumidityFields();
                logEvents("Decreased humidity to " + actualHumidity);
            }
        });



    }
    //methods
    private void updateTextFields() {
        if(tempFormat == 0) {
            actTempField.setText(actualTemp + " C");
            DesiredTempField.setText(desiredTemp + " C");
        }
        else if (tempFormat == 1){
            actTempField.setText(actualTemp *9 /5 +32 + " F");
            DesiredTempField.setText(desiredTemp *9 /5 +32 + " F");
        }
        else{
            actTempField.setText("How did you fuck this up?!");
            DesiredTempField.setText("How did you fuck this up?!");
        }
        //date
        date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateTextField.setText(simpleDateFormat.format(date));
        // heating indicator
        if(actualTemp<desiredTemp) {
            onOffTextField.setText("On");
        } else if (actualTemp>desiredTemp) {
            onOffTextField.setText("Off");
        }
    }

    private void updateHumidityFields() {
            ActHumidField.setText(actualHumidity + "%");
        DesiredHumidField.setText(desiredHumidity + "%");
    }

    //main
    public static void main(String[] args) {
        JFrame smartTermo = new SmartTermo("TPSI");
        smartTermo.setVisible(true);
    }

    //logy
    private void logEvents(String informationToLog) {
        date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        loggedMessages += simpleDateFormat.format(date) + ": " + informationToLog + '\n';
        ScrollTextArea.setText(loggedMessages);
    };


}
