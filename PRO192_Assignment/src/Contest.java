
import java.util.Random;


public class Contest {
    //Contest có các instances là String ContestID ,String ID Contestant đã tạo đề , Array chứa 5 Problem , Double Total( tổng điểm 5 câu)
    private String ContestID;
    private String ContestantID;
    private Problem[] arrProblem ;
    private double Total;
    
    public Contest(){
        ContestID = generateID();
        ContestantID = "";
        arrProblem = generateProblems();
        Total = calcTotal();
    }
    
    public Contest(String ContestID, String ContestantID, Problem[] arrProblem, double Total) {
        this.ContestID = ContestID;
        this.ContestantID = ContestantID;
        this.arrProblem = arrProblem;
        this.Total = Total;
    }

    public String getContestID() {
        return ContestID;
    }

    public String getContestantID() {
        return ContestantID;
    }

    public void setContestantID(String ContestantID) {
        this.ContestantID = ContestantID;
    }

    public Problem[] getArrProblem() {
        return arrProblem;
    }

    public double getTotal() {
        return Total;
    }
    
    private Problem[] generateProblems(){
        throw new UnsupportedOperationException("Chưa làm");
    }
    
    private double calcTotal(){
        throw new UnsupportedOperationException("Chưa làm");
    }
    
    private  String generateID(){
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
}
