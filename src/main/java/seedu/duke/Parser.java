package seedu.duke;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Resolves the user input into a command to execute
 */
public class Parser {
    final String movieKeyword = "/movie";
    final int movieSpacing = 5;
    final String tvKeyword = "/tv";
    final int tvSpacing = 2;
    final String ratingKeyword = "/rating";
    final int ratingSpacing = 6;
    final String dateKeyword = "/date";
    final int dateSpacing = 4;
    final String genreKeyword = "/genre";
    final int genreSpacing = 5;
    final String siteKeyword = "/site";
    final int siteSpacing = 4;
    final int favouriteSpacing = 9;
    private Commands executor;
    private ReviewList mediaList;

    //@@author naz019
    private static Logger logger = Logger.getLogger("ParserClass");

    public Parser(ReviewList reviewList) {
        this.mediaList = reviewList;
    }

    
    public void processUserInput(String userInput) {
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
        
        String[] words = userInput.split(" ");

        //@@author naz019
        assert words[0] != null : "words[0] is supposed to contain user command";

        //@@author indraneelrp
        switch (words[0]) {
        case endCommand:
            break;
            
        case listCommand:
            executeList();
            break;
    
        case addCommand:
            executeAdd(userInput);
            break;
    
        case deleteCommand:
            executeDelete(words);
            break;
        
        case clearCommand:
            executeClear();
            break;

        case favouriteCommand:
            executeFavourite(words);
            break;

        case findCommand:
            executeFind(words);
            break;

        case sortCommand:
            executeSort(words);
            break;

        case NT:
        
        default:
            logger.log(Level.WARNING, "An unrecognised command was given by the user.");
            System.out.println("Unrecognised command");
            break;
        }
    }

    //@@author naz019
    public void executeFind(String[] words) {
        try {
            executor = new FindCommand(mediaList, words [1]); 
            String output = executor.execute();
            Ui.print(output);
            logger.log(Level.INFO, "\n\tFind command executed");
        } catch (Exception e) {
            System.out.println("\nIncomplete or wrongly formatted command, try again.\n");
        }
    }

    public void executeSort(String[] words) {
        try {
            executor = new SortCommand(mediaList, words);
            String output = executor.execute();
            Ui.print(output);
            logger.log(Level.INFO, "\n\tSort command executed");
        } catch (Exception e) {
            System.out.println("\nIncomplete or wrongly formatted command, try again.\n");
        }
    }

    public void executeFavourite(String[] words) {
        try {
            executor = new FavouriteCommand(mediaList, words);
            String output = executor.execute();
            Ui.print(output);
            logger.log(Level.INFO, "\n\tFavourites command executed");
        } catch (Exception e) {
            System.out.println("\nIncomplete or wrongly formatted command, try again.\n");
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
    public void addMedia(String[] fields, Integer spacingType) {
        String name = fields[1].substring(spacingType);
        double rating = Double.parseDouble(fields[2].substring(ratingSpacing));
        String date = fields[3].substring(dateSpacing);
        String genre = fields[4].substring(genreSpacing);
        Media toAdd;

        String[] dateFields = date.split("-");
        try {
            if (!isValidDate(dateFields)) {
                throw new Exception();
            }

            if (spacingType == movieSpacing) {
                toAdd = new Movie(name, rating, genre, date);
            } else {
                String site = fields[5].substring(siteSpacing);
                toAdd = new TvShow(name, rating, genre, date, site);
            }
            executor = new AddCommand(mediaList, toAdd);
            String output = executor.execute();
            Ui.print(output);
            logger.log(Level.INFO, "\n\tAdd command executed");
        } catch (Exception e) {
            System.out.println("Invalid date format");
        }
    }

    public boolean isValidDate(String[] dateFields) {
        if (dateFields.length != 3) {
            return false;
        }

        int day = Integer.parseInt(dateFields[0].trim());
        int month = Integer.parseInt(dateFields[1].trim());
        int year = Integer.parseInt(dateFields[2].trim());

        if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1 || year > 2022 || (day > 29 && month == 2)) {
            return false;
        }

        return true;
    }

    //@@author indraneelrp
    public void executeAdd(String userInput) {
        String[] fields = userInput.split("/");
        try {
            if (userInput.contains(movieKeyword)) {
                addMedia(fields, movieSpacing);
            } else if (userInput.contains(tvKeyword)) {
                addMedia(fields, tvSpacing);
            } else {
                throw new DukeException();
            }
        } catch (DukeException e) {
            logger.log(Level.WARNING, "\n\tAdd command failed");
            System.out.println("\n" + e.getMessage());
        }
    }

    //@@author matthewphua
    public void executeClear() {
        executor = new ClearCommand(mediaList);
        String output = executor.execute();
        Ui.print(output);
        logger.log(Level.INFO, "\n\tClear command executed");
    }

    //@@author matthewphua
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
        } catch (DukeException e) {
            logger.log(Level.WARNING, "\n\tDelete command failed");
            System.out.println("\n" + e.getMessage());
        }

    }
}
