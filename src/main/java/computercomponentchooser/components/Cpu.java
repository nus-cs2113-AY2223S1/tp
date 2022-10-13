package computercomponentchooser.components;

public class Cpu {
    protected String name;
    protected float price;
    protected String socket;
    protected float clock;
    protected int power;

    public Cpu(String name, float price, String socket, float clock, int power) {
        this.name = name;
        this.price = price;
        this.socket = socket;
        this.clock = clock;
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

    public float getClock() {
        return clock;
    }

    public void setClock(float clock) {
        this.clock = clock;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}

