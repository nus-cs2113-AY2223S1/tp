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
}
