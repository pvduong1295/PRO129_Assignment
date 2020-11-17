

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        TeachersSystem T_system = new TeachersSystem();
        ContestantsSystem C_system = new ContestantsSystem();
        Scanner sc = new Scanner(System.in);
        System.out.println("Contest Management System (CMS)");
        System.out.println("Log in as:");
        System.out.println("1.Contestant");
        System.out.println("2.Teacher");
        int k = 1;
        System.out.println("====================================================");
        while (k != 0) {

            int person = 0;
            person = sc.nextInt();
            if (person == 1) {
                int l = 1;
                while (l != 0) {
                    boolean check_Member = C_system.ContestantLogin();
                    if (check_Member == true) {
                        System.out.println("====================================================");
                        C_system.mainMenuofContestant();
                    } else {
                        System.out.println("Do you have an Account?(y/n)");
                        sc.nextLine();
                        String yon = sc.nextLine();
                        if (yon.equals("n")) {
                            C_system.signIn();
                        }
                        System.out.println("====================================================");
                    }
                }
            } else if (person == 2) {
                 int n = 1;
                while (n != 0) {
                    boolean check_Member = T_system.TeacherLogin();
                    if (check_Member == true) {
                        System.out.println("====================================================");
                        T_system.mainMenuofTeacher();
                    } else {
                        System.out.println("Do you have an Account?(y/n)");
                        sc.nextLine();
                        String yon = sc.nextLine();
                        if (yon.equals("n")) {
                            T_system.signIn();
                        }
                        System.out.println("====================================================");
                    }
                }
            }
        }

    }

}
