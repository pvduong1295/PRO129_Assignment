
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

    public ArrayList<Problem> getArrProblem() {
        return arrProblem;
    }

    public ArrayList<Teacher> getArrTeacher() {
        return arrTeacher;
    }

    public int getLoggedInpos() {
        return LoggedInpos;
    }

    public void setLoggedInpos(int LoggedInpos) {
        this.LoggedInpos = LoggedInpos;
    }

    public void addProblem() {
        Scanner sc = new Scanner(System.in);
        Scanner sc_double = new Scanner(System.in);
        Problem newProblem = new Problem();
        System.out.println("ID of new Problem : " + newProblem.getId());
        System.out.println("Please complete the Problem.");

        System.out.println("Problem's name: ");//imput Problem name
        String name = "";
        while (name.equals("")) {
            name = sc.nextLine();
            if (name.equals("")) {
                System.out.println("Name cannot be emty!Please type again: ");
            }
        }
        newProblem.setName(name);
        //===================================================
        String category = "";//input category
        //vong while chay de yeu cau nhap dung the loai toan 
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
        while (short_des.equals("")) {
            short_des = sc.nextLine();
            if (short_des.equals("")) {
                System.out.println("Short description cannot be emty!Please type again: ");
            }
        }
        newProblem.setShort_des(short_des);
        //======================================================
        System.out.println("Full description :(Press Enter and Type $D to end the input)");//nhập full description
        // Use StringBuilder to build full long text
        StringBuilder full_des = new StringBuilder();
        String line = "";
        String temp_text = "";
        int k = 1;
        while ((!"$D".equals(line)) && (k == 1)) { // Vòng while lặp cho đến khi gõ $D . Nếu full_des chỉ chứa dấu cách hoặc không có gì thì lặp lại vòng while
            line = sc.nextLine();
            temp_text = line;
            if ((!"$D".equals(line)) && (false == temp_text.trim().isEmpty())) {// khi line khác $D và line bỏ hết các dấu cách và xuống dòng thì không rỗng 
                //thì add line vào StringBuilder.
                full_des.append(line).append("\n");
            } else if ("$D".equals(line)) {
                if (full_des.toString().isEmpty() || full_des.toString() == null) {
                    k = 1;
                    System.out.println("============================================");
                    System.out.println("Full description cannot be Empty. Type again:");
                    line = "";
                } else {
                    k = 0;
                }
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
            } catch (Exception e) {
                System.out.println("Mark should be number from 0.0 to 3.0");
                Mark = 4.0;
            }
        }
        newProblem.setMark_weight(Mark);
        //=========================================================
        newProblem.setAuthor(arrTeacher.get(LoggedInpos).getName() + " : " + arrTeacher.get(LoggedInpos).getId());
        //=========================================================
        arrProblem.add(newProblem);
        writeProblem();
        System.out.println("Done!");
        System.out.println("====================================================");

    }

    public void updateProblem() {
        // Tìm Problem theo ID
        Scanner sc_num = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        Integer Problem_index = searchProblem();

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
                k = 0;
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
                k = 0;
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
                k = 0;
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
                k = 0;
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
                k = 0;
                writeProblem();
            } else {
                if (k != 0) {
                    System.out.println("Please choose from 0 to 5!");
                }
            }

        }
        System.out.println("====================================================");
    }

    public Integer searchProblem() {
        Scanner sc = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        Integer index = null;
        int k = 1;
        while (k != 0) {
            System.out.println("Problem ID: ");
            String problem_id = sc.nextLine();
            for (Problem i : arrProblem) {
                if (i.getId().toUpperCase().equals(problem_id.toUpperCase())) {
                    index = arrProblem.indexOf(i);
                    return index;
                }
            }
            System.out.println("There is no Problem " + problem_id);
            System.out.println("Try another ? (0 for Back to Menu . Any number for yes)");
            k = sc_int.nextInt();

        }
        System.out.println("====================================================");
        return index;
    }

    public void writeProblem() {
        try {
            FileOutputStream writeData = new FileOutputStream("QBs.dat");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(arrProblem);
            writeData.close();
            writeStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAllProblem() {
        loadProblem();
        ArrayList<Problem> sorted_Problem = new ArrayList();
        sorted_Problem.addAll(arrProblem);
        Collections.sort(sorted_Problem);
        for (Problem i : sorted_Problem) {
            System.out.println(i.toString());
        }
    }

    private boolean searchTeacher() {
        Scanner sc = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        int index = 0;
        int k = 1;
        while (k != 0) {
            System.out.println("Teacher ID: ");
            String teacher_id = sc.nextLine();
            for (Teacher i : arrTeacher) {
                if (i.getId().toUpperCase().equals(teacher_id.toUpperCase())) {
                    setLoggedInpos(arrTeacher.indexOf(i));
                    return true;
                }
            }
            System.out.println("There is no macth ID " + teacher_id);
            System.out.println("Try another ? (0 for no . Any number for yes)");
            k = sc_int.nextInt();
        }
        System.out.println("====================================================");
        return false;
    }

    public void signIn() {
        Scanner sc = new Scanner(System.in);
        Scanner sc_int = new Scanner(System.in);
        Teacher newTeacher = new Teacher();
        int k = 1;
        while (k != 0) {
            System.out.println("Sign In");
            //============================
            System.out.println("Your auto generated ID: " + newTeacher.getId());
            String name = "";
            while ("".equals(name)) {
                System.out.println("Name:");
                name = sc.nextLine();
                newTeacher.setName(name);
                if ("".equals(name)) {
                    System.out.println("Name cannot be emty . Please type your name again!: ");
                }
            }
            //=====================
            String email = "";
            while ("".equals(email)) {
                System.out.println("Email:");
                email = sc.nextLine();
                newTeacher.setEmail(email);
                if ("".equals(email)) {
                    System.out.println("Email cannot be emty . Please type your Email again!: ");
                }
            }
            //=====================
            String mobilephone = "";
            while ("".equals(mobilephone)) {
                System.out.println("Mobilephone:");
                mobilephone = sc.nextLine();
                newTeacher.setMobilephone(mobilephone);
                if ("".equals(mobilephone)) {
                    System.out.println("Mobilephone cannot be emty . Please type your Mobilephone again!: ");
                }
            }
            //======================
            String password = "";
            while ("".equals(password)) {
                System.out.println("Password:");
                password = sc.nextLine();
                newTeacher.setPassword(password);
                if ("".equals(password)) {
                    System.out.println("Password cannot be emty . Please type your Password again!: ");
                }
            }

            System.out.println("Do you want to sign these in ?(y/n)");
            String ans = sc.nextLine();
            if (ans.equals("y")) {
                arrTeacher.add(newTeacher);
                writeTeacher();
                System.out.println("Done!");
                k = 0;
            } else {
                System.out.println("Do you want to sign in another or back to Menu ?");
                System.out.println("0.Back to Menu");
                System.out.println("1.Sign another");
                k = sc_int.nextInt();
            }
        }

        System.out.println("====================================================");
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

    public void showContestInfo() {
        Scanner sc = new Scanner(System.in);
        int k = 1;
        while (k != 0) {//chạy cho đến khi tìm thấy ContestID
            System.out.println("Contest ID:");
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
