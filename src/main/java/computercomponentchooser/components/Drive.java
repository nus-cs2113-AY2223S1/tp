package computercomponentchooser.components;

public class Drive extends Component {
    protected String size;
    protected String type;

    public Drive(String name, String price, String power, String size, String type) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.type = type;
        this.power = power;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
