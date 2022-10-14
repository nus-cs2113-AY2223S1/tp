package computercomponentchooser.components;

public class Cpu extends Component {
    protected String socket;
    protected String clock;

    public Cpu(String name, String price, String socket, String clock, String power) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
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
}

