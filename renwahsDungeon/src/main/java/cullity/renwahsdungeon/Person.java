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

    public static Comparator<Person> lNameCompare = new Comparator<Person>() {
@Override
        public int compare(Person p1, Person p2) {
            String StudentName1 = p1.getFName().toUpperCase();
            String StudentName2 = p2.getFName().toUpperCase();

            return StudentName1.compareTo(StudentName2);
        }
    };
    
    

    private int age;
    private double height;//cm
    private boolean employed;
    private String job;
    private double weight;//lbs
    private String education;
    private String fName;   //first name
    private String lName;   //last name
    private boolean alive;

    public Person() {
        age = 20;
        height = 173;//~5'8"
        employed = true;
        job = "";
        weight = 150;
        education = "none";
        fName = "Michael";
        lName = "Smith";
        alive = true;

    }

    public Person(int ag, double h, boolean em, String j, double w, String ed, String fN, String lN, boolean al) {
        age = ag;
        height = h;
        employed = em;
        job = j;
        weight = w;
        education = ed;
        fName = fN;
        lName = lN;
        alive = al;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public boolean isEmployed() {
        return employed;
    }

    public String getJob() {
        return job;
    }

    public double getWeight() {
        return weight;
    }

    public String getEducation() {
        return education;
    }

    public String getFName() {
        return fName;
    }

    public String getLName() {
        return lName;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAge(int a) {
        age = a;
    }

    public void setHeight(double h) {
        height = h;
    }

    public void setEmployed(boolean em) {
        employed = em;
    }

    public void setJob(String j) {
        job = j;
    }

    public void setWeight(double w) {
        weight = w;
    }

    public void setEducation(String ed) {
        education = ed;
    }

    public void setFName(String fN) {
        fName = fN;
    }

    public void setLName(String lN) {
        lName = lN;
    }

    public void setAlive(boolean al) {
        alive = al;
    }

    public void ageOneYear() {
        age++;
    }

}
