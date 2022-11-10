package computercomponentchooser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import computercomponentchooser.components.Case;
import computercomponentchooser.components.Cpu;
import computercomponentchooser.components.Drive;
import computercomponentchooser.components.Gpu;
import computercomponentchooser.components.Memory;
import computercomponentchooser.components.Motherboard;
import computercomponentchooser.components.PowerSupply;
import computercomponentchooser.exceptions.NegativeNumberException;

public class BuildTest {
    @Test
    public void testName() {
        Build build = new Build("test");
        assertEquals("test", build.getName());
        build.setName("test1");
        assertEquals("test1", build.getName());
    }

    @Test
    public void testAddComponent() throws NegativeNumberException {
        Build build = new Build("test");
        Cpu cpu = new Cpu("cpu", "123", "12", "1", "4.0");
        build.addComponent("cpu", cpu);
        assertEquals(cpu, build.getComponent("cpu", "cpu"));
    }

    @Test
    public void testDeleteComponent() throws NegativeNumberException {
        Build build = new Build("test");
        Cpu cpu = new Cpu("cpu", "123", "12", "1", "4.0");
        build.addComponent("cpu", cpu);
        assertEquals(cpu, build.getComponent("cpu", "cpu"));
        build.deleteComponent("cpu", "cpu");
        assertNull(build.getComponent("cpu", "cpu"));
    }

    @Test
    public void testAddNonExistentComponent() throws NegativeNumberException {
        Build build = new Build("test");
        Cpu cpu = new Cpu("cpu", "123", "12", "1", "4.0");
        build.addComponent("fake", cpu);
        assertNull(build.getComponent("fake", "cpu"));
    }

    @Test
    public void testGetTotalCost() throws NegativeNumberException {
        Build build = new Build("test");
        Cpu cpu = new Cpu("cpu", "123", "12", "1", "4.0");
        build.addComponent("cpu", cpu);
        assertEquals(123, build.getTotalCost());
        Cpu cpu2 = new Cpu("cpu2", "400", "85", "1", "4.2");
        build.addComponent("cpu", cpu2);
        assertEquals(523, build.getTotalCost());
        build.deleteComponent("cpu", "cpu2");
        assertEquals(123, build.getTotalCost());
    }

    @Test
    public void testGetTotalPower() throws NegativeNumberException {
        Build build = new Build("test");
        Cpu cpu = new Cpu("cpu", "123", "12", "1", "4.0");
        build.addComponent("cpu", cpu);
        assertEquals(12, build.getTotalPower());
        Cpu cpu2 = new Cpu("cpu2", "400", "85", "1", "4.2");
        build.addComponent("cpu", cpu2);
        assertEquals(97, build.getTotalPower());
        build.deleteComponent("cpu", "cpu2");
        assertEquals(12, build.getTotalPower());
    }

    @Test
    public void testGetTotalPowerWithPowerSupply() throws NegativeNumberException {
        Build build = new Build("test");
        Cpu cpu = new Cpu("cpu", "123", "12", "1", "4.0");
        build.addComponent("cpu", cpu);
        assertEquals(12, build.getTotalPower());
        PowerSupply psu = new PowerSupply("psu", "100", "100");
        build.addComponent("psu", psu);
        assertEquals(12, build.getTotalPower());
    }

    @Test
    public void testCheckPowerSupply() throws NegativeNumberException {
        Build build = new Build("test");
        PowerSupply psu = new PowerSupply("psu", "100", "100");
        build.addComponent("powersupply", psu);
        Cpu cpu = new Cpu("cpu", "123", "12", "1", "4.0");
        build.addComponent("cpu", cpu);
        assertTrue(build.checkPowerSupply());
    }

    @Test
    public void testCheckPowerSupplyNotEnough() throws NegativeNumberException {
        Build build = new Build("test");
        PowerSupply psu = new PowerSupply("psu", "100", "100");
        build.addComponent("powersupply", psu);
        Cpu cpu = new Cpu("cpu", "123", "101", "1", "4.0");
        build.addComponent("cpu", cpu);
        assertFalse(build.checkPowerSupply());
    }

    @Test
    public void testCheckSocket() throws NegativeNumberException {
        Build build = new Build("test");
        Cpu cpu = new Cpu("cpu", "123", "12", "ABC", "4.0");
        build.addComponent("cpu", cpu);
        Motherboard mb = new Motherboard("mb", "123", "50", "ABC", "form", "4", "2");
        build.addComponent("motherboard", mb);
        assertTrue(build.checkSocket());
    }

    @Test
    public void testCheckSocketNotCompatible() throws NegativeNumberException {
        Build build = new Build("test");
        Cpu cpu = new Cpu("cpu", "123", "12", "ABC", "4.0");
        build.addComponent("cpu", cpu);
        Motherboard mb = new Motherboard("mb", "123", "50", "DEF", "form", "4", "2");
        build.addComponent("motherboard", mb);
        assertFalse(build.checkSocket());
    }

    @Test
    public void testCheckSocketNoCpu() throws NegativeNumberException {
        Build build = new Build("test");
        Motherboard mb = new Motherboard("mb", "123", "50", "DEF", "form", "4", "2");
        build.addComponent("motherboard", mb);
        assertTrue(build.checkSocket());
    }

    @Test
    public void testCheckSocketNoMb() throws NegativeNumberException {
        Build build = new Build("test");
        Cpu cpu = new Cpu("cpu", "123", "12", "ABC", "4.0");
        build.addComponent("cpu", cpu);
        assertFalse(build.checkSocket());
    }

    @Test
    public void testCheckFormFactor() throws NegativeNumberException {
        Build build = new Build("test");
        Motherboard mb = new Motherboard("mb", "123", "50", "ABC", "form", "4", "2");
        build.addComponent("motherboard", mb);
        Case c = new Case("c", "123", "50", "form", "2");
        build.addComponent("case", c);
        assertTrue(build.checkFormFactor());
    }

