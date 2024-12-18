package tests;

import managers.EnergyManager;
import models.SmartObject;
import models.EnergySource;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnergyManagerTest {
    @Test
    public void testCalculateTotalConsumption() {
        EnergyManager manager = new EnergyManager();
        manager.addSmartObject(new SmartObject("TV", 50));
        manager.addSmartObject(new SmartObject("Refrigerator", 200));
        assertEquals(250, manager.calculateTotalConsumption(), "Total consumption should be 250 kWh.");
    }

    @Test
    public void testCalculateTotalSupply() {
        EnergyManager manager = new EnergyManager();
        manager.addEnergySource(new EnergySource("Solar", 300));
        manager.addEnergySource(new EnergySource("Grid", 200));
        assertEquals(500, manager.calculateTotalSupply(), "Total supply should be 500 kWh.");
    }
}
