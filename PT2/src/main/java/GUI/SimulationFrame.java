package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame {
    private JTextField nrQuetextField;
    private JTextField simIntervaltextField;
    private JTextField minArrTimetextField;
    private JTextField nrClientstextField;
    private JTextField maxArrTimetextField;
    private JTextField maxServiceTimetextField;
    private JTextField minServiceTimetextField;
    private JButton validateButton;
   // private JButton startButton;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    public SimulationFrame(){
        this.getContentPane().setBackground(new Color(240, 248, 255));
        this.getContentPane().setForeground(SystemColor.inactiveCaption);
        this.setBounds(100, 100, 946, 541);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel nrclientsLabel = new JLabel("Number of clients");
        nrclientsLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        nrclientsLabel.setBounds(86, 54, 143, 36);
        this.getContentPane().add(nrclientsLabel);

        JLabel nrQueLabel = new JLabel("Number of queques");
        nrQueLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        nrQueLabel.setBounds(331, 63, 165, 19);
        this.getContentPane().add(nrQueLabel);

        JLabel simIntervalLabel = new JLabel("Simulation Interval(s)");
        simIntervalLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        simIntervalLabel.setBounds(67, 125, 179, 26);
        this.getContentPane().add(simIntervalLabel);

        JLabel minArrivalTimeLabel = new JLabel("Minimum");
        minArrivalTimeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        minArrivalTimeLabel.setBounds(67, 222, 196, 13);
        this.getContentPane().add(minArrivalTimeLabel);

        JLabel maxArrivalTimeLabel = new JLabel("Maximum");
        maxArrivalTimeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        maxArrivalTimeLabel.setBounds(221, 222, 196, 13);
        this.getContentPane().add(maxArrivalTimeLabel);

        JLabel maxServiceTimeLabel = new JLabel("Minimum");
        maxServiceTimeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        maxServiceTimeLabel.setBounds(67, 335, 196, 13);
        this.getContentPane().add(maxServiceTimeLabel);

        JLabel minServiceTimeLabel = new JLabel("Maximum");
        minServiceTimeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        minServiceTimeLabel.setBounds(221, 335, 196, 13);
        this.getContentPane().add(minServiceTimeLabel);

        validateButton = new JButton("Validate input data");
        validateButton.setBackground(new Color(173, 216, 230));
        validateButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        validateButton.setBounds(86, 435, 196, 36);
        this.getContentPane().add(validateButton);

        nrQuetextField = new JTextField();
        nrQuetextField.setBounds(514, 56, 45, 26);
        this.getContentPane().add(nrQuetextField);
        nrQuetextField.setColumns(10);

        simIntervaltextField = new JTextField();
        simIntervaltextField.setBounds(307, 125, 53, 26);
        this.getContentPane().add(simIntervaltextField);
        simIntervaltextField.setColumns(10);

        minArrTimetextField = new JTextField();
        minArrTimetextField.setBounds(159, 209, 45, 26);
        this.getContentPane().add(minArrTimetextField);
        minArrTimetextField.setColumns(10);

        nrClientstextField = new JTextField();
        nrClientstextField.setBounds(260, 53, 45, 26);
        this.getContentPane().add(nrClientstextField);
        nrClientstextField.setColumns(10);

        maxArrTimetextField = new JTextField();
        maxArrTimetextField.setColumns(10);
        maxArrTimetextField.setBounds(307, 209, 53, 26);
        this.getContentPane().add(maxArrTimetextField);

        maxServiceTimetextField = new JTextField();
        maxServiceTimetextField.setColumns(10);
        maxServiceTimetextField.setBounds(320, 320, 45, 28);
        this.getContentPane().add(maxServiceTimetextField);

        minServiceTimetextField = new JTextField();
        minServiceTimetextField.setColumns(10);
        minServiceTimetextField.setBounds(159, 320, 45, 28);
        this.getContentPane().add(minServiceTimetextField);

        textArea = new JTextArea();
        textArea.setBounds(633, 110, 250, 348);
        this.add(textArea);
        this.getContentPane().add(textArea);

        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(633, 110, 250, 348);
        this.add(scrollPane);

        JLabel logsLabel = new JLabel("Logs:");
        logsLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        logsLabel.setForeground(new Color(0, 0, 0));
        logsLabel.setBounds(633, 49, 63, 46);
        this.getContentPane().add(logsLabel);

        JLabel arrivalLabel = new JLabel("Arrival time:");
        arrivalLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        arrivalLabel.setBounds(67, 179, 137, 24);
        this.getContentPane().add(arrivalLabel);

        JLabel serviceLabel = new JLabel("Service time:");
        serviceLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        serviceLabel.setBounds(67, 280, 143, 28);
        this.getContentPane().add(serviceLabel);

        this.setVisible(true);
    }
    public int getNrQuetextField() {
        return Integer.parseInt(nrQuetextField.getText());
    }
    public void setNrQuetextField(int nrQuetextField) {
        this.nrQuetextField.setText(String.valueOf( nrQuetextField));
    }
    public int getSimIntervaltextField() {
        return Integer.parseInt(simIntervaltextField.getText());
    }
    public void setSimIntervaltextField(int simIntervaltextField) {
        this.simIntervaltextField.setText(String.valueOf( simIntervaltextField));
    }
    public int getMinArrTimetextField() {
        return Integer.parseInt(minArrTimetextField.getText());
    }
    public void setMinArrTimetextField(JTextField minArrTimetextField) {
        this.minArrTimetextField.setText(String.valueOf( minArrTimetextField));
    }
    public int getNrClientstextField() {
        return Integer.parseInt(nrClientstextField.getText());
    }
    public void setNrClientstextField(JTextField nrClientstextField) {
        this.nrClientstextField.setText(String.valueOf(nrClientstextField));
    }
    public int getMaxArrTimetextField() {
        return Integer.parseInt(maxArrTimetextField.getText());
    }
    public void setMaxArrTimetextField(JTextField maxArrTimetextField) {
        this.maxArrTimetextField.setText(String.valueOf( maxArrTimetextField));
    }
    public int getMaxServiceTimetextField() {
        return Integer.parseInt(maxServiceTimetextField.getText());
    }
    public void setMaxServiceTimetextField(JTextField maxServiceTimetextField) {
        this.maxServiceTimetextField.setText(String.valueOf( maxServiceTimetextField));
    }
    public int getMinServiceTimetextField() {
        return Integer.parseInt(minServiceTimetextField.getText());
    }
    public void setMinServiceTimetextField(JTextField minServiceTimetextField) {
        this.minServiceTimetextField.setText(String.valueOf(minServiceTimetextField));
    }
    public String getTextArea() {
        return textArea.getText();
    }
    public void setTextArea(String textArea) {
        this.textArea.setText(textArea);
    }
    public void addValidateListener(ActionListener ActionListener) {
        validateButton.addActionListener(ActionListener);
    }
    public void showMessage1(String message) {
        JOptionPane.showMessageDialog(this, message);}

}
