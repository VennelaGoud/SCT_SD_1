import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TemperatureConverter extends JFrame implements ActionListener {

    JLabel titleLabel, tempLabel, fromLabel, resultLabel;
    JTextField tempField;
    JComboBox<String> fromBox;
    JButton convertButton, clearButton;

    public TemperatureConverter() {

        setTitle("Temperature Converter");
        setSize(500, 350);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("Temperature Converter");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(120, 20, 300, 30);
        add(titleLabel);

        tempLabel = new JLabel("Enter Temperature:");
        tempLabel.setBounds(50, 80, 150, 25);
        add(tempLabel);

        tempField = new JTextField();
        tempField.setBounds(200, 80, 180, 25);
        add(tempField);

        fromLabel = new JLabel("Select Unit:");
        fromLabel.setBounds(50, 130, 150, 25);
        add(fromLabel);

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        fromBox = new JComboBox<>(units);
        fromBox.setBounds(200, 130, 180, 25);
        add(fromBox);

        convertButton = new JButton("Convert");
        convertButton.setBounds(100, 190, 120, 35);
        convertButton.addActionListener(this);
        add(convertButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(250, 190, 120, 35);
        clearButton.addActionListener(this);
        add(clearButton);

        resultLabel = new JLabel("Result will appear here");
        resultLabel.setBounds(50, 260, 400, 25);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(resultLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == convertButton) {

            try {
                double temp = Double.parseDouble(tempField.getText());
                String unit = (String) fromBox.getSelectedItem();

                String result = "";

                if (unit.equals("Celsius")) {
                    double f = (temp * 9 / 5) + 32;
                    double k = temp + 273.15;

                    result = String.format(
                        "Fahrenheit = %.2f °F, Kelvin = %.2f K",
                        f, k
                    );
                }

                else if (unit.equals("Fahrenheit")) {
                    double c = (temp - 32) * 5 / 9;
                    double k = c + 273.15;

                    result = String.format(
                        "Celsius = %.2f °C, Kelvin = %.2f K",
                        c, k
                    );
                }

                else if (unit.equals("Kelvin")) {
                    double c = temp - 273.15;
                    double f = (c * 9 / 5) + 32;

                    result = String.format(
                        "Celsius = %.2f °C, Fahrenheit = %.2f °F",
                        c, f
                    );
                }

                resultLabel.setText(result);

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid temperature value!",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }

        if (e.getSource() == clearButton) {
            tempField.setText("");
            resultLabel.setText("Result will appear here");
            fromBox.setSelectedIndex(0);
        }
    }

    public static void main(String[] args) {
        new TemperatureConverter();
    }
}