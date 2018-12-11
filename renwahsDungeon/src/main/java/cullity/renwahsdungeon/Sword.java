/*
 * Made By: Shawn Benedict
 * Date: Dec 5, 2018
 * Made to be a sword
 */
package cullity.renwahsdungeon;

import javafx.scene.image.Image;

/**
 *
 * @author shawnb58
 */
public class Sword extends Item {

    private double dMultiplier;//damage multiplier
   
    private double price;

    public Sword() {
        super("sword", true, "s".charAt(0),"baseSword",10);
        dMultiplier = 2;
        
    }

    public Sword(String it, int m, char s, Image im, double p) {
        super(it, true, s,"baseSword",p);//true because sword is weapoon
        dMultiplier = m;
        

    }

    public double getDMultiplier() {
        return dMultiplier;
    }

    

    public void setDMultiplier(double m) {
        dMultiplier = m;

    }

   
    

}


