package computercomponentchooser;

import computercomponentchooser.components.Cpu;
import computercomponentchooser.components.Gpu;
import computercomponentchooser.components.Motherboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ComponentsTest {

    Cpu cpu1;
    Gpu gpu1;
    Motherboard mobo1;

    @BeforeEach
    public void setUp() {
        cpu1 = new Cpu("cpu1", "123", "12", "1", "4.0");
        gpu1 = new Gpu("gpu1", "456", "45", "4","5");
        mobo1 = new Motherboard("mobo1", "123", "12", "1", "2", "2");
    }

    @Test
    public void testGetName() {
        assertEquals("cpu1", cpu1.getName());
        assertEquals("gpu1", gpu1.getName());
        assertEquals("mobo1", mobo1.getName());

        assertNotEquals("Tutorial", cpu1.getName());
        assertNotEquals("Lecture", gpu1.getName());
        assertNotEquals("Lecture", mobo1.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals("123", cpu1.getPrice());
        assertEquals("456", gpu1.getPrice());
        assertEquals("123", mobo1.getPrice());

        assertNotEquals("6969", cpu1.getPrice());
        assertNotEquals("123", gpu1.getPrice());
        assertNotEquals("0", gpu1.getPrice());
    }

    @Test
    public void testGetPower() {
        assertEquals("12", cpu1.getPower());
        assertEquals("45", gpu1.getPower());
        assertEquals("12", mobo1.getPower());

        assertNotEquals("-1", cpu1.getPower());
        assertNotEquals("-1", gpu1.getPower());
        assertNotEquals("-1", mobo1.getPower());
    }
}
