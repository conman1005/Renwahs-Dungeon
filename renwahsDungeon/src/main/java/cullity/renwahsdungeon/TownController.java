package cullity.renwahsdungeon;

/*
 * Made By: Shawn Benedict
 * Date: Dec 4, 2018
 * Made to
 */
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import static javafx.animation.Animation.INDEFINITE;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.transform.Rotate;

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
    @FXML
    private Rectangle recTI;//rec that shows the item in the hand of the person in the town scene

    @FXML
    private Label lblCoins;

    Person psn = new Person();

    String direction = "";

    Timeline move = new Timeline(new KeyFrame(Duration.millis(25), ae -> movement()));

    MediaPlayer sword;

    @FXML
    private void keyPressed(KeyEvent event) {

        if (event.getCode() == KeyCode.H) {
            move.stop();
            direction = "u";
        }
        keyStuff temp = new keyStuff();// this is because the pause button is in the global method
        temp.keys(event, true, ancTown, recTI);//true because it is in town scene (pausing button)
        if (event.getCode() == KeyCode.H) {
            move.play();
        }
        if (event.getCode() == KeyCode.T) {//cheat is in keystuff
            lblCoins.setText("Coins: " + MainApp.currentP.getCoins());
        }
        if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
            try {
                if (MainApp.currentI.getSymbol() == 'b') {
                    move.stop();
                    direction = "r";
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                    a.setTitle("Not Allowed");
                    a.setHeaderText("This town has outlawed the use of bows");
                    a.setContentText(null);
                    a.showAndWait();
                    move.play();
                    return;
                }
            } catch (NullPointerException e) {//means theres no item being held
            }
        }
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
//                    System.out.println(pneTown.getTranslateX());//TESTING
//                    System.out.println(pneTown.getTranslateY());///TESTING
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

    Rotate rotate = new Rotate();

    @FXML
    private void mousePressed(MouseEvent event) {
        try {
            if (MainApp.currentI.isWeapon() && (MainApp.currentI.getSymbol() != "b".charAt(0))) {
                rotate.setPivotX(0);
                rotate.setPivotY(50);
                if ((direction.equals("up")) || (direction.equals("u"))) {
                    rotate.setAngle(-45);
                } else if ((direction.equals("down")) || (direction.equals("d"))) {
                    rotate.setAngle(135);
                } else {
                    rotate.setAngle(45);
                }

                recTI.getTransforms().clear();
                recTI.getTransforms().addAll(rotate);
                sword = new MediaPlayer((new Media(getClass().getResource("/woosh.mp3").toString())));
                sword.play();
            }
        } catch (NullPointerException e) {
        }
    }

    @FXML
    private void mouseReleased(MouseEvent event) {
        try {
            if ((MainApp.currentI.isWeapon()) && (!recTI.getTransforms().isEmpty()) && (MainApp.currentI.getSymbol() != "b".charAt(0))) {
                rotate.setPivotX(0);
                rotate.setPivotY(50);
                rotate.setAngle(0);

                recTI.getTransforms().clear();
                recTI.getTransforms().addAll(rotate);
            }
        } catch (NullPointerException e) {
        }
    }

    private void movement() {
        psn.moveTown(pneTown, direction, recHero, recTI);
        for (Polygon i : ply) {
            if (checkCol(plyHero, i)) {
                if ((direction.equals("up")) || (direction.equals("u"))) {
                    pneTown.setTranslateY(pneTown.getTranslateY() - 5);
                } else if ((direction.equals("down")) || (direction.equals("d"))) {
                    pneTown.setTranslateY(pneTown.getTranslateY() + 5);
                } else if ((direction.equals("left")) || (direction.equals("l"))) {
                    pneTown.setTranslateX(pneTown.getTranslateX() - 5);
                } else if ((direction.equals("right")) || (direction.equals("r"))) {
                    pneTown.setTranslateX(pneTown.getTranslateX() + 5);
                }
            }
            if (checkCol(plyHero, plyPath)) {//stop move timer and go to
                pneTown.setTranslateX(pneTown.getTranslateX() + 5);
                direction = "l";
                move.stop();
                Platform.runLater(() -> {
                    MainApp.townLocation = "CAVEPATH";
                    if (!determineCaveLevel()) {
                        move.play();
                        return;
                    }
                    MainApp.townSong.stop();
                    try {
                        Parent town_parent = FXMLLoader.load(getClass().getResource("/fxml/cavePath.fxml")); //where FXMLPage2 is the name of the scene

                        Scene town_scene = new Scene(town_parent);
                        MainApp.currentS = town_scene;
                        //get reference to the stage
                        Stage stage = MainApp.mainStage;

                        stage.hide(); //optional
                        town_scene.getRoot().requestFocus();
                        stage.setScene(town_scene); //puts the new scence in the stage

                        //     stage.setTitle("Town"); //changes the title
                        stage.show(); //shows the new page
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                return;
            }
        }
    }

    private boolean determineCaveLevel() {//allows user to choose level of dungeon

        TextInputDialog dialog = new TextInputDialog("" + (MainApp.currentP.getHighestLevel() + 1));
        dialog.setTitle("Choose Which Level Dungeon you would like to enter");
        dialog.setHeaderText("Type any number from 1 to the farthest level you've been to \n (currently " + (MainApp.currentP.getHighestLevel() + 1) + ")");//might need to make easier to understand
        dialog.setContentText(null);

        Optional<String> result = dialog.showAndWait();
        String chosen;
        if (!result.isPresent()) {//if they cancel
            return false;
        }
        try {
            if (Integer.parseInt(result.get()) < 1 || Integer.parseInt(result.get()) > (MainApp.currentP.getHighestLevel() + 1)) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Error");
            a.setHeaderText("Must input a valid number from 1 to " + (MainApp.currentP.getHighestLevel() + 1));
            a.setContentText("Please exit this message to try again");
            a.showAndWait();

            return determineCaveLevel();
        }
        MainApp.currentL = Integer.parseInt(result.get()) - 1;
        return true;

    }

    private boolean checkCol(Shape obj1, Shape obj2) {
        Shape intersect = Shape.intersect(obj1, obj2);
        return intersect.getBoundsInParent().getWidth() > 0;
    }

    @FXML
    private void scrollItem(ScrollEvent s) {//nextItem
        MainApp.scrollI(s);
        recTI.getTransforms().clear();
        // System.out.println(recTI.getTranslateY());
        if (MainApp.currentI == null) {
            return;
        }
        if (MainApp.currentI.getSymbol() == "b".charAt(0)) {
            recTI.setTranslateY(35);

        } else {
            recTI.setTranslateY(-35);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainApp.currentHealth = MainApp.currentP.getBHealth() * (MainApp.currentP.getLevel() / 10 + 1);
        move.setCycleCount(INDEFINITE);
        move.play();
        lblCoins.setText("Coins: " + MainApp.currentP.getCoins());
        MainApp.townSong.play();
        MainApp.currentA = ancTown;
        recHero.setFill(MainApp.currentP.getImageP());
        direction = "r";
        //MainApp.currentP.setInventory("hsh!!!");//for testing
        //MainApp.getItemsFromData(MainApp.currentP.getInventory());//for testing
        MainApp.slot.clear();
        MainApp.slot.add(recT1);
        MainApp.slot.add(recT2);
        MainApp.slot.add(recT3);
        MainApp.slot.add(recT4);
        MainApp.slot.add(recT5);
        MainApp.slot.add(recT6);
        MainApp.recItem = recTI;
        MainApp.showItems();

        ply[0] = plyWall;
        ply[1] = plyTavern;
        ply[2] = plyBlacksmith;

        if (!MainApp.townLocation.equals("")) {
            if (MainApp.townLocation.equalsIgnoreCase("CAVEPATH")) {
                pneTown.setTranslateX(-1250);
                pneTown.setTranslateY(-743);
            }
        }
        
        System.out.println((MainApp.currentL * 4) / 2);

//        MainApp.currentA = ancTown;
    }
}
