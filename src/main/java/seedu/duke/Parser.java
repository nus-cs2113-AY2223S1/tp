package seedu.duke;

import java.util.ArrayList;

/*
 * Resolves the user input into a command to execute
 */
public class Parser {
    private Commands executor;
    private ArrayList<Media> mediaList;

    public Parser(ReviewList reviewList) {
        this.executor = new Commands(reviewList);
        this.mediaList = reviewList.inputs;
    }

    public void processUserInput(String userInput) {
        final String LIST_TRIGGER = "list";
        final String ADD_TRIGGER = "add";
        final String DELETE_TRIGGER = "delete"; 
        final String CLEAR_TRIGGER = "clear";
        final String EXIT_TRIGGER = "bye";
        final String DO_NOTHING = "";
        
        String[] words = userInput.split(" ");

        switch (words[0]) {
        case EXIT_TRIGGER:
            break;
            
        case LIST_TRIGGER:
            executeList();
            break;
    
        case ADD_TRIGGER:  
            executeAdd(userInput);
            break;
    
        case DELETE_TRIGGER:
            executeDelete(words);
            break;
        
        case CLEAR_TRIGGER:
            executeClear();
            break;
        
        case DO_NOTHING:
            break;
        
        default:
            // unrecognised command
            break;
        }
    }

    public void executeList() {
        executor.list(mediaList);
    }

    public void executeAdd(String userInput) {
        final String MOVIE_KEYWORD = "/movie";
        final int MOVIE_SPACING = 5;
        final String RATING_KEYWORD = "/rating";
        final int RATING_SPACING = 6;
        final String DATE_KEYWORD = "/date";
        final int DATE_SPACING = 4;

        String[] fields = userInput.split("/");

        try {
            if (!userInput.contains(MOVIE_KEYWORD) ||
                !userInput.contains(RATING_KEYWORD) ||
                !userInput.contains(DATE_KEYWORD)
            ) {
                throw new Exception();
            } else {
                String name = fields[1].substring(MOVIE_SPACING);
                double rating = Double.parseDouble(fields[2].substring(RATING_SPACING));
                String date = fields[3].substring(DATE_SPACING);
                
                Movie toAdd = new Movie(name, rating, date);
                System.out.println(toAdd.toString());
                executor.add(toAdd);   
            }
        } catch (Exception e) {
            System.out.println("\nIncomplete or wrongly formatted command, try again.\n");
        }
    }

    public void executeClear() {
        executor.clear(mediaList);
    }

    public void executeDelete(String[] words) {
        try {
            if (words.length <= 1) {
                throw new Exception();
            } else {
                executor.delete(mediaList, words[1]);   
            }
        } catch (Exception e) {
            System.out.println("Wrong Command\n");
        }
       
    }
}
