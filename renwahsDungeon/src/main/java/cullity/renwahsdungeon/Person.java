/*
 * Made By: Shawn Benedict
 * Date: Nov 8, 2018
 * Made to be a person object
 */
package cullity.renwahsdungeon;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author shawnb58
 */
public class Person {

//    public static Comparator<Person> lNameCompare = new Comparator<Person>() {
//        @Override
//        public int compare(Person p1, Person p2) {
//            String StudentName1 = p1.getFName().toUpperCase();
//            String StudentName2 = p2.getFName().toUpperCase();
//
//            return StudentName1.compareTo(StudentName2);
//        }
//    };

    private double bStrength;
    private double bHealth;
    //private boolean employed;
    private String type;//class
    private double bDefense;
    
    
    
    //data stuff
       //name = 30
    //level = 4
    //inventory = 12
    private final int SIZE = 46;
    private String inventory;
    private String name;
    private int level;
    
    public void setName(String fn) {
        StringBuffer buff = new StringBuffer(fn);
        buff.setLength(15);
        name = buff.toString();
    }

    public String getName() {
        return name.trim();
    }
    
    public void setLevel(int lvl) {
        level = lvl;
    }

    public int getLevel() {
        return level;
    }
    
    public void setInventory(String i) {
        StringBuffer buff = new StringBuffer(i);
        buff.setLength(6);
        inventory = buff.toString();
    }

    public String getInventory() {
        return inventory.trim();
    }
    
    public void save(String file, int record) {
        try {
            RandomAccessFile save = new RandomAccessFile(file, "rw");
            save.seek(record * SIZE);
            save.writeChars(name);
            save.writeInt(level);
            save.writeChars(inventory);

            save.close();
        } catch (IOException io) {

        }
    }
    
    public void open(String file, int record) {
        try {
            RandomAccessFile open = new RandomAccessFile(file, "r");
            open.seek(record * SIZE);
            char first[] = new char[15];
            for (int i = 0; i < first.length; i++) {
                first[i] = open.readChar();
            }
            name = new String(first);

            level = open.readInt();
            
            char inve[] = new char[6];
            for (int i = 0; i < inve.length; i++) {
                inve[i] = open.readChar();
            }
            inventory = new String(inve);

            open.close();
        } catch (IOException io) {

        }
    }
    
    public int numRecord(String file) {
        int numR = 0;
        try {
            RandomAccessFile recordFile = new RandomAccessFile(file, "r");
            numR = (int) (recordFile.length() / SIZE);

        } catch (Exception ex) {
        }
        return numR;
    }
    
    
//person stuff
    public Person() {
        name="bob";
        level=1;
        inventory="!!!!!!";//! is nothing
        bStrength = 10;
        bHealth = 100;
        bDefense = 10;
        type = "warrior";
       

    }

    public Person(String n, int l,double s,double h, double d, String t,String i) {
        name=n;
        level=l;
        bStrength = s;
        bHealth = h;
        bDefense = d;
        type = t; 
        inventory=i;
    }

    public double getBStrength() {
        return bStrength;
    }

    public double getBHealth() {
        return bHealth;
    }

    public double getBDefense() {
        return bDefense;
    }

    public String getType() {
        return type;
    }
    
    public void setBStrength(double b) {
        bStrength=b;
    }

    public void setBHealth(double h) {
        bHealth = h;
    }

    public void setBDefense(double d) {
        bDefense=d;
    }

    public void setType(String t) {
        type = t;
    }

    
}
