package models;

public class EnergySource {
    private String name;
    private double supplyRate; // kWh

    public EnergySource(String name, double supplyRate) {
        if (supplyRate < 0) {
            throw new IllegalArgumentException("Supply rate cannot be negative.");
        }
        this.name = name;
        this.supplyRate = supplyRate;
    }

    public String getName() {
        return name;
    }

    public double getSupply() {  // Renamed the method here
        return supplyRate;
    }

    @Override
    public String toString() {
        return name + " (Supply: " + supplyRate + " kWh)";
    }
}
