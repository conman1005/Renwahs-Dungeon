/*
 * Made By: Conner Cullity
 * Date: 
 * Description: 
 */
package cullity.renwahsdungeon;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Conner
 */
public class CavePathController implements Initializable {
    
    @FXML
    private Polygon plyWall1;

    @FXML
    private Polygon plyWall2;

    @FXML
    private Polygon plyWall3;

    @FXML
    private Polygon plyWall4;

    @FXML
    private Polygon plyWall5;

    @FXML
    private Polygon plyWall6;

    @FXML
    private Polygon plyWall7;

    @FXML
    private Polygon plyExit;

    @FXML
    private Polygon plyStairs;
    
    @FXML
    private Polygon plyHero;
    
    @FXML
    private Rectangle recHero;
    
    Polygon ply[] = new Polygon[8];
    
    String direction = "";
    
    Person psn = new Person();
    
    Timeline move = new Timeline(new KeyFrame(Duration.millis(5), ae -> movement()));
    
    @FXML
    private void keyPressed(KeyEvent event) {
        keyStuff temp = new keyStuff();
        temp.keys(event);// this is because the pause button is in the global method

        if (null != event.getCode()) {
            switch (event.getCode()) {
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
    }

    @FXML
    private void keyReleased(KeyEvent event) {
        if (null != event.getCode()) {
            switch (event.getCode()) {
                case W:
                    direction = "u";
                    break;
                case A:
                    direction = "l";
                    break;
                case S:
                    direction = "d";
                    break;
                case D:
                    direction = "r";
                    break;
                default:
                    break;
            }
        }
        kEvent = event;
    }
    KeyEvent kEvent;
    /*private void movement() {
        psn.move(pneTown, direction, recHero);
        for (Polygon i : ply) {
            if (checkCol(plyHero, i)) {
                if ((direction.equals("up")) || (direction.equals("u"))) {
                    pneTown.setTranslateY(pneTown.getTranslateY() - 1);
                } else if ((direction.equals("down")) || (direction.equals("d"))) {
                    pneTown.setTranslateY(pneTown.getTranslateY() + 1);
                } else if ((direction.equals("left")) || (direction.equals("l"))) {
                    pneTown.setTranslateX(pneTown.getTranslateX() - 1);
                } else if ((direction.equals("right")) || (direction.equals("r"))) {
                    pneTown.setTranslateX(pneTown.getTranslateX() + 1);
                }
            }
            if (checkCol(plyHero, plyPath)) {
                move.stop();
                try {
                    Parent town_parent = FXMLLoader.load(getClass().getResource("/fxml/cavePath.fxml")); //where FXMLPage2 is the name of the scene

                    Scene town_scene = new Scene(town_parent);
                    MainApp.currentS = town_scene;
                    //get reference to the stage
                    Stage stage = (Stage) ((Node) kEvent.getSource()).getScene().getWindow();

                    stage.hide(); //optional
                    town_scene.getRoot().requestFocus();
                    stage.setScene(town_scene); //puts the new scence in the stage

                    //     stage.setTitle("Town"); //changes the title
                    stage.show(); //shows the new page
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }*/
    
    private boolean checkCol(Shape obj1, Shape obj2) {
        Shape intersect = Shape.intersect(obj1, obj2);
        return intersect.getBoundsInParent().getWidth() > 0;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBack.png").toString())));
        
        ply[0] = plyWall1;
        ply[1] = plyWall2;
        ply[2] = plyWall3;
        ply[3] = plyWall4;
        ply[4] = plyWall5;
        ply[5] = plyWall6;
        ply[6] = plyWall7;
    }    
    
}
