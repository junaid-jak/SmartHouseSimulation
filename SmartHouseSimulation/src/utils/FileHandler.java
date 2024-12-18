package utils;

import models.EnergySource;
import models.SmartObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String LOG_FILE = "logs.txt";
    private static final String SETTINGS_FILE = "settings.txt";

    // Method to save logs to a file
    public static void saveLog(String log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(log);
            writer.newLine();
            System.out.println("Log saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving log: " + e.getMessage());
        }
    }

    // Method to load initial settings (SmartObjects and EnergySources)
    public static List<Object> loadInitialSettings() {
        List<Object> initialSettings = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(SETTINGS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase("SmartObject")) {
                    initialSettings.add(new SmartObject(parts[1], Double.parseDouble(parts[2])));
                } else if (parts[0].equalsIgnoreCase("EnergySource")) {
                    initialSettings.add(new EnergySource(parts[1], Double.parseDouble(parts[2])));
                }
            }
            System.out.println("Initial settings loaded successfully!");
        } catch (IOException e) {
            System.err.println("Error loading settings: " + e.getMessage());
        }
        return initialSettings;
    }

    // Method to save settings (SmartObjects and EnergySources) to the file
    public static void saveSettings(List<Object> settings) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SETTINGS_FILE))) {
            for (Object obj : settings) {
                if (obj instanceof SmartObject) {
                    SmartObject smartObject = (SmartObject) obj;
                    writer.write("SmartObject," + smartObject.getName() + "," + smartObject.getConsumption());
                    writer.newLine();
                } else if (obj instanceof EnergySource) {
                    EnergySource energySource = (EnergySource) obj;
                    writer.write("EnergySource," + energySource.getName() + "," + energySource.getSupply());
                    writer.newLine();
                }
            }
            System.out.println("Settings saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving settings: " + e.getMessage());
        }
    }
}
