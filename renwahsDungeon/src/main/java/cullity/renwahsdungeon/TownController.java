package cullity.renwahsdungeon;

/*
 * Made By: Shawn Benedict
 * Date: Dec 4, 2018
 * Made to
 */
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.animation.Animation.INDEFINITE;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private AnchorPane ancTown;

    ImagePattern img = new ImagePattern(new Image(getClass().getResource("/sprites/heroFront.png").toString()));

    Person psn = new Person();

    String direction = "";

    Timeline move = new Timeline(new KeyFrame(Duration.millis(5), ae -> movement()));

    @FXML
    private void keyPressed(KeyEvent event) {
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
        
        MainApp.currentA = ancTown;
    }
}
