package computercomponentchooser.components;

public class PowerSupply extends Component {

    public PowerSupply(String name, String price, String power) {
        this.name = name;
        this.price = price;
        this.power = power;
    }

    public String getType() {
        return "powersupply";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
