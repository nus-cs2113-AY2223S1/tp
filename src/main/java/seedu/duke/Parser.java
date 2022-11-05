package seedu.duke;

import commands.Commands;
import commands.AddCommand;
import commands.ClearCommand;
import commands.RemoveCommand;
import commands.FavouriteCommand;
import commands.ListCommand;
import commands.SortCommand;
import commands.FindCommand;
import exceptions.IllegalCharacterException;
import exceptions.FutureDateException;
import exceptions.InvalidDateException;
import exceptions.DukeException;
import exceptions.InvalidCommandException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Resolves the user input into a command to execute
 */
public class Parser {
    static final String KEYWORD_MOVIE = "/movie";
    static final int SPACING_MOVIE = 5;
    static final String KEYWORD_TV = "/tv";
    static final int SPACING_TV = 2;
    static final String KEYWORD_RATING = "/rating";
    static final int SPACING_RATING = 6;
    static final String KEYWORD_DATE = "/date";
    static final int SPACING_DATE = 4;
    static final String KEYWORD_GENRE = "/genre";
    static final int SPACING_GENRE = 5;
    static final String KEYWORD_SITE = "/site";
    static final int SPACING_SITE = 4;
    static final int SPACING_FAVORITE = 9;
    static final int POS_TITLE = 1;
    static final int POS_RATING = 2;
    static final int POS_DATE = 3;
    static final int POS_GENRE = 4;
    static final int POS_SITE = 5;
    static final String MOVIE_TYPE = "class seedu.duke.Movie";
    static final String TV_TYPE = "class seedu.duke.TvShow";
    private Commands executor;
    private ReviewList mediaList;

    //@@author naz019
    private static Logger logger = Logger.getLogger("DukeLogger");

    public Parser(ReviewList reviewList) {
        mediaList = reviewList;
    }

    //@@author redders7
    public String[] getCommandWord(String input) {
        String[] result = input.split(" ");
        return result;
    }

    /**.
     * Checks user input for illegal "|" character
     * @param userInput Raw input given by user
     * @throws IllegalCharacterException Exception thrown if input contains "|"
     */
    public void checkIllegalCharacter(String userInput) throws IllegalCharacterException {
        if (userInput.contains("|")) {
            throw new IllegalCharacterException();
        }
    }

    public void checkEmptyCommand(String parsedInput) throws InvalidCommandException {
        if (parsedInput.isEmpty()) {
            throw new InvalidCommandException();
        }
    }

    //@@author indraneelrp
    /**.
     * Checks that user input is valid, parses the input and executes any valid commands given
     * @param userInput Raw input given by user
     */
    public void processUserInput(String userInput) {
        try {
            checkIllegalCharacter(userInput);
            //@@author indraneelrp
            final String listCommand = "list";
            final String addCommand = "add";
            final String deleteCommand = "delete";
            final String clearCommand = "clear";
            final String endCommand = "bye";
            final String favouriteCommand = "favourite";
            final String sortCommand = "sort";
            final String findCommand = "find";
            final String NT = "";

            String[] parsedCommand = getCommandWord(userInput);

            //@@author naz019
            assert parsedCommand[0] != null : "words[0] is supposed to contain user command";

            //@@author indraneelrp
            switch (parsedCommand[0]) {
            case endCommand:
                break;

            case listCommand:
                executeList();
                break;

            case addCommand:
                executeAdd(userInput);
                break;

            case deleteCommand:
                executeDelete(parsedCommand);
                break;

            case clearCommand:
                executeClear();
                break;

            case favouriteCommand:
                executeFavourite(parsedCommand);
                break;

            case findCommand:
                executeFind(parsedCommand);
                break;

            case sortCommand:
                executeSort(parsedCommand);
                break;

            case NT:

            default:
                logger.log(Level.WARNING, "An unrecognised command was given by the user.");
                Ui.print("Unrecognised command");
                break;
            }
        } catch (IllegalCharacterException e) {
            Ui.print("Illegal character entered!");
        }
    }

    //@@author matthewphua
    /**
     * Executes the find action by creating a find object.
     */
    public ArrayList<Media> executeFind(String[] words) {
        ArrayList<Media> found = new ArrayList<>();
        try {
            String keyWord = words[1];
            //@@author indraneelrp
            FindCommand executor;
            executor = new FindCommand(mediaList, keyWord);
            found = executor.getMatching();
            
            //@@author matthewphua
            String output = executor.execute();
            Ui.print(output);
            logger.log(Level.INFO, "\n\tFind command executed");
        } catch (Exception e) {
            Ui.print("Incomplete or wrongly formatted command, try again.");
        }
        return found;
    }

