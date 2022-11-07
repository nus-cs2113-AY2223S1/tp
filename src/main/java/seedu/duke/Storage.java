package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.IllegalFormatException;
import java.util.Scanner;
import java.io.FileWriter;

//@@author indraneelrp
/**
 * Represents the storage of information relating to the tasklist.
 */
public class Storage {
    private String filepath;
    private String folderpath;
    static final String SPLITTER = "\\|";
    static final String MOVIE_TYPE = "M";
    static final String TV_TYPE = "T";
    static final String YES_STRING = "Y";
    static final String MOVIE_KEY = "M|";
    static final String TV_KEY = "T|";
    static final int NUM_FIELDS_MOVIE = 6;
    static final int NUM_FIELDS_TV = 7;

    public Storage(String filepath, String folderpath) {
        assert filepath.length() > 0 : "Filepath length cannot be 0";
        assert folderpath.length() > 0 : "Folder-path length cannot be 0";
        this.filepath = filepath;
        this.folderpath = folderpath;
    }

    /**
     * Retrieves the file storing user's tasks from the previous run
     * of the program. If no such file is found, makes the appropriate file.
     * 
     * @return File representing the saved information.
     */
    public File getMakeFile() {
        try {
            File folder = new File(folderpath);
            File file = new File(filepath);
            
            if (!folder.exists() && !folder.isDirectory()) {
                // need to create folder and then file as determined by the path string
                folder.mkdirs();
                file.createNewFile();
                return file;
            } else if (!file.exists() && folder.isDirectory()) {
                // need to create file as determined by the path string
                file.createNewFile();
                return file;
            }
            return file;

        } catch (Exception e) {
            Ui.print("File i/o error.");
            return null;
        }
    }

    /**.
     * Checks that stored input has a valid format that can be added to the list
     * @param lineInput Current line from stored file to be processed
     * @return True if fields are valid, false if invalid
     */
    public Boolean isValidFields(String lineInput) {
        String[] fields = lineInput.split(SPLITTER);
        
        if (!(lineInput.contains(TV_KEY) || lineInput.contains(MOVIE_KEY))) {
            System.out.println(lineInput);
            System.out.println(TV_KEY);
            return false;
        }

        if ((lineInput.contains(TV_KEY) && fields.length != NUM_FIELDS_TV)
            || (lineInput.contains(MOVIE_KEY) && fields.length != NUM_FIELDS_MOVIE)) {
            return false;
        }

        return true;
    }

    /**.
     * Load saved list from stored.txt file to current instance of the program
     * @param f Text file to be loaded from, stored.txt
     * @param reviewList List for reviews to be loaded into
     */
    public void loadMedia(File f, ReviewList reviewList) {
        // read in past saved data
        try {
            Scanner fileScanner = new Scanner(f);
            int counter = 0;
        
            while (fileScanner.hasNext()) {
                counter++;
                String lineInput = fileScanner.nextLine();
        
                try {
                    if (!isValidFields(lineInput)) {
                        throw new IllegalArgumentException("Error in format of saved data (line " 
                            + counter + "): Entry ignored.");
                    }
                } catch (IllegalArgumentException e) {
                    Ui.print(e.getMessage());
                    continue;
                }

                String[] words = lineInput.split(SPLITTER);
                Media newMedia = new Media();

                try {
                    Double.parseDouble(words[3]);
                } catch (NumberFormatException e) {
                    Ui.print("Error reading value from line " + counter + ". Entry ignored.");
                    continue;
                }

                double rating = Double.parseDouble(words[3]);

                switch (words[0]) {
                case MOVIE_TYPE:
                    newMedia = new Movie(words[2], rating, words[4], words[5]);
                    reviewList.add(newMedia);
                    break;

                case TV_TYPE:
                    newMedia = new TvShow(words[2], rating, words[4], words[5], words[6]);
                    reviewList.add(newMedia);
                    break;

                default:
                    break;
                }

                if (words[1].equals(YES_STRING)) {
                    newMedia.setFavourite(true);
                }
            }
            fileScanner.close();

        } catch (FileNotFoundException e) {
            Ui.print("Error accessing file.");
        } catch (Exception e) {
            Ui.print("Corrupted file. You may continue using myReviews with a new list.");
            f.delete();
            f.mkdirs();
        }
    }


    /**
     * Writes the information in the tasks array to the file
     * in the specified location for storage.
     * 
     * @param reviewList the current list of tasks to be written into the file.
     */
    public void updateFile(ReviewList reviewList) { 
        try {
            File f = new File(this.filepath);
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();

            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < reviewList.inputs.size(); i++) {
                fw.write(reviewList.inputs.get(i).createFileString() + "\n");
            }

            fw.close();
        } catch (Exception e) {
            Ui.print("File i/o error");
        }
    }

}
