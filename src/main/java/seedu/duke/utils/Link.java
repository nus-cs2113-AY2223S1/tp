package seedu.duke.utils;

import seedu.duke.exceptions.YamomException;
import seedu.duke.model.LessonType;
import seedu.duke.model.Module;
import seedu.duke.model.RawLesson;
import seedu.duke.model.SelectedModule;
import seedu.duke.model.Timetable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles the creating and parsing of NUSMods export link.
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
public class Link {
    private static final String DOMAIN = "https://nusmods.com/timetable/";

    private static final String DELIMITER = "/";

    private static final String DELIMITER_REGEX = "\\/";

    private static final String SEMESTER_DELIMITER = "sem-";

    private static final String SHARE_DELIMITER = "share?";

    private static final String LESSON_TYPE_DELIMITER = ":";

    private static final String MODULE_CODE_DELIMITER = "=";

    private static final String POSSIBLE_SEMESTER_NUMBER = "1";

    private static final String SUPPOSED_PREFIX = DOMAIN + SEMESTER_DELIMITER + POSSIBLE_SEMESTER_NUMBER
            + DELIMITER + SHARE_DELIMITER;

    private static final String SUPPOSED_PREFIX_REGEX = "^" + DOMAIN + SEMESTER_DELIMITER + "\\d/share\\?";

    private static final String MODULE_DELIMITER = "&";

    private static final String LESSON_DELIMITER = ",";

    public static final int SEMESTER_PARAM_INDEX = 4;

    public static final int MODULES_PARAM_INDEX = 5;

    private static final String LINK_EXAMPLE = "https://nusmods.com/timetable/sem-SEMESTER_NUMBER"
            + "/share?MODULE_INFO&MODULE_INFO";

    private static final String LINK_PROCESS_ERROR_MESSAGE = "Kindly ensure that the link is in the format of "
            + System.lineSeparator() + LINK_EXAMPLE;

    private static final String SEMESTER_PROCESS_ERROR_MESSAGE = "The semester in the link provided is incorrect."
            + "The SEMESTER_NUMBER should be from 1 to 4" + LINK_PROCESS_ERROR_MESSAGE;

    /**
     * Parses the NUSMods export link into module code and lessons information.
     *
     * @param link  For exporting to NUSMods.
     * @param state Current state of the application to be saved to.
     */
    public static void parseLink(String link, State state, Ui ui) throws YamomException {
        String[] infoParam = link.split(DELIMITER_REGEX);
        /*
        infoParam[0] = "https:";
        infoParam[1] = "";
        infoParam[2] = "nusmods.com";
        infoParam[3] = "timetable";
        infoParam[4] = "sem-SEMESTER_NUMBER"; <- SEMESTER_PARAM_INDEX
        infoParam[5] = "share?MODULE_INFO&MODULE_INFO"; <- MODULES_PARAM_INDEX
         */

        int semester;
        try {
            String semesterParam = infoParam[SEMESTER_PARAM_INDEX];
            semester = getSemesterFromParam(semesterParam);
        } catch (NumberFormatException e) {
            throw new YamomException(SEMESTER_PROCESS_ERROR_MESSAGE);
        }

        if (Arrays.asList(1, 2, 3, 4).contains(semester)) {
            state.setSemester(semester);
            ui.addMessage("Semester " + semester + " timetable imported.");
        } else {
            throw new YamomException(SEMESTER_PROCESS_ERROR_MESSAGE);
        }
        String modulesParam = infoParam[MODULES_PARAM_INDEX];
        String cleanModuleParam = modulesParam.replace(SHARE_DELIMITER, "");
        cleanModuleParam = cleanModuleParam.toUpperCase();

        if (cleanModuleParam.isEmpty()) {
            return;
        }
        String[] moduleAndLessonsArray = cleanModuleParam.split(MODULE_DELIMITER);
        List<SelectedModule> selectedModules = new ArrayList<>();
        for (String moduleAndLessons : moduleAndLessonsArray) {
            String[] splitModuleAndLesson = moduleAndLessons.split(Pattern.quote(MODULE_CODE_DELIMITER));
            if (splitModuleAndLesson.length == 0) {
                continue;
            }
            String moduleCode = splitModuleAndLesson[0];
            Module module = Module.get(moduleCode);
            if (module == null || module.getSemesterData(semester) == null) {
                continue;
            }
            ui.addMessage(moduleCode + " added.");
            ui.addMessage("The following lessons were added:");
            SelectedModule selectedModule = new SelectedModule(module, semester);
            //only parses the lessons between the first and second = sign (there is not supposed to be a second = sign)
            if (splitModuleAndLesson.length > 1) {
                String[] lessonsInfo = (splitModuleAndLesson[1]).split(LESSON_DELIMITER);
                addLessons(lessonsInfo, selectedModule, semester, ui);
            }
            selectedModules.add(selectedModule);
            ui.addMessage("");
        }
        ui.addMessage("Please check that the format of the link provided is correct if there are missing "
                + "modules or lessons.");
        ui.addMessage("Please visit https://ay2223s1-cs2113-f11-3.github.io/tp/UserGuide.html#import-a-"
                + "timetable-import");
        ui.addMessage("for more information.");
        state.setSelectedModulesList(selectedModules);
    }

    /**
     * Extracts the semester number from the semester parameter.
     *
     * @param semesterParam Clean semester parameter which should contain of a single digit.
     * @return The semester number of the previous saved state.
     * @throws NumberFormatException The semester parameter has been modified incorrectly to include other characters.
     */
    private static int getSemesterFromParam(String semesterParam) throws NumberFormatException {
        return Integer.parseInt(semesterParam.replace(SEMESTER_DELIMITER, ""));
    }

