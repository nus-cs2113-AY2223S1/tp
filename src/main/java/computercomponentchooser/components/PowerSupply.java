package computercomponentchooser.components;

public class PowerSupply extends Component {

    public PowerSupply(String name, String price, String power) {
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

    public String getType() {
        return "powersupply";
    }
}
