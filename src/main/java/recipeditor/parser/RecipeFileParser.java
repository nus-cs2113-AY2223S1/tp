package recipeditor.parser;

import recipeditor.exception.ParseFileException;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.Ingredient;
import org.apache.commons.lang3.StringUtils;

import java.util.logging.Logger;

public class RecipeFileParser {

    private static final Logger logger = Logger.getLogger(RecipeFileParser.class.getName());

    private static final String UNIMPORTANT_TEXT = "There are unimportant text that cannot be parsed. Please follow "
            + "the Template";
    private static final String TITLE_ONE_LINE = "TITLE should be a single line and less than 255 characters";
    private static final String TITLE_ERROR_ALPHANUMERIC = "TITLE contains characters that are not alphanumeric "
            + "(except whitespace)";
    private static final String TITLE_ERROR_LIMIT = "TITLE is too long! TITLE should be less than 255 characters!";

    private static final String INGREDIENT_ERROR_FORMAT = "INGREDIENT format is incorrect!\nFORMAT: "
            + "index. "
            + "ingredient_name / "
            + "amount_in_positive_rational / unit\nNOTE: ingredient_name and unit SHOULD NOT have / ";
    private static final String INGREDIENT_ERROR_INDEX = "INGREDIENT index must be a positive integer!";

    private static final String INGREDIENT_ERROR_AMOUNT = "INGREDIENT amount should be a positive rational number! "
            + "Please indicate fraction in number as well.\nExample: 12, 0.1, 0.5 (for half)";

    private static final String INGREDIENT_ERROR_INDEX_INCREMENT = "INGREDIENT index increment is incorrect! "
        + "Index starts from 1";
    private static final String STEP_ERROR_FORMAT = "STEP format is incorrect!\nFORMAT: index. step_description\n"
            + "STEP index must be a positive integer!";
    private static final String STEP_ERROR_INDEX = "STEP index must be a positive integer!";
    private static final String STEP_ERROR_INDEX_INCREMENT = "STEP index increment is incorrect! Index starts "
            + "from 1";
    private static final String HEADING_OCCURRENCE = "Incorrect number of HEADINGS! Please follow the template!";
    private static final String WRONG_HEADING = "Cannot parse HEADING! Please follow the template and don't use # in "
            + "content";
    private static final String EMPTY = "There is an empty field. The recipe is not valid";
    private static final String HASHTAG = "Don't use # in the content if it is not a heading";


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
     * Parse the text content
     * @param text with newlines
     * @return parsed Recipe
     * @throws ParseFileException
     */
    public Recipe parseTextToRecipe(String text) throws ParseFileException {
        parsedLine = text.split("\n");
        // Go down line by line
        for (int i = 0; i < parsedLine.length; i++) {
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
        if (trimmedLine.contains("#")) {
            String[] parsedWords = trimmedLine.replace("#"," ").trim().split(" ");
            switch (parsedWords[0].toLowerCase()) {
            case "title":
                incrementStageCounterAt(stageCounter, 0);
                return LineType.TITLE;
            case "description":
                incrementStageCounterAt(stageCounter, 1);
                return LineType.DESCRIPTION;
            case "ingredients":
                incrementStageCounterAt(stageCounter, 2);
                return LineType.INGREDIENT;
            case "steps":
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
        String[] parsedDot = parsedSlashed[0].split("\\.");


        if (parsedSlashed.length != 3) {
            throw new ParseFileException(INGREDIENT_ERROR_FORMAT);
        }

        int lineIndex = ingredientParsedIndex(parsedDot[0]);
        if (isNotPositive(lineIndex)) {
            throw new ParseFileException(INGREDIENT_ERROR_INDEX);
        }
        if (lineIndex != index) {
            throw new ParseFileException(INGREDIENT_ERROR_INDEX_INCREMENT);
        }

        String name = parsedSlashed[0].replace(String.format("%d.",lineIndex),"");
        Double amount = ingredientParsedAmount(parsedSlashed[1]);
        String unit = parsedSlashed[2];
        return new Ingredient(name, amount, unit);
    }

    private String parsedStep(String line, int index) throws ParseFileException {
        String[] parsed = line.split("\\.");

        int lineIndex = stepParseIndex(parsed[0]);

        if (isNotPositive(lineIndex)) {
            throw new ParseFileException(STEP_ERROR_INDEX);
        }
        if (lineIndex != index) {
            throw new ParseFileException(STEP_ERROR_INDEX_INCREMENT);
        }

        return line.replace(String.format("%d.", lineIndex),"").trim();
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
