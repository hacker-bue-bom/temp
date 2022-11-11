package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
    private JTextArea ScrollTextArea;
    //labels
    private JLabel ActTempLabel;
    private JLabel DesiredTempLabel;
    private JPanel ActHumidPanel;
    private JLabel ActHumidLabel;
    //buttons
    private JButton HumidOnOffButton;
    private JButton ChangeTempButton;
    private JButton IncreaseHumidButton;
    private JButton DecreaseTempButton;
    private JButton DecreaseHumidButton;
    private JButton IncreaseTempButton;
    //attributes
    private float actualTemp;
    private float desiredTemp;
    private int tempFormat;
    private float actualHumidity;
    private float desiredHumidity;

    //constructors
    public SmartTermo(String title) {
        super(title);
        this.setContentPane(mainPanel);
        this.pack();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //initializations
        actualTemp = 22;
        desiredTemp = 22;
        tempFormat = 0;  //0-> C 1->F
        updateTextFields();
        actualHumidity = 50;
        desiredHumidity = 50;
        updateHumidityFields();
        //button actions
        ChangeTempButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tempFormat==1) {
                    tempFormat = 0;
                }
                else if(tempFormat==0) {
                    tempFormat=1;
                }
                else {
                    actTempField.setText("How did you fuck this up?!");
                    DesiredTempField.setText("How did you fuck this up?!");
                }
                updateTextFields();
            }
        });
        IncreaseTempButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desiredTemp += 0.5;
                actualTemp += 0.5;
                updateTextFields();
            }
        });
        DecreaseTempButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desiredTemp -= 0.5;
                actualTemp += 0.5;
                updateTextFields();
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
}
