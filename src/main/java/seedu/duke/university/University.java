package seedu.duke.university;

public class University {
    private String name;
    private String country;

    public University(String name, String country) {
        assert name.length() > 0 : "Name cannot be empty";
        assert country.length() > 0 : "Country cannot be empty";
        
        setName(name);
        setCountry(country);
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