    //@@author naz019
    /**
     * Executes the sort command by creating a SortCommand object.
     *
     * @param words The user input which has been split into separate words.
     */
    public void executeSort(String[] words) {
        try {
            Commands executor = new SortCommand(mediaList, words);
            String output = executor.execute();
            Ui.print(output);
            logger.log(Level.INFO, "\n\tSort command executed");
        } catch (Exception e) {
            Ui.print("Incomplete or wrongly formatted command, try again.");
        }
    }

    /**
     * If a user input of valid length is given, this method executes the favourite command by creating a
     * FavouriteCommand object.
     *
     * @param words The user input which has been split into separate words.
     */
    public void executeFavourite(String[] words) {
        try {
            if (words.length > 3 || words.length < 2) {
                throw new DukeException();
            } else {
                executor = new FavouriteCommand(mediaList, words);
                String output = executor.execute();
                Ui.print(output);
                logger.log(Level.INFO, "\n\tFavourite command executed");
            }
        } catch (DukeException e) {
            logger.log(Level.WARNING, "\n\tFavourite command failed");
            Ui.print("Incomplete or wrongly formatted command, try again.");
        }
    }

    //@@author indraneelrp
    public void executeList() {
        executor = new ListCommand(mediaList);
        String output = executor.execute();
        Ui.print(output);
        logger.log(Level.INFO, "\n\tList command executed");
    }


    //@@author redders7
    /**.
     * Determines if added Media is a Movie or TV Show and executes addCommand to add it to the list
     * @param fields User input containing review data
     * @param spacingType Identifies if review is for a Movie or TV Show
     */
    public void addMedia(String[] fields, Integer spacingType) {
        String title;
        double rating;
        String date;
        String genre;
        Media toAdd;
        String[] dateFields;

        try {
            title = fields[POS_TITLE].substring(spacingType);
            checkEmptyCommand(title);
            rating = Double.parseDouble(fields[POS_RATING].substring(SPACING_RATING));
            //ensure rating is not above 100
            if (rating > 100) {
                throw new IllegalArgumentException();
            }
            checkEmptyCommand(Double.toString(rating));
            date = fields[POS_DATE].substring(SPACING_DATE);
            checkEmptyCommand(date);
            genre = fields[POS_GENRE].substring(SPACING_GENRE);
            checkEmptyCommand(genre);
            dateFields = date.split("-");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            logger.log(Level.WARNING, "\n\tAdd command failed");
            Ui.print("Ensure that input format and number of arguments is correct.");
            return;
        } catch (InvalidCommandException e) {
            logger.log(Level.WARNING, "\n\tAdd command failed");
            Ui.print("Fields cannot be left empty!");
            return;
        }

        //@@author indraneelrp
        try {
            if (isDuplicate(title, spacingType)) {
                throw new UnsupportedOperationException("List already contains item.");
            }
        } catch (UnsupportedOperationException e) {
            Ui.print(e.getMessage());
            return;
        }

        //@@author naz019
        try {
            if (!isValidDate(dateFields)) {
                throw new InvalidDateException();
            }

            if (isFutureDate(date)) {
                throw new FutureDateException();
            }

            //@@author redders7
            if (spacingType == SPACING_MOVIE) {
                toAdd = new Movie(title, rating, genre, date);
            } else {
                String site = fields[POS_SITE].substring(SPACING_SITE);
                toAdd = new TvShow(title, rating, genre, date, site);
            }
            executor = new AddCommand(mediaList, toAdd);
            String output = executor.execute();
            Ui.print(output);
            logger.log(Level.INFO, "\n\tAdd command executed");
        } catch (InvalidDateException e) {
            logger.log(Level.WARNING, "\n\tAdd command failed");
            Ui.print("Invalid date. Give a date from the past, in the following format: DD-MM-YYYY.");
        } catch (FutureDateException e) {
            logger.log(Level.WARNING, "\n\tAdd command failed");
            Ui.print("You have given a date in the future. Give a date from the past, in the following format: "
                    + "DD-MM-YYYY.");
        }
    }

