package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class AccountHistoryFile {

    static final Path FILE_PATH = Paths.get("src","main","data");

    public static void createNewLoginAccount(String username, String loginTime){
        File f = new File(FILE_PATH + "/" + username + loginTime + ".txt");
        try{
            FileWriter fw = new FileWriter(f);
            String[] time = loginTime.split(">");
            String editLoginTime = time[0] + " " + time[1];
            fw.write(username + " logged in at " + editLoginTime);
            fw.write(System.lineSeparator());
            fw.close();
        }
        catch(IOException e){
            System.out.println("Unable to create file");
        }
    }

    public static void updateLoginAccount(String username, String loginTime, String in){
        try{
            File f = new File(FILE_PATH + "/" + username + loginTime + ".txt");
            FileWriter fw = new FileWriter(f, true);
            fw.write(in);
            fw.write(System.lineSeparator());
            fw.close();
        }
        catch (IOException e){
            //FILE NOT WORKING
        }
    }

    public static void deleteFile(String username, String loginTime){
        File f = new File(FILE_PATH + "/" + username + loginTime + ".txt");
        f.delete();
    }

    public static void printFile(String username, String loginTime){
        try{
            Scanner in = new Scanner(new File(FILE_PATH + "/" + username + loginTime + ".txt"));
            while(in.hasNextLine()){
                System.out.println(in.nextLine());
            }
        }
        catch (FileNotFoundException e){
            //FILE IS DELETED
        }

    }
}
