
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TeachersSystem {

    private ArrayList<Problem> arrProblem = new ArrayList();
    private ArrayList<Teacher> arrTeacher = new ArrayList();
    private int LoggedInpos;

    public TeachersSystem() {

        this.arrProblem = arrProblem;
        loadProblem();
        
        this.arrTeacher = arrTeacher;
        loadTeacher();
      
        this.LoggedInpos = LoggedInpos;
    }

    
    public void addProblem() {
        throw new UnsupportedOperationException("Chưa làm!");
    }

    public void updateProblem() {
        throw new UnsupportedOperationException("Chưa làm!");
    }

    public Integer searchProblem() {
        throw new UnsupportedOperationException("Chưa làm!");
    }

    public void writeProblem(String filename) {
        throw new UnsupportedOperationException("Chưa làm!");
    }

    public void showAllProblem() {
        throw new UnsupportedOperationException("Chưa làm!");
    }

    private boolean searchTeacher() {
        throw new UnsupportedOperationException("Chưa làm!");
    }

    public void signIn() {
        throw new UnsupportedOperationException("Chưa làm!");
    }

    public void writeTeacher() {
        throw new UnsupportedOperationException("Chưa làm!");
    }

    public void showContestInfo() {
        throw new UnsupportedOperationException("Chưa làm!");
    }

    public void mainMenuofTeacher() {

        String k = "1";
        while (k.equals("0") == false) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Welcome " + getArrTeacher().get(getLoggedInpos()).getName());
            System.out.println("Press 1 to Login to other Account.");
            System.out.println("Press 2 to Add Problem.");
            System.out.println("Press 3 to Update Problem.");
            System.out.println("Press 4 to Show all Problem.");
            System.out.println("Press 5 to Show Contest Info.");
            System.out.println("Press 0 to Exit Program.");
            System.out.println("Choose an option :");
            k = sc.nextLine();
            System.out.println("=================================================");
            if (k.equals("1")) {
                TeacherLogin();
            } else if (k.equals("2")) {
                addProblem();
            } else if (k.equals("3")) {
                updateProblem();
            } else if (k.equals("4")) {
                showAllProblem();
            } else if (k.equals("5")) {
                showContestInfo();
            } else if (k.equals("0")) {
                System.out.println("Goodbye!");
                System.exit(0);
            } else {
                System.out.println("Choose from 0 to 5");
            }
        }
    }

    public boolean TeacherLogin() {
        throw new UnsupportedOperationException("Chưa làm!");
    }
    
    public void loadProblem(){
        try {//load QuestionBank
            FileInputStream file = new FileInputStream("QBs.dat");
            ObjectInputStream ois = new ObjectInputStream(file);
            arrProblem = (ArrayList<Problem>) ois.readObject();
            ois.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    
    public void loadTeacher(){
        try {//load Teachers Array
            FileInputStream file = new FileInputStream("Teachers.dat");
            ObjectInputStream ois = new ObjectInputStream(file);
            arrTeacher = (ArrayList<Teacher>) ois.readObject();
            ois.close();
            file.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
