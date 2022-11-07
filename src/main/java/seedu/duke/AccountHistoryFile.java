package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class AccountHistoryFile {

    static final Path FILE_PATH = Paths.get("src","main","data");

    public static void createNewLoginAccount(String username, String loginTime) throws IOException {
        String fileName = (username + loginTime + ".txt").replaceAll(":|>|<" , ".");

        try {
            Files.createDirectories(FILE_PATH);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            createLoginAccount(fileName);
            writeToLoginAccount(fileName, loginTime, username);

        } catch(IOException e) {
            System.out.println("Unable to create file");
        }
    }
    public static void writeToLoginAccount(String fileName, String loginTime, String username) throws IOException {
        File f = new File(FILE_PATH + File.separator + fileName);
        FileWriter fw = new FileWriter(f, true); // create a FileWriter in append mode
        String[] time = loginTime.split(">");
        String editLoginTime = time[0] + " " + time[1];
        fw.write(username + " logged in at " + editLoginTime);
        fw.write(System.lineSeparator());
        fw.close();
    }

    protected static void createLoginAccount(String fileName) throws IOException {
        File f = new File(FILE_PATH + File.separator + fileName);
        FileWriter fw = new FileWriter(f);
        fw.close();
    }

    public static void updateLoginAccount(String username, String loginTime, String in){
        try{
            String fileName = (username + loginTime + ".txt").replaceAll(":|>|<" , ".");
            File f = new File(FILE_PATH + File.separator + fileName);
            FileWriter fw = new FileWriter(f, true);
            fw.write(in);
            fw.write(System.lineSeparator());
            fw.close();
        }
        catch (IOException e){
            //FILE NOT WORKING
        }
    }

    public static void deleteFile(String username, String loginTime) throws IOException {
        String fileName = (username + loginTime + ".txt").replaceAll(":|>|<" , ".");
        fileName = FILE_PATH + File.separator + fileName;
        System.out.println(fileName);
        Files.deleteIfExists(Paths.get(fileName));
    }

    public static void deleteFile(String username) throws IOException {
        String fileName = FILE_PATH + "/" + username + ".txt";
        Files.deleteIfExists(Paths.get(fileName));
    }

    public static void printFile(String username, String loginTime){
        try{
            String fileName = (username + loginTime + ".txt").replaceAll(":|>|<" , ".");
            Scanner in = new Scanner(new File(FILE_PATH + File.separator + fileName));
            while(in.hasNextLine()){
                System.out.println(in.nextLine());
            }
        }
        catch (FileNotFoundException e){
            //FILE IS DELETED
        }
    }
}