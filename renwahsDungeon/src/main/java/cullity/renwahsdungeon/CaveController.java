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
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Rectangle;

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
    private void scrollItem(ScrollEvent k) {//nextItem //put in anchorpane !!!!!!!!!!!!!!!!!!
        MainApp.scrollI(k);
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
    }

}
