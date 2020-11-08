
import java.io.Serializable;
import java.util.Random;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Problem implements Comparable<Problem>, Serializable{

    
    private String id;
    private String date;
    private String name;
    private String short_des;
    private String full_des;
    private double mark_weight;
    private String category;
    private String author;
    
    
    public Problem(){
        this.id = generateID() ;
        this.date = setDate();
        this.name = name;
        this.short_des = short_des;
        this.full_des = full_des;
        this.mark_weight = mark_weight;
        this.category = category;
        this.author = author;
        
    }
    
    public Problem(String id, String date, String name, String short_des, String full_des, double mark_weight, String category, String author) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.short_des = short_des;
        this.full_des = full_des;
        this.mark_weight = mark_weight;
        this.category = category;
        this.author = author;
       
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    private String setDate() {
        Timestamp tstamp= new Timestamp(System.currentTimeMillis());
        SimpleDateFormat simpleDate_Format = new  SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z");
        return simpleDate_Format.format(tstamp);
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

    public double getMark_weight() {
        return mark_weight;
    }

    public void setMark_weight(double mark_weight) {
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
    public String toString() {
        return "Problem: "+  name + "\n"
                + "ID: " + id + "\n"
                + "Date: " + date +"\n"
                + "Short Description: " +"\n"
                + short_des +"\n"
                + "Mark Weight: " + mark_weight +"\n "
                + "Category: " + category +"\n"
                + "Author: " + author + "\n"
                +"=================================================";
    }
    
    @Override
    public int compareTo(Problem o) {
        // So sánh Problems tăng dần theo category
        return this.getCategory().compareTo(o.getCategory());
    }
    
    private  String generateID(){
        
        
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
        return new_char+new_num;
    }
    
    
}
