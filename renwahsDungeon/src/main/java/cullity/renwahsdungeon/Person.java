/*
 * Made By: Shawn Benedict
 * Date: Nov 8, 2018
 * Made to be a person object
 */
package cullity.renwahsdungeon;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Comparator;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author shawnb58
 */
public class Person {

    public static Comparator<Person> sorter = new Comparator<Person>() {

        @Override
        public int compare(Person p1, Person p2) {
            String personName1 = p1.getName().toUpperCase();
            String personName2 = p2.getName().toUpperCase();

            return personName1.compareTo(personName2);
        }
    };

    private double bStrength;
    private double bHealth;
    //private boolean employed;
    private char type;//class
    private double bDefense;
    private Image img;
    //data stuff
    //name = 30
    //level = 4
    //inventory = 12
    //type = 2
    //coins = 4
    private final int SIZE = 52;
    private String inventory;
    private String name;
    private int level;
    private int coins;

    //person stuff
    public Person() {
        StringBuffer tempN = new StringBuffer("bob");
        tempN.setLength(15);
        name = tempN.toString();

        StringBuffer tempI = new StringBuffer("!!!!!!");
        tempI.setLength(6);
        inventory = tempI.toString();//! is nothing
        level = 1;
        bStrength = 10;
        bHealth = 100;
        bDefense = 10;
        StringBuffer tempT = new StringBuffer("w");
        tempT.setLength(1);
        type = tempT.toString().charAt(0);
        coins = 0;
        img = null;//image of the person

    }

    public Person(String n, int l, double s, double h, double d, char t, String i, int c, String im) {
        StringBuffer tempN = new StringBuffer(n);
        tempN.setLength(15);
        name = tempN.toString();
        StringBuffer tempT = new StringBuffer(t);
        tempT.setLength(1);
        type = tempT.toString().charAt(0);

        level = l;
        bStrength = s;
        bHealth = h;
        bDefense = d;

        inventory = i;
        coins = c;
        img = new Image(getClass().getResource("/" + im + ".png").toString());

    }

    public Image getImage() {
        return img;
    }

