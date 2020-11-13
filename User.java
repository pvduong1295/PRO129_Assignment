
import java.io.Serializable;
import java.util.Random;


public class User implements Serializable{// Class cha Serialize thì subclass tự động serialize
    private String name;
    private String id;
    private String email;
    private String mobilephone;
    private String password;
    
    public User(){
        name = "Noname";
        id = generateID();
        email = "NoEmail";
        mobilephone = "NoNumber";
        password = "123456789";
    }

    public User(String name, String id, String email, String mobilephone, String password) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.mobilephone = mobilephone;
        this.password = password;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Name:" + name + "\n"
                +"ID: " + id + "\n" 
                +"Email: "+ email +"\n"
                +"Mobilephone: " + mobilephone ;
    }
    
    
    
    private  String generateID(){
        
        
        Random rand = new Random();
        String Num = "1234567890";
        StringBuilder text = new StringBuilder();
        
        String Char = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //StringBuilder char_text = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            int index = (int) (rand.nextFloat() * Char.length());
            text.append(Char.charAt(index));
        }        
        //===============================
        for (int i = 0; i < 4; i++) {
            int index = (int) (rand.nextFloat() * Num.length());
            text.append(Num.charAt(index));
        }
        //String new_num = text.toString();
        
        String new_char = text.toString();
        return new_char;
    }
}
