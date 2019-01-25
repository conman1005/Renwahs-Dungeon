/*
 * Made By: Shawn Benedict
 * Date: Nov 8, 2018
 * Made to be a person object
 */
package cullity.renwahsdungeon;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Comparator;
import javafx.geometry.Point3D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
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
    private ImagePattern imageP;
    //data stuff
    //name = 30
    //level = 4
    //inventory = 12
    //type = 2
    //coins = 4
    //highestLevel = 4
//xp=4
    private final int SIZE = 60;
    private String inventory;
    private String name;
    private int level;
    private int coins;
    private int highestLevel;
    // private double itemStatMultiplier;
    private int hitCooldown = 0;

    private int xp = 0;

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
        bHealth = 30;
        bDefense = 10;
        StringBuffer tempT = new StringBuffer("w");
        tempT.setLength(1);
        type = tempT.toString().charAt(0);
        coins = 0;
        imageP = new ImagePattern(new Image(getClass().getResource("/sprites/heroFront.png").toString()));//image of the person
        highestLevel = 1;//furthest level travelled in the dungeon
        xp = 0;
//        itemStatMultiplier = 1;
    }

    public Person(String n, int l, double s, double h, double d, char t, String i, int c, String im, int hl, double ism) {
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
        imageP = new ImagePattern(new Image(getClass().getResource("/" + im + ".png").toString()));
        highestLevel = hl;
        xp = 0;
//        itemStatMultiplier = ism;
    }

    public int getHighestLevel() {
        return highestLevel;
    }

    public void setHighestLevel(int hL) {
        highestLevel = hL;
    }

