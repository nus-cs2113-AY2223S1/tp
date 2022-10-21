package computercomponentchooser.components;

public class Cooler extends Component {

    protected String fanSpeed;
    protected String noiseLevel;
    protected String socket;

    public Cooler(String name, String price, String power, String fanSpeed, String noiseLevel, String socket) {
        this.name = name;
        this.price = price;
        this.power = power;
        this.fanSpeed = fanSpeed;
        this.noiseLevel = noiseLevel;
        this.socket = socket;
    }

    public String getFanSpeed() {
        return fanSpeed;
    }

    public void setFanSpeed(String fanSpeed) {
        this.fanSpeed = fanSpeed;
    }

    public String getNoiseLevel() {
        return noiseLevel;
    }

    public void setNoiseLevel(String noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String toString() {
        return super.toString() + String.format(" [%s RPM] [%s dB] [%s]", fanSpeed, noiseLevel, socket);
    }

    public String toCsv() {
        return super.toCsv() + "," + fanSpeed + "," + noiseLevel + "," + socket;
    }

    public String saveAsString() {
        return super.saveAsString() + "/" + fanSpeed + "/" + noiseLevel + "/" + socket;
    }

    public String getDetails() {
        return super.getDetails() + String.format("\nFan Speed: %s RPM\nNoise Level: %s dB\nSocket: %s", fanSpeed,
                noiseLevel, socket);
    }

    @Override
    public String getType() {
        return "cooler";
    }
}
