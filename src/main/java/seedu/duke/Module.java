package seedu.duke;

public class Module {

    public String course;
    public String semesterTaken;
    public String grade;
    public int mcs;

    public Module(){

    }
    public Module(String course, String semesterTaken, String grade, int mcs) {
        this.course = course;
        this.semesterTaken = semesterTaken;
        this.grade = grade;
        this.mcs = mcs;
    }

    public String toString() {
        String moduleInformation = this.course + " " + this.semesterTaken + ' '
                + this.grade + ' ' + this.mcs;
        return moduleInformation;
    }

    public String getCourse() { return this.course; }
    public String getSemesterTaken() { return this.semesterTaken; }
    public String getGrade() { return this.grade; }
    public int getMcs() { return this.mcs; }

}
