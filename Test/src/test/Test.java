/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
//import java.util.TreeSet;
//import java.util.Random;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


class Ts implements Comparable<Ts> , Serializable {
    private String Name;
    private int Age;
    private Ts(){
        Name = "Noname";
        Age = 0;
    }
    public Ts(String Name, int Age){
        this.Name = Name;
        this.Age = Age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    @Override
    public int compareTo(Ts o) {
       int l1 = this.Name.length(); 
       int l2 = o.Name.length(); 
       int lmin = Math.min(l1, l2); 
  
       for (int i = 0; i < lmin; i++) { 
            int str1_ch = (int)this.Name.charAt(i); 
            int str2_ch = (int)o.Name.charAt(i); 
  
            if (str1_ch != str2_ch) { 
                return str1_ch - str2_ch; 
            } 
        }
       return 0;
    }

    @Override
    public String toString() {
        return "Ts{" + "Name=" + Name + ", Age=" + Age + '}';
    }
    
   
}

public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws FileNotFoundException, IOException {
        List<Ts> arrTs = new ArrayList<Ts>();
        Ts ts = new Ts("Duong", 20);
        Ts ts1 = new Ts("Dáda", 3);
        Ts ts2 = new Ts("12asd", 24);
        Ts ts3 = new Ts("dfsadf", 25);
        Ts ts4 = new Ts("bdfdf", 14);
        arrTs.add(ts);
        arrTs.add(ts1);
        arrTs.add(ts2);
        arrTs.add(ts3);
        arrTs.add(ts4);
        try {
            FileOutputStream writeData = new FileOutputStream("QB.dat");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(arrTs);
            writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
// Read the content from file
        try {
            FileInputStream readData = new FileInputStream("QB.dat");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            ArrayList<Ts> people2 = (ArrayList<Ts>) readStream.readObject();
            readStream.close();
            for(Ts i : people2){
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
  
}
}
    

