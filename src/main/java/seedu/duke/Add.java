package seedu.duke;

public class Add extends Command {
    private static Module mod = new Module();
    public Add(String input) {
        int courseIndex = input.indexOf("m/");
        int semesterIndex = input.indexOf("s/");
        int mcIndex = input.indexOf("mc/");
        int gradeIndex = input.indexOf("g/");
        String course = input.substring(courseIndex + 2, courseIndex + 8);
        String semester = input.substring(semesterIndex + 2, semesterIndex + 6);
        String mcString = input.substring(mcIndex + 3, mcIndex + 4);
        int mc = Integer.parseInt(mcString);
        int spaceIndex = input.indexOf(" ", gradeIndex);

        String grade;
        if (spaceIndex == -1) {
            grade = input.substring(gradeIndex + 2);
        } else {
            grade = input.substring(gradeIndex + 2, spaceIndex);
        }
        this.mod = new Module(course, semester, grade, mc);
    }

    @Override
    public void execute(UI ui, ModuleList modulelist) {
        modulelist.add(this.mod);
    }
}
