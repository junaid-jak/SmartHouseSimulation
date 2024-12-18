package models;

public class SmartObject {
    private String name;
    private double consumptionRate; // kWh

    public SmartObject(String name, double consumptionRate) {
        if (consumptionRate < 0) {
            throw new IllegalArgumentException("Consumption rate cannot be negative.");
        }
        this.name = name;
        this.consumptionRate = consumptionRate;
    }

    public String getName() {
        return name;
    }

    public double getConsumption() {  // Renamed the method here
        return consumptionRate;
    }

    @Override
    public String toString() {
        return name + " (Consumption: " + consumptionRate + " kWh)";
    }
}
