package recipeditor.parser;

import recipeditor.exception.ParseFileException;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.Ingredient;
import recipeditor.ui.Ui;

import java.util.logging.Logger;

public class RecipeFileParser {

    public static final String TITLE_ONE_LINE = "TITLE should be a single line and less than 255 characters";
    public static final String TITLE_ERROR_ALPHANUMERIC = "TITLE contains characters that are not alphanumeric "
            + "(except whitespace)";
    public static final String TITLE_ERROR_LIMIT = "TITLE is too long! TITLE should be less than 255 characters!";

    public static final String INGREDIENT_ERROR_FORMAT = "INGREDIENT format is incorrect!\nFORMAT: "
            + "index. "
            + "ingredient_name / "
            + "amount_in_positive_rational / unit\nNOTE: ingredient_name and unit SHOULD NOT have / ";
    public static final String INGREDIENT_ERROR_INDEX = "INGREDIENT index must be a positive integer!";

    public static final String INGREDIENT_ERROR_AMOUNT = "INGREDIENT amount should be a positive rational number! "
            + "Please indicate fraction in decimal as well.\nExample: 12, 0.1, 0.5 (for half)";

    public static final String INGREDIENT_ERROR_INDEX_INCREMENT = "INGREDIENT index increment is incorrect! "
        + "Index starts from 1";
    public static final String STEP_ERROR_FORMAT = "STEP format is incorrect!\nFORMAT: index. step_description\n"
            + "STEP index must be a positive integer!";
    public static final String STEP_ERROR_INDEX = "STEP index must be a positive integer!";
    public static final String STEP_ERROR_INDEX_INCREMENT = "STEP index increment is incorrect! Index starts "
            + "from 1";
    public static final String HEADING_OCCURRENCE = "Incorrect number of HEADINGS! Please follow the template!";
    public static final String WRONG_HEADING = "Cannot parse HEADING! Please follow the template and don't use # in "
            + "content";
    public static final String EMPTY = "There is an empty field. The recipe is not valid";
    private static final String TITLE_STRING = "title";
    private static final String DESCRIPTION_STRING = "description";
    private static final String INGREDIENTS_STRING = "ingredients";
    private static final String STEPS_STRING = "steps";
    private static final String HASH_DIVIDER = "#";
    private static final int START_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int UNIT_INDEX = 2;
    private static final int CORRECT_LENGTH = 3;

    Recipe recipe = new Recipe();
    LineType lineType;
    LineType stage = LineType.TITLE_START;
    int[] stageCounter = {0, 0, 0, 0};
    int descriptionCounter = 0;
    int ingredientIndex = 1;
    int stepIndex = 1;
    String[] parsedLine;
    String line;

    /**
     * Parse the text file into a correct recipe.
     */
    public RecipeFileParser(){
    }

    /**
     * Parse the text content.
     * @param text with newlines
     * @return parsed Recipe
     * @throws ParseFileException will be handele by GUIWorkflow
     */
    public Recipe parseTextToRecipe(String text) throws ParseFileException {
        parsedLine = text.split("\n");
        // Go down line by line
        for (int i = START_INDEX; i < parsedLine.length; i++) {
            line = parsedLine[i];
            lineType = checkLineType(line, stageCounter);
            parsingSequence();
        }
        checkAllStages(stageCounter);
        if (recipe.isNotRecipeValid()) {
            throw new ParseFileException(EMPTY);
        }
        return recipe;
    }

    private void parsingSequence() throws ParseFileException {
        if (!lineType.equals(LineType.NORMAL)) {
            stage = lineType;
        } else {
            switch (stage) {
            default:
                break;
            case TITLE:
                caseTitle();
                break;
            case TITLE_END:
                caseTitleEnd();
                return;
            case DESCRIPTION:
                caseDescription();
                break;
            case INGREDIENT:
                caseIngredient();
                break;
            case STEP:
                caseStep();
                break;
            }
        }
    }

    private void caseStep() throws ParseFileException {
        if (line.isBlank()) {   //Ignore Blank lines
            return;
        }
        recipe.addStep(parsedStep(line, stepIndex));
        stepIndex++;
    }

    private void caseIngredient() throws ParseFileException {
        if (line.isBlank()) {   //Ignore Blank lines
            return;
        }
        recipe.addIngredient(parsedIngredient(line, ingredientIndex));
        ingredientIndex++;
    }

    private void caseDescription() {
        recipe.setDescription(parsedDescription(line, recipe, descriptionCounter));
        descriptionCounter++;
    }

    private void caseTitleEnd() throws ParseFileException {
        if (line.isBlank()) {   //Ignore Blank lines
            return;
        }
        throw new ParseFileException(TITLE_ONE_LINE);
    }

    private void caseTitle() throws ParseFileException {
        if (line.isBlank()) {   //Ignore Blank lines, implement this to allow Description to have blanks
            return;
        }
        recipe.setTitle(parsedTitle(line));
        stage = LineType.TITLE_END;
    }