    public void setImage(String im) {
        img = new Image(getClass().getResource("/" + im + ".png").toString());
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

    public char getType() {
        return type;
    }

    public void setBStrength(double b) {
        bStrength = b;
    }

    public void setBHealth(double h) {
        bHealth = h;
    }

    public void setBDefense(double d) {
        bDefense = d;
    }

    public void setType(String t) {
        StringBuffer tempT = new StringBuffer("w");
        tempT.setLength(1);
        type = tempT.toString().charAt(0);
    }

    public void setName(String fn) {
        StringBuffer n = new StringBuffer(fn);
        n.setLength(15);
        name = n.toString();
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

    public void setCoins(int c) {
        coins = c;
    }

    public int getCoins() {
        return coins;
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
            save.writeChar(type);
            save.writeChars(inventory);
            save.writeInt(level);
            save.writeInt(coins);
            save.close();
        } catch (IOException io) {

        }
    }

    public void open(String file, int record) {
        try {
            RandomAccessFile open = new RandomAccessFile(file, "r");
            open.seek(record * SIZE);
            char n[] = new char[15];
            for (int i = 0; i < n.length; i++) {
                n[i] = open.readChar();
            }
            name = new String(n);
            type = open.readChar();
            char inve[] = new char[6];
            for (int i = 0; i < inve.length; i++) {
                inve[i] = open.readChar();
            }
            inventory = new String(inve);
            level = open.readInt();
            coins = open.readInt();
            open.close();
        } catch (IOException io) {

        }
    }

    public void delete(String file, int recordNumber) {
//move the last record from the file to the top and removes the empty space at the end

        open(file, numRecord(file) - 1);
        save(file, recordNumber);

        try {
            RandomAccessFile recordFile = new RandomAccessFile(file, "rw");
            recordFile.setLength(recordFile.length() - SIZE);
            recordFile.close();
        } catch (IOException ex) {

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


    int wAnimation = -1;

    public void moveTown(Pane pne, String direction, Rectangle recHero) {
        switch (direction) {
            case "up":
                pne.setTranslateY(pne.getTranslateY() + 1);
                wAnimation++;
                switch (wAnimation) {
                    case 0:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBackLeft.png").toString())));
                        break;
                    case 30:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBack.png").toString())));
                        break;
                    case 60:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBackRight.png").toString())));
                        break;
                    case 90:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBack.png").toString())));
                        break;
                    case 120:
                        wAnimation = -1;
                        break;
                    default:
                        break;
                }
                break;
            case "down":
                pne.setTranslateY(pne.getTranslateY() - 1);
                wAnimation++;
                switch (wAnimation) {
                    case 0:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroFrontLeft.png").toString())));
                        break;
                    case 30:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroFront.png").toString())));
                        break;
                    case 60:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroFrontRight.png").toString())));
                        break;
                    case 90:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroFront.png").toString())));
                        break;
                    case 120:
                        wAnimation = -1;
                        break;
                    default:
                        break;
                }
                break;
            case "left":
                pne.setTranslateX(pne.getTranslateX() + 1);
                wAnimation++;
                switch (wAnimation) {
                    case 0:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroLeftLeft.png").toString())));
                        break;
                    case 30:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroLeft.png").toString())));
                        break;
                    case 60:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroLeftRight.png").toString())));
                        break;
                    case 90:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroLeft.png").toString())));
                        break;
                    case 120:
                        wAnimation = -1;
                        break;
                    default:
                        break;
                }
                break;
            case "right":
                pne.setTranslateX(pne.getTranslateX() - 1);
                wAnimation++;
                switch (wAnimation) {
                    case 0:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroRightLeft.png").toString())));
                        break;
                    case 30:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroRight.png").toString())));
                        break;
                    case 60:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroRightRight.png").toString())));
                        break;
                    case 90:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroRight.png").toString())));
                        break;
                    case 120:
                        wAnimation = -1;
                        break;
                    default:
                        break;
                }
                break;
            case "u":
                recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBack.png").toString())));
                break;
            case "d":
                recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroFront.png").toString())));
                break;
            case "l":
                recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroLeft.png").toString())));
                break;
            case "r":
                recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroRight.png").toString())));
                break;
            default:
                break;
        }
    }
    
    public void moveCave(Pane pne, String direction, Rectangle recHero) {
        switch (direction) {
            case "up":
                pne.setTranslateY(pne.getTranslateY() + 1);
                wAnimation++;
                switch (wAnimation) {
                    case 0:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBackLeft.png").toString())));
                        break;
                    case 30:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBack.png").toString())));
                        break;
                    case 60:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBackRight.png").toString())));
                        break;
                    case 90:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBack.png").toString())));
                        break;
                    case 120:
                        wAnimation = -1;
                        break;
                    default:
                        break;
                }
                break;
            case "down":
                pne.setTranslateY(pne.getTranslateY() - 1);
                wAnimation++;
                switch (wAnimation) {
                    case 0:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroFrontLeft.png").toString())));
                        break;
                    case 30:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroFront.png").toString())));
                        break;
                    case 60:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroFrontRight.png").toString())));
                        break;
                    case 90:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroFront.png").toString())));
                        break;
                    case 120:
                        wAnimation = -1;
                        break;
                    default:
                        break;
                }
                break;
            case "left":
                pne.setTranslateX(pne.getTranslateX() + 1);
                wAnimation++;
                switch (wAnimation) {
                    case 0:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroLeftLeft.png").toString())));
                        break;
                    case 30:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroLeft.png").toString())));
                        break;
                    case 60:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroLeftRight.png").toString())));
                        break;
                    case 90:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroLeft.png").toString())));
                        break;
                    case 120:
                        wAnimation = -1;
                        break;
                    default:
                        break;
                }
                break;
            case "right":
                pne.setTranslateX(pne.getTranslateX() - 1);
                wAnimation++;
                switch (wAnimation) {
                    case 0:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroRightLeft.png").toString())));
                        break;
                    case 30:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroRight.png").toString())));
                        break;
                    case 60:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroRightRight.png").toString())));
                        break;
                    case 90:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroRight.png").toString())));
                        break;
                    case 120:
                        wAnimation = -1;
                        break;
                    default:
                        break;
                }
                break;
            case "u":
                recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBack.png").toString())));
                break;
            case "d":
                recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroFront.png").toString())));
                break;
            case "l":
                recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroLeft.png").toString())));
                break;
            case "r":
                recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroRight.png").toString())));
                break;
            default:
                break;
        }
    }

    public Person(String usr) {
        StringBuffer tempN = new StringBuffer(usr);
        tempN.setLength(15);
        name = tempN.toString();

        StringBuffer tempI = new StringBuffer("!!!!!!");
        tempI.setLength(6);
        inventory = tempI.toString();//! is nothing
        level = 1;
        bStrength = 10;
        bHealth = 100;
        bDefense = 10;
        StringBuffer tempT = new StringBuffer("w");
        tempT.setLength(1);
        type = tempT.toString().charAt(0);
        coins = 0;
        img = null;//image of the person
    }

}
