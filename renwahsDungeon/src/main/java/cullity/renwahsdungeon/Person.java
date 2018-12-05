/*
 * Made By: Shawn Benedict
 * Date: Nov 8, 2018
 * Made to be a person object
 */
package cullity.renwahsdungeon;

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
    

    public Person() {
        bStrength = 10;
        bHealth = 100;
        bDefense = 10;
        type = "warrior";
       

    }

    public Person(double s,double h, double d, String t) {
        bStrength = s;
        bHealth = h;
        bDefense = d;
        type = t; 
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
