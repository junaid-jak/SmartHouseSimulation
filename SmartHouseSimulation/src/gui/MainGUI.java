package gui;

import managers.EnergyManager;
import models.SmartObject;
import models.EnergySource;
import utils.FileHandler;
import javax.swing.*;
import java.awt.*;


public class MainGUI extends JFrame {
    private JTextField smartObjectName, smartObjectConsumption;
    private JTextField energySourceName, energySourceSupply;
    private JTextArea outputArea;
    private JButton simulateButton;
    private EnergyManager manager;

    public MainGUI() {
        manager = new EnergyManager();

        // Frame settings
        setTitle("Smart House: Energy Supply and Consumption");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Smart Objects and Energy Sources"));

        // Input fields for SmartObject
        inputPanel.add(new JLabel("Smart Object Name:"));
        smartObjectName = new JTextField();
        inputPanel.add(smartObjectName);

        inputPanel.add(new JLabel("Consumption (kWh):"));
        smartObjectConsumption = new JTextField();
        inputPanel.add(smartObjectConsumption);

        JButton addSmartObjectBtn = new JButton("Add Smart Object");
        inputPanel.add(addSmartObjectBtn);
        inputPanel.add(new JLabel("")); // Empty placeholder for alignment

        // Input fields for EnergySource
        inputPanel.add(new JLabel("Energy Source Name:"));
        energySourceName = new JTextField();
        inputPanel.add(energySourceName);

        inputPanel.add(new JLabel("Supply (kWh):"));
        energySourceSupply = new JTextField();
        inputPanel.add(energySourceSupply);

        JButton addEnergySourceBtn = new JButton("Add Energy Source");
        inputPanel.add(addEnergySourceBtn);
        inputPanel.add(new JLabel("")); // Empty placeholder for alignment

        // Simulation Button
        simulateButton = new JButton("Run Simulation");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(simulateButton);

        // Output Area
        outputArea = new JTextArea(15, 40);
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createTitledBorder("Simulation Results"));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        addSmartObjectBtn.addActionListener(e -> {
            try {
                String name = smartObjectName.getText().trim();
                double consumption = Double.parseDouble(smartObjectConsumption.getText().trim());
                manager.addSmartObject(new SmartObject(name, consumption));
                outputArea.append("Added Smart Object: " + name + " (" + consumption + " kWh)\n");
                smartObjectName.setText("");
                smartObjectConsumption.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid consumption (numeric).", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        addEnergySourceBtn.addActionListener(e -> {
            try {
                String name = energySourceName.getText().trim();
                double supply = Double.parseDouble(energySourceSupply.getText().trim());
                manager.addEnergySource(new EnergySource(name, supply));
                outputArea.append("Added Energy Source: " + name + " (" + supply + " kWh)\n");
                energySourceName.setText("");
                energySourceSupply.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid supply (numeric).", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        simulateButton.addActionListener(e -> {
            double totalSupply = manager.getEnergySources().stream()
                    .mapToDouble(EnergySource::getSupply)
                    .sum();
            double totalConsumption = manager.getSmartObjects().stream()
                    .mapToDouble(SmartObject::getConsumption)
                    .sum();

            if (totalSupply >= totalConsumption) {
                outputArea.append("Simulation Result: Energy supply is sufficient!\n");
            } else {
                double deficit = totalConsumption - totalSupply;
                outputArea.append("Simulation Result: Energy deficit of " + deficit + " kWh.\n");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainGUI();
        });
    }
}