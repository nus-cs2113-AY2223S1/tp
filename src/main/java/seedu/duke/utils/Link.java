package seedu.duke.utils;

import seedu.duke.exceptions.YamomException;
import seedu.duke.model.LessonType;
import seedu.duke.model.Module;
import seedu.duke.model.RawLesson;
import seedu.duke.model.SelectedModule;
import seedu.duke.model.Timetable;
import seedu.duke.parser.LessonTypeParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles the creating and parsing of NUSMods export link.
 */
public class Link {
    private static final String DOMAIN = "https://nusmods.com/timetable/";

    private static final String DELIMITER = "/";

    private static final String SEMESTER_DELIMITER = "sem-";

    private static final String SPECIAL_TERM_1_SEMESTER_DELIMITER = "st-i";

    private static final String SPECIAL_TERM_2_SEMESTER_DELIMITER = "st-ii";

    private static final String SHARE_DELIMITER = "share?";

    private static final String LESSON_TYPE_DELIMITER = ":";

    private static final String MODULE_CODE_DELIMITER = "=";

    private static final String SUPPOSED_PREFIX_REGEX = "^" + Pattern.quote(DOMAIN) + "("
            + SPECIAL_TERM_1_SEMESTER_DELIMITER + "|" + SPECIAL_TERM_2_SEMESTER_DELIMITER
            + "|" + SEMESTER_DELIMITER + "\\d" + ")" + Pattern.quote(DELIMITER + SHARE_DELIMITER);

    private static final String MODULE_DELIMITER = "&";

    private static final String LESSON_DELIMITER = ",";

    public static final int SEMESTER_PARAM_INDEX = 4;

    public static final int MODULES_PARAM_INDEX = 5;

    private static final String LINK_EXAMPLE = "https://nusmods.com/timetable/[sem|st]-SEMESTER_NUMBER"
            + "/share?MODULE_INFO&MODULE_INFO";

    private static final String LINK_PROCESS_ERROR_MESSAGE = "Kindly ensure that the link is in the format of "
            + System.lineSeparator() + LINK_EXAMPLE;

    private static final String SEMESTER_PROCESS_ERROR_MESSAGE = "The semester in the link provided is incorrect."
            + System.lineSeparator() + "The semester parameter should be:" + System.lineSeparator()
            + "For normal semesters - \"sem-[1|2]\"" + System.lineSeparator() + "For special terms - \"st-[i|ii]\""
            + System.lineSeparator() + LINK_PROCESS_ERROR_MESSAGE;

    /**
     * Parses the NUSMods export link into module code and lessons information. The link should
     * only be parsed after validating the link through {@link #isValidLink(String)}.
     *
     * @param link  For exporting to NUSMods.
     * @param state Current state of the application to be saved to.
     */
    public static void parseLink(String link, State state, Ui ui) throws YamomException {
        String[] infoParam = link.split(Pattern.quote(DELIMITER));

        int semester = extractSemester(state, ui, infoParam);
        String modulesParam = infoParam[MODULES_PARAM_INDEX];
        String cleanModuleParam = modulesParam.replace(SHARE_DELIMITER, "");
        cleanModuleParam = cleanModuleParam.toUpperCase();
        if (cleanModuleParam.isEmpty()) {
            return;
        }
        String[] moduleAndLessonsArray = cleanModuleParam.split(Pattern.quote(MODULE_DELIMITER));
        List<SelectedModule> selectedModules = new ArrayList<>();
        List<String> moduleCodesAdded = new ArrayList<>();
        extractModulesAndLessons(ui, semester, moduleAndLessonsArray, selectedModules, moduleCodesAdded);
        ui.addMessage("Please check that the format of the link provided is correct if there are missing "
                + "modules or lessons.");
        ui.addMessage("Please visit https://ay2223s1-cs2113-f11-3.github.io/tp/UserGuide.html#import-a-"
                + "timetable-import");
        ui.addMessage("for more information.");
        state.setSelectedModulesList(selectedModules);
    }