//    public void setItemStatMultiplier(int ism) {
//        itemStatMultiplier = ism;
//    }
//
//    public double getItemStatMultiplier() {
//        return itemStatMultiplier;
//    }
    public ImagePattern getImageP() {
        return imageP;
    }

    public void setImageP(String im) {
        imageP = new ImagePattern(new Image(getClass().getResource("/" + im + ".png").toString()));
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

    public void setXP(int x) {
        xp = x;
    }

    public int getXP() {
        return xp;
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
            save.writeInt(highestLevel);
            save.writeInt(xp);
            save.close();
        } catch (IOException io) {

        }
    }

    public void open(String file, int record) {
        try {
            RandomAccessFile opn = new RandomAccessFile(file, "r");
            opn.seek(record * SIZE);
            char n[] = new char[15];
            for (int i = 0; i < n.length; i++) {
                n[i] = opn.readChar();
            }
            name = new String(n);
            type = opn.readChar();
            char inve[] = new char[6];
            for (int i = 0; i < inve.length; i++) {
                inve[i] = opn.readChar();
            }
            inventory = new String(inve);
            level = opn.readInt();
            coins = opn.readInt();
            highestLevel = opn.readInt();
            xp = opn.readInt();
            opn.close();
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

    public void setHitCooldown(int cd) {
        hitCooldown = cd;
    }

    public int getHitCooldown() {
        return hitCooldown;
    }

    int wAnimation = -1;

    public void moveTown(Pane pne, String direction, Rectangle recHero, Rectangle recItem) {
        switch (direction) {
            case "up":
                recItem.setRotationAxis(new Point3D(0, 90, 1));
                recItem.setRotate(0);
                recItem.setTranslateX(0);
                recItem.setTranslateY(0);
                pne.setTranslateY(pne.getTranslateY() + 5);
                try {
                    if (MainApp.currentI.getSymbol() == "b".charAt(0)) {
                        recItem.setTranslateY(35);
                        recItem.setTranslateX(-60);
                    }
                } catch (NullPointerException e) {
                }
                wAnimation++;
                switch (wAnimation) {
                    case 0:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBackLeft.png").toString())));
                        break;
                    case 6:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBack.png").toString())));
                        break;
                    case 12:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBackRight.png").toString())));
                        break;
                    case 18:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBack.png").toString())));
                        break;
                    case 24:
                        wAnimation = -1;
                        break;
                    default:
                        break;
                }
                break;
            case "down":
                recItem.setTranslateX(-65);
                recItem.setRotationAxis(new Point3D(0, 90, 1));
                recItem.setRotate(180);
                recItem.setTranslateY(0);
                pne.setTranslateY(pne.getTranslateY() - 5);

                try {
                    if (MainApp.currentI.getSymbol() == "b".charAt(0)) {
                        recItem.setTranslateY(35);
                    }
                } catch (NullPointerException e) {
                }
                wAnimation++;
                switch (wAnimation) {
                    case 0:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroFrontLeft.png").toString())));
                        break;
                    case 6:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroFront.png").toString())));
                        break;
                    case 12:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroFrontRight.png").toString())));
                        break;
                    case 18:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroFront.png").toString())));
                        break;
                    case 24:
                        wAnimation = -1;
                        break;
                    default:
                        break;
                }
                break;
            case "left":
                recItem.setTranslateX(-65);
                recItem.setRotationAxis(new Point3D(0, 90, 1));
                recItem.setRotate(180);
                recItem.setTranslateY(0);
                pne.setTranslateX(pne.getTranslateX() + 5);
                try {
                    if (MainApp.currentI.getSymbol() == "b".charAt(0)) {
                        recItem.setTranslateY(35);
                    }
                } catch (NullPointerException e) {
                }
                wAnimation++;
                switch (wAnimation) {
                    case 0:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroLeftLeft.png").toString())));
                        break;
                    case 6:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroLeft.png").toString())));
                        break;
                    case 12:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroLeftRight.png").toString())));
                        break;
                    case 18:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroLeft.png").toString())));
                        break;
                    case 24:
                        wAnimation = -1;
                        break;
                    default:
                        break;
                }
                break;
            case "right":
                recItem.setTranslateX(0);
                recItem.setRotationAxis(new Point3D(0, 90, 1));
                recItem.setRotate(0);
                recItem.setTranslateX(0);
                recItem.setTranslateY(0);
                pne.setTranslateX(pne.getTranslateX() - 5);
                try {//tried in case there is no item in hand because then currentI is null
                    if (MainApp.currentI.getSymbol() == "b".charAt(0)) {
                        recItem.setTranslateY(35);
                    }
                } catch (Exception e) {
                }
                wAnimation++;
                switch (wAnimation) {
                    case 0:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroRightLeft.png").toString())));
                        break;
                    case 6:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroRight.png").toString())));
                        break;
                    case 12:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroRightRight.png").toString())));
                        break;
                    case 18:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroRight.png").toString())));
                        break;
                    case 24:
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

    public void moveCave(Pane pne, String direction, Rectangle recHero, Rectangle recItem) {
        switch (direction) {
            case "up":
                recItem.setRotationAxis(new Point3D(0, 90, 1));
                recItem.setRotate(0);
                recItem.setTranslateX(0);
                //recItem.setTranslateY(0);
                pne.setTranslateY(pne.getTranslateY() - 5);
                wAnimation++;
                switch (wAnimation) {
                    case 0:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBackLeft.png").toString())));
                        break;
                    case 6:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBack.png").toString())));
                        break;
                    case 12:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBackRight.png").toString())));
                        break;
                    case 18:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBack.png").toString())));
                        break;
                    case 24:
                        wAnimation = -1;
                        break;
                    default:
                        break;
                }
                break;
            case "down":
                recItem.setRotationAxis(new Point3D(0, 90, 1));
                recItem.setRotate(180);
                recItem.setTranslateX(-40);
                //recItem.setTranslateY(0);
                pne.setTranslateY(pne.getTranslateY() + 5);
                wAnimation++;
                switch (wAnimation) {
                    case 0:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroFrontLeft.png").toString())));
                        break;
                    case 6:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroFront.png").toString())));
                        break;
                    case 12:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroFrontRight.png").toString())));
                        break;
                    case 18:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroFront.png").toString())));
                        break;
                    case 24:
                        wAnimation = -1;
                        break;
                    default:
                        break;
                }
                break;
            case "left":
                recItem.setRotationAxis(new Point3D(0, 90, 1));
                recItem.setRotate(180);
                recItem.setTranslateX(-40);
                recItem.setTranslateY(0);
                pne.setTranslateX(pne.getTranslateX() - 5);
                wAnimation++;
                switch (wAnimation) {
                    case 0:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroLeftLeft.png").toString())));
                        break;
                    case 6:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroLeft.png").toString())));
                        break;
                    case 12:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroLeftRight.png").toString())));
                        break;
                    case 18:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroLeft.png").toString())));
                        break;
                    case 24:
                        wAnimation = -1;
                        break;
                    default:
                        break;
                }
                break;
            case "right":
                recItem.setRotationAxis(new Point3D(0, 90, 1));
                recItem.setRotate(0);
                recItem.setTranslateX(0);
                recItem.setTranslateY(0);
                pne.setTranslateX(pne.getTranslateX() + 5);
                wAnimation++;
                switch (wAnimation) {
                    case 0:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroRightLeft.png").toString())));
                        break;
                    case 6:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroRight.png").toString())));
                        break;
                    case 12:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroRightRight.png").toString())));
                        break;
                    case 18:
                        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroRight.png").toString())));
                        break;
                    case 24:
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
        imageP = null;//image of the person
    }

}