    /**
     * Checks if the link is of the correct form. Also checks for potentially wrongly modified link.
     *
     * @param link Single string with no spaces.
     * @return <code>true</code> if the link is of a valid form.
     */
    public static boolean isValidLink(String link) {
        Pattern pattern = Pattern.compile(SUPPOSED_PREFIX_REGEX);
        Matcher matcher = pattern.matcher(link);
        boolean hasMatch = matcher.find();
        boolean hasRequiredLength = (link.length() > SUPPOSED_PREFIX.length());
        return hasMatch && hasRequiredLength;
    }

    /**
     * Checks if the link contains no useful parameters other than semester number. This is a helper function
     * that should only be called after {@link #isValidLink(String)}.
     *
     * @param link Single string with no spaces.
     * @return <code>true</code> if the link only contains the supposed prefix.
     */
    public static boolean isEmptyLink(String link) {
        return link.length() == SUPPOSED_PREFIX.length();
    }

    /**
     * Splits the lessons information into <code>lessonType</code> and <code>classNo</code>.
     *
     * @param lessonsInfo    Contains the lessons information in the form <code>MODULE_CODE=LESSON:LESSON_NUMBER,
     *                       LESSON:LESSON_NUMBER</code>.
     * @param selectedModule Current module to select lessons.
     * @param semester       Semester in which lessons are being selected for.
     */
    private static void addLessons(String[] lessonsInfo, SelectedModule selectedModule, int semester, Ui ui) {
        for (String s : lessonsInfo) {
            if (!isLessonInfo(s)) {
                continue;
            }
            String[] lessonInfo = s.split(LESSON_TYPE_DELIMITER);
            LessonType lessonType = getLessonType(lessonInfo[0]);
            String classNo = lessonInfo[1];
            addValidLesson(selectedModule, semester, lessonType, classNo, ui);
        }
    }

    /**
     * Cross-checks if the selected lesson is being taught in the current semester.
     *
     * @param selectedModule Current module to select lessons.
     * @param semester       Semester in which lessons are being selected for.
     * @param lessonType     Specified lesson type.
     * @param classNo        Specified class number.
     */
    private static void addValidLesson(SelectedModule selectedModule, int semester,
                                       LessonType lessonType, String classNo, Ui ui) {
        List<RawLesson> potentialLesson = selectedModule.getModule().getSemesterData(semester)
            .getLessonsByTypeAndNo(lessonType, classNo);
        if (!potentialLesson.isEmpty()) {
            selectedModule.selectSlot(lessonType, classNo);
            ui.addMessage(lessonType + ":" + classNo);
        }
    }

    /**
     * Checks if the lesson information is of a valid form. It should begin with 3 to 4 alphanumeric
     * capital alphabets defined in the {@link #getLessonType(String)} function.
     *
     * @param lessonInfo Single lesson information of a module.
     * @return <code>true</code> if the lesson information is of a valid form.
     */
    private static boolean isLessonInfo(String lessonInfo) {
        //pattern for classNo is not definite.
        Pattern pattern = Pattern.compile("[A-Z]{3,4}\\d?:");
        Matcher matcher = pattern.matcher(lessonInfo);
        return matcher.find();
    }

    /**
     * Translates the short string to its respective <code>LessonType</code>.
     *
     * @param shortString Unique identifier for <code>LessonType</code>.
     * @return Corresponding <code>LessonType</code>.
     */
    private static LessonType getLessonType(String shortString) {
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
        map.put("PROJ", LessonType.MINI_PROJECT);
        map.put("SEM", LessonType.SEMINAR_STYLE_MODULE_CLASS);
        return map.get(shortString);
    }

    /**
     * Creates a NUSMods export link from current state.
     * @param state Current state of the application to be saved.
     * @return The valid NUSMods export link.
     */
    public static String getLink(State state) {
        StringBuilder toSave = new StringBuilder();
        toSave.append(DOMAIN);
        toSave.append(SEMESTER_DELIMITER);
        toSave.append(state.getSemester());
        toSave.append(DELIMITER);
        toSave.append(SHARE_DELIMITER);
        List<SelectedModule> selectedModules = state.getSelectedModulesList();
        appendModules(selectedModules, toSave);
        return String.valueOf(toSave);
    }

    /**
     * Goes through the selected modules from the state and appends it in the correct format to be saved.
     *
     * @param selectedModules List of selected modules from the state.
     * @param toSave          NUSMods formatted link.
     */
    private static void appendModules(List<SelectedModule> selectedModules, StringBuilder toSave) {
        ArrayList<String> modules = new ArrayList<>();
        for (SelectedModule selectedModule: selectedModules) {
            Module module = selectedModule.getModule();
            String moduleInfo = module.moduleCode + MODULE_CODE_DELIMITER;
            Map<LessonType, String> selectedSlots = selectedModule.getSelectedSlots();
            String lessonsInfo = joinLessons(selectedSlots);
            modules.add(moduleInfo + lessonsInfo);
        }
        toSave.append(String.join(MODULE_DELIMITER, modules));
    }

    /**
     * Goes through all the selected lessons slots for the selected module and joins it in
     * the correct format to be saved.
     *
     * @param selectedSlots The selected lessons slots.
     * @return The String containing all the joint lessons.
     */
    private static String joinLessons(Map<LessonType, String> selectedSlots) {
        ArrayList<String> lessons = new ArrayList<>();
        for (Map.Entry<LessonType, String> slot: selectedSlots.entrySet()) {
            String shortLessonType = Timetable.lessonTypeToShortString(slot.getKey());
            String lesson = shortLessonType + LESSON_TYPE_DELIMITER + slot.getValue();
            lessons.add(lesson);
        }
        return String.join(LESSON_DELIMITER, lessons);
    }
}
