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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles the saving and loading of states into a format that can be exported to NUSMods
 *
 * <p>It would be in the form</p>
 * https://nusmods.com/timetable/sem-SEMESTER_NUMBER/share?MODULE_INFO&MODULE_INFO etc
 *
 * <p>The MODULE_INFO will be in the form</p>
 * MODULE_CODE=LESSON:LESSON_NUMBER,LESSON:LESSON_NUMBER etc
 *
 * <p>An e.g.</p>
 * <a href="https://nusmods.com/timetable/sem-1/share?CS1231=SEC:1,TUT:04&CS2113=TUT:4,LEC:1">
 *         https://nusmods.com/timetable/sem-1/share?CS1231=SEC:1,TUT:04&CS2113=TUT:4,LEC:1</a>
 */
public class Storage {
    private static final String DOMAIN = "https://nusmods.com/timetable/";

    private static final String DELIMITER = "/";

    private static final String SEMESTER_DELIMITER = "sem-";

    private static final String SHARE_DELIMITER = "share?";

    private static final String LESSON_TYPE_DELIMITER = ":";

    private static final String MODULE_CODE_DELIMITER = "=";

    private static String moduleDelimiter = "&";

    private static String lessonDelimiter = ",";

    public static final int SEMESTER_PARAM_INDEX = 4;

    public static final int MODULES_PARAM_INDEX = 5;

    public static final String FILE_PATH = "data/duke.txt";

    public static final String NO_PREVIOUS_STATE_ERROR_MESSAGE = "There was no previously saved state.";

    public static final String LOADING_PREVIOUS_STATE_MESSAGE = "Loading previous state.";

    public static final String EXPORT_MESSAGE = "This is your export link:";

    private Logger logger = Logger.getLogger(Storage.class.getName());

    public static final String SUBSYSTEM_NAME = "storage";

    /**
     * Tries to open the file containing the previously saved state from specified file path.
     * Then outputs to the user if the opening of file was successful.
     *
     * @param state current state of the application to be saved
     * @param ui    to output to the user
     */
    public void openPreviousState(State state, Ui ui) {
        assert state != null : "List of lessons should not be null";
        logger = Logger.getLogger(SUBSYSTEM_NAME);
        logger.log(Level.INFO, "Opening previous saved file");
        try {
            String link = readPreviousState();
            loadPreviousState(link, state);
            ui.addMessage(LOADING_PREVIOUS_STATE_MESSAGE);
        } catch (FileNotFoundException e) {
            ui.addMessage(NO_PREVIOUS_STATE_ERROR_MESSAGE);
        }
        ui.displayUi();
    }

    /**
     * Opens the previously saved file. The saved file should only contain one line which is the
     * link for exporting to NUSMods.
     *
     * @return the link for exporting to NUSMods
     * @throws FileNotFoundException the file in the file path cannot be found
     */
    public String readPreviousState() throws FileNotFoundException {
        String link = "";
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                link = line;
                break;
            }
        }
        scanner.close();
        return link;
    }

    /**
     * Finds the saved semester and split the remaining parts into module code and lessons information.
     *
     * @param link  for exporting to NUSMods
     * @param state current state of the application to be saved
     */
    public void loadPreviousState(String link, State state) {
        String[] infoParam = link.split(DELIMITER);
        String semesterParam = infoParam[SEMESTER_PARAM_INDEX];
        int semester = Integer.parseInt(semesterParam.replace(SEMESTER_DELIMITER,""));
        state.setSemester(semester);

        String modulesParam = infoParam[MODULES_PARAM_INDEX];
        String cleanModuleParam = modulesParam.replace(SHARE_DELIMITER,"");
        if (cleanModuleParam.isEmpty()) {
            return;
        }
        String[] moduleAndLessonsArray = cleanModuleParam.split(moduleDelimiter);
        for (String moduleAndLessons : moduleAndLessonsArray) {
            String[] splitModuleAndLesson = moduleAndLessons.split(MODULE_CODE_DELIMITER);
            String moduleCode = splitModuleAndLesson[0];
            Module module = Module.get(moduleCode);
            SelectedModule selectedModule = new SelectedModule(module,semester);
            String[] lessonsInfo = splitModuleAndLesson[1].split(lessonDelimiter);
            addLessons(lessonsInfo, selectedModule);
            state.addSelectedModule(selectedModule);
        }
    }

    /**
     * Splits the lessons information into <code>lessonType</code> and <code>classNo</code>.
     *
     * @param lessonsInfo    contains the lessons information in the form <code>MODULE_CODE=LESSON:LESSON_NUMBER,
     *                       LESSON:LESSON_NUMBER</code>
     * @param selectedModule current module to select classes
     */
    private void addLessons(String[] lessonsInfo, SelectedModule selectedModule) {
        for (int i = 1; i < lessonsInfo.length; i++) {
            String[] lessonInfo = lessonsInfo[i].split(LESSON_TYPE_DELIMITER);
            LessonType lessonType = getLessonType(lessonInfo[0]);
            String classNo = lessonInfo[1];
            selectedModule.selectSlot(lessonType,classNo);
        }
    }

    /**
     * Translates the short string to its respective <code>LessonType</code>.
     *
     * @param shortString unique identifier for <code>LessonType</code>
     * @return corresponding <code>LessonType</code>
     */
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
     * @param state the current state of the application
     * @param ui    to output to the user
     * @throws IOException failed or interrupted I/O operations
     */
    public void saveState(State state, Ui ui) throws IOException {
        assert state != null : "State should not be null";
        logger = Logger.getLogger(SUBSYSTEM_NAME);
        logger.log(Level.INFO, "Saving current state with " + state.getSelectedModulesList().size()
                + " modules into a file. The format will be NUSMods export link.");
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
        ui.addMessage(EXPORT_MESSAGE);
        ui.addMessage(link);
        ui.displayUi();
        FileWriter fw = new FileWriter(file);
        fw.write(link);
        fw.close();
    }

    public void appendModules(List<SelectedModule> selectedModules, StringBuilder toSave) {
        moduleDelimiter = "";
        for (SelectedModule selectedModule: selectedModules) {
            toSave.append(moduleDelimiter);
            moduleDelimiter = "&";
            Module module = selectedModule.getModule();
            toSave.append(module.moduleCode);
            toSave.append(MODULE_CODE_DELIMITER);
            Map<LessonType, String> selectedSlots = selectedModule.getSelectedSlots();
            appendLessons(selectedSlots, toSave);

        }
    }

    private void appendLessons(Map<LessonType, String> selectedSlots, StringBuilder toSave) {
        lessonDelimiter = "";
        for (Map.Entry<LessonType, String> slot: selectedSlots.entrySet()) {
            toSave.append(lessonDelimiter);
            lessonDelimiter = ",";
            LessonType lessonType = slot.getKey();
            String shortLessonType = Timetable.lessonTypeToShortString(lessonType);
            toSave.append(shortLessonType);
            toSave.append(LESSON_TYPE_DELIMITER);
            toSave.append(slot.getValue());
        }
    }
}
