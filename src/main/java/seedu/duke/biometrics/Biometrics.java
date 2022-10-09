package seedu.duke.biometrics;

import seedu.duke.exception.IllegalValueException;

import java.util.Arrays;

public class Biometrics {
    private int age;
    private String gender;
    private int height;
    private int weight;
    private int fatPercentage;

    private final String[] genderOptions = new String[]{"male", "female", "other"};

    public Biometrics() {
        age = 0;
        gender = "-";
        height = 0;
        weight = 0;
        fatPercentage = 0;
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

    public void setAge(String input) throws IllegalValueException {
        int age = Integer.parseInt(input);
        if (age <= 0 || age > 120) {
            throw new IllegalValueException("That age ain't possible");
        }
        this.age = age;
    }

    public void setGender(String input) throws IllegalValueException {
        if (!Arrays.asList(genderOptions).contains(input)) {
            throw new IllegalValueException("Hi, I only recognise other, female and male genders");
        }
        this.gender = input;
    }

    public void setHeight(String input) throws IllegalValueException {
        int height = Integer.parseInt(input);
        if (height <= 0 || height > 300) {
            throw new IllegalValueException("That's a strange height...");
        }
        this.height = height;
    }

    public void setWeight(String input) throws IllegalValueException {
        int weight = Integer.parseInt(input);
        if (weight <= 0 || weight > 200) {
            throw new IllegalValueException("That weight can't be real!");
        }
        this.weight = weight;
    }

    public void setFatPercentage(String input) throws IllegalValueException {
        int fatPercentage = Integer.parseInt(input);
        if (fatPercentage <= 0 || fatPercentage >= 100) {
            throw new IllegalValueException("Invalid fat percentage");
        }
        this.fatPercentage = fatPercentage;
    }

    @Override
    public String toString() {
        return "Biometrics" + System.lineSeparator()
                + "Age: " + age + System.lineSeparator()
                + "Gender: " + gender + System.lineSeparator()
                + "Height: " + height + "cm" + System.lineSeparator()
                + "Weight: " + weight + "kg" + System.lineSeparator()
                + "Fat percentage: " + fatPercentage + "%";
    }
}
