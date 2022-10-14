package computercomponentchooser.components;

public class Drive extends Component {
    protected String size;
    protected String driveType;

    public Drive(String name, String price, String power, String size, String type) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.driveType = type;
        this.power = power;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [%s GB] [%s]", size, driveType);
    }

    public String getDetails() {
        return super.getDetails() + String.format("\nSize: %s GB\nType: %s", size, driveType);
    }

    public String getType() {
        return "drive";
    }
}
