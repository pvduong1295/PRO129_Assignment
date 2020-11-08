
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
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
//    static Problem searched_Problem = new Problem();
//    static Contestant searched_Contestant = new Contestant();
    static Contestant loggedIn = new Contestant();
    static int Problem_index = 0;
    static int Contestant_index=0;
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        loadData();  //load Data vào Arrays        
        int k = 1;
        while (k != 0) {
            boolean check_Member = Login();
            if (check_Member == true) {
                System.out.println("====================================================");
                mainMenu();
            } else {
                System.out.println("Do you have an Account?(y/n)");
                String yon = sc.nextLine();
                if (yon.equals("n")) {
                    signIn();
                }
                System.out.println("====================================================");
            }
        }
        
    }
    
    public static void mainMenu() {
        String k = "1";
        while (k.equals("0")==false) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Contest Management System (CMS)");
            System.out.println("Press 1 to Login to other Account.");
            System.out.println("Press 2 to Add a Problem.");
            System.out.println("Press 3 to Edit Contestant's Profile.");
            System.out.println("Press 4 to Show all Problems.");
            System.out.println("Press 5 to Edit Contestant's Profile.");
            System.out.println("Press 6 to Generate Contest.");
            System.out.println("Press 7 to Find Contest.");
            System.out.println("Press 0 to Exit Program.");
            System.out.println("Choose an option :");
            k = sc.nextLine();
            System.out.println("=================================================");
            if (k.equals("1")) {
                Login();
            } else if (k.equals("2")) {
                addProblem();
            } else if (k.equals("3")) {
                updateProblem();
            } else if (k.equals("4")) {
                showAllProblem();
            } else if (k.equals("5")) {
                editContestantProfile();// ascending order by Category
            } else if (k.equals("6")) {
                generateContest();
            }else if (k.equals("7")) {
                showContestInfo();
            } else if (k.equals("0")) {
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
        System.out.println("LOG IN");
        int k = 1;
        while (k != 0) {
            System.out.println("For tester: id(WA6576), password(richkid)");
            boolean check_Account = searchContestant();
            if (check_Account == true) {
                String password = "";
                System.out.println("Password:");
                password = sc.nextLine();
                if (password.compareTo(arrContestant.get(Contestant_index).getPassword()) == 0) {
                    loggedIn = arrContestant.get(Contestant_index);
                    return true;
                }
            }
            k=0;
            return false;
            
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
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try{
            FileInputStream readCon_Data = new FileInputStream("contestants.dat");
            ObjectInputStream readCon_Stream = new ObjectInputStream(readCon_Data);
            ArrayList<Contestant> contestants = (ArrayList<Contestant>) readCon_Stream.readObject();
            arrContestant.addAll(contestants);
            readCon_Stream.close();
        } catch (Exception e){
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
        String name = "";
        while (name.equals("")){
            name = sc.nextLine();
            if (name.equals("")) System.out.println("Name cannot be emty!Please type again: ");
        }
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
        System.out.println("Short description :");//nhập sort des
        String short_des = "";
        while (short_des.equals("")){
            short_des = sc.nextLine();
            if (short_des.equals("")) System.out.println("Short description cannot be emty!Please type again: ");
        }
        newProblem.setShort_des(short_des);
        //======================================================
        System.out.println("Full description :(Press Enter and Type $D to end the input)");//nhập full description
        // Use StringBuilder to build full long text
        StringBuilder full_des = new StringBuilder();
        String line = "";
        String temp_text = "";
        int k=1;
        while ((!"$D".equals(line))&&(k==1)) { // Vòng while lặp cho đến khi gõ $D . Nếu full_des chỉ chứa dấu cách hoặc không có gì thì lặp lại vòng while
            line = sc.nextLine();
            temp_text = line;
            if ((!"$D".equals(line))&&(false==temp_text.trim().isEmpty())) {// khi line khác $D và line bỏ hết các dấu cách và xuống dòng thì không rỗng 
                                                                            //thì add line vào StringBuilder.
                full_des.append(line).append("\n");    
            }else if("$D".equals(line)){
                if( full_des.toString().isEmpty()||full_des.toString()==null) {
                    k=1;
                    System.out.println("============================================");
                    System.out.println("Full description cannot be Empty. Type again:");
                    line="";   
                }
                else k=0;
            }
        }
        newProblem.setFull_des(full_des.toString());
        //=======================================================
        System.out.println("Mark: ");
        double Mark = 4.0;
        while (0.0 > Mark || Mark > 3.0) {
            try {
                Mark = sc_double.nextDouble();
                if (0.0 > Mark || Mark > 3.0) {
                    System.out.println("Mark should be from 0.0 to 3.0, depends on question's difficulty.");
                }
            }catch(Exception e){
                System.out.println("Mark should be number from 0.0 to 3.0");
                Mark = 4.0;
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
                    System.out.println("Old name: " + arrProblem.get(Problem_index).getName());
                    System.out.println("New name: ");
                    String name = sc.nextLine();

                    while (name.equals("")) {
                        name = sc.nextLine();

                        if (name.equals("")) {
                            System.out.println("Name cannot be emty!Please type again: ");
                        }
                    }
                    arrProblem.get(Problem_index).setName(name);
                    System.out.println("Done!");
                    k=0;
                    writeProblem();
                }
                if (k == 2) {
                    System.out.println("Old Short Description: " + arrProblem.get(Problem_index).getShort_des());
                    System.out.println("New Short Description: ");
                    String short_des = sc.nextLine();
                    while (short_des.equals("")) {
                        short_des = sc.nextLine();
                        if (short_des.equals("")) {
                            System.out.println("Short description cannot be emty!Please type again: ");
                        }
                    }
                    arrProblem.get(Problem_index).setShort_des(short_des);
                    k=0;
                    writeProblem();
                }
                if (k == 3) {
                    System.out.println("Old Full Description: " + arrProblem.get(Problem_index).getFull_des());
                    System.out.println("New Full Description: ");
                    StringBuilder full_des = new StringBuilder();
                    String line = "";
                    String temp_text = "";
                    int l = 1;
                    while ((!"$D".equals(line)) && (l == 1)) { //Vòng while lặp cho đến khi gõ $D . Nếu full_des chỉ chứa dấu cách hoặc không có gì thì lặp lại vòng while
                        line = sc.nextLine();
                        temp_text = line;
                        if ((!"$D".equals(line)) && (false == temp_text.trim().isEmpty())) {
                            full_des.append(line).append("\n");
                        } else if ("$D".equals(line)) {
                            if (full_des.toString().isEmpty() || full_des.toString() == null) {
                                l = 1;
                                System.out.println("============================================");
                                System.out.println("Full description cannot be Empty. Type again:");
                                line = "";

                            } else {
                                l = 0;
                            }
                        }
                    }
                    arrProblem.get(Problem_index).setFull_des(full_des.toString());
                    k=0;
                    writeProblem();
                }
                if (k == 4) {
                    System.out.println("Old Category: " + arrProblem.get(Problem_index).getCategory());
                    String category = "";
                    //vòng while tiếp tục chạy cho đến khi nhập đúng 1 trong 5 category : analytics, greedy algorithm, geometry, dynamic programming , graph
                    while ((category.toLowerCase().compareTo("analytics") != 0) && (category.toLowerCase().compareTo("greedy algorithm") != 0) && (category.toLowerCase().compareTo("geometry") != 0) && (category.toLowerCase().compareTo("dynamic programming") != 0) && (category.toLowerCase().compareTo("graph") != 0)) {
                        System.out.println("Category :");
                        category = sc.nextLine();
                        if ((category.toLowerCase().compareTo("analytics") != 0) && (category.toLowerCase().compareTo("greedy algorithm") != 0) && (category.toLowerCase().compareTo("geometry") != 0) && (category.toLowerCase().compareTo("dynamic programming") != 0) && (category.toLowerCase().compareTo("graph") != 0)) {
                            System.out.println("There are only 5 types of Problem: analytics, greedy algorithm, geometry, dynamic progamming, graph.");
                            System.out.println("Please type again!");
                        }
                    }
                    arrProblem.get(Problem_index).setCategory(category);
                    k=0;
                    writeProblem();
                }
                if (k == 5) {
                    System.out.println("Old Mark: " + arrProblem.get(Problem_index).getMark_weight());
                    System.out.println("New Mark: ");
                    double Mark = 4.0;
                    while (0.0 > Mark || Mark > 3.0) {
                        try {
                            Mark = sc_num.nextDouble();
                            if (0.0 > Mark || Mark > 3.0) {
                                System.out.println("Mark should be from 0.0 to 3.0, depends on question's difficulty.");
                            }
                        } catch (Exception e) {
                            System.out.println("Mark should be number from 0.0 to 3.0");
                            Mark = 4.0;
                        }
                    }
                    arrProblem.get(Problem_index).setMark_weight(Mark);
                    System.out.println("Done!");
                    k=0;
                    writeProblem();
                } else {
                   if(k!=0) System.out.println("Please choose from 0 to 5!");
                }
            }
            k=0;
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
                    System.out.println("Name cannot be emty . Please type your name again!: ");
                }
            }
            //=====================
            String email = "";
            while ("".equals(email)) {
                System.out.println("Email:");
                email = sc.nextLine();
                newContestant.setEmail(email);
                if ("".equals(email)) {
                    System.out.println("Email cannot be emty . Please type your Email again!: ");
                }
            }
            //=====================
            String mobilephone = "";
            while ("".equals(mobilephone)) {
                System.out.println("Mobilephone:");
                mobilephone = sc.nextLine();
                newContestant.setMobilephone(mobilephone);
                if ("".equals(mobilephone)) {
                    System.out.println("Mobilephone cannot be emty . Please type your Mobilephone again!: ");
                }
            }
            //======================
            String password = "";
            while ("".equals(password)) {
                System.out.println("Password:");
                password = sc.nextLine();
                newContestant.setPassword(password);
                if ("".equals(password)) {
                    System.out.println("Password cannot be emty . Please type your Password again!: ");
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
        
        System.out.println("====================================================");
    }
    
    public static void generateContest() {
        if (arrProblem.size() < 5) {
            System.out.println("There are only " + arrProblem.size() + " Problems . These are not enough to generate a Contest");
            System.out.println("====================================================");
            
        } else {
            StringBuilder sb = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader("Contest_TimeStamp.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException ex) {
                System.out.println("Cannot find anything.");
                return;// thoát nếu không có file
            }

            // thêm ContestID
            String contest_Id = generateID();
            sb.append("Contest: ").append(contest_Id).append("\n");

            //thêm thời gian tạo đề
            Timestamp tstamp = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat simpleDate_Format = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z");
            String date = simpleDate_Format.format(tstamp);
            sb.append("Date: ").append(date).append("\n");

            //thêm người tạo đề
            sb.append("Created by: ").append(loggedIn.getName()).append(": ").append(loggedIn.getId()).append("\n");

            //tạo 5 Problems
            double sum = 0.0;
            Random rand = new Random();
            ArrayList<Integer> index_store = new ArrayList();

            for (int i = 1; i <= 5; i++) {
                int index = (int) (rand.nextFloat() * arrProblem.size());
                while (index_store.contains(index)) {
                    index = (int) (rand.nextFloat() * arrProblem.size());
                }
                index_store.add(index);
                sum = sum + arrProblem.get(index).getMark_weight();
                sb.append("Problems").append(i).append(": ").append(arrProblem.get(index).getFull_des()).append("\n");
                sb.append("Mark: ").append(arrProblem.get(index).getMark_weight()).append("\n");
                sb.append("\n");

            }

            // Thêm điểm tổng
            sb.append("Total Mark: ").append(sum).append("\n");
            sb.append("==================================");

            // Ghi Contest vào Contest_Timestamp.txt
            try (FileWriter writer = new FileWriter("Contest_TimeStamp.txt");
                    BufferedWriter bw = new BufferedWriter(writer)) {

                bw.write(sb.toString());
                System.out.println("Done!");
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        }
    }
    
    private static String generateID(){

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
    
    public static void showContestInfo() {
        Scanner sc = new Scanner(System.in);
        int k = 1;        
        while (k != 0) {//chạy cho đến khi tìm thấy ContestID
            String Contest_Id = sc.nextLine();
            try {
                File contest = new File("Contest_TimeStamp.txt");
                Scanner contestReader = new Scanner(contest);
                while (contestReader.hasNextLine()) {
                    
                    String data = contestReader.nextLine();
                    if (data.startsWith("Contest: " + Contest_Id)) {// khi tìm được contest_id , thì in tiếp các dòng cho đến dòng toàn dấu = 
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
                if (i.getId().toUpperCase().equals(problem_id.toUpperCase())) {
                    Problem_index = arrProblem.indexOf(i);                    
                    return true;
                }
            }
                System.out.println("There is no Problem " + problem_id);
                System.out.println("Try another ? (0 for Back to Menu . Any number for yes)");
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
                if (i.getId().toUpperCase().equals(contestant_id.toUpperCase())) {
                    Contestant_index = arrContestant.indexOf(i);
                    return true;
                }
            }
            System.out.println("There is no Contestant " + contestant_id);
            System.out.println("Try another ? (0 for no . Any number for yes)");
            k = sc_int.nextInt();
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

    private static void editContestantProfile() {
        Scanner sc_num = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        boolean check_Contestant = searchContestant();

        if (check_Contestant) {
            System.out.println("1.Edit name.");
            System.out.println("2.Edit email.");
            System.out.println("3.Edit mobilephone.");
            System.out.println("4.Edit password.");
            System.out.println("0.Back to Menu.");
            int k = 1;
            while (k != 0) {
                System.out.println("Option :");
                k = sc_num.nextInt();
                System.out.println("\n");
                if (k == 1) {
                    System.out.println("Old name: " + arrContestant.get(Contestant_index).getName());
                    System.out.println("New name: ");
                    String name = sc.nextLine();

                    while (name.equals("")) {
                        name = sc.nextLine();
                        
                        if (name.equals("")) {
                            System.out.println("Name cannot be emty!Please type again: ");
                        }
                    }
                    arrContestant.get(Contestant_index).setName(name);
                    System.out.println("Done!");
                    k=0;
                    writeContestant();
                }if (k == 2) {
                    System.out.println("Old email: " + arrContestant.get(Contestant_index).getEmail());
                    System.out.println("New email: ");
                    String email = sc.nextLine();

                    while (email.equals("")) {
                        email = sc.nextLine();

                        if (email.equals("")) {
                            System.out.println("Email cannot be emty!Please type again: ");
                        }
                    }
                    arrContestant.get(Contestant_index).setEmail(email);
                    System.out.println("Done!");
                    k=0;
                    writeContestant();
                }if (k == 3) {
                    System.out.println("Old mobilephone: " + arrContestant.get(Contestant_index).getMobilephone());
                    System.out.println("New mobilephone: ");
                    String mobilephone = sc.nextLine();

                    while (mobilephone.equals("")) {
                        mobilephone = sc.nextLine();

                        if (mobilephone.equals("")) {
                            System.out.println("Mobilephone cannot be emty!Please type again: ");
                        }
                    
                    }
                    arrContestant.get(Contestant_index).setMobilephone(mobilephone);
                    System.out.println("Done!");
                    k=0;
                    writeContestant();
                }if (k == 4) {
                    System.out.println("Old password: " + arrContestant.get(Contestant_index).getPassword());
                    System.out.println("New password: ");
                    String password = sc.nextLine();

                    while (password.equals("")) {
                        password = sc.nextLine();

                        if (password.equals("")) {
                            System.out.println("password cannot be emty!Please type again: ");
                        }
                    }
                    arrContestant.get(Contestant_index).setPassword(password);
                    System.out.println("Done!");
                    k=0;
                    writeContestant();
                }else{
                    if(k!=0)System.out.println("Please choose from 0 to 4!");
                }
            }
        }
    }
    
}
