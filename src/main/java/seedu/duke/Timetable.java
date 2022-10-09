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

}
