package recipeditor.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import recipeditor.parser.RecipeFileParser;
import recipeditor.recipe.RecipeList;
import recipeditor.storage.Storage;


public class RecipeFileParserTest {

    private static final String BASE = "# TITLE (1 line)\n"
            + "Example Title\n\n"
            + "# DESCRIPTION\n"
            + "Example "
            +
            "Description\n\n"
            + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
            + "1. Example ingredient /"
            +
            " 1.2 / example unit \n\n"
            + "# STEPS index. description\n"
            + "1. Example step \n";

    @Test
    void parseTextToRecipe_validInput_true() {
        try {
            String string = BASE;
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.TITLE_ONE_LINE));
        }
    }


    @Test
    void parseTextToRecipe_emptyField_fail() {
        try {
            String string = "# TITLE (1 line)\n"
                    + "# DESCRIPTION\n"
                    + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
                    + "# STEPS index. description\n";
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.EMPTY));
        }
    }

    @Test
    void parseTextToRecipe_missingHeadingInput_fail() {
        try {
            String string = "# TITLE (1 line)\n"
                    + "Example Title\n\n"
                    + "# DESCRIPTION\n"
                    + "Example Description\n\n"
                    + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
                    + "1. Example ingredient / 1.2 / "
                    +
                    "example unit \n\n";
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.HEADING_OCCURRENCE));
        }
    }

    @Test
    void parseTextToRecipe_duplicateHeadingInput_fail() {
        try {
            String string = "# TITLE (1 line)\n"
                    + "Example Title\n\n"
                    + "# DESCRIPTION\n"
                    + "Example Description\n\n"
                    + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
                    + "1. Example ingredient / 1.2 / "
                    +
                    "example unit \n\n"
                    + "# STEPS index. description\n"
                    + "# STEPS index. description\n"
                    + "1. "
                    +
                    "Example step \n";
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.HEADING_OCCURRENCE));
        }
    }

    @Test
    void parseTextToRecipe_headingDifferentOrderInput_true() {
        try {
            String string = "# TITLE (1 line)\n"
                    + "Example Title\n\n"
                    + "# DESCRIPTION\n"
                    + "Example Description\n\n"
                    + "# STEPS index. description\n"
                    + "1. Example step \n"
                    + "# INGREDIENTS  index. ingredient_name "
                    +
                    "/ amount / unit \n"
                    + "1. Example ingredient / 1.2 / example unit \n\n";
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.HEADING_OCCURRENCE));
        }
    }

    @Test
    void parseTextToRecipe_inputWithHashTag_true() {
        try {
            String string = "# TITLE (1 line)\n"
                    + "Exa##m@$tle asdfasdf\n\n"
                    + "# DESCRIPTION\n"
                    + "Example "
                    +
                    "Description\n\n"
                    + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
                    + "1. Example "
                    +
                    "ingredient / 1.2 / example unit \n\n"
                    + "# STEPS index. description\n"
                    + "1. Example step \n";
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.WRONG_HEADING));
        }
    }

    @Test
    void parseTextToRecipe_inputWithWhitespaceInFront_true() {
        try {
            String string = "\n\n\n\n\n\n# TITLE (1 line)\n"
                    + "         Example Title\n\n"
                    + "          # DESCRIPTION\n"
                    + "    "
                    +
                    "       Example Description\n\n"
                    + "#    INGREDIENTS  index. ingredient_name / amount / unit \n"
                    + "1.       Example ingredient / 1.2 / example unit \n\n"
                    + "# STEPS index. description\n"
                    + "1. Example step \n";

            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.HEADING_OCCURRENCE));
        }
    }


    @Test
    void parseTextToRecipe_multiLineTitleInput_fail() {
        try {
            String string = "# TITLE (1 line)\n"
                    + "Example Title\n\n"
                    + "Example Title 1\n\n"
                    + "# DESCRIPTION\n"
                    +
                    "Example Description\n\n"
                    + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
                    + "1. "
                    +
                    "Example ingredient / 1.2 / example unit \n\n"
                    + "# STEPS index. description\n"
                    + "1. Example "
                    +
                    "step \n";
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.TITLE_ONE_LINE));
        }
    }


    @Test
    void parseTextToRecipe_nonAlphanumericTitleInput_true() {
        try {
            String string = "# TITLE (1 line)\n"
                    + "Exam@$tle asdfasdf\n\n"
                    + "# DESCRIPTION\n"
                    + "Example "
                    +
                    "Description\n\n"
                    + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
                    + "1. Example "
                    +
                    "ingredient / 1.2 / example unit \n\n"
                    + "# STEPS index. description\n"
                    + "1. Example step \n";
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.TITLE_ERROR_ALPHANUMERIC));
        }
    }

    @Test
    void parseTextToRecipe_exceedLengthTitleInput_fail() {
        try {
            String string = "# TITLE (1 line)\n"
                    + "Example "
                    +
                    "Titleaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                    +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                    +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                    +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                    +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                    +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n\n"
                    + "# DESCRIPTION\n"
                    + "Example Description\n\n"
                    + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
                    + "1. Example ingredient / 1.2 / example unit \n\n"
                    + "# STEPS index. description\n"
                    + "1. Example step \n";
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.TITLE_ERROR_LIMIT));
        }
    }

    @Test
    void parseTextToRecipe_wordsInFrontIngredientsInput_fail() {
        try {
            String string = "# TITLE (1 line)\n"
                    + "Example Title\n\n"
                    + "# DESCRIPTION\n"
                    + "Example Description\n\n"
                    + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
                    + "asdfasdf1. Example ingredient / 1"
                    +
                    ".2 / example unit \n\n"
                    + "# STEPS index. description\n"
                    + "1. Example step \n";
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.INGREDIENT_ERROR_FORMAT));
        }
    }

    @Test
    void parseTextToRecipe_negativeIndexIngredientsInput_fail() {
        try {
            String string = "# TITLE (1 line)\n"
                    + "Example Title\n\n"
                    + "# DESCRIPTION\n"
                    + "Example Description\n\n"
                    + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
                    + "-1. Example ingredient / 1.2 / "
                    +
                    "example unit \n\n"
                    + "# STEPS index. description\n"
                    + "1. Example step \n";
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.INGREDIENT_ERROR_INDEX));
        }
    }

    @Test
    void parseTextToRecipe_wrongIndexIncrementIngredientsInput_fail() {
        try {
            String string = "# TITLE (1 line)\n"
                    + "Example Title\n\n"
                    + "# DESCRIPTION\n"
                    + "Example Description\n\n"
                    + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
                    + "3. Example ingredient / 1.2 / "
                    +
                    "example unit \n\n"
                    + "# STEPS index. description\n"
                    + "1. Example step \n";
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.INGREDIENT_ERROR_INDEX_INCREMENT));
        }
    }

    @Test
    void parseTextToRecipe_ingredientsWithSlashInput_fail() {
        try {
            String string = "# TITLE (1 line)\n"
                    + "Example Title\n\n"
                    + "# DESCRIPTION\n"
                    + "Example Description\n\n"
                    + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
                    + "1. Example in/gre/dient / 1.2 / "
                    +
                    "example unit \n\n"
                    + "# STEPS index. description\n"
                    + "1. Example step \n";
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.INGREDIENT_ERROR_FORMAT));
        }
    }

    @Test
    void parseTextToRecipe_ingredientsWithDot_true() {
        try {
            String string = "# TITLE (1 line)\n"
                    + "Example Title\n\n"
                    + "# DESCRIPTION\n"
                    + "Example Description\n\n"
                    + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
                    + "1. Example in.asd.fa.df"
                    +
                    ".asdfgredient / 1.2 / ex.asdf.asd.fample unit \n\n"
                    + "# STEPS index. description\n"
                    + "1. "
                    +
                    "Example step \n";
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.INGREDIENT_ERROR_FORMAT));
        }
    }


    @Test
    void parseTextToRecipe_negativeAmountsIngredients_fail() {
        try {
            String string = "# TITLE (1 line)\n"
                    + "Example Title\n\n"
                    + "# DESCRIPTION\n"
                    + "Example Description\n\n"
                    + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
                    + "1. Example ingredient / -1.2 / "
                    +
                    "example unit \n\n"
                    + "# STEPS index. description\n"
                    + "1. Example step \n";
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.INGREDIENT_ERROR_AMOUNT));
        }
    }

    @Test
    void parseTextToRecipe_nonDoubleIngredientsAmount_fail() {
        try {
            String string = "# TITLE (1 line)\n"
                    + "Example Title\n\n"
                    + "# DESCRIPTION\n"
                    + "Example Description\n\n"
                    + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
                    + "1. Example ingredient / 1.2asdf /"
                    +
                    " example unit \n\n"
                    + "# STEPS index. description\n"
                    + "1. Example step \n";
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.INGREDIENT_ERROR_AMOUNT));
        }
    }

    @Test
    void parseTextToRecipe_wordsInFrontStepInput_fail() {
        try {
            String string = "# TITLE (1 line)\n"
                    + "Example Title\n\n"
                    + "# DESCRIPTION\n"
                    + "Example Description\n\n"
                    + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
                    + "1. Example ingredient / 1.2 / "
                    +
                    "example unit \n\n"
                    + "# STEPS index. description\n"
                    + "asdfas1. Example step \n";
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.STEP_ERROR_FORMAT));
        }
    }


    @Test
    void parseTextToRecipe_wrongIndexIncrementStepInput_fail() {
        try {
            String string = "# TITLE (1 line)\n"
                    + "Example Title\n\n"
                    + "# DESCRIPTION\n"
                    + "Example Description\n\n"
                    + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
                    + "1. Example ingredient / 1.2 / "
                    +
                    "example unit \n\n"
                    + "# STEPS index. description\n"
                    + "2. Example step \n";
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.STEP_ERROR_INDEX_INCREMENT));
        }
    }

    @Test
    void parseTextToRecipe_negativeIndexStepInput_fail() {
        try {
            String string = "# TITLE (1 line)\n"
                    + "Example Title\n\n"
                    + "# DESCRIPTION\n"
                    + "Example Description\n\n"
                    + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
                    + "1. Example ingredient / 1.2 / "
                    +
                    "example unit \n\n"
                    + "# STEPS index. description\n"
                    + "-1. Example step \n";
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.STEP_ERROR_INDEX));
        }
    }

    @Test
    void parseTextToRecipe_stepWithDotInput_true() {
        try {
            String string = "# TITLE (1 line)\n"
                    + "Example Title\n\n"
                    + "# DESCRIPTION\n"
                    + "Example Description\n\n"
                    + "# INGREDIENTS  index. ingredient_name / amount / unit \n"
                    + "1. Example ingredient / 1.2 / "
                    +
                    "example unit \n\n"
                    + "# STEPS index. description\n"
                    + "1. Example step...asdf.asd.fa.sdf.asdf \n";
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.STEP_ERROR_FORMAT));
        }
    }
}
