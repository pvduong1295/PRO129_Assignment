
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author phamv
 */
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
