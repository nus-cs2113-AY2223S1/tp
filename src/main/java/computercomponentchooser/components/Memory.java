package computercomponentchooser.components;

public class Memory extends Component {
    protected String speed;
    protected String size;

    public Memory(String name, String price, String power, String size, String speed) {
        this.name = name;
        this.price = price;
        this.power = power;
        this.size = size;
        this.speed = speed;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [%s GB] [%s MHz]", size, speed);
    }

    public String toCsv() {
        return super.toCsv() + "," + size + "," + speed;
    }

    @Override
    public String saveAsString() {
        return super.saveAsString() + "/" + size + "/" + speed;
    }

    public String getDetails() {
        return super.getDetails() + String.format("\nSize: %s GB\nSpeed: %s MHz", size, speed);
    }

    public String getType() {
        return "memory";
    }
}
