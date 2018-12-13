
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
    private Rectangle recUser;
    @FXML
    private Rectangle recEnemy;
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
        } else if (ke.getSource() == KeyCode.Q) {
            //use item like a potion  special ability for weapons
            if (MainApp.currentI.getItemName().equals("h")) {//if health potion
                if (MainApp.currentHealth + (((HPotion) MainApp.currentI).getExtraHealth()) < (MainApp.currentP.getBHealth() * (MainApp.currentP.getLevel() / 10 + 1))) {//if current health + health potion is less than max health then add it normally
                    MainApp.currentHealth += ((HPotion) MainApp.currentI).getExtraHealth();
                } else {//current health + health potion is higher than full health so just make it full health
                    MainApp.currentHealth = MainApp.currentP.getBHealth() * (MainApp.currentP.getLevel() / 10 + 1);
                    
                }
            }
            //set user progress bar

        }
    }

    private void enemyAtt() {
        rand = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        if (rand == 7) {
            //enemy crit attacks
        } else if (8 < rand && rand < 13) {
            //enemy normal attack
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
        MainApp.showItems();
        recUser.setFill(new ImagePattern(MainApp.currentP.getImage()));
        recEnemy.setFill(new ImagePattern(MainApp.currentE.getImage()));
        MainApp.fighting = true;
        cool.setCycleCount(1);

    }

}
