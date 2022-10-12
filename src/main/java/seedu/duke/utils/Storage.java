package seedu.duke.utils;

import seedu.duke.model.LessonType;
import seedu.duke.model.Module;
import seedu.duke.model.SelectedModule;
import seedu.duke.model.Timetable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Storage {
    private static final String DOMAIN = "https://nusmods.com/timetable/";

    private static final String DELIMITER = "/";

    private static final String SEMESTER_DELIMITER = "sem-";

    private static final String SHARE_DELIMITER = "share?";

    private static final String LESSON_DELIMITER = ",";

    private static final String LESSON_TYPE_DELIMITER = ":";

    private static final String MODULE_DELIMITER = "&";

    private static final String MODULE_CODE_DELIMITER = "=";

    public static final String FILE_PATH = "data/duke.txt";

    public static final String NO_PREVIOUS_STATE_ERROR_MESSAGE = "There was no previously saved state.";

    public static final String LOADING_PREVIOUS_STATE_MESSAGE = "Loading previous state.";

    //format
    //https://nusmods.com/timetable/sem-1/share?CS1231=SEC:1,TUT:04&CS2113=TUT:4,LEC:1
    public static final int SEMESTER_PARAM_INDEX = 4;

    public static final int MODULES_PARAM_INDEX = 5;

    public void openPreviousState(State state, Ui ui) {
        try {
            String link = readPreviousState();
            loadPreviousState(link, state);
            ui.addMessage(LOADING_PREVIOUS_STATE_MESSAGE);
        } catch (FileNotFoundException e) {
            ui.addMessage(NO_PREVIOUS_STATE_ERROR_MESSAGE);
        }
        ui.displayUi();
    }

    public String readPreviousState() throws FileNotFoundException {
        String link = "";
        File f = new File(FILE_PATH); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            if (!line.isEmpty()) {
                link = line;
                break;
            }
        }
        s.close();
        return link;
    }

    public void loadPreviousState(String link, State state) {
        String[] infoParam = link.split(DELIMITER);
        String semesterParam = infoParam[SEMESTER_PARAM_INDEX];
        int semester = Integer.parseInt(semesterParam.replace(SEMESTER_DELIMITER,""));
        state.setSemester(semester);

        String modulesParam = infoParam[MODULES_PARAM_INDEX];
        String cleanModuleParam = modulesParam.replace(SHARE_DELIMITER,"");
        String[] moduleAndLessonsArray = cleanModuleParam.split(MODULE_DELIMITER);
        for (String moduleAndLessons : moduleAndLessonsArray) {
            String[] splitModuleAndLesson = moduleAndLessons.split(MODULE_CODE_DELIMITER);
            String moduleCode = splitModuleAndLesson[0];
            Module module = Module.get(moduleCode);
            SelectedModule selectedModule = new SelectedModule(module,semester);
            String[] lessonsInfo = splitModuleAndLesson[1].split(LESSON_DELIMITER);
            addLessons(lessonsInfo, selectedModule);
            state.addSelectedModule(selectedModule);
        }
    }

    private void addLessons(String[] lessonsInfo, SelectedModule selectedModule) {
        for (int i = 1; i < lessonsInfo.length; i++) {
            String[] lessonInfo = lessonsInfo[i].split(LESSON_TYPE_DELIMITER);
            LessonType lessonType = getLessonType(lessonInfo[0]);
            String classNo = lessonInfo[1];
            selectedModule.selectSlot(lessonType,classNo);
        }
    }

    public static LessonType getLessonType(String shortString) {
        Map<String, LessonType> map = new HashMap<>();
        map.put("TUT", LessonType.TUTORIAL);
        map.put("TUT2", LessonType.TUTORIAL_TYPE_2);
        map.put("LEC", LessonType.LECTURE);
        map.put("REC", LessonType.RECITATION);
        map.put("DLEC", LessonType.DESIGN_LECTURE);
        map.put("PLEC", LessonType.PACKAGED_LECTURE);
        map.put("PTUT", LessonType.PACKAGED_TUTORIAL);
        map.put("SEC", LessonType.SECTIONAL_TEACHING);
        map.put("WKSH", LessonType.WORKSHOP);
        map.put("LAB", LessonType.LABORATORY);
        map.put("PROJ",LessonType.MINI_PROJECT);
        map.put("SEM", LessonType.SEMINAR_STYLE_MODULE_CLASS);
        return Optional.ofNullable(map.get(shortString)).orElseThrow();
    }

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
        toSave.append(DOMAIN);
        toSave.append(SEMESTER_DELIMITER);
        toSave.append(state.getSemester());
        toSave.append(DELIMITER);
        toSave.append(SHARE_DELIMITER);
        List<SelectedModule> selectedModules = state.getSelectedModulesList();
        appendModules(selectedModules,toSave);
        String link = String.valueOf(toSave);
        if (link.endsWith(",")) {
            link = link.substring(0,link.length() - 1);
        }
        FileWriter fw = new FileWriter(file);
        fw.write(link);
        fw.close();
    }

    public void appendModules(List<SelectedModule> selectedModules, StringBuilder toSave) {
        for (SelectedModule selectedModule: selectedModules) {
            Module module = selectedModule.getModule();
            toSave.append(module.moduleCode);
            toSave.append(MODULE_CODE_DELIMITER);
            Map<LessonType, String> selectedSlots = selectedModule.getSelectedSlots();
            appendLessons(selectedSlots, toSave);
        }
    }

    private void appendLessons(Map<LessonType, String> selectedSlots, StringBuilder toSave) {
        for (Map.Entry<LessonType, String> slot: selectedSlots.entrySet()) {
            LessonType lessonType = slot.getKey();
            String shortLessonType = Timetable.lessonTypeToShortString(lessonType);
            toSave.append(shortLessonType);
            toSave.append(LESSON_TYPE_DELIMITER);
            toSave.append(slot.getValue());
            toSave.append(LESSON_DELIMITER);
        }
    }
}
