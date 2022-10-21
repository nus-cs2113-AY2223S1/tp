package computercomponentchooser.components;

public class Motherboard extends Component {
    protected String socket;

    protected String formFactor;

    protected String memorySlots;

    protected String gpuSlots;

    public Motherboard(String name, String price, String power, String socket, String formFactor, String memorySlots, String gpuSlots) {
        this.name = name;
        this.price = price;
        this.socket = socket;
        this.formFactor = formFactor;
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

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
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
        return super.toString() + String.format(" [%s] [%s] [%s RAM slots] [%s GPU slots]", socket, formFactor,
                memorySlots, gpuSlots);
    }

    @Override
    public String saveAsString() {
        return super.saveAsString() + "/" + socket + "/" + formFactor + "/" + memorySlots + "/" + gpuSlots;
    }

    public String getDetails() {
        return super.getDetails() + String.format("\nSocket: %s\nformFactor: %s\nRAM Slots: %s\nGPU Slots: %s", socket,
                formFactor, memorySlots, gpuSlots);
    }

    public String getType() {
        return "motherboard";
    }
}
