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
}
