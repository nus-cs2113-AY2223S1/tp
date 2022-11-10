package computercomponentchooser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import computercomponentchooser.components.Case;
import computercomponentchooser.components.Cooler;
import computercomponentchooser.components.Cpu;
import computercomponentchooser.components.Drive;
import computercomponentchooser.components.Gpu;
import computercomponentchooser.components.Memory;
import computercomponentchooser.components.Monitor;
import computercomponentchooser.components.Motherboard;
import computercomponentchooser.components.PowerSupply;
import computercomponentchooser.exceptions.DuplicateBuildException;
import computercomponentchooser.exceptions.NegativeNumberException;
import computercomponentchooser.exceptions.UnlistedBuildException;

public class BuildManagerTest {
    BuildManager buildManager;
    Build build1;
    Build build2;

    @BeforeEach
    public void setUp() throws NegativeNumberException {
        buildManager = new BuildManager();
        build1 = new Build("build1");
        build1.addComponent("cpu", new Cpu("cpu1", "123", "12", "ABC", "4.0"));
        build1.addComponent("gpu", new Gpu("gpu1", "456", "45", "4", "5"));
        build1.addComponent("motherboard", new Motherboard("mobo1", "123", "12", "ABC",
                "Full ATX", "2", "2"));
        build1.addComponent("memory", new Memory("memory1", "123", "12", "8", "4"));
        build1.addComponent("drive", new Drive("drive1", "123", "12", "200", "SSD"));
        build1.addComponent("monitor", new Monitor("monitor1", "123", "12", "144",
                "4", "3440x1440"));
        build1.addComponent("cooler", new Cooler("cooler1", "123", "12", "ABC",
                "1500", "40"));
        build1.addComponent("case", new Case("case1",
                "123", "1", "Full ATX", "2"));
        build1.addComponent("powersupply", new PowerSupply("powersupply1", "123", "850"));
        // build1 price: 1440, compatible: true
        build2 = new Build("build2");
        build2.addComponent("cpu", new Cpu("cpu2", "2", "12", "ABC", "4.0"));
        build2.addComponent("gpu", new Gpu("gpu2", "2", "45", "4", "5"));
        build2.addComponent("motherboard", new Motherboard("mobo2", "2", "12", "DEF",
                "Mini ATX", "0", "0"));
        build2.addComponent("memory", new Memory("memory2", "2", "12", "2", "4"));
        build2.addComponent("drive", new Drive("drive2", "2", "12", "1", "HDD"));
        build2.addComponent("monitor", new Monitor("monitor2", "2", "12", "144",
                "4", "3440x1440"));
        build2.addComponent("cooler", new Cooler("cooler2", "2", "12", "XYZ",
                "1500", "40"));
        build2.addComponent("case", new Case("case2",
                "2", "1", "Full ATX", "0"));
        build2.addComponent("powersupply", new PowerSupply("powersupply2", "2", "50"));
        // build2 price: 18, compatible: false
    }

    @Test
    public void testAddAndGetBuild() throws DuplicateBuildException {
        buildManager.addBuild(build1);
        buildManager.addBuild(build2);
        assertEquals(2, buildManager.size());
        assertEquals(build1, buildManager.getBuild("build1"));
        assertEquals(build2, buildManager.getBuild("build2"));
    }

    @Test
    public void testAddDuplicateBuild() {
        try {
            buildManager.addBuild(build1);
            buildManager.addBuild(build1);
        } catch (DuplicateBuildException e) {
            assertEquals("Build already exists", e.getMessage());
        }
    }

    @Test
    public void testDeleteBuild() throws DuplicateBuildException, UnlistedBuildException {
        buildManager.addBuild(build1);
        buildManager.addBuild(build2);
        buildManager.deleteBuild("build1");
        assertEquals(1, buildManager.size());
        assertNull(buildManager.getBuild("build1"));
    }

    @Test
    public void testDeleteUnlistedBuild() {
        try {
            buildManager.deleteBuild("fake");
        } catch (UnlistedBuildException e) {
            assertEquals("This build does not exist", e.getMessage());
        }
    }

    @Test
    public void testDoesBuildExist() throws DuplicateBuildException {
        buildManager.addBuild(build1);
        buildManager.addBuild(build2);
        assertTrue(buildManager.doesBuildExist("build1"));
        assertTrue(buildManager.doesBuildExist("build2"));
        assertFalse(buildManager.doesBuildExist("fake"));
    }
}
