package seedu.duke.command;

import seedu.duke.utils.State;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;
import seedu.duke.model.RawLesson;
import seedu.duke.model.SelectedModule;
import seedu.duke.model.Timetable;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import seedu.duke.model.Module;

/**
 * Get all module details by module code. Display all tutorial and labs session in timetable format.
 */

public class GetModuleCommand extends Command {
    Module module;
    public static final String COMMAND_WORD = "get";

    public GetModuleCommand(String[] input) {
        super(input);
        String moduleCode = input[1].toUpperCase();
        this.module = Module.get(moduleCode);
    }

    @Override
    public void execute(State state, Ui ui, Storage storage) {
        ui.addMessage("Module: " + module.moduleCode);
        ui.addMessage("Module Name: " + module.title);
        ui.addMessage("Module Description: " + module.description);
        ui.addMessage("Module Credit: " + module.moduleCredit);
        ui.addMessage("Department: " + module.department);
        ui.addMessage("Faculty: " + module.faculty);
        ui.addMessage("Workload: " + module.workload.toString());
        ui.addMessage("semester offering : " + module.getSemestersOffering(module));
        ui.addMessage("Prerequisite: " + module.prerequisite.toString());
        ui.addMessage("Preclusion: " + module.preclusion);
        ui.addMessage("Corequisite" + module.corequisite);

        ui.displayDivider();
        // add module's rawlesson into timetable
        // shown in simple form

        List<Pair<Module, RawLesson>> lessons = new ArrayList<>();
        Pair<Module, RawLesson> lesson;
        List<RawLesson> tempLesson = module.getSemesterData(state.getSemester()).timetable;
        for (RawLesson rawLesson : tempLesson) {
            lesson = Pair.of(module, rawLesson);
            lessons.add(lesson);
        }

        Timetable timetable = new Timetable(lessons, true, false);
        ui.addMessage(timetable.toString());
        ui.displayUi();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getExecutionMessage() {
        return null;
    }
}
