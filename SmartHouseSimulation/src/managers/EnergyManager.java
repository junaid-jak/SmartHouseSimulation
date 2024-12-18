package managers;

import models.EnergySource;
import models.SmartObject;
import java.util.ArrayList;
import java.util.List;

public class EnergyManager {
    private List<SmartObject> smartObjects = new ArrayList<>();
    private List<EnergySource> energySources = new ArrayList<>();

    // Add a Smart Object
    public void addSmartObject(SmartObject object) {
        smartObjects.add(object);
    }

    // Add an Energy Source
    public void addEnergySource(EnergySource source) {
        energySources.add(source);
    }

    // Calculate total consumption
    public double calculateTotalConsumption() {
        return smartObjects.stream().mapToDouble(SmartObject::getConsumption).sum();
    }

    // Calculate total supply
    public double calculateTotalSupply() {
        return energySources.stream().mapToDouble(EnergySource::getSupply).sum();
    }

    // Check energy balance
    public String getEnergyBalance() {
        double totalConsumption = calculateTotalConsumption();
        double totalSupply = calculateTotalSupply();

        if (totalSupply >= totalConsumption) {
            return "Energy is Balanced. Supply is sufficient.";
        } else {
            return "Warning! Energy Deficit: " + (totalConsumption - totalSupply) + " kWh.";
        }
    }

    public List<SmartObject> getSmartObjects() {
        return smartObjects;
    }

    public List<EnergySource> getEnergySources() {
        return energySources;
    }
}
