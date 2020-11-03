
import java.util.Random;


public class Problem implements Comparable<Problem>{
    private String id;
    private String date;
    private String name;
    private String short_des;
    private String full_des;
    private String mark_weight;
    private String category;
    private String author;
    private String password;
    
    public Problem(){
        this.id = generateID();
        this.date = date;
        this.name = name;
        this.short_des = short_des;
        this.full_des = full_des;
        this.mark_weight = mark_weight;
        this.category = category;
        this.author = author;
        this.password = password;
    }
    
    public Problem(String id, String date, String name, String short_des, String full_des, String mark_weight, String category, String author, String password) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.short_des = short_des;
        this.full_des = full_des;
        this.mark_weight = mark_weight;
        this.category = category;
        this.author = author;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_des() {
        return short_des;
    }

    public void setShort_des(String short_des) {
        this.short_des = short_des;
    }

    public String getFull_des() {
        return full_des;
    }

    public void setFull_des(String full_des) {
        this.full_des = full_des;
    }

    public String getMark_weight() {
        return mark_weight;
    }

    public void setMark_weight(String mark_weight) {
        this.mark_weight = mark_weight;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    
    @Override
    public int compareTo(Problem o) {
        // So sánh Problems tăng dần theo category
        return this.getCategory().compareTo(o.getCategory());
    }
    
    private String generateCategory(){
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
