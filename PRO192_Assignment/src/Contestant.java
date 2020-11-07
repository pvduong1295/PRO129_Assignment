
import java.io.Serializable;
import java.util.Random;


public class Contestant implements Serializable {
    private String name;
    private String id;
    private String email;
    private String mobilephone;
    private int rank;
    private String password;
    // Khai báo có biến và không có biến
    public Contestant(){
        this.name = "";
        this.id = generateID();
        this.email = "";
        this.mobilephone = "";
        this.rank = 0;
        this.password = "123456";
    }

    public Contestant(String name, String id, String email, String mobilephone, int rank, String password) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.mobilephone = mobilephone;
        this.rank = rank;
        this.password = password;
    }
    
    // thêm getter , setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    // Thêm hàm toString để hiện thông tin Problem
    @Override
    public String toString() {
        return   "name=" + name + ", id=" + id + ", email=" + email + ", mobilephone=" + mobilephone + ", rank=" + rank ;
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