    //@@author indraneelrp
    public boolean isDuplicate(String title, int spacingType) {
        for (int i = 0; i < mediaList.inputs.size(); i++) {
            Media comparisonItem = mediaList.inputs.get(i);
            if (title.equals(comparisonItem.getTitle())
                && comparisonItem.getClass().toString().equals(getMediaTypeString(spacingType))) {
                return true;
            }
        }
        return false;
    }

    public String getMediaTypeString(int spacingType) {
        if (spacingType == SPACING_MOVIE) {
            return MOVIE_TYPE;
        } else if (spacingType == SPACING_TV) {
            return TV_TYPE;
        } else {
            return "";
        }
    }


    //@@author naz019
    /**
     * Checks if provided date is in the future by comparing provided date to system date.
     *
     * @param dateWatchedString The date provided by the user.
     * @return Returns true if provided date is in the future, else returns false.
     */
    public boolean isFutureDate(String dateWatchedString) {
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            dateFormat.setLenient(false);
            date = dateFormat.parse(dateWatchedString);
        } catch (ParseException e) {
            Ui.print("Invalid date format. Use the following format: DD-MM-YYYY.");
        }

        Date currentDate = new Date();
        return currentDate.before(date);
    }

    /**.
     * Checks if provided date is valid
     * @param dateFields Date provided by user to be tested
     * @return True if date is valid, false if invalid
     */
    //@@author Naz019
    public boolean isValidDate(String[] dateFields) {
        if (dateFields.length != 3) {
            return false;
        }

        int day = Integer.parseInt(dateFields[0].trim());
        int month = Integer.parseInt(dateFields[1].trim());
        int year = Integer.parseInt(dateFields[2].trim());

        return day >= 1 && day <= 31 && month >= 1 && month <= 12 && year >= 1 && year <= 2022
                && (day <= 29 || month != 2);
    }

    //@@author redders7
    /**.
     * Command executed by Parser to add new reviews to the user's list
     * @param userInput Raw input given by user containing review details
     */
    //@@author indraneelrp
    public void executeAdd(String userInput) throws IllegalArgumentException {
        String[] reviewFields = userInput.split("/");
        try {
            //checks the number of / instances to ensure that user does not add extra / which messes up parsing
            int slashInstances = userInput.length() - userInput.replace("/", "").length();
            if (userInput.contains(KEYWORD_MOVIE)) {
                if (slashInstances != 4) {
                    throw new IllegalArgumentException("Ensure that input format and number of arguments is correct");
                }
                addMedia(reviewFields, SPACING_MOVIE);
            } else if (userInput.contains(KEYWORD_TV)) {
                if (slashInstances != 5) {
                    throw new IllegalArgumentException("Ensure that input format and number of arguments is correct");
                }
                addMedia(reviewFields, SPACING_TV);
            } else {
                throw new IllegalArgumentException("Ensure that input format and number of arguments is correct");
            }
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "\n\tAdd command failed");
            Ui.print("Ensure that input format and number of arguments is correct.");
        }
    }

    //@@author matthewphua
    /**
     * Executes the clear action by creating a clear object.
     */
    //@@author indraneelrp
    public ReviewList executeClear() {
        executor = new ClearCommand(mediaList);
        String output = executor.execute();
        Ui.print(output);
        logger.log(Level.INFO, "\n\tClear command executed");
        return mediaList;
    }

    //@@author matthewphua
    /**
     * Executes the delete action by creating a deleted object.
     */
    public void executeDelete(String[] words) {
        try {
            if (words.length != 3) {
                throw new DukeException();
            } else {
                String typeString = words[1];
                Class type;
                if (typeString.equals("movie")) {
                    type = Movie.class;
                } else if (typeString.equals("tv")) {
                    type = TvShow.class;
                } else {
                    throw new DukeException();
                }
                String index = words[2];
                int deleteIndex = Integer.parseInt(index);
                executor = new RemoveCommand(mediaList, type, deleteIndex);
                String output = executor.execute();
                Ui.print(output);
                logger.log(Level.INFO, "\n\tDelete command executed");
            }
        } catch (DukeException | NumberFormatException e) {
            logger.log(Level.WARNING, "\n\tDelete command failed");
            Ui.print("Incomplete or wrongly formatted command, try again.");
        }

    }

    //@@author indraneelrp
    /**
     * Gets the private reviewList object.
     */
    public ReviewList getReviewList() {
        return mediaList;
    }
}
