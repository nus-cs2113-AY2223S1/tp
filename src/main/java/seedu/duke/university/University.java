package seedu.duke.university;

import seedu.duke.exceptions.InvalidUniversityException;

public class University {
    private String name;
    private String country;

    public University(String name, String country) throws InvalidUniversityException {
        if (!isValidUniversity(name, country)) {
            throw new InvalidUniversityException("Invalid University: " + name + " " + country);
        }

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

    private boolean isValidUniversity(String name, String country) {
        if (name.length() == 0) {
            return false;
        }

        if (country.length() == 0) {
            return false;
        }

        return true;
    }
}