    @Test
    public void testCheckFormFactorNotCompatible() throws NegativeNumberException {
        Build build = new Build("test");
        Motherboard mb = new Motherboard("mb", "123", "50", "ABC", "form", "4", "2");
        build.addComponent("motherboard", mb);
        Case c = new Case("c", "123", "50", "form2", "2");
        build.addComponent("case", c);
        assertFalse(build.checkFormFactor());
    }

    @Test
    public void testCheckFormFactorNoMb() throws NegativeNumberException {
        Build build = new Build("test");
        Case c = new Case("c", "123", "50", "form2", "2");
        build.addComponent("case", c);
        assertFalse(build.checkFormFactor());
    }

    @Test
    public void testCheckFormFactorNoCase() throws NegativeNumberException {
        Build build = new Build("test");
        Motherboard mb = new Motherboard("mb", "123", "50", "ABC", "form", "4", "2");
        build.addComponent("motherboard", mb);
        assertFalse(build.checkFormFactor());
    }

    @Test
    public void testCheckExpansionSlots() throws NegativeNumberException {
        Build build = new Build("test");
        Case c = new Case("c", "123", "50", "form", "2");
        build.addComponent("case", c);
        Drive drive = new Drive("drive", "123", "50", "200", "SSD");
        build.addComponent("drive", drive);
        assertTrue(build.checkExpansionSlots());
    }

    @Test
    public void testCheckExpansionSlotsNotEnough() throws NegativeNumberException {
        Build build = new Build("test");
        Case c = new Case("c", "123", "50", "form", "1");
        build.addComponent("case", c);
        Drive drive = new Drive("drive", "123", "50", "200", "SSD");
        build.addComponent("drive", drive);
        Drive drive2 = new Drive("drive2", "123", "50", "200", "SSD");
        build.addComponent("drive", drive2);
        assertFalse(build.checkExpansionSlots());
    }

    @Test
    public void testCheckExpansionSlotsNoCase() throws NegativeNumberException {
        Build build = new Build("test");
        Drive drive = new Drive("drive", "123", "50", "200", "SSD");
        build.addComponent("drive", drive);
        assertFalse(build.checkExpansionSlots());
    }

    @Test
    public void testCheckExpansionSlotsNoDrive() throws NegativeNumberException {
        Build build = new Build("test");
        Case c = new Case("c", "123", "50", "form", "1");
        build.addComponent("case", c);
        assertTrue(build.checkExpansionSlots());
    }

    @Test
    public void testCheckGpuSlot() throws NegativeNumberException {
        Build build = new Build("test");
        Motherboard mb = new Motherboard("mb", "123", "50", "ABC", "form", "4", "2");
        build.addComponent("motherboard", mb);
        Gpu gpu = new Gpu("gpu", "123", "50", "2", "2");
        build.addComponent("gpu", gpu);
        assertTrue(build.checkGpuSlot());
    }

    @Test
    public void testCheckGpuSlotNotEnough() throws NegativeNumberException {
        Build build = new Build("test");
        Motherboard mb = new Motherboard("mb", "123", "50", "ABC", "form", "4", "1");
        build.addComponent("motherboard", mb);
        Gpu gpu = new Gpu("gpu", "123", "50", "2", "2");
        build.addComponent("gpu", gpu);
        Gpu gpu2 = new Gpu("gpu2", "123", "50", "2", "2");
        build.addComponent("gpu", gpu2);
        assertFalse(build.checkGpuSlot());
    }

    @Test
    public void testCheckGpuSlotNoMb() throws NegativeNumberException {
        Build build = new Build("test");
        Gpu gpu = new Gpu("gpu", "123", "50", "2", "2");
        build.addComponent("gpu", gpu);
        assertFalse(build.checkGpuSlot());
    }

    @Test
    public void testCheckGpuSlotNoGpu() throws NegativeNumberException {
        Build build = new Build("test");
        Motherboard mb = new Motherboard("mb", "123", "50", "ABC", "form", "4", "1");
        build.addComponent("motherboard", mb);
        assertTrue(build.checkGpuSlot());
    }

    @Test
    public void testCheckMemorySlot() throws NegativeNumberException {
        Build build = new Build("test");
        Motherboard mb = new Motherboard("mb", "123", "50", "ABC", "form", "4", "2");
        build.addComponent("motherboard", mb);
        Memory mem = new Memory("mem", "123", "50", "2", "2");
        build.addComponent("memory", mem);
        assertTrue(build.checkMemorySlot());
    }

    @Test
    public void testCheckMemorySlotNotEnough() throws NegativeNumberException {
        Build build = new Build("test");
        Motherboard mb = new Motherboard("mb", "123", "50", "ABC", "form", "1", "2");
        build.addComponent("motherboard", mb);
        Memory mem = new Memory("mem", "123", "50", "2", "2");
        build.addComponent("memory", mem);
        Memory mem2 = new Memory("mem2", "123", "50", "2", "2");
        build.addComponent("memory", mem2);
        assertFalse(build.checkMemorySlot());
    }

    @Test
    public void testCheckMemorySlotNoMb() throws NegativeNumberException {
        Build build = new Build("test");
        Memory mem = new Memory("mem", "123", "50", "2", "2");
        build.addComponent("memory", mem);
        assertFalse(build.checkMemorySlot());
    }

    @Test
    public void testCheckMemorySlotNoMem() throws NegativeNumberException {
        Build build = new Build("test");
        Motherboard mb = new Motherboard("mb", "123", "50", "ABC", "form", "1", "2");
        build.addComponent("motherboard", mb);
        assertTrue(build.checkMemorySlot());
    }


}
