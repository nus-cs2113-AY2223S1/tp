package computercomponentchooser;

import computercomponentchooser.components.Cpu;
import computercomponentchooser.components.Gpu;
import computercomponentchooser.components.Motherboard;
import computercomponentchooser.components.Cooler;
import computercomponentchooser.components.Case;
import computercomponentchooser.components.Drive;
import computercomponentchooser.components.Memory;
import computercomponentchooser.components.Monitor;
import computercomponentchooser.components.PowerSupply;
import computercomponentchooser.components.Other;
import computercomponentchooser.components.Component;
import computercomponentchooser.exceptions.NegativeNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ComponentsTest {

    Cpu cpu1;
    Gpu gpu1;
    Motherboard mobo1;
    Cooler cooler1;
    Case case1;
    Drive drive1;
    Memory memory1;
    Monitor monitor1;
    PowerSupply powersupply1;
    Other other1;

    @BeforeEach
    public void setUp() {
        try {
            cpu1 = new Cpu("cpu1", "123", "12", "1", "4.0");
            gpu1 = new Gpu("gpu1", "456", "45", "4", "5");
            mobo1 = new Motherboard("mobo1", "123", "12", "1", "Full ATX", "2", "2");
            cooler1 = new Cooler("cooler1", "123", "12", "1", "1500", "40");
            case1 = new Case("case1", "123", "1", "Full ATX", "2");
            powersupply1 = new PowerSupply("powersupply1", "123", "850");
            memory1 = new Memory("memory1", "123", "12", "8", "4");
            drive1 = new Drive("drive1", "123", "12", "200", "SSD");
            monitor1 = new Monitor("monitor1", "123", "12", "144", "4", "3440x1440");
            other1 = new Other("other1", "123", "12");

        } catch (NumberFormatException | NegativeNumberException e) {
            System.out.println("Error in setUp");
        }
    }

    @Test
    public void testGetName() {
        assertEquals("cpu1", cpu1.getName());
        assertEquals("gpu1", gpu1.getName());
        assertEquals("mobo1", mobo1.getName());
        assertEquals("cooler1", cooler1.getName());
        assertEquals("case1", case1.getName());
        assertEquals("powersupply1", powersupply1.getName());
        assertEquals("memory1", memory1.getName());
        assertEquals("drive1", drive1.getName());
        assertEquals("monitor1", monitor1.getName());
        assertEquals("other1", other1.getName());

        assertNotEquals("Tutorial", cpu1.getName());
        assertNotEquals("Lecture", gpu1.getName());
        assertNotEquals("Lecture", mobo1.getName());
        assertNotEquals("Recitation", cooler1.getName());
        assertNotEquals("Recitation", case1.getName());
        assertNotEquals("Recitation", powersupply1.getName());
        assertNotEquals("Lab", memory1.getName());
        assertNotEquals("Lab", drive1.getName());
        assertNotEquals("Lab", monitor1.getName());
        assertNotEquals("Lab", other1.getName());

        assertNotNull(cpu1.getName());
        assertNotNull(gpu1.getName());
        assertNotNull(mobo1.getName());
        assertNotNull(cooler1.getName());
        assertNotNull(case1.getName());
        assertNotNull(powersupply1.getName());
        assertNotNull(memory1.getName());
        assertNotNull(drive1.getName());
        assertNotNull(monitor1.getName());
        assertNotNull(other1.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals("123", cpu1.getPrice());
        assertEquals("456", gpu1.getPrice());
        assertEquals("123", mobo1.getPrice());
        assertEquals("123", cooler1.getPrice());
        assertEquals("123", case1.getPrice());
        assertEquals("123", powersupply1.getPrice());
        assertEquals("123", memory1.getPrice());
        assertEquals("123", drive1.getPrice());
        assertEquals("123", monitor1.getPrice());
        assertEquals("123", other1.getPrice());

        assertNotEquals("6969", cpu1.getPrice());
        assertNotEquals("123", gpu1.getPrice());
        assertNotEquals("0", gpu1.getPrice());
        assertNotEquals("420", mobo1.getPrice());
        assertNotEquals("0", mobo1.getPrice());
        assertNotEquals("420", cooler1.getPrice());
        assertNotEquals("0", cooler1.getPrice());
        assertNotEquals("420", case1.getPrice());
        assertNotEquals("0", case1.getPrice());
        assertNotEquals("420", powersupply1.getPrice());
        assertNotEquals("0", powersupply1.getPrice());
        assertNotEquals("420", memory1.getPrice());
        assertNotEquals("0", memory1.getPrice());
        assertNotEquals("420", drive1.getPrice());
        assertNotEquals("0", drive1.getPrice());
        assertNotEquals("420", monitor1.getPrice());
        assertNotEquals("0", monitor1.getPrice());
        assertNotEquals("420", other1.getPrice());
        assertNotEquals("0", other1.getPrice());

        assertNotNull(cpu1.getPrice());
        assertNotNull(gpu1.getPrice());
        assertNotNull(mobo1.getPrice());
        assertNotNull(cooler1.getPrice());
        assertNotNull(case1.getPrice());
        assertNotNull(powersupply1.getPrice());
        assertNotNull(memory1.getPrice());
        assertNotNull(drive1.getPrice());
        assertNotNull(monitor1.getPrice());
        assertNotNull(other1.getPrice());
    }

    @Test
    public void testGetPower() {
        assertEquals("12", cpu1.getPower());
        assertEquals("45", gpu1.getPower());
        assertEquals("12", mobo1.getPower());
        assertEquals("12", cooler1.getPower());
        assertEquals("1", case1.getPower());
        assertEquals("850", powersupply1.getPower());
        assertEquals("12", memory1.getPower());
        assertEquals("12", drive1.getPower());
        assertEquals("12", monitor1.getPower());
        assertEquals("12", other1.getPower());

        assertNotEquals("-1", cpu1.getPower());
        assertNotEquals("-1", gpu1.getPower());
        assertNotEquals("-1", mobo1.getPower());
        assertNotEquals("-1", cooler1.getPower());
        assertNotEquals("-1", case1.getPower());
        assertNotEquals("-1", powersupply1.getPower());
        assertNotEquals("-1", memory1.getPower());
        assertNotEquals("-1", drive1.getPower());
        assertNotEquals("-1", monitor1.getPower());
        assertNotEquals("-1", other1.getPower());

        assertNotNull(cpu1.getPower());
        assertNotNull(gpu1.getPower());
        assertNotNull(mobo1.getPower());
        assertNotNull(cooler1.getPower());
        assertNotNull(case1.getPower());
        assertNotNull(powersupply1.getPower());
        assertNotNull(memory1.getPower());
        assertNotNull(drive1.getPower());
        assertNotNull(monitor1.getPower());
        assertNotNull(other1.getPower());

        assertNotEquals("0", cpu1.getPower());
        assertNotEquals("0", gpu1.getPower());
        assertNotEquals("0", mobo1.getPower());
        assertNotEquals("0", cooler1.getPower());
        assertNotEquals("0", case1.getPower());
        assertNotEquals("0", powersupply1.getPower());
        assertNotEquals("0", memory1.getPower());
        assertNotEquals("0", drive1.getPower());
        assertNotEquals("0", monitor1.getPower());
        assertNotEquals("0", other1.getPower());
    }

    @Test
    public void testToString() {

        assertNotNull(cpu1.toString());
        assertNotNull(gpu1.toString());
        assertNotNull(mobo1.toString());
        assertNotNull(cooler1.toString());
        assertNotNull(case1.toString());
        assertNotNull(powersupply1.toString());
        assertNotNull(memory1.toString());
        assertNotNull(drive1.toString());
        assertNotNull(monitor1.toString());
        assertNotNull(other1.toString());
    }

    @Test
    public void testToCSV() {

        assertNotNull(cpu1.toCsv());
        assertNotNull(gpu1.toCsv());
        assertNotNull(mobo1.toCsv());
        assertNotNull(cooler1.toCsv());
        assertNotNull(case1.toCsv());
        assertNotNull(powersupply1.toCsv());
        assertNotNull(memory1.toCsv());
        assertNotNull(drive1.toCsv());
        assertNotNull(monitor1.toCsv());
        assertNotNull(other1.toCsv());
    }

    @Test
    public void testSaveAsString() {


        assertNotNull(cpu1.saveAsString());
        assertNotNull(gpu1.saveAsString());
        assertNotNull(mobo1.saveAsString());
        assertNotNull(cooler1.saveAsString());
        assertNotNull(case1.saveAsString());
        assertNotNull(powersupply1.saveAsString());
        assertNotNull(memory1.saveAsString());
        assertNotNull(drive1.saveAsString());
        assertNotNull(monitor1.saveAsString());
        assertNotNull(other1.saveAsString());
    }

    @Test
    public void testGetDetails() {


        assertNotNull(cpu1.getDetails());
        assertNotNull(gpu1.getDetails());
        assertNotNull(mobo1.getDetails());
        assertNotNull(cooler1.getDetails());
        assertNotNull(case1.getDetails());
        assertNotNull(powersupply1.getDetails());
        assertNotNull(memory1.getDetails());
        assertNotNull(drive1.getDetails());
        assertNotNull(monitor1.getDetails());
        assertNotNull(other1.getDetails());
    }

    @Test
    public void testGetType() {
        assertEquals("cpu", cpu1.getType());
        assertEquals("gpu", gpu1.getType());
        assertEquals("motherboard", mobo1.getType());
        assertEquals("cooler", cooler1.getType());
        assertEquals("case", case1.getType());
        assertEquals("powersupply", powersupply1.getType());
        assertEquals("memory", memory1.getType());
        assertEquals("drive", drive1.getType());
        assertEquals("monitor", monitor1.getType());
        assertEquals("other", other1.getType());

        assertNotNull(cpu1.getType());
        assertNotNull(gpu1.getType());
        assertNotNull(mobo1.getType());
        assertNotNull(cooler1.getType());
        assertNotNull(case1.getType());
        assertNotNull(powersupply1.getType());
        assertNotNull(memory1.getType());
        assertNotNull(drive1.getType());
        assertNotNull(monitor1.getType());
        assertNotNull(other1.getType());
    }
}
