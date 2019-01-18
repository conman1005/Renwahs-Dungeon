
/*
 * Made By: Shawn Benedict
 * Date: Dec 7, 2018
 * Made to
 */
package cullity.renwahsdungeon;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
//after killing creature, update stats and then save

/**
 * FXML Controller class
 *
 * @author shawnb58
 */
public class CaveController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Rectangle recC1;//rec slots in cave controller (C)
    @FXML
    private Rectangle recC2;
    @FXML
    private Rectangle recC3;
    @FXML
    private Rectangle recC4;
    @FXML
    private Rectangle recC5;
    @FXML
    private Rectangle recC6;

    @FXML
    private Rectangle recHero;
    @FXML
    private Rectangle recEnemy;
    @FXML
    private AnchorPane ancCave;
    @FXML
    private Rectangle recCI;//rec that shows the item in the hand of the person in the cave scene
    
    Enemy enemy = new Enemy();

//set currentE before coming to this screen when attacked by an enemy
    Timeline cool = new Timeline(new KeyFrame(Duration.millis(1000), ae -> cooldown()));//cooldown between user attack
    Timeline eAttack = new Timeline(new KeyFrame(Duration.millis(100), ae -> enemyAtt()));//enemy attacking 
    boolean canAttack;//if weapon on cooldown then false
    int rand;

    @FXML
    private void cooldown() {
//cooldown boolean

//check if enemy is dead
    }

    @FXML
    private void keypress(KeyEvent ke) {
        keyStuff temp = new keyStuff();// this is because the pause button is in the global method
        temp.keys(ke, false, ancCave,recCI);//false because it is not in town scene (pausing button)

        if (ke.getSource() == KeyCode.E && MainApp.currentI.isWeapon() && MainApp.fighting && canAttack) {
            //attack
            if (MainApp.currentI.getItemName().equals("s")) {//if sword
                rand = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                double damage;
                if (rand < MainApp.currentP.getLevel()) {//if # is less than their level they do a crit
                    //crit
                    damage = MainApp.currentP.getBStrength() * (MainApp.currentP.getLevel() / 10 + 1) * 1.5;
                } else {
                    //normal attack
                    damage = MainApp.currentP.getBStrength() * (MainApp.currentP.getLevel() / 10 + 1);
                }
                MainApp.currentE.setHealth(MainApp.currentE.getHealth() - damage);
                if (MainApp.currentE.getHealth() <= 0) {
                    //dead

                    //  ends with destroying enemy
                    MainApp.fighting = false;
                    MainApp.currentE = null;

                } else {//still alive
                    //set enemy progress bar
                }
            }
        } 
    }

    private void enemyAtt() {
        if (MainApp.paused) {//dont need if can disable the current root
            return;
        }
        rand = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        double eDamage;//enemy damage
        if (rand == 7) {
            //enemy crit attacks
            eDamage = MainApp.currentE.getStrength() * 1.5;

        } else if (8 < rand && rand < 13) {
            //enemy normal attack
            eDamage = MainApp.currentE.getStrength();

        } else {//not attacking
            return;
        }
        if (MainApp.currentHealth < eDamage) {
//user is dead

//ends with
            MainApp.fighting = false;
            MainApp.currentE = null;
        } else {
            //still alive
            MainApp.currentHealth -= eDamage;
//change progress bar
        }

    }

    @FXML
    private void scrollItem(ScrollEvent s) {//nextItem //put in anchorpane !!!!!!!!!!!!!!!!!!
        MainApp.scrollI(s);
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainApp.slot.clear();
        MainApp.slot.add(recC1);
        MainApp.slot.add(recC2);
        MainApp.slot.add(recC3);
        MainApp.slot.add(recC4);
        MainApp.slot.add(recC5);
        MainApp.slot.add(recC6);
        MainApp.recItem = recCI;
        MainApp.showItems();
        //  recUser.setFill(new ImagePattern(MainApp.currentP.getImage()));
        //    recEnemy.setFill(new ImagePattern(MainApp.currentE.getImage()));
        MainApp.fighting = true;
        cool.setCycleCount(1);
        //MainApp.currentA = ancCave;
        
        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroFront.png").toString())));
        
        recEnemy.setFill(MainApp.currentE.getFill());
        recEnemy.setHeight(MainApp.currentE.getHeight() * 3);
        recEnemy.setWidth(MainApp.currentE.getWidth() * 3);
    }

}
