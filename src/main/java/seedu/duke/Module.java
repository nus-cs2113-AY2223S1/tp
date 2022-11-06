package seedu.duke;

public class Module {

    private final String course;
    private final String semesterTaken;
    private final String grade;
    private final int mcs;

    /**
     * Contructor to initialize the class variables of Module class
     * @param course the course taken or module code
     * @param semesterTaken the year and semester in which the course was taken
     * @param grade the grade received in the module
     * @param mcs the number of mcs of the module
     */
    public Module(String course, String semesterTaken, String grade, int mcs) {
        this.course = course;
        this.semesterTaken = semesterTaken;
        this.grade = grade;
        this.mcs = mcs;
    }

    public String toString() {
        return this.course + " " + this.semesterTaken + " "
                + this.grade + " " + this.mcs;
    }

    public String getCourse() {
        return this.course;
    }

    public String getSemesterTaken() {
        return this.semesterTaken;
    }

    public String getGrade() {
        return this.grade;
    }

    public int getMcs() {
        return this.mcs;
    }

}
