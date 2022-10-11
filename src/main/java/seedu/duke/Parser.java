package seedu.duke;

import java.util.ArrayList;

/*
 * Resolves the user input into a command to execute
 */
public class Parser {
    private Commands executor;
    private ReviewList mediaList;

    public Parser(ReviewList reviewList) {
        this.mediaList = reviewList;
    }

    public void processUserInput(String userInput) {
        final String L = "list";
        final String A = "add";
        final String D = "delete";
        final String C = "clear";
        final String E = "bye";
        final String NT = "";
        
        String[] words = userInput.split(" ");

        switch (words[0]) {
        case E:
            break;
            
        case L:
            executeList();
            break;
    
        case A:
            executeAdd(userInput);
            break;
    
        case D:
            executeDelete(words);
            break;
        
        case C:
            executeClear();
            break;
        
        case NT:
            break;
        
        default:
            // unrecognised command
            System.out.println("Unrecognised command");
            break;
        }
    }

    public void executeList() {
        executor = new ListCommand(mediaList);
        String output = executor.execute();
        System.out.println(output);//i think UI should handle the print statement

    }

    public void executeAdd(String userInput) {
        final String movieKeyword = "/movie";
        final int movieSpacing = 5;
        final String ratingKeyword = "/rating";
        final int ratingSpacing = 6;
        final String dateKeyword = "/date";
        final int dateSpacing = 4;

        String[] fields = userInput.split("/");

        try {
            if (!userInput.contains(movieKeyword)
                    || !userInput.contains(ratingKeyword)
                    || !userInput.contains(dateKeyword)
            ) {
                throw new Exception();
            } else {
                String name = fields[1].substring(movieSpacing);
                double rating = Double.parseDouble(fields[2].substring(ratingSpacing));
                String date = fields[3].substring(dateSpacing);
                
                Movie toAdd = new Movie(name, rating, date);
                executor = new AddCommand(mediaList, toAdd);
                String output = executor.execute();
                System.out.println(output);//i think UI should handle the print statement
            }
        } catch (Exception e) {
            System.out.println("\nIncomplete or wrongly formatted command, try again.\n");
        }
    }

    public void executeClear() {
        executor = new ClearCommand(mediaList);
        String output = executor.execute();
        System.out.println(output);//i think UI should handle the print statement
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
                System.out.println(output); //i think UI should handle the print statement
            }
        } catch (Exception e) {
            System.out.println("Wrong Command\n");
        }
       
    }
}
