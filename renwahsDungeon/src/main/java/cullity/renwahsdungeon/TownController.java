package cullity.renwahsdungeon;

/*
 * Made By: Shawn Benedict
 * Date: Dec 4, 2018
 * Made to
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author shawnb58
 */
public class TownController implements Initializable {
    
    @FXML
    private Pane pneTown;
    
    String direction = "";
    
    Timeline move = new Timeline(new KeyFrame(Duration.millis(5), ae -> movement()));

    @FXML
    private void keyPressed (KeyEvent event) {
        if (null != event.getCode()) switch (event.getCode()) {
            case W:
                direction = "up";
                break;
            case A:
                direction = "left";
                break;
            case S:
                direction = "down";
                break;
            case D:
                direction = "right";
                break;
            default:
                break;
        }
    }
    @FXML
    private void keyReleased (KeyEvent event) {
        if ((event.getCode() == KeyCode.W) || (event.getCode() == KeyCode.A) || (event.getCode() == KeyCode.S) || (event.getCode() == KeyCode.D)) {
            direction = "";
        }
    }
    
    private void movement() {
        switch (direction) {
            case "up":
                pneTown.setTranslateY(pneTown.getTranslateY() + 1);
                break;
            case "down":
                pneTown.setTranslateY(pneTown.getTranslateY() - 1);
                break;
            case "left":
                pneTown.setTranslateY(pneTown.getTranslateX() + 1);
                break;
            case "right":
                pneTown.setTranslateY(pneTown.getTranslateX() - 1);
                break;
            default:
                break;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        move.play();
    }    
    
}
