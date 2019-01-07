/*
 * Made By: Conner Cullity
 * Date:
 * Description:
 */
package cullity.renwahsdungeon;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import static javafx.animation.Animation.INDEFINITE;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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

    @FXML
    private Pane pneHero;

    @FXML
    private AnchorPane ancCavePath;
    @FXML
    private Rectangle recCPI;//rec that shows the item in the hand of the person in the cavePath scene

    Polygon ply[] = new Polygon[7];

    ArrayList<Enemy> enemies = new ArrayList();

    String direction = "";

    Person psn = new Person();
    Enemy enm = new Enemy();

    Timeline move = new Timeline(new KeyFrame(Duration.millis(7), ae -> movement()));

    Random rand = new Random();

    @FXML
    private void keyPressed(KeyEvent event) {
        keyStuff temp = new keyStuff();
        temp.keys(event, false, ancCavePath);// this is because the pause button is in the global method//false means not in town

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

    private void movement() {
        psn.moveCave(pneHero, direction, recHero);
        for (Polygon i : ply) {
            if (checkCol(plyHero, i)) {
                if ((direction.equals("up")) || (direction.equals("u"))) {
                    pneHero.setTranslateY(pneHero.getTranslateY() + 1);
                } else if ((direction.equals("down")) || (direction.equals("d"))) {
                    pneHero.setTranslateY(pneHero.getTranslateY() - 1);
                } else if ((direction.equals("left")) || (direction.equals("l"))) {
                    pneHero.setTranslateX(pneHero.getTranslateX() + 1);
                } else if ((direction.equals("right")) || (direction.equals("r"))) {
                    pneHero.setTranslateX(pneHero.getTranslateX() - 1);
                }
            }
            for (int em = 0; em < enemies.size(); em++) {
                switch (enemies.get(em).getDirection()) {
                    case "up":
                        enemies.get(em).setTranslateY(enemies.get(em).getTranslateY() - 1);
                        break;
                    case "down":
                        enemies.get(em).setTranslateY(enemies.get(em).getTranslateY() + 1);
                        break;
                    case "left":
                        enemies.get(em).setTranslateX(enemies.get(em).getTranslateX() - 1);
                        break;
                    case "right":
                        enemies.get(em).setTranslateX(enemies.get(em).getTranslateX() + 1);
                        break;
                }
            }
        }

        for (int e = 0; e < enemies.size(); e++) {
            for (Polygon ply1 : ply) {
                if (checkCol(enemies.get(e), ply1)) {
                    switch (enemies.get(e).getDirection()) {
                        case "up":
                            enemies.get(e).setTranslateY(enemies.get(e).getTranslateY() + 1);
                            break;
                        case "down":
                            enemies.get(e).setTranslateY(enemies.get(e).getTranslateY() - 1);
                            break;
                        case "left":
                            enemies.get(e).setTranslateX(enemies.get(e).getTranslateX() + 1);
                            break;
                        case "right":
                            enemies.get(e).setTranslateX(enemies.get(e).getTranslateX() - 1);
                            break;
                    }
                }
            }
        }

        if (checkCol(plyHero, plyExit)) {
            move.stop();
            try {
                Parent town_parent = FXMLLoader.load(getClass().getResource("/fxml/town.fxml")); //where FXMLPage2 is the name of the scene

                Scene cave_scene = new Scene(town_parent);
                MainApp.currentS = cave_scene;
                //get reference to the stage
                Stage stage = (Stage) ((Node) kEvent.getSource()).getScene().getWindow();

                stage.hide(); //optional
                cave_scene.getRoot().requestFocus();
                stage.setScene(cave_scene); //puts the new scence in the stage

                //     stage.setTitle("Town"); //changes the title
                stage.show(); //shows the new page
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private boolean checkCol(Shape obj1, Shape obj2) {
        Shape intersect = Shape.intersect(obj1, obj2);
        return intersect.getBoundsInParent().getWidth() > 0;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        psn.wAnimation = -1;

        recHero.setFill(new ImagePattern(new Image(getClass().getResource("/sprites/heroBack.png").toString())));

        ply[0] = plyWall1;
        ply[1] = plyWall2;
        ply[2] = plyWall3;
        ply[3] = plyWall4;
        ply[4] = plyWall5;
        ply[5] = plyWall6;
        ply[6] = plyWall7;

        for (int i = 0; i < rand.nextInt(5); i++) {
            enemies.add(new Enemy("sprites/slimeGreen", 35, 30, rand.nextInt(900), rand.nextInt(625), "left"));
        }

        for (Rectangle e : enemies) {
            ancCavePath.getChildren().add(e);
        }

        move.setCycleCount(INDEFINITE);
        move.play();
    }

}
