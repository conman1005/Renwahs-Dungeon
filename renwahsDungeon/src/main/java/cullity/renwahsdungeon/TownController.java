package cullity.renwahsdungeon;

/*
 * Made By: Shawn Benedict
 * Date: Dec 4, 2018
 * Made to
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.animation.Animation.INDEFINITE;
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
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author shawnb58
 */
public class TownController implements Initializable {

    @FXML
    private Rectangle recT1;//rec slots in town controller (T)
    @FXML
    private Rectangle recT2;
    @FXML
    private Rectangle recT3;
    @FXML
    private Rectangle recT4;
    @FXML
    private Rectangle recT5;
    @FXML
    private Rectangle recT6;

    @FXML
    private Pane pneTown;

    @FXML
    private Rectangle recHero;

    @FXML
    private Polygon plyWall;
    @FXML
    private Polygon plyTavern;
    @FXML
    private Polygon plyBlacksmith;
    @FXML
    private Polygon plyHero;
    @FXML
    private Polygon plyPath;

    Polygon ply[] = new Polygon[3];

    @FXML
    private AnchorPane ancTown;

    ImagePattern img = new ImagePattern(new Image(getClass().getResource("/sprites/heroFront.png").toString()));

    Person psn = new Person();

    String direction = "";

    Timeline move = new Timeline(new KeyFrame(Duration.millis(5), ae -> movement()));

    @FXML
    private void keyPressed(KeyEvent event) {
        temp.keys();
        
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
    }

    private void movement() {
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
                /*try {
                    Parent town_parent = FXMLLoader.load(getClass().getResource("/fxml/town.fxml")); //where FXMLPage2 is the name of the scene

                    Scene town_scene = new Scene(town_parent);
                    MainApp.currentS = town_scene;
                    //get reference to the stage
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.hide(); //optional
                    town_scene.getRoot().requestFocus();
                    stage.setScene(town_scene); //puts the new scence in the stage

                    //     stage.setTitle("Town"); //changes the title
                    stage.show(); //shows the new page
                } catch (IOException ex) {
                    ex.printStackTrace();
                }*/
                System.out.println("Path");
                pneTown.setTranslateX(pneTown.getTranslateX() + 1);
            }
        }
    }

    private Polygon createBoundsPolygon(Polygon poly) {
        Polygon pol = new Polygon();
        pol.setFill(Color.TRANSPARENT);
        pol.getPoints().addAll(poly.getPoints());
        return pol;
    }

    private boolean checkCol(Shape obj1, Shape obj2) {
        Shape intersect = Shape.intersect(obj1, obj2);
        return intersect.getBoundsInParent().getWidth() > 0;
    }

    @FXML
    private void scrollItem(ScrollEvent s) {//nextItem
        MainApp.scrollI(s);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        move.setCycleCount(INDEFINITE);
        move.play();

        recHero.setFill(img);

        MainApp.slot.clear();
        MainApp.slot.add(recT1);
        MainApp.slot.add(recT2);
        MainApp.slot.add(recT3);
        MainApp.slot.add(recT4);
        MainApp.slot.add(recT5);
        MainApp.slot.add(recT6);
        MainApp.showItems();

        ply[0] = plyWall;
        ply[1] = plyTavern;
        ply[2] = plyBlacksmith;

        MainApp.currentA = ancTown;
    }
}
