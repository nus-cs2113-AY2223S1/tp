package seedu.duke.university;

import java.util.ArrayList;

import seedu.duke.module.Module;

public class University {
    private String name;
    private String country;
    private ArrayList<Module> modules;

    public University(String name, String country) {
        setName(name);
        setCountry(country);
        setModules(new ArrayList<>());
    }

    @Override
    public String toString() {
        return getName() + " is in " + getCountry();
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

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }
}
