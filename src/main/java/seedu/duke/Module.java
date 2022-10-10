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
        return "";
    }


}
