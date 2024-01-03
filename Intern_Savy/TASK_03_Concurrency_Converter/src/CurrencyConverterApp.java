import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CurrencyConverterApp extends JFrame {
    private JTextField amountField;
    private JButton convertButton;
    private JTextArea resultArea;
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;

    public CurrencyConverterApp() {
        setTitle("Currency Converter");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        JLabel label = new JLabel("Enter amount:");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBounds(30, 30, 150, 30);
        panel.add(label);

        amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.PLAIN, 14));
        amountField.setBounds(150, 30, 200, 30);
        panel.add(amountField);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setForeground(Color.WHITE);
        fromLabel.setFont(new Font("Arial", Font.BOLD, 16));
        fromLabel.setBounds(30, 80, 60, 30);
        panel.add(fromLabel);

        String[] currencies = {"USD", "EUR", "GBP", "JPY", "INR"}; // Added INR for Indian Rupee
        fromCurrencyComboBox = new JComboBox<>(currencies);
        fromCurrencyComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        fromCurrencyComboBox.setBounds(90, 80, 100, 30);
        panel.add(fromCurrencyComboBox);

        JLabel toLabel = new JLabel("To:");
        toLabel.setForeground(Color.WHITE);
        toLabel.setFont(new Font("Arial", Font.BOLD, 16));
        toLabel.setBounds(250, 80, 40, 30);
        panel.add(toLabel);

        toCurrencyComboBox = new JComboBox<>(currencies);
        toCurrencyComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        toCurrencyComboBox.setBounds(290, 80, 100, 30);
        panel.add(toCurrencyComboBox);

        convertButton = new JButton("Convert");
        convertButton.setBounds(160, 130, 100, 40);
        convertButton.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(convertButton);

        resultArea = new JTextArea();
        resultArea.setForeground(Color.WHITE);
        resultArea.setBackground(Color.BLACK);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setEditable(false);
        resultArea.setBounds(30, 190, 380, 100);
        panel.add(resultArea);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        add(panel);
        setVisible(true);
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String fromCurrency = (String) fromCurrencyComboBox.getSelectedItem();
            String toCurrency = (String) toCurrencyComboBox.getSelectedItem();

            // Sample conversion rates for demonstration purposes (not actual rates)
            double usdToInr = 75.0;
            double eurToInr = 88.0;
            double gbpToInr = 100.0;
            double jpyToInr = 0.70;

            double convertedAmount;
            switch (fromCurrency) {
                case "USD":
                    convertedAmount = amount * usdToInr;
                    break;
                case "EUR":
                    convertedAmount = amount * eurToInr;
                    break;
                case "GBP":
                    convertedAmount = amount * gbpToInr;
                    break;
                case "JPY":
                    convertedAmount = amount * jpyToInr;
                    break;
                case "INR":
                    convertedAmount = amount; // For INR to other currencies (1:1 for simplicity)
                    break;
                default:
                    convertedAmount = 0.0;
                    break;
            }

            double finalConvertedAmount;
            switch (toCurrency) {
                case "USD":
                    finalConvertedAmount = convertedAmount / usdToInr;
                    break;
                case "EUR":
                    finalConvertedAmount = convertedAmount / eurToInr;
                    break;
                case "GBP":
                    finalConvertedAmount = convertedAmount / gbpToInr;
                    break;
                case "JPY":
                    finalConvertedAmount = convertedAmount / jpyToInr;
                    break;
                case "INR":
                    finalConvertedAmount = convertedAmount; // For INR from other currencies
                    break;
                default:
                    finalConvertedAmount = 0.0;
                    break;
            }

            DecimalFormat df = new DecimalFormat("#.##");
            String formattedResult = amount + " " + fromCurrency + " = " +
                    df.format(finalConvertedAmount) + " " + toCurrency;

            resultArea.setText(formattedResult);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CurrencyConverterApp();
            }
        });
    }
}
