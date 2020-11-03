

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class main {
    //Initiate 2 Arrays Problem and Contestant
    static ArrayList<Problem> arrProblem = new ArrayList();
    static ArrayList<Contestant> arrContestant = new ArrayList();
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //load Data into 2 Arrays
        loadData();
        int k=1;
        while(k!=0){
            Scanner sc = new Scanner (System.in);
            System.out.println("Contest Management System (CMS)");
            System.out.println("Press 1 to Login.");
            System.out.println("Press 2 to edit Contestants profile.");
            System.out.println("Press 3 to add a Problem.");
            System.out.println("Press 4 to update Problem.");
            System.out.println("Press 5 to show all Problems.");
            System.out.println("Press 6 to generate Contest.");
            System.out.println("Press 7 to show ContestInfo.");
            System.out.println("Press 0 to Exit Program.");
            System.out.println("Choose an option :");
            k = sc.nextInt();
            if(k==1){
                Login();
            }else if(k==2){
                editContestantInfo();
            }else if(k==3){
                addProblem();
            }else if(k==4){
                updateProblem();
            }else if(k==5){
                showAllProblem();// ascending order
            }else if(k==6){
                generateContest();
            }else if (k==7){
                showContestInfo();
            }
        }
        
        
       
        
    }
    
    //Login system function
    public static void Login(){
        Scanner sc = new Scanner(System.in);
        
    }
    public static void loadData(){
        try {
            FileInputStream readPro_Data = new FileInputStream("QBs.dat");
            ObjectInputStream readPro_Stream = new ObjectInputStream(readPro_Data);
            ArrayList<Problem> problems = (ArrayList<Problem>) readPro_Stream.readObject();
            arrProblem.addAll(problems);
            readPro_Stream.close();
            
            FileInputStream readCon_Data = new FileInputStream("contestants.dat");
            ObjectInputStream readCon_Stream = new ObjectInputStream(readCon_Data);
            ArrayList<Contestant> contestants = (ArrayList<Contestant>) readCon_Stream.readObject();
            arrContestant.addAll(contestants);
            readPro_Stream.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addProblem(){    
        throw new UnsupportedOperationException("Chưa làm!");
    }
    
    public static void updateProblem(){
        // Tìm Problem theo ID
        throw new UnsupportedOperationException("Chưa làm!");
    }
    
    public static void showAllProblem(){
        throw new UnsupportedOperationException("Chưa làm!");
    }
    
    public static void addContestant(){
        throw new UnsupportedOperationException("Chưa làm!");
    }
    
    public static void editContestantInfo(){
        throw new UnsupportedOperationException("Chưa làm!");
    }
    
    public static void generateContest(){
        throw new UnsupportedOperationException("Chưa làm!");
    }
    
    public static void showContestInfo(){
        throw new UnsupportedOperationException("Chưa làm!");
    }
}
