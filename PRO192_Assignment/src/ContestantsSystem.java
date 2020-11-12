
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ContestantsSystem {

    private ArrayList<Contestant> arrContestant = new ArrayList();
    private ArrayList<Problem> arrProblem = new ArrayList();
    private int LoggedInpos;

    public ContestantsSystem() {
        this.arrContestant = arrContestant;
        loadContestants();
        this.arrProblem = arrProblem;
        loadProblems();
        this.LoggedInpos = LoggedInpos;
    }

    

    private void editContestantProfile() {
        throw new UnsupportedOperationException("Chưa làm!");
    }

    public void generateContest() {
        throw new UnsupportedOperationException("Chưa làm!");

    }

    private Integer searchContestant() {
        throw new UnsupportedOperationException("Chưa làm!");
    }

    

    public boolean ContestantLogin() {
        throw new UnsupportedOperationException("Chưa làm!");
    }

    public void mainMenuofContestant() {

        String k = "1";
        while (k.equals("0") == false) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Welcome " + getArrContestant().get(getLoggedInpos()).getName());
            System.out.println("Press 1 to Login to other Account.");
            System.out.println("Press 2 to Edit Your's Profile.");
            System.out.println("Press 3 to Generate Contest.");
            System.out.println("Press 0 to Exit Program.");
            System.out.println("Choose an option :");
            k = sc.nextLine();
            System.out.println("=================================================");
            if (k.equals("1")) {
                ContestantLogin();
            } else if (k.equals("2")) {
                editContestantProfile();// ascending order by Category
            } else if (k.equals("3")) {
                generateContest();
            } else if (k.equals("0")) {
                System.out.println("Goodbye!");
                System.exit(0);
            } else {
                System.out.println("Choose from 0 to 3");
            }
        }
    }

    public void signIn() {
        throw new UnsupportedOperationException("Chưa làm!");
    }

    public void showContestInfo(String Contest_Id) {

        throw new UnsupportedOperationException("Chưa làm!");
    }

    private void loadProblems() {
        try {
            FileInputStream file = new FileInputStream("QBs.dat");
            ObjectInputStream ois = new ObjectInputStream(file);
            arrProblem.clear();
            arrProblem = (ArrayList<Problem>) ois.readObject();
            file.close();
            ois.close();
        } catch (Exception e) {
            System.out.println("Error: Problem");
        }
    }
    
    private void loadContestants(){
        try {
            FileInputStream file = new FileInputStream("contestants.dat");
            ObjectInputStream ois = new ObjectInputStream(file);
            arrContestant.clear();
            arrContestant = (ArrayList<Contestant>) ois.readObject();
            
            file.close();
            ois.close();
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    private String generateID() {

        Random rand = new Random();
        String Num = "1234567890";
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int index = (int) (rand.nextFloat() * Num.length());
            text.append(Num.charAt(index));
        }
        String new_num = text.toString();
        //===============================
        String Char = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder char_text = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            int index = (int) (rand.nextFloat() * Char.length());
            char_text.append(Char.charAt(index));
        }
        String new_char = char_text.toString();
        return new_char + new_num;
    }
}
