package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * Represents the storage of information relating to the tasklist
 */
public class Storage {
    private String filepath;
    private String folderpath;

    public Storage(String filepath, String folderpath) {
        this.filepath = filepath;
        this.folderpath = folderpath;
    }

    /**
     * Retreives the file storing user's tasks from the previous run
     * of the program. If no such file is found, makes the appropriate file
     * 
     * @return File representing the saved information
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
            }
            else if (!file.exists() && folder.isDirectory()) {
                // need to create file as determined by the path string
                file.createNewFile();
                return file;
            }
            return file;

        } catch (Exception e) {
            System.out.println("File i/o error.");
            return null;
        }
    }

    public void loadMedia(File f, ReviewList reviewList) {
        final String SPLITTER = "\\|";
        final String MOVIE_IDENTIFIER = "M";
        final String TVSHOW_IDENTIFIER = "T";
        final String YES = "Y";

        // read in past saved data
        try {
            Scanner fileScanner = new Scanner(f);
        
            while (fileScanner.hasNext()) {
                String lineInput = fileScanner.nextLine();
                String[] words = lineInput.split(SPLITTER);
                double rating = Double.parseDouble(words[3]);
                Media newMedia = new Media();

                switch (words[0]) {
                case MOVIE_IDENTIFIER:
                    newMedia = new Movie(words[2], rating, words[4], words[5]);
                    reviewList.add(newMedia);
                    break;

                case TVSHOW_IDENTIFIER:
                    newMedia = new TvShow(words[2], rating, words[4], words[5], words[6]);
                    reviewList.add(newMedia);
                    break;

                default:
                    break;
                }

                if (words[1].equals(YES)) {
                    newMedia.setFavourite(true);
                }
            }
            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("error accessing file");
        } 
    }


    /**
     * Writes the information in the tasks array to the file
     * in the specified location for storage
     * 
     * @param tasks the current list of tasks to be written into the file
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
            System.out.println("File i/o error");
        }
    }

}
