package computercomponentchooser.components;

public class Cooler extends Component {

    protected String socket;
    protected String fanSpeed;
    protected String noiseLevel;

    public Cooler(String name, String price, String power, String socket, String fanSpeed, String noiseLevel) {
        this.name = name;
        this.price = price;
        this.power = power;
        this.socket = socket;
        this.fanSpeed = fanSpeed;
        this.noiseLevel = noiseLevel;
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
        return super.toString() + String.format(" [%s] [%s RPM] [%s dB]", socket, fanSpeed, noiseLevel);
    }

    public String toCsv() {
        return super.toCsv() + "," + socket + "," + fanSpeed + "," + noiseLevel;
    }

    public String saveAsString() {
        return super.saveAsString() + "," + socket + "," + fanSpeed + "," + noiseLevel;
    }

    public String getDetails() {
        return super.getDetails() + String.format("\nSocket: %s\nFan Speed: %s RPM\nNoise Level: %s dB", socket, fanSpeed,
                noiseLevel);
    }

    @Override
    public String getType() {
        return "cooler";
    }
}
