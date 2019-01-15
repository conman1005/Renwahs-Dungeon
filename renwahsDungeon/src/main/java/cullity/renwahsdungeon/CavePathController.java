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
import java.util.concurrent.ThreadLocalRandom;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
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
    private Rectangle recItem;//rec that shows the item in the hand of the person in the cavePath scene

    @FXML
    private Rectangle recC1;
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
    private ProgressBar prgHealth;

    Polygon ply[] = new Polygon[7];

    ArrayList<Enemy> enemies = new ArrayList();

    String direction = "";

    Person psn = new Person();
    Enemy enm = new Enemy();

    Timeline move = new Timeline(new KeyFrame(Duration.millis(7), ae -> movement()));

    @FXML
    private void keyPressed(KeyEvent event) {
        kEvent = event;
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
        if ((event.getCode() == KeyCode.LEFT && MainApp.currentI.getItemName().equalsIgnoreCase("Bow")) || (event.getCode() == KeyCode.RIGHT && MainApp.currentI.getItemName().equalsIgnoreCase("Bow")) | (event.getCode() == KeyCode.UP && MainApp.currentI.getItemName().equalsIgnoreCase("Bow")) || (event.getCode() == KeyCode.DOWN && MainApp.currentI.getItemName().equalsIgnoreCase("Bow"))) {
            if (MainApp.currentA.toString().equalsIgnoreCase("ancTown")) {
                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setTitle("Input not allowed");
                al.setHeaderText("shooting arrtows in this town is against the law");
                al.setContentText(null);
                Platform.runLater(al::showAndWait);
                return;

            }
            if (event.getCode() == KeyCode.LEFT && MainApp.currentI.getItemName().equalsIgnoreCase("Bow")) {
                ((Bow) MainApp.currentI).useBow(4, pneHero.getLayoutX() + pneHero.getTranslateX() + recHero.getLayoutX() + recHero.getTranslateX(), pneHero.getLayoutY() + pneHero.getTranslateY() + recHero.getLayoutY() + recHero.getTranslateY());

            }
            if (event.getCode() == KeyCode.RIGHT && MainApp.currentI.getItemName().equalsIgnoreCase("Bow")) {
                ((Bow) MainApp.currentI).useBow(2, pneHero.getLayoutX() + pneHero.getTranslateX() + recHero.getLayoutX() + recHero.getTranslateX(), pneHero.getLayoutY() + pneHero.getTranslateY() + recHero.getLayoutY() + recHero.getTranslateY());

            }
            if (event.getCode() == KeyCode.UP && MainApp.currentI.getItemName().equalsIgnoreCase("Bow")) {
                ((Bow) MainApp.currentI).useBow(1, pneHero.getLayoutX() + pneHero.getTranslateX() + recHero.getLayoutX() + recHero.getTranslateX(), pneHero.getLayoutY() + pneHero.getTranslateY() + recHero.getLayoutY() + recHero.getTranslateY());

            }
            if (event.getCode() == KeyCode.DOWN && MainApp.currentI.getItemName().equalsIgnoreCase("Bow")) {
                ((Bow) MainApp.currentI).useBow(3, pneHero.getLayoutX() + pneHero.getTranslateX() + recHero.getLayoutX() + recHero.getTranslateX(), pneHero.getLayoutY() + pneHero.getTranslateY() + recHero.getLayoutY() + recHero.getTranslateY());

            }
        }
    }
    KeyEvent kEvent;

    int eMov = 0; //eMov is used to make the enemies move 1 pixel in a longer time, so they move slower than the person

    private void movement() {

        psn.moveCave(pneHero, direction, recHero, recItem);
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

            for (int e = 0; e < enemies.size(); e++) {
                for (Polygon ply1 : ply) {
                    if (checkCol(enemies.get(e), ply1)) {
                        switch (enemies.get(e).getDirection()) {
                            case "up":
                                enemies.get(e).setTranslateY(enemies.get(e).getTranslateY() + 1);
                                switch (ThreadLocalRandom.current().nextInt(3)) {
                                    case 0:
                                        enemies.get(e).setDirection("left");
                                        break;
                                    case 1:
                                        enemies.get(e).setDirection("right");
                                        break;
                                    case 2:
                                        enemies.get(e).setDirection("down");
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case "down":
                                enemies.get(e).setTranslateY(enemies.get(e).getTranslateY() - 1);
                                switch (ThreadLocalRandom.current().nextInt(3)) {
                                    case 0:
                                        enemies.get(e).setDirection("left");
                                        break;
                                    case 1:
                                        enemies.get(e).setDirection("right");
                                        break;
                                    case 2:
                                        enemies.get(e).setDirection("up");
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case "left":
                                enemies.get(e).setTranslateX(enemies.get(e).getTranslateX() + 1);
                                switch (ThreadLocalRandom.current().nextInt(3)) {
                                    case 0:
                                        enemies.get(e).setDirection("up");
                                        break;
                                    case 1:
                                        enemies.get(e).setDirection("right");
                                        break;
                                    case 2:
                                        enemies.get(e).setDirection("down");
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case "right":
                                enemies.get(e).setTranslateX(enemies.get(e).getTranslateX() - 1);
                                switch (ThreadLocalRandom.current().nextInt(3)) {
                                    case 0:
                                        enemies.get(e).setDirection("left");
                                        break;
                                    case 1:
                                        enemies.get(e).setDirection("up");
                                        break;
                                    case 2:
                                        enemies.get(e).setDirection("down");
                                        break;
                                    default:
                                        break;
                                }
                                break;
                        }
                    }

                    if (checkCol(enemies.get(e), plyHero)) {
                        enemies.get(e).setBounce(1);
                    }
                    if (enemies.get(e).getBounce() >= 1) {
                        enemies.get(e).setBounce(enemies.get(e).getBounce() + 1);
                        switch (enemies.get(e).getDirection()) {
                            case "up":
                                enemies.get(e).setDirection("down");
                                break;
                            case "down":
                                enemies.get(e).setDirection("up");
                                break;
                            case "left":
                                enemies.get(e).setDirection("right");
                                break;
                            case "right":
                                enemies.get(e).setDirection("left");
                                break;
                        }
                    }
                }
            }

            eMov++;
            if (eMov == 10) {
                eMov = 0;
                for (int em = 0; em < enemies.size(); em++) {
                    if (enemies.get(em).getDirectionTime() == 10) {
                        enemies.get(em).setDiretctionTime(0);

                        if (ThreadLocalRandom.current().nextBoolean() == true) {
                            if (enemies.get(em).getTranslateX() > pneHero.getTranslateX() + 450) {
                                enemies.get(em).setDirection("left");
                            } else if (enemies.get(em).getTranslateX() < pneHero.getTranslateX() + 450) {
                                enemies.get(em).setDirection("right");
                            }
                        } else {

                            if (enemies.get(em).getTranslateY() > pneHero.getTranslateY() + 556) {
                                enemies.get(em).setDirection("up");
                            } else if (enemies.get(em).getTranslateY() < pneHero.getTranslateY() + 556) {
                                enemies.get(em).setDirection("down");
                            }
                            return;
                        }
                    }

                    if (ThreadLocalRandom.current().nextInt(500) == 500) {
                        switch (ThreadLocalRandom.current().nextInt(4)) {
                            case 0:
                                enemies.get(em).setDirection("up");
                                break;
                            case 1:
                                enemies.get(em).setDirection("down");
                                break;
                            case 2:
                                enemies.get(em).setDirection("left");
                                break;
                            case 3:
                                enemies.get(em).setDirection("up");
                                break;
                        }
                    }
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

                    enemies.get(em).setDiretctionTime(enemies.get(em).getDirectionTime() + 1);
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
                Stage stage = MainApp.mainStage;

                stage.hide(); //optional
                cave_scene.getRoot().requestFocus();
                stage.setScene(cave_scene); //puts the new scence in the stage

                //     stage.setTitle("Town"); //changes the title
                stage.show(); //shows the new page
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }
//arrow stuff
//if (MainApp.arrows.isEmpty()){return;}
//        ArrayList<Arrow> copy = new ArrayList();
//        copy.addAll(MainApp.arrows);

        for (Arrow a : MainApp.arrows) {

//            for (Arrow ar : copy) {//if it is in another arrow then delete it//for if the user spams the shooting button
//                if (MainApp.arrows.indexOf(a) != copy.indexOf(ar) && checkCol(a, ar)) {
//                    MainApp.currentA.getChildren().remove(a);
//                    MainApp.arrows.remove(a);
//                }
//            }
            for (Polygon p : ply) {//ply is walls and rocks,plyExit is the door//if hit those then delete yourself
                if (checkCol(a, p) || checkCol(a, plyExit)) {
                    MainApp.currentA.getChildren().remove(a);
                    MainApp.arrows.remove(a);
                }
            }
            for (Enemy e : enemies) {
                if (checkCol(a, e)) {
                    //damage enemy //note not done yet
                    System.out.println("hit with arrow");
                    //then delete arrow
                    MainApp.currentA.getChildren().remove(a);
                    MainApp.arrows.remove(a);
                }
            }
            int x = 0;
            int y = 0;
            if (a.getD() == 1) {
                x = 0;
                y = 5;
                a.setRotate(90);
            } else if (a.getD() == 2) {
                x = 5;
                y = 0;
                a.setRotate(0);
            } else if (a.getD() == 3) {
                x = 0;
                y = -5;
                a.setRotate(270);
            } else if (a.getD() == 4) {
                x = -5;
                y = 0;
                a.setRotate(180);
            }
            a.setTranslateX(a.getTranslateX() + x);
            a.setTranslateY(a.getTranslateY() - y);
        }

    }

    private boolean checkCol(Shape obj1, Shape obj2) {
        Shape intersect = Shape.intersect(obj1, obj2);
        return intersect.getBoundsInParent().getWidth() > 0;
    }

    @FXML
    private void scrollItem(ScrollEvent s) {//nextItem
        MainApp.scrollI(s);
    }

    Rotate rotate = new Rotate();

    @FXML
    private void mousePressed(MouseEvent event) {
        if (MainApp.currentI.isWeapon()) {
            rotate.setPivotX(0);
            rotate.setPivotY(50);
            rotate.setAngle(45);

            recItem.getTransforms().clear();
            recItem.getTransforms().addAll(rotate);
        }
    }

    @FXML
    private void mouseReleased(MouseEvent event) {
        if ((MainApp.currentI.isWeapon()) && (!recItem.getTransforms().isEmpty())) {
            rotate.setPivotX(0);
            rotate.setPivotY(50);
            rotate.setAngle(0);

            recItem.getTransforms().clear();
            recItem.getTransforms().addAll(rotate);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        plyHero.setTranslateX(442);
        plyHero.setTranslateY(534);

        prgHealth.setProgress(MainApp.currentHealth / MainApp.currentP.getBHealth());

        psn.wAnimation = -1;

        recHero.setFill(MainApp.currentP.getImageP());
        MainApp.currentA = ancCavePath;

        ply[0] = plyWall1;
        ply[1] = plyWall2;
        ply[2] = plyWall3;
        ply[3] = plyWall4;
        ply[4] = plyWall5;
        ply[5] = plyWall6;
        ply[6] = plyWall7;
        double multiplier = (MainApp.currentL * 0.01) + 1;//multiplies strength and health of enemies
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(1, 5 + 1); i++) {
            enemies.add(new Enemy(100 * multiplier, 10 * multiplier, "sprites/slimeGreen", 35, 30, 0, 0, "left"));
        }
        for (int ii = 0; ii < enemies.size(); ii++) {
            enemies.get(ii).setTranslateX(ThreadLocalRandom.current().nextInt(50, 850));
            enemies.get(ii).setTranslateY(ThreadLocalRandom.current().nextInt(50, 550));
        }

        ancCavePath.getChildren().addAll(enemies);

        move.setCycleCount(INDEFINITE);
        move.play();

        MainApp.slot.clear();
        MainApp.slot.add(recC1);
        MainApp.slot.add(recC2);
        MainApp.slot.add(recC3);
        MainApp.slot.add(recC4);
        MainApp.slot.add(recC5);
        MainApp.slot.add(recC6);
        MainApp.recItem = recItem;
        MainApp.showItems();
    }

}
