package seedu.duke.records.biometrics;

import seedu.duke.logic.exception.IllegalValueException;

import java.util.Arrays;

/**
 * Represents the biometrics component. Stores information about static biometrics (age, gender, height, activity level)
 * and dynamic biometrics (weight and fat percentage).
 */
public class Biometrics {

    private static final String[] GENDER_OPTIONS = new String[]{"male", "female", "others"};
    private static final int MAX_AGE = 120;
    private static final int MAX_HEIGHT = 300;

    private int age;
    private String gender;
    private int height;
    private int activityLevel;
    public boolean isSet;
    public WeightAndFatList weightAndFatList;

    public Biometrics() {
        age = 0;
        gender = "-";
        height = 0;
        activityLevel = 0;
        isSet = false;
        weightAndFatList = new WeightAndFatList();
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
        if (weightAndFatList.getSize() == 0) {
            return 0;
        }
        return weightAndFatList.getMostRecent().getWeight();
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    /**
     * Sets the biometrics to user's inputs.
     *
     * @param age           user's input for age
     * @param gender        user's input for gender
     * @param height        user's input for height
     * @param activityLevel user's input for activity level
     * @throws IllegalValueException if any of the parameters are out of the accepted range of values
     */
    public void setBiometrics(int age, String gender, int height, int activityLevel)
            throws IllegalValueException {
        setAge(age);
        setGender(gender);
        setHeight(height);
        setActivityLevel(activityLevel);
        isSet = true;
    }

    private void setAge(int age) throws IllegalValueException {
        if (age <= 0 || age > MAX_AGE) {
            throw new IllegalValueException("That age ain't possible");
        }
        this.age = age;
    }

    private void setGender(String gender) throws IllegalValueException {
        if (!Arrays.asList(GENDER_OPTIONS).contains(gender)) {
            throw new IllegalValueException("Hi, I only recognise others, female and male genders");
        }
        this.gender = gender;
    }

    private void setHeight(int height) throws IllegalValueException {
        if (height <= 0 || height > MAX_HEIGHT) {
            throw new IllegalValueException("That's a strange height...");
        }
        this.height = height;
    }

    public void setActivityLevel(int activityLevel) throws IllegalValueException {
        if (activityLevel < 1 || activityLevel > 5) {
            throw new IllegalValueException(
                    "You should only input a number between 1 to 5" + System.lineSeparator()
                            + "Input a number for your activity level " + System.lineSeparator()
                            + "with 1 being the least active and 5 being the most active!"
            );
        }
        this.activityLevel = activityLevel;
    }

    /**
     * Converts biometrics into String for output. If at least one weight and fat record exists, the most recent
     * record will also be displayed.
     *
     * @return returns the biometrics String for printing
     */

    @Override
    public String toString() {
        if (!isSet) {
            return "Biometrics are not set";
        }
        assert (age != 0 && !gender.equals("-") && height != 0 && activityLevel != 0);
        String output = "Age: " + age + System.lineSeparator()
                + "Gender: " + gender + System.lineSeparator()
                + "Height: " + height + "cm" + System.lineSeparator();
        if (weightAndFatList.getSize() > 0) {
            output += "Weight: " + weightAndFatList.getMostRecent().getWeight() + "kg" + System.lineSeparator()
                    + "Fat percentage: " + weightAndFatList.getMostRecent().getFat() + "%" + System.lineSeparator();
        }
        output += "Activity Level: " + activityLevel;
        return output;
    }

    public String saveBiometrics() {
        return String.format("/%d /%s /%d /%d", age, gender, height, activityLevel);
    }
}
