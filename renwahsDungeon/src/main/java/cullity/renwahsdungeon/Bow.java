/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cullity.renwahsdungeon;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 *
 * @author shawn
 */
public class Bow extends Item {

//note, might need an enemy arraylist that constantly updates during shoot timer
    public Bow() {
        super("Bow", true, "b".charAt(0), "bow", 50);

    }

    public Bow(int dir, ArrayList obs) {
        super("bow", true, "b".charAt(0), "bow", 50);

    }

    public void useBow(int dir, double x, double y) {//x and y of user//obstacles are walls, rocks, etc
        if (MainApp.currentA.toString().equalsIgnoreCase("ancTown")) {//no shooting arrows in town
            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
            al.setTitle("Input not allowed");
            al.setHeaderText("shooting arrtows in this town is against the law");
            al.setContentText(null);
            Platform.runLater(al::showAndWait);
            return;
        }
        Arrow arr = new Arrow(dir, x, y);
        MainApp.arrows.add(arr);
        MainApp.currentA.getChildren().add(arr);

    }

}
