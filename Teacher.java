
import java.io.Serializable;

public class Teacher extends User  {

    public Teacher() {
        super();
    }

    public Teacher(String name, String id, String email, String mobilephone, String password) {
        super(name, id, email, mobilephone, password);
    }

    @Override
    public String toString() {
        return "Teacher:" + "\n"
                + super.toString();
    }
    
    
    
}
