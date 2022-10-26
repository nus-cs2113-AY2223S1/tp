package seedu.duke.commands;

import seedu.duke.ModuleList;
import seedu.duke.UI;

public class Help extends Command {

    final String message = "## Command Summary\n" +
            "\n" +
            "* Add module `add m/MODULE_CODE s/YEAR_NUMBER_SEMESTER_NUMBER mc/NUMBER_OF_MCS g/GRADE`\n" +
            "* Deletes module `delete m/MODULE_CODE`\n" +
            "* View modules `view s/YEAR_NUMBER_SEMESTER_NUMBER`\n" +
            "* Calculate MCs `mcs s/YEAR_NUMBER_SEMESTER_NUMBER`\n" +
            "* Finds module information `find <KEYWORD>`\n" +
            "* Checks for program eligibility `check <PROGRAM>`\n" +
            "* Exits the App `exit`";

    public Help() {
    }

    @Override
    public void execute(ModuleList moduleList) {
        UI.helpMessage(message);
    }
}
