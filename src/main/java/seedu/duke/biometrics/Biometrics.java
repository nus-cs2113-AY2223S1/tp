package seedu.duke.biometrics;

import seedu.duke.exception.IllegalValueException;

import java.util.Arrays;

public class Biometrics {

    private final String[] genderOptions = new String[]{"male", "female", "other"};

    private int age;
    private String gender;
    private int height;
    private int weight;
    private int fatPercentage;
    public boolean isSet;

    public Biometrics() {
        age = 0;
        gender = "-";
        height = 0;
        weight = 0;
        fatPercentage = 0;
        isSet = false;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getFatPercentage() {
        return fatPercentage;
    }

    public void setBiometrics(int age, String gender, int height, int weight, int fatPercentage)
            throws IllegalValueException {
        setAge(age);
        setGender(gender);
        setHeight(height);
        setWeight(weight);
        setFatPercentage(fatPercentage);
        isSet = true;
    }

    public void setAge(int age) throws IllegalValueException {
        if (age <= 0 || age > 120) {
            throw new IllegalValueException("That age ain't possible");
        }
        this.age = age;
    }

    public void setGender(String gender) throws IllegalValueException {
        if (!Arrays.asList(genderOptions).contains(gender)) {
            throw new IllegalValueException("Hi, I only recognise other, female and male genders");
        }
        this.gender = gender;
    }

    public void setHeight(int height) throws IllegalValueException {
        if (height <= 0 || height > 300) {
            throw new IllegalValueException("That's a strange height...");
        }
        this.height = height;
    }

    public void setWeight(int weight) throws IllegalValueException {
        if (weight <= 0 || weight > 400) {
            throw new IllegalValueException("That weight can't be real!");
        }
        this.weight = weight;
    }

    public void setFatPercentage(int fatPercentage) throws IllegalValueException {
        if (fatPercentage <= 0 || fatPercentage >= 100) {
            throw new IllegalValueException("Invalid fat percentage");
        }
        this.fatPercentage = fatPercentage;
    }

    @Override
    public String toString() {
        if(!isSet){
            return "Biometrics are not set";
        }
        assert (age != 0 && !gender.equals("-") && height != 0 && weight != 0 && fatPercentage != 0);
        return "Age: " + age + System.lineSeparator()
                + "Gender: " + gender + System.lineSeparator()
                + "Height: " + height + "cm" + System.lineSeparator()
                + "Weight: " + weight + "kg" + System.lineSeparator()
                + "Fat percentage: " + fatPercentage + "%";
    }

    public String saveBiometrics() {
        return String.format("/%d /%s /%d /%d /%d", age, gender, height, weight, fatPercentage);
    }
}