    /**
     * Extracts all the module code and their respective lesson types and numbers.
     *
     * @param ui                    To output to the user.
     * @param semester              Semester in which lessons are being selected for.
     * @param moduleAndLessonsArray The segment of user input that contains the modules' information.
     * @param selectedModules       To store all the modules specified in a standardized format.
     * @param moduleCodesAdded      To verify that there are no duplicate modules added.
     */
    private static void extractModulesAndLessons(Ui ui, int semester, String[] moduleAndLessonsArray,
                                                 List<SelectedModule> selectedModules, List<String> moduleCodesAdded) {
        for (String moduleAndLessons : moduleAndLessonsArray) {
            String[] splitModuleAndLesson = moduleAndLessons.split(Pattern.quote(MODULE_CODE_DELIMITER));
            if (splitModuleAndLesson.length == 0) {
                continue;
            }
            String moduleCode = splitModuleAndLesson[0];
            Module module = Module.get(moduleCode);
            if (module == null || module.getSemesterData(semester) == null || moduleCodesAdded.contains(moduleCode)) {
                continue;
            }
            ui.addMessage(moduleCode + " added.");
            ui.addMessage("The following lesson(s) were added:");
            SelectedModule selectedModule = new SelectedModule(module, semester);
            moduleCodesAdded.add(moduleCode);

            if (splitModuleAndLesson.length > 1) {
                String[] lessonsInfo = (splitModuleAndLesson[1]).split(Pattern.quote(LESSON_DELIMITER));
                addLessons(lessonsInfo, selectedModule, semester, ui);
            }
            selectedModules.add(selectedModule);
            ui.addMessage("");
        }
    }

    /**
     * Extracts the semester number from the supposed <code>SEMESTER_PARAM_INDEX</code> and checks to see if
     * the semester number is valid.
     *
     * @param state     Current state of the application to be saved to.
     * @param ui        To output to the user.
     * @param infoParam The user input split by spaces.
     * @return A valid semester number from 1 to 4 inclusive.
     * @throws YamomException When the semester number is not a number or is not from 1 to 4 inclusive.
     */
    private static int extractSemester(State state, Ui ui, String[] infoParam) throws YamomException {
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
        return semester;
    }

    /**
     * Extracts the semester number from the semester parameter.
     *
     * @param semesterParam Clean semester parameter which should contain of a single digit.
     * @return The semester number of the previous saved state.
     * @throws NumberFormatException The semester parameter has been modified incorrectly to include other characters.
     */
    private static int getSemesterFromParam(String semesterParam) throws NumberFormatException {
        if (semesterParam.equals(SPECIAL_TERM_1_SEMESTER_DELIMITER)) {
            return 3;
        }
        if (semesterParam.equals(SPECIAL_TERM_2_SEMESTER_DELIMITER)) {
            return 4;
        }
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
        return matcher.find();
    }

    /**
     * Checks if the link contains no useful parameters other than semester number. This is a helper function
     * that should only be called after {@link #isValidLink(String)}.
     *
     * @param link Single string with no spaces.
     * @return <code>true</code> if the link only contains the supposed prefix.
     */
    public static boolean isEmptyLink(String link) {
        return link.endsWith("share?");
    }

    /**
     * Splits the lessons information into <code>lessonType</code> and <code>classNo</code>.
     *
     * @param lessonsInfo    Contains the lessons information in the form <code>MODULE_CODE=LESSON:LESSON_NUMBER,
     *                       LESSON:LESSON_NUMBER</code>.
     * @param selectedModule Current module to select lessons.
     * @param semester       Semester in which lessons are being selected for.
     * @param ui             To output to the user.
     */
    private static void addLessons(String[] lessonsInfo, SelectedModule selectedModule, int semester, Ui ui) {
        List<String> lessonsAdded = new ArrayList<>();
        for (String s : lessonsInfo) {
            if (s.endsWith(":") || !isLessonInfo(s)) {
                continue;
            }
            String[] lessonInfo = s.split(Pattern.quote(LESSON_TYPE_DELIMITER));
            LessonType lessonType = getLessonType(lessonInfo[0], ui);
            String classNo = lessonInfo[1];
            if (lessonsAdded.contains(lessonType + ":" + classNo)) {
                continue;
            }
            addValidLesson(selectedModule, semester, lessonType, classNo, ui, lessonsAdded);
        }
    }

