package computercomponentchooser;

import computercomponentchooser.components.Cpu;
import computercomponentchooser.components.Gpu;
import computercomponentchooser.components.Motherboard;
import org.junit.jupiter.api.BeforeEach;

public class BuildTest {

    Build build1;
    Cpu cpu1;
    Gpu gpu1;
    Motherboard mobo1;

    @BeforeEach
    public void setUp() {
        cpu1 = new Cpu("cpu1", "123", "12", "1", "4.0");
        gpu1 = new Gpu("gpu1", "456", "45", "4","5");
        mobo1 = new Motherboard("mobo1", "123", "12", "1", "2", "2");
        build1 = new Build("build1");
        build1.addComponent("cpu", cpu1);
        build1.addComponent("gpu", gpu1);
        build1.addComponent("motherboard", mobo1);
    }
}
