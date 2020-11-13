
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
 public void writeTeacher() {
        try {
            FileOutputStream writeData = new FileOutputStream("Teachers.dat");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(arrTeacher);
            writeStream.flush();
            writeStream.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean TeacherLogin() {
        Scanner sc = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        System.out.println("LOG IN");

        System.out.println("For tester: id(GY9394), password(deptrai)");
        boolean check_Teacher = searchTeacher();
        if (check_Teacher) {
            int k = 1;
            while (k != 0) {
                String password = "";
                System.out.println("Password: (Type (0) to Exit programm)");
                password = sc.nextLine();
                if (password.compareTo(getArrTeacher().get(getLoggedInpos()).getPassword()) == 0) {
                    return true;
                } else if(password.equals("(0)")){
                      System.exit(0);  
                }else{
                    System.out.println("Wrong Password!Try again");
                }
            }
        } else{
            System.out.println("Do you want to sign in?(y/n)");
            String opt= sc.nextLine();
            if(opt.equals("y")){
                signIn();
            } else {
                System.out.println("Goodbye!");
                System.exit(0);
            }
        }
        return false;
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
