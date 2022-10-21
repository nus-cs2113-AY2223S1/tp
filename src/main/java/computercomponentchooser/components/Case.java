package computercomponentchooser.components;

public class Case extends Component {
    protected String formFactor;
    protected String expansionSlots;

    public Case(String name, String price, String power, String formFactor, String expansionSlots) {
        this.name = name;
        this.price = price;
        this.power = power;
        this.formFactor = formFactor;
        this.expansionSlots = expansionSlots;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getExpansionSlots() {
        return expansionSlots;
    }

    public void setExpansionSlots(String expansionSlots) {
        this.expansionSlots = expansionSlots;
    }

    public String toString() {
        return super.toString() + String.format(" [%s] [%s]", formFactor, expansionSlots);
    }

    public String toCsv() {
        return super.toCsv() + "," + formFactor + "," + expansionSlots;
    }

    public String saveAsString() {
        return super.saveAsString() + "/" + formFactor + "/" + expansionSlots;
    }

    public String getDetails() {
        return super.getDetails() + String.format("\nForm Factor: %s\nExpansion Slots: %s", formFactor,
                expansionSlots);
    }

    @Override
    public String getType() {
        return "case";
    }
}
