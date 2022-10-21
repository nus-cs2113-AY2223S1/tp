package computercomponentchooser.components;

public class Monitor extends Component {
    protected String refreshRate;
    protected String responseTime;
    protected String resolution;

    public Monitor(String name, String price, String power, String refreshRate, String responseTime,
                   String resolution) {
        this.name = name;
        this.price = price;
        this.power = power;
        this.refreshRate = refreshRate;
        this.responseTime = responseTime;
        this.resolution = resolution;
    }

    public String getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(String refreshRate) {
        this.refreshRate = refreshRate;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [%s Hz] [%s ms] [%s p]", refreshRate, responseTime, resolution);
    }

    public String saveAsString() {
        return super.saveAsString() + " " + refreshRate + " " + responseTime + " " + resolution;
    }

    public String getDetails() {
        return super.getDetails() + String.format("\nRefresh Rate: %s Hz\nResponse Time: %s ms\nResolution: %s p",
                                                 refreshRate, responseTime, resolution);
    }

    @Override
    public String getType() {
        return "monitor";
    }


}
