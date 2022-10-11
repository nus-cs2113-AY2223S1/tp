package seedu.duke;

import seedu.duke.module.Module;
import seedu.duke.module.lessons.Lesson;

import java.util.ArrayList;
import java.util.List;

public class Timetable {
    public static List<Module> listOfModules = new ArrayList<>();

    public void addNewModule(String code, String name, String description, List<Lesson> lessons) {
        listOfModules.add(new Module(code, name, description, lessons));
    }

    public String listModules() {
        StringBuilder list;
        int counter = 1;

        if (listOfModules.size() == 0) {
            return "You have no modules at the moment!";
        } else {
            list = new StringBuilder("Here are your modules:\n");
        }
        for (Module modules : listOfModules) {
            list.append(counter).append(". ").append(modules.getModuleDetails()).append("\n");
            counter += 1;
        }
        return list.toString();
    }


    public void deleteModule(int index){
        listOfModules.remove(index - 1); // the nth module in list has index n-1

    public int getListLength() {
        return listOfModules.size();
    }

    public String getShortenedList() {
        StringBuilder list = new StringBuilder();
        int index = 1;
        for (Module module : listOfModules) {
            list.append(index).append(". ").append(module.toString()).append("\n");
            index += 1;
        }
        return list.toString();
    }

    public int getLessonTypeLength(int index) {
        return listOfModules.get(index).getLessonTypeLength();
    }

    public String getLessonTypes(int index) {
        return listOfModules.get(index).getLessonTypes();
    }

    public String getLessonTypeFromIndex(int indexForModule, int lessonIndex) {
        assert indexForModule >= 0 : "index should be within range";
        assert lessonIndex >= 0 : "index should be within range";

        return listOfModules.get(indexForModule).getTypeOfLessonFromIndex(lessonIndex);
    }

    public String listAllPossibleLessonReplacements(int indexForModule, String targetLessonType) {
        assert indexForModule >= 0 : "index should be within range";

        return listOfModules.get(indexForModule).getListOfLessonReplacements(targetLessonType);
    }

    public int getNumberOfPossibleReplacements(int indexForModule, String targetLessonType) {
        assert indexForModule >= 0 : "index should be within range";

        return listOfModules.get(indexForModule).getNumberOfReplacements(targetLessonType);
    }

    public Lesson getLessonReplacement(int indexForModule, int indexForTarget, String targetType) {
        assert indexForModule >= 0 : "index should be within range";

        return listOfModules.get(indexForModule).getReplacement(targetType, indexForTarget);
    }

    public void replaceLesson(Lesson newLesson, int indexForModule) {
        assert indexForModule >= 0 : "index should be within range";

        listOfModules.get(indexForModule).replaceAttending(newLesson);
    }
}
