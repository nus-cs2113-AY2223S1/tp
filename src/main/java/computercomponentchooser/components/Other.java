package computercomponentchooser.components;

public class Other extends Component {

    public Other(String name, String price, String power) {
        this.name = name;
        this.price = price;
        this.power = power;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String toCsv() {
        return super.toCsv();
    }

    @Override
    public String saveAsString() {
        return super.saveAsString();
    }

    public String getDetails() {
        return super.getDetails();
    }

    public String getType() {
        return "other";
    }
}

