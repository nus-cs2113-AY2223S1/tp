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
    void parseTextToRecipeValid() {
        try {
            String string = BASE;
            new RecipeFileParser().parseTextToRecipe(string);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            Assertions.assertTrue(e.getMessage().contains(RecipeFileParser.TITLE_ONE_LINE));
        }
    }

    @Test
    void parseTextToRecipe_MissingHeading() {
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
    void parseTextToRecipe_DuplicateHeading() {
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
    void parseTextToRecipe_HeadingDifferentOrder() {
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
    void parseTextToRecipe_HashTag() {
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
    void parseTextToRecipe_Whitespace() {
        try {
            String string = "# TITLE (1 line)\n"
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
    void parseTextToRecipe_MultiLineTitle() {
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
    void parseTextToRecipe_NonAlphanumericTitle() {
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
    void parseTextToRecipe_255Title() {
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
    void parseTextToRecipe_CharacterInFrontIngredients() {
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
    void parseTextToRecipe_NegativeIndexIngredients() {
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
    void parseTextToRecipe_IndexIncrementIngredients() {
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
    void parseTextToRecipe_SlashIngredients() {
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
    void parseTextToRecipe_DotIngredient() {
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
    void parseTextToRecipe_AmountNegativeIngredients() {
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
    void parseTextToRecipe_AmountNonDoubleIngredients() {
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
    void parseTextToRecipe_CharacterInFrontStep() {
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
    void parseTextToRecipe_IndexIncrementStep() {
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
    void parseTextToRecipe_NegativeIndexStep() {
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
    void parseTextToRecipe_DotStep() {
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
