package seedu.duke.commands;

import seedu.duke.ModuleList;
import seedu.duke.UI;

public class Help extends Command {

    final String message = "Here's PlanIT! Command Summary for your reference :\n" +
            "\n" +
            "* Add a Module : add m/MODULE_CODE s/YEAR_NUMBER_SEMESTER_NUMBER mc/NUMBER_OF_MCS g/GRADE \n" +
            "* Delete a module : delete m/MODULE_CODE \n" +
            "* View modules in a semester : view s/YEAR_NUMBER_SEMESTER_NUMBER \n" +
            "* View all modules taken : view all \n" +
            "* Clears modules in a semesters : clear s/YEAR_NUMBER_SEMESTER_NUMBER \n" +
            "* Clears all modules taken : clear all \n" +
            "* Calculate MCs taken : mcs s/YEAR_NUMBER_SEMESTER_NUMBER \n" +
            "* Finds module information : find <KEYWORD> \n" +
            "* Checks for SEP/NOC program eligibility : check <PROGRAM> \n" +
            "* Overview of your Plan (MCs, CAP, Eligibility) : overview \n" +
            "* Exits the App : exit ";

    public Help() {
    }

    @Override
    public void execute(ModuleList moduleList) {
        UI.helpMessage(message);
    }
}
