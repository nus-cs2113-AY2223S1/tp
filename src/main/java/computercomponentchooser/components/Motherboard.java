package computercomponentchooser.components;

public class Motherboard extends Component {
    protected String socket;
    protected String memorySlots;

    protected String gpuSlots;

    public Motherboard(String name, String price, String power, String socket, String memorySlots, String gpuSlots) {
        this.name = name;
        this.price = price;
        this.socket = socket;
        this.memorySlots = memorySlots;
        this.power = power;
        this.gpuSlots = gpuSlots;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getMemorySlots() {
        return memorySlots;
    }

    public void setMemorySlots(String memorySlots) {
        this.memorySlots = memorySlots;
    }

    public String getGpuSlots() {
        return gpuSlots;
    }

    public void setGpuSlots(String gpuSlots) {
        this.gpuSlots = gpuSlots;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [%s] [%s RAM slots] [%s GPU slots]", socket, memorySlots, gpuSlots);
    }

    public String getDetails() {
        return super.getDetails() + String.format("\nSocket: %s\nRAM Slots: %s\nGPU Slots: %s", socket, memorySlots,
                gpuSlots);
    }
}