    /**
     * Cross-checks if the selected lesson is being taught in the current semester.
     *
     * @param selectedModule Current module to select lessons.
     * @param semester       Semester in which lessons are being selected for.
     * @param lessonType     Specified lesson type.
     * @param classNo        Specified class number.
     * @param ui             To output to the user.
     * @param lessonsAdded   To verify that there are no duplicate lessons added.
     */
    private static void addValidLesson(SelectedModule selectedModule, int semester, LessonType lessonType,
                                       String classNo, Ui ui, List<String> lessonsAdded) {
        List<RawLesson> potentialLesson = selectedModule.getModule().getSemesterData(semester)
            .getLessonsByTypeAndNo(lessonType, classNo);
        if (!potentialLesson.isEmpty()) {
            selectedModule.selectSlot(lessonType, classNo);
            ui.addMessage(lessonType + ":" + classNo);
            lessonsAdded.add(lessonType + ":" + classNo);
        }
    }

    /**
     * Checks if the lesson information is of a valid form. It should begin with 3 to 4 alphanumeric
     * alphabets defined in the {@link LessonTypeParser#parse(String)} function.
     *
     * @param lessonInfo Single lesson information of a module.
     * @return <code>true</code> if the lesson information is of a valid form.
     */
    private static boolean isLessonInfo(String lessonInfo) {
        //pattern for classNo is not definite.
        Pattern pattern = Pattern.compile("^[A-Z]{3,4}\\d?:");
        Matcher matcher = pattern.matcher(lessonInfo);
        return matcher.find();
    }

    /**
     * Translates the short string to its respective <code>LessonType</code>.
     *
     * @param shortString Unique identifier for <code>LessonType</code>.
     * @param ui          To output to the user.
     * @return Corresponding <code>LessonType</code>. <code>null</code> if the shortString could not be parsed.
     */
    private static LessonType getLessonType(String shortString, Ui ui) {
        try {
            return LessonTypeParser.parse(shortString);
        } catch (IllegalArgumentException e) {
            ui.addMessage(e.getMessage());
        }
        return null;
    }

    /**
     * Creates a NUSMods export link from current state.
     * @param state Current state of the application to be saved.
     * @return The valid NUSMods export link.
     */
    public static String getLink(State state) {
        StringBuilder toSave = new StringBuilder();
        toSave.append(DOMAIN);
        appendSemester(state, toSave);
        toSave.append(DELIMITER);
        toSave.append(SHARE_DELIMITER);
        List<SelectedModule> selectedModules = state.getSelectedModulesList();
        appendModules(selectedModules, toSave);
        return String.valueOf(toSave);
    }

    /**
     * Appends the correct semester form to the String according to the current semester.
     *
     * @param state  Current state of the application to be saved.
     * @param toSave Partial NUSMods formatted link.
     */
    private static void appendSemester(State state, StringBuilder toSave) {
        int semester = state.getSemester();
        switch (semester) {
        case 3:
            toSave.append(SPECIAL_TERM_1_SEMESTER_DELIMITER);
            break;
        case 4:
            toSave.append(SPECIAL_TERM_2_SEMESTER_DELIMITER);
            break;
        default:
            toSave.append(SEMESTER_DELIMITER);
            toSave.append(semester);
        }
    }

    /**
     * Goes through the selected modules from the state and appends it in the correct format to be saved.
     *
     * @param selectedModules List of selected modules from the state.
     * @param toSave          Partial NUSMods formatted link.
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
