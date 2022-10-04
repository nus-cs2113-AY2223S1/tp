package seedu.duke;

public class Biometrics {
    private int age;
    private String gender;
    private int height;
    private int weight;
    private int fatPercentage;

    public Biometrics() {
        age = 0;
        gender = "-";
        height = 0;
        weight = 0;
        fatPercentage = 0;
    }

    public void setBiometrics(int age, String gender, int height, int weight, int fatPercentage) {
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.fatPercentage = fatPercentage;
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
