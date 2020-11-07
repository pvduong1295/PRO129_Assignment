
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class main {

    //Initiate 2 Arrays Problem and Contestant
    static ArrayList<Problem> arrProblem = new ArrayList();
    static ArrayList<Contestant> arrContestant = new ArrayList();
    static Problem searched_Problem = new Problem();
    static Contestant searched_Contestant = new Contestant();
    static Contestant loggedIn = new Contestant();
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //load Data into Arrays
        Scanner sc = new Scanner(System.in);
        loadData();
        mainMenu();
//        int k = 1;
//        while (k != 0) {
//            boolean check_Member = Login();
//            if (check_Member == true) {
//                mainMenu();
//            } else {
//                System.out.println("Your password is false.");
//                System.out.println("Do you have an Account?(y/n)");
//                String yon = sc.nextLine();
//                if (yon.equals("n")) {
//                    signIn();
//                }
//            }
//        }
        
    }
    
    public static void mainMenu() {
        int k = 1;
        while (k != 0) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Contest Management System (CMS)");
            System.out.println("Press 1 to Login.");
            System.out.println("Press 2 to add a Problem.");
            System.out.println("Press 3 to edit Contestant's Profile.");
            System.out.println("Press 4 to update Problem.");
            System.out.println("Press 5 to show all Problems.");
            System.out.println("Press 6 to generate Contest.");
            System.out.println("Press 0 to Exit Program.");
            System.out.println("Choose an option :");
            k = sc.nextInt();
            System.out.println("=================================================");
            if (k == 1) {
                Login();
            } else if (k == 2) {
                addProblem();
            } else if (k == 3) {
                updateProblem();
            } else if (k == 4) {
                updateProblem();
            } else if (k == 5) {
                showAllProblem();// ascending order by Category
            } else if (k == 6) {
                generateContest();
            } else if (k == 0) {
                System.out.println("Goodbye!");
                System.exit(0);
            } else {
                System.out.println("Choose from 0 to 5");
            }
        }
    }

    //Login system function
    public static boolean Login() {
        Scanner sc = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        System.out.println("Log In");
        int k = 1;
        while (k != 0) {
            boolean check_Account = searchContestant();
            if (check_Account == true) {
                String password = "";
                System.out.println("Password:");
                password = sc.nextLine();
                if (password.compareTo(searched_Contestant.getPassword()) == 0) {
                    loggedIn = searched_Contestant;
                    return true;
                }
                return false;
            } else {
                System.out.println("Your ID is false!");
                System.out.println("1.Sign in");
                System.out.println("2.Type again");
                System.out.println("0.Exit");
                int l = sc_int.nextInt();
                if (l == 0) {
                    System.exit(0);
                    System.out.println("Goodbye!");
                } else if (l == 1) {
                    signIn();
                }
            }
        }
        return false;
    }
    
    public static void loadData() {
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
    
    public static void addProblem() {
        Scanner sc = new Scanner(System.in);
        Scanner sc_double = new Scanner(System.in);
        Problem newProblem = new Problem();
        System.out.println("ID of new Problem : " + newProblem.getId());
        System.out.println("Please complete the Problem.");
        
        System.out.println("Problem's name: ");//imput Problem name
        String name = sc.nextLine();
        newProblem.setName(name);
        //===================================================
        String category = "";//input category
        //while continue to run until user input the right category
        while ((category.toLowerCase().compareTo("analytics") != 0) && (category.toLowerCase().compareTo("greedy algorithm") != 0) && (category.toLowerCase().compareTo("geometry") != 0) && (category.toLowerCase().compareTo("dynamic programming") != 0) && (category.toLowerCase().compareTo("graph") != 0)) {
            System.out.println("Category :");
            category = sc.nextLine();
            if ((category.toLowerCase().compareTo("analytics") != 0) && (category.toLowerCase().compareTo("greedy algorithm") != 0) && (category.toLowerCase().compareTo("geometry") != 0) && (category.toLowerCase().compareTo("dynamic programming") != 0) && (category.toLowerCase().compareTo("graph") != 0)) {
                System.out.println("There are only 5 types of Problem: analytics, greedy algorithm, geometry, dynamic progamming, graph.");
                System.out.println("Please type again!");
            }
        }
        newProblem.setCategory(category.toLowerCase());
        //=====================================================
        System.out.println("Short description :");//input short description
        String short_des = sc.nextLine();
        //======================================================
        System.out.println("Full description :(Press Enter and Type (D) to end the input)");//input full description
        // Use StringBuilder to build full long text
        StringBuilder full_des = new StringBuilder();
        String line = "";
        while (!"(D)".equals(line)) { //When user enter StringBuilder will add the input line and start a new line
            line = sc.nextLine();
            if (!"(D)".equals(line)) {
                full_des.append(line);
            }
        }
        newProblem.setFull_des(full_des.toString());
        //=======================================================
        System.out.println("Mark: ");
        double Mark = 4.0;
        while (0.0 > Mark || Mark > 3.0) {
            Mark = sc_double.nextDouble();
            if (0.0 > Mark || Mark > 3.0) {
                System.out.println("Mark should be from 0.0 to 3.0");
            }
        }
        newProblem.setMark_weight(Mark);
        //=========================================================
        newProblem.setAuthor(loggedIn.getName()+ " : " +loggedIn.getId());
        //=========================================================
        arrProblem.add(newProblem);
        writeProblem();
        System.out.println("Done!");
        System.out.println("====================================================");
        
    }
    
    public static void updateProblem() {
        // Tìm Problem theo ID
        Scanner sc_num = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        boolean check_Prob = searchProblem();

        if (check_Prob) {
            System.out.println("1.Edit name.");
            System.out.println("2.Short Description.");
            System.out.println("3.Full Description.");
            System.out.println("4.Category.");
            System.out.println("5.Mark Weight.");
            System.out.println("0.Back to Menu.");
            int k = 1;
            while (k != 0) {
                System.out.println("Option :");
                k = sc_num.nextInt();
                System.out.println("\n");
                if (k == 1) {
                    System.out.println("Old name: " + searched_Problem.getName());
                    System.out.println("New name: ");
                    String name = sc.nextLine();
                    searched_Problem.setName(name);
                    System.out.println("Done!");
                }
                if (k == 2) {
                    System.out.println("Old Short Description: " + searched_Problem.getShort_des());
                    System.out.println("New Short Description: ");
                    String short_des = sc.nextLine();
                    searched_Problem.setShort_des(short_des);
                    System.out.println("Done!");
                }
                if (k == 3) {
                    System.out.println("Old Full Description: " + searched_Problem.getFull_des());
                    System.out.println("New Full Description: ");
                    String full_des = sc.nextLine();
                    searched_Problem.setFull_des(full_des);
                    System.out.println("Done!");
                }
                if (k == 4) {
                    System.out.println("Old Category: " + searched_Problem.getCategory());
                    String category = "";
                    //while continue to run until user input the right category
                    while ((category.toLowerCase().compareTo("analytics") != 0) && (category.toLowerCase().compareTo("greedy algorithm") != 0) && (category.toLowerCase().compareTo("geometry") != 0) && (category.toLowerCase().compareTo("dynamic programming") != 0) && (category.toLowerCase().compareTo("graph") != 0)) {
                        System.out.println("Category :");
                        category = sc.nextLine();
                        if ((category.toLowerCase().compareTo("analytics") != 0) && (category.toLowerCase().compareTo("greedy algorithm") != 0) && (category.toLowerCase().compareTo("geometry") != 0) && (category.toLowerCase().compareTo("dynamic programming") != 0) && (category.toLowerCase().compareTo("graph") != 0)) {
                            System.out.println("There are only 5 types of Problem: analytics, greedy algorithm, geometry, dynamic progamming, graph.");
                            System.out.println("Please type again!");
                        }
                    }
                    searched_Problem.setCategory(category);
                }
                if (k == 5) {
                    System.out.println("Old Mark: " + searched_Problem.getMark_weight());
                    System.out.println("New Mark: ");
                    double Mark = sc_num.nextDouble();
                    searched_Problem.setMark_weight(Mark);
                    System.out.println("Done!");
                } else {
                    System.out.println("Please choose from 0 to 5!");
                }
            }

        }
        System.out.println("====================================================");
    }
    
    public static void showAllProblem() {
        ArrayList<Problem> sorted_Problem = new ArrayList();
        sorted_Problem.addAll(arrProblem);
        Collections.sort(sorted_Problem);
        for (Problem i : sorted_Problem) {
            System.out.println(i.toString());
        }
    }
    
    public static void signIn() {
        Scanner sc = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        Contestant newContestant = new Contestant();
        int k = 1;
        while (k != 0) {
            System.out.println("Sign In");
            //============================
            System.out.println("Your auto generated ID: " + newContestant.getId());
            String name = "";
            while ("".equals(name)) {
                System.out.println("Name:");
                name = sc.nextLine();
                newContestant.setName(name);
                if ("".equals(name)) {
                    System.out.println("Name cannot be emty . Please type your name!");
                }
            }
            //=====================
            String email = "";
            while ("".equals(email)) {
                System.out.println("Email:");
                email = sc.nextLine();
                newContestant.setEmail(email);
                if ("".equals(email)) {
                    System.out.println("Email cannot be emty . Please type your Email!");
                }
            }
            //=====================
            String mobilephone = "";
            while ("".equals(mobilephone)) {
                System.out.println("Mobilephone:");
                mobilephone = sc.nextLine();
                newContestant.setMobilephone(mobilephone);
                if ("".equals(mobilephone)) {
                    System.out.println("Mobilephone cannot be emty . Please type your Mobilephone!");
                }
            }
            //======================
            String password = "";
            while ("".equals(password)) {
                System.out.println("Password:");
                password = sc.nextLine();
                newContestant.setPassword(password);
                if ("".equals(password)) {
                    System.out.println("Password cannot be emty . Please type your Password!");
                }
            }
            
            System.out.println("Do you want to sign these in ?(y/n)");
            String ans = sc.nextLine();
            if (ans.equals("y")) {
                arrContestant.add(newContestant);
                writeContestant();
                System.out.println("Done!");
                k = 0;
            } else {
                System.out.println("Do you want to sign in again or back to Menu ?");
                System.out.println("0.Back to Menu");
                System.out.println("1.Sign another");
                k = sc_int.nextInt();
            }
        }
        
        System.out.println("====================");
    }
    
    public static void generateContest() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("Contest_TimeStamp.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException ex) {
            System.out.println("Không tìm thấy file csv.");
            return;// exit generateContest method
        }
        
        // generate 5 Problem
        String contest_Id = generateID();
        sb.append("Contest: ").append(contest_Id).append("\n");
        
        //add the time Contest was generated
        Timestamp tstamp= new Timestamp(System.currentTimeMillis());
        SimpleDateFormat simpleDate_Format = new  SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z");
        String date = simpleDate_Format.format(tstamp);
        sb.append("Date: ").append(date).append("\n");
        
        //add the person who generated Contest
        sb.append("Created by: ").append(loggedIn.getId()).append("\n");
        
        //generate 5 Problems
        double sum=0.0;
        Random rand = new Random();
        ArrayList<Integer> index_store = new ArrayList();

        for (int i = 1; i <= 5; i++) {
            int index = (int) (rand.nextFloat() * arrProblem.size());
            while (index_store.contains(index)) {
                index = (int) (rand.nextFloat() * arrProblem.size());
            }
            index_store.add(index);
            sum = sum + arrProblem.get(index).getMark_weight();
            sb.append("Problems").append(i).append(": ").append(arrProblem.get(index).getShort_des()).append(". Mark: ").append(arrProblem.get(index).getMark_weight()).append("\n");
            
        }
        sb.append("Total Mark: ").append(sum).append("\n");
        sb.append("==================================");
    }
    
    private static String generateID() {
        String Norm = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder text = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            int index = (int) (rand.nextFloat() * Norm.length());
            text.append(Norm.charAt(index));
        }
        String new_text = text.toString();
        return new_text;
    }
    
    public static void showContestInfo() {
        Scanner sc = new Scanner(System.in);
        int k = 1;        
        while (k != 0) {//run until find the right Contest ID in text file
            String Contest_Id = sc.nextLine();
            try {
                File contest = new File("Contest_TimeStamp.txt");
                Scanner contestReader = new Scanner(contest);
                while (contestReader.hasNextLine()) {
                    
                    String data = contestReader.nextLine();
                    if (data.startsWith("Contest: " + Contest_Id)) {// when contest_id was found , print next line until reach line===========
                        System.out.println(data);
                        while (data.startsWith("==================================") == false) {
                            
                            data = contestReader.nextLine();
                            System.out.println(data);
                        }
                        break;
                    }                    
                }
                k = 0;
            } catch (IOException ex) {
                System.out.println("Invalid!");
                k = 0;
            }
        }
        System.out.println("====================================================");
    }
    
    public static boolean searchProblem() {
        Scanner sc = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        int k = 1;
        
        while (k != 0) {
            System.out.println("Problem ID: ");
            String problem_id = sc.nextLine();
            for (Problem i : arrProblem) {
                if (i.getId().toUpperCase() == problem_id.toUpperCase()) {
//                    searched_Problem = i;
                    
                    return true;
                }
            }
                System.out.println("There is no Problem " + problem_id);
                System.out.println("Try another ? (0 for no . Any number for yes)");
                k = sc_int.nextInt();
            
        }
        System.out.println("====================================================");
        return false;
    }
    
    public static boolean searchContestant() {
        Scanner sc = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        int k = 1;
        while (k != 0) {
            System.out.println("Contestant ID: ");
            String contestant_id = sc.nextLine();
            for (Contestant i : arrContestant) {
                if (i.getId().toUpperCase() == contestant_id.toUpperCase()) {
                    searched_Contestant = i;
                    return true;
                }
            }
            k = 0;
        }
        System.out.println("====================================================");
        return false;
    }
    
    public static void writeProblem() {
        try {
            FileOutputStream writeData = new FileOutputStream("QBs.dat");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(arrProblem);
            writeStream.flush();
            writeStream.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void writeContestant() {
        try {
            FileOutputStream writeData = new FileOutputStream("contestants.dat");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(arrContestant);
            writeStream.flush();
            writeStream.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
