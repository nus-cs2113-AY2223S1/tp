package computercomponentchooser.components;

public class Cpu extends Component {
    protected String socket;
    protected String clock;

    public Cpu(String name, String price, String power, String socket, String clock) {
        this.name = name;
        this.price = price;
        this.power = power;
        this.socket = socket;
        this.clock = clock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSocket() {
        return socket;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [%s] [%s GHz]", socket, clock);
    }

    public String getDetails() {
        return super.getDetails() + String.format("\nSocket: %s\nClock: %s GHz", socket, clock);
    }
}

