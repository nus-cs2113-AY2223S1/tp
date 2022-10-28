package recipeditor.parser;

import recipeditor.exception.ParseFileException;
import recipeditor.recipe.Recipe;
import recipeditor.recipe.Ingredient;
import org.apache.commons.lang3.StringUtils;
import recipeditor.ui.Editor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

//TODO: Better Exception check
public class TextFileParser {

    private static final Logger logger = Logger.getLogger(TextFileParser.class.getName());

    public TextFileParser() {

    }

    /**
     * Parse the text file into a correct recipe.
     */
    public Recipe parseTextToRecipe(String text) throws ParseFileException {
        Recipe recipe = new Recipe();
        String[] parsedLine = text.split("\n");
        LineType lineType = LineType.NORMAL;
        LineType stage = LineType.NORMAL;

        // Go down line by line
        for (int i = 0; i < parsedLine.length; i++) {
            String line = parsedLine[i];
            if (line.isBlank()) {
                continue;
            }
            lineType = checkLineType(line);
            if (!lineType.equals(LineType.NORMAL)) {
                stage = lineType;
            } else {
                switch (stage) {
                case TITLE:
                    recipe.setTitle(parsedTitle(line));
                    break;
                case DESCRIPTION:
                    recipe.setDescription(parsedDescription(line, recipe));
                    break;
                case INGREDIENT:
                    recipe.addIngredient(parsedIngredient(line));
                    break;
                case STEP:
                    recipe.addStep(parsedStep(line));
                    break;
                default:
                    throw new ParseFileException("Wrong Header");
                }
            }
        }
        return recipe;
    }

    private LineType checkLineType(String line) throws ParseFileException {
        String[] parsedWords = line.split(" ");
        if (parsedWords[0].equals("#")) {
            switch (parsedWords[1].toLowerCase()) {
            case "title":
                return LineType.TITLE;
            case "description":
                return LineType.DESCRIPTION;
            case "ingredients":
                return LineType.INGREDIENT;
            case "steps":
                return LineType.STEP;
            default:
                throw new ParseFileException("Wrong Heading Format");
            }
        } else {
            return LineType.NORMAL;
        }
    }

    private Recipe checkValidity() {
        return new Recipe();
    }

    private String parsedTitle(String line) throws ParseFileException {
        String[] parsed = line.split(" ");
        for (String word : parsed) {
            if (!StringUtils.isAlphanumeric(word)) {
                throw new ParseFileException("Title should be alphanumeric");
            }
        }
        return line;
    }

    private String parsedDescription(String line, Recipe recipe) {
        String appendDescription = recipe.getDescription() + "\n" + line;
        return appendDescription;
    }

    private Ingredient parsedIngredient(String line) throws ParseFileException {
        try {
            String[] parsed = line.split("/", 3);
            String name = parsed[0].split("\\.", 2)[1].trim();
            double amount = Double.parseDouble(parsed[1]);
            return new Ingredient(name, amount, parsed[2]);
        } catch (Exception e) {
            logger.log(Level.INFO, e.getMessage());
            throw new ParseFileException("Ingredient syntax is not correct");
        }
    }

    private String parsedStep(String line) {
        String[] parsed = line.split("\\.", 2);
        return parsed[1].trim();
    }

    private String removeIndex(String line) {
        return "";
    }

    private enum LineType {
        TITLE, DESCRIPTION, INGREDIENT, STEP, NORMAL
    }
}