    private void checkAllStages(int[] stageCounter) throws ParseFileException {
        for (int j : stageCounter) {
            if (j != 1) {
                throw new ParseFileException(HEADING_OCCURRENCE);
            }
        }
    }

    private LineType checkLineType(String line, int[] stageCounter) throws ParseFileException {
        String trimmedLine = line.trim();
        if (trimmedLine.contains(HASH_DIVIDER)) {
            String[] parsedWords = trimmedLine.replace(HASH_DIVIDER, Ui.SPACE_DIVIDER).trim().split(Ui.SPACE_DIVIDER);
            switch (parsedWords[START_INDEX].toLowerCase()) {
            case TITLE_STRING:
                incrementStageCounterAt(stageCounter, 0);
                return LineType.TITLE;
            case DESCRIPTION_STRING:
                incrementStageCounterAt(stageCounter, 1);
                return LineType.DESCRIPTION;
            case INGREDIENTS_STRING:
                incrementStageCounterAt(stageCounter, 2);
                return LineType.INGREDIENT;
            case STEPS_STRING:
                incrementStageCounterAt(stageCounter, 3);
                return LineType.STEP;
            default:
                throw new ParseFileException(WRONG_HEADING);
            }
        } else {
            return LineType.NORMAL;
        }

    }

    private void incrementStageCounterAt(int[] array, int j) throws ParseFileException {
        array[j]++;
        if (array[j] == 2) {
            throw new ParseFileException(HEADING_OCCURRENCE);
        }
    }

    private String parsedTitle(String line) throws ParseFileException {
        String trimmedLine = line.trim();
        if (ParserUtils.isTitleNotAlphanumeric(trimmedLine)) {
            throw new ParseFileException(TITLE_ERROR_ALPHANUMERIC);
        }
        if (ParserUtils.doesTitleExceedLimit(trimmedLine)) {
            throw new ParseFileException(TITLE_ERROR_LIMIT);
        }
        return line.trim();
    }

    private String parsedDescription(String line, Recipe recipe, int counter) {
        String trimmedLine = line;
        if (counter == 0) {
            return trimmedLine;
        } else {
            return recipe.getDescription() + "\n" + trimmedLine;
        }
    }

    private Ingredient parsedIngredient(String line, int index) throws ParseFileException {

        String[] parsedSlashed = line.split("/");
        String[] parsedDot = parsedSlashed[START_INDEX].split("\\.");

        if (parsedSlashed.length != CORRECT_LENGTH) {
            throw new ParseFileException(INGREDIENT_ERROR_FORMAT);
        }

        int lineIndex = ingredientParsedIndex(parsedDot[START_INDEX]);
        if (isNotPositive(lineIndex)) {
            throw new ParseFileException(INGREDIENT_ERROR_INDEX);
        }
        if (lineIndex != index) {
            throw new ParseFileException(INGREDIENT_ERROR_INDEX_INCREMENT);
        }

        String name = parsedSlashed[START_INDEX].replace(String.format("%d.",lineIndex), Ui.EMPTY_STRING);
        Double amount = ingredientParsedAmount(parsedSlashed[AMOUNT_INDEX]);
        String unit = parsedSlashed[UNIT_INDEX];
        return new Ingredient(name, amount, unit);
    }

    private String parsedStep(String line, int index) throws ParseFileException {
        String[] parsed = line.split("\\.");

        int lineIndex = stepParseIndex(parsed[START_INDEX]);

        if (isNotPositive(lineIndex)) {
            throw new ParseFileException(STEP_ERROR_INDEX);
        }
        if (lineIndex != index) {
            throw new ParseFileException(STEP_ERROR_INDEX_INCREMENT);
        }

        return line.replace(String.format("%d.", lineIndex), Ui.EMPTY_STRING).trim();
    }


    private boolean isNotPositive(int index) {
        return (index <= 0);
    }

    private boolean isNotPositive(double number) {
        return (number <= 0);
    }


    private int ingredientParsedIndex(String parsed) throws ParseFileException {
        try {
            return Integer.parseInt(parsed.trim());
        } catch (Exception e) {
            throw new ParseFileException(INGREDIENT_ERROR_FORMAT);
        }
    }


    private Double ingredientParsedAmount(String parsed) throws ParseFileException {
        try {
            Double amount = Double.parseDouble(parsed);
            if (isNotPositive(amount)) {
                throw new ParseFileException(INGREDIENT_ERROR_AMOUNT);
            }
            return amount;
        } catch (Exception e) {
            throw new ParseFileException(INGREDIENT_ERROR_AMOUNT);
        }
    }

    private int stepParseIndex(String parsed) throws ParseFileException {
        try {
            return Integer.parseInt(parsed);
        } catch (Exception e) {
            throw new ParseFileException(STEP_ERROR_FORMAT);
        }
    }

    private enum LineType {
        TITLE_START, TITLE, TITLE_END,  DESCRIPTION, INGREDIENT, STEP, NORMAL
    }

}
