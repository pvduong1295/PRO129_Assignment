
import java.io.Serializable;
import java.util.Random;


public class Contestant extends User  {
    
    private int rank;
    
    public Contestant() {
        super();
        this.rank = 0;
    }

    public Contestant(int rank, String name, String id, String email, String mobilephone, String password) {
        super(name, id, email, mobilephone, password);
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Contestant:"
                + super.toString()+"\n"
                + "rank=" + rank + '}';
    }
    
    
    
        
}
