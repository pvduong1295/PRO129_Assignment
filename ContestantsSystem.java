
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

    public int getLoggedInpos() {
        return LoggedInpos;
    }

    public void setLoggedInpos(int LoggedInpos) {
        this.LoggedInpos = LoggedInpos;
    }

    public ArrayList<Contestant> getArrContestant() {
        return arrContestant;
    }

    private void editContestantProfile() {
        Scanner sc_num = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        Integer Contestant_index = searchContestant();
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
                k = 0;
                writeContestant();
            }
            if (k == 2) {
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
                k = 0;
                writeContestant();
            }
            if (k == 3) {
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
                k = 0;
                writeContestant();
            }
            if (k == 4) {
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
                k = 0;
                writeContestant();
            } else {
                if (k != 0) {
                    System.out.println("Please choose from 0 to 4!");
                }
            }

        }
    }

    public void generateContest() {
        loadProblems();//Cập nhật QuestionBank mới nhất 
        String contest_Id = generateID();
        if (arrProblem.size() < 5) {
            System.out.println("There are only " + arrProblem.size() + " Problems . These are not enough to generate a Contest");
            System.out.println("====================================================");

        } else {
            StringBuilder sb = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader("Contest_TimeStamp.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } catch (IOException ex) {
                
            }

            // thêm ContestID
            sb.append("Contest: ").append(contest_Id).append("\n");

            //thêm thời gian tạo đề
            Timestamp tstamp = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat simpleDate_Format = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z");
            String date = simpleDate_Format.format(tstamp);
            sb.append("Date: ").append(date).append("\n");

            //thêm người tạo đề
            sb.append("Created by: ").append(arrContestant.get(LoggedInpos).getName()).append(": ").append(arrContestant.get(LoggedInpos).getId()).append("\n");

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
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        }
        showContestInfo(contest_Id);

    }

    private Integer searchContestant() {
        Scanner sc = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        Integer index = null;
        int k = 1;
        while (k != 0) {
            System.out.println("Contestant ID: ");
            String contestant_id = sc.nextLine();
            for (Contestant i : arrContestant) {
                if (i.getId().toUpperCase().equals(contestant_id.toUpperCase())) {                    
                    index = arrContestant.indexOf(i);
                    return index ;
                }
            }
            System.out.println("There is no macth ID " + contestant_id);
            System.out.println("Try another ? (0 for no . Any number for yes)");
            k = sc_int.nextInt();
        }
        System.out.println("====================================================");
        return index;
    }

    public void writeContestant() {
        try {
            FileOutputStream writeData = new FileOutputStream("contestants.dat");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(arrContestant);
            writeData.close();
            writeStream.close();
            System.out.println("Done!");

        } catch (IOException e) {
            e.printStackTrace();
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

    public boolean ContestantLogin() {
        Scanner sc = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        System.out.println("LOG IN");
        System.out.println("For tester: id(YT7711), password(deptrai)");
        Integer check_Contestant = searchContestant();
        if (check_Contestant != null) {
            setLoggedInpos(check_Contestant);
            int k = 1;
            while (k != 0) {
                String password = "";
                System.out.println("Password: (Type (0) to Exit programm)");
                password = sc.nextLine();
                if (password.compareTo(getArrContestant().get(getLoggedInpos()).getPassword()) == 0) {
                    return true;
                } else if(password.equals("(0)")){
                      System.exit(0);  
                }else{
                    System.out.println("Wrong Password!Try again");
                }
            }
        } else{
            System.out.println("Wrong account!");
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

    public void showContestInfo(String Contest_Id) {

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

        } catch (IOException ex) {
            System.out.println("Invalid!");

        }

        System.out.println("====================================================");
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
}
