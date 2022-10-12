package seedu.duke.utils;

import seedu.duke.model.LessonType;
import seedu.duke.model.Module;
import seedu.duke.model.SelectedModule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Storage {
    public static final String FILE_PATH = "data/duke.txt";

    public static final String LESSONS_DELIMITER = "\\|";

    public static final String LESSONS_DELIMITER_FOR_SAVING = "|";

    public static final String LESSON_TYPE_AND_CLASS_NO_DELIMITER = " ";

    public static final String NO_PREVIOUS_STATE_ERROR_MESSAGE = "There was no previously saved state.";

    public static final String LOADING_PREVIOUS_STATE_MESSAGE = "Loading previous state.";

    public void openPreviousState(State state, Ui ui) {
        try {
            List<String> previousState = readPreviousState();
            loadPreviousState(previousState, state);
            ui.addMessage(LOADING_PREVIOUS_STATE_MESSAGE);
        } catch (FileNotFoundException e) {
            ui.addMessage(NO_PREVIOUS_STATE_ERROR_MESSAGE);
        }
        ui.displayUi();
    }

    public List<String> readPreviousState() throws FileNotFoundException {
        List<String> previousState = new ArrayList<>();
        File f = new File(FILE_PATH); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            previousState.add(line);
        }
        s.close();
        return previousState;
    }

    public void loadPreviousState(List<String> previousState, State state) {
        int semester = Integer.parseInt(previousState.get(0));
        state.setSemester(semester);
        previousState.remove(0);
        for (String moduleInfo: previousState) {
            if (moduleInfo.isEmpty()) {
                continue;
            }
            String[] moduleLessonInfos = moduleInfo.split(LESSONS_DELIMITER);
            String moduleCode = moduleLessonInfos[0];
            Module module = Module.get(moduleCode);
            SelectedModule selectedModule = new SelectedModule(module,semester);
            addLessons(moduleLessonInfos, selectedModule);
            state.addSelectedModule(selectedModule);
        }
    }

    private void addLessons(String[] moduleLessonInfos, SelectedModule selectedModule) {
        for (int i = 1; i < moduleLessonInfos.length; i++) {
            String[] lessonInfos = moduleLessonInfos[i].split(LESSON_TYPE_AND_CLASS_NO_DELIMITER);
            LessonType lessonType = LessonType.valueOf(lessonInfos[0]);
            String classNo = lessonInfos[1];
            selectedModule.selectSlot(lessonType,classNo);
        }
    }

    //saved file format
    // MODULE_CODE |LESSON_TYPE CLASS_NO| ETC...
    /**
     * Saves all current tasks by overriding the file.
     *
     * @param state        the current state of the application
     * @throws IOException failed or interrupted I/O operations
     */
    public void saveState(State state) throws IOException {
        File file = new File(FILE_PATH);
        if (file.getParentFile().mkdirs()) {
            file.createNewFile();
        }

        StringBuilder toSave = new StringBuilder();
        toSave.append(state.getSemester());
        toSave.append(System.lineSeparator());
        List<SelectedModule> selectedModules = state.getSelectedModulesList();
        appendModules(selectedModules,toSave);

        FileWriter fw = new FileWriter(file);
        fw.write(String.valueOf(toSave));
        fw.close();
    }

    public void appendModules(List<SelectedModule> selectedModules, StringBuilder toSave) {
        for (SelectedModule selectedModule: selectedModules) {
            Module module = selectedModule.getModule();
            toSave.append(module.moduleCode);
            Map<LessonType, String> selectedSlots = selectedModule.getSelectedSlots();
            appendLessons(selectedSlots, toSave);
        }
        toSave.append(System.lineSeparator());
    }

    private void appendLessons(Map<LessonType, String> selectedSlots, StringBuilder toSave) {
        for (Map.Entry<LessonType, String> slot: selectedSlots.entrySet()) {
            toSave.append(LESSONS_DELIMITER_FOR_SAVING);
            toSave.append(slot.getKey().toString());
            toSave.append(LESSON_TYPE_AND_CLASS_NO_DELIMITER);
            toSave.append(slot.getValue());
        }
    }
}
