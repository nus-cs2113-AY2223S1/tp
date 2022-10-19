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

    private static Logger logger = Logger.getLogger("ParserClass");

    public Parser(ReviewList reviewList) {
        this.mediaList = reviewList;
    }

    public void processUserInput(String userInput) {
        final String listCommand = "list";
        final String addCommand = "add";
        final String deleteCommand = "delete";
        final String clearCommand = "clear";
        final String endCommand = "bye";
        final String favouriteCommand = "favourite";
        final String sortCommand = "sort";
        final String NT = "";
        
        String[] words = userInput.split(" ");

        assert words[0] != null : "words[0] is supposed to contain user command";

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

    public void executeSort(String[] words) {
        try {

            executor = new SortCommand(mediaList, words);
            String output = executor.execute();
            Ui.print(output);
        } catch (Exception e) {
            System.out.println("\nIncomplete or wrongly formatted command, try again.\n");
        }
    }

    public void executeFavourite(String[] words) {
        try {
            executor = new FavouriteCommand(mediaList, words);
            String output = executor.execute();
            Ui.print(output);
        } catch (Exception e) {
            System.out.println("\nIncomplete or wrongly formatted command, try again.\n");
        }
    }

    public void executeList() {
        executor = new ListCommand(mediaList);
        String output = executor.execute();
        Ui.print(output);
    }

    public void addMedia(String[] fields, Integer spacingType) {
        String name = fields[1].substring(spacingType);
        double rating = Double.parseDouble(fields[2].substring(ratingSpacing));
        String date = fields[3].substring(dateSpacing);
        String genre = fields[4].substring(genreSpacing);
        Media toAdd;

        if (spacingType == movieSpacing) {
            toAdd = new Movie(name, rating, genre, date);
        } else {
            String site = fields[5].substring(siteSpacing);
            toAdd = new TvShow(name, rating, genre, date, site);
        }
        executor = new AddCommand(mediaList, toAdd);
        String output = executor.execute();
        Ui.print(output);
    }

    public void executeAdd(String userInput) {
        String[] fields = userInput.split("/");
        try {
            if (userInput.contains(movieKeyword)) {
                addMedia(fields, movieSpacing);
            } else if (userInput.contains(tvKeyword)) {
                addMedia(fields, tvSpacing);
            }
        } catch (Exception e) {
            System.out.println("\nIncomplete or wrongly formatted command, try again.\n");
        }
    }

    public void executeClear() {
        executor = new ClearCommand(mediaList);
        String output = executor.execute();
        Ui.print(output);
    }

    public void executeDelete(String[] words) {
        try {
            if (words.length <= 1) {
                throw new Exception();
            } else {
                String index = words[1];
                int deleteIndex = Integer.parseInt(index) - 1;
                executor = new RemoveCommand(mediaList, deleteIndex);
                String output = executor.execute();
                Ui.print(output);
            }
        } catch (Exception e) {
            System.out.println("Wrong Command\n");
        }

    }
}
