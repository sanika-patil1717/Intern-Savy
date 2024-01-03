import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterApp extends JFrame {
    private JTextField inputField;
    private JButton convertButton;
    private JLabel resultLabel;
    private JRadioButton celsiusToFahrenheitRadioButton;
    private JRadioButton fahrenheitToCelsiusRadioButton;

    public TemperatureConverterApp() {
        setTitle("Temperature Converter");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBounds(30, 30, 380, 60);
        inputPanel.setBackground(Color.DARK_GRAY);

        JLabel label = new JLabel("Enter temperature:");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        inputField = new JTextField(15);
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputField.setPreferredSize(new Dimension(100, 30));
        inputPanel.add(label);
        inputPanel.add(inputField);
        panel.add(inputPanel);

        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setLayout(new GridLayout(2, 1));
        radioButtonPanel.setBounds(30, 110, 380, 80);
        radioButtonPanel.setBackground(Color.DARK_GRAY);

        celsiusToFahrenheitRadioButton = new JRadioButton("Celsius to Fahrenheit");
        celsiusToFahrenheitRadioButton.setForeground(Color.BLACK); // Change font color to black
        celsiusToFahrenheitRadioButton.setFont(new Font("Arial", Font.PLAIN, 14));
        fahrenheitToCelsiusRadioButton = new JRadioButton("Fahrenheit to Celsius");
        fahrenheitToCelsiusRadioButton.setForeground(Color.BLACK); // Change font color to black
        fahrenheitToCelsiusRadioButton.setFont(new Font("Arial", Font.PLAIN, 14));

        ButtonGroup group = new ButtonGroup();
        group.add(celsiusToFahrenheitRadioButton);
        group.add(fahrenheitToCelsiusRadioButton);

        radioButtonPanel.add(celsiusToFahrenheitRadioButton);
        radioButtonPanel.add(fahrenheitToCelsiusRadioButton);
        panel.add(radioButtonPanel);

        convertButton = new JButton("Convert");
        convertButton.setBounds(160, 210, 100, 40);
        convertButton.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(convertButton);

        resultLabel = new JLabel();
        resultLabel.setBounds(30, 270, 380, 30);
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        panel.add(resultLabel);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });

        add(panel);
        setVisible(true);
    }

    private void convertTemperature() {
        try {
            double temperature = Double.parseDouble(inputField.getText());
            if (celsiusToFahrenheitRadioButton.isSelected()) {
                double fahrenheit = (temperature * 9 / 5) + 32;
                resultLabel.setText(temperature + " Celsius is equal to " + fahrenheit + " Fahrenheit");
            } else if (fahrenheitToCelsiusRadioButton.isSelected()) {
                double celsius = (temperature - 32) * 5 / 9;
                resultLabel.setText(temperature + " Fahrenheit is equal to " + celsius + " Celsius");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a conversion type.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for temperature.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TemperatureConverterApp();
            }
        });
    }
}
