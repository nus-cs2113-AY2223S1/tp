package computercomponentchooser.components;

public class Motherboard {
    protected String name;
    protected float price;
    protected String socket;
    protected int memorySlots;
    protected int power;

    public Motherboard(String name, float price, String socket, int memorySlots, int power) {
        this.name = name;
        this.price = price;
        this.socket = socket;
        this.memorySlots = memorySlots;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public int getMemorySlots() {
        return memorySlots;
    }

    public void setMemorySlots(int memorySlots) {
        this.memorySlots = memorySlots;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
