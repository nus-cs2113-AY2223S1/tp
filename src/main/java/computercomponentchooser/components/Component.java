package computercomponentchooser.components;

public abstract class Component {

    protected String name;
    protected String price;
    protected String power;

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

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return String.format("%s [$%s] [%s W]", name, price, power);
    }

    /**
     * Returns a string representation of the component's attributes and used to save
     *
     * @return
     */
    public String saveAsString() {
        return getType() + " " + name + " " + price + " " + power;
    }

    public String getDetails() {
        return String.format("Name: %s\nPrice: $%s\nPower: %s W", name, price, power);
    }

    public abstract  Object getType();
}
