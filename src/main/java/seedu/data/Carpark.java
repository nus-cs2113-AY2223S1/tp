package seedu.data;

import org.codehaus.jackson.annotate.JsonProperty;
public class Carpark {
    private String carparkId;
    private String area;
    private String development;
    private String location;
    private String availableLots;
    private String lotType;
    private String agency;

    @Override
    public String toString(){
        return String.format("CarparkID %s at %s", carparkId, development);
    }

    public String getCarparkId() {
        return carparkId;
    }

    @JsonProperty("CarParkID")
    public void setCarparkId(String carparkId) {
        this.carparkId = carparkId;
    }

    public String getArea() {
        return area;
    }

    @JsonProperty("Area")
    public void setArea(String area) {
        this.area = area;
    }

    public String getDevelopment() {
        return development;
    }

    @JsonProperty("Development")
    public void setDevelopment(String development) {
        this.development = development;
    }

    public String getLocation() {
        return location;
    }

    @JsonProperty("Location")
    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvailableLots() {
        return availableLots;
    }

    @JsonProperty("AvailableLots")
    public void setAvailableLots(String availableLots) {
        this.availableLots = availableLots;
    }

    public String getLotType() {
        return lotType;
    }

    @JsonProperty("LotType")
    public void setLotType(String lotType) {
        this.lotType = lotType;
    }

    public String getAgency() {
        return agency;
    }

    @JsonProperty("Agency")
    public void setAgency(String agency) {
        this.agency = agency;
    }
}
