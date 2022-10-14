package computercomponentchooser.components;

public class Gpu extends Component {
    protected String chipset;
    protected String memory;
    protected String clock;

    public Gpu(String name, String price, String power,  String memory, String clock) {
        this.name = name;
        this.price = price;
        this.memory = memory;
        this.clock = clock;
        this.power = power;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }
}
