/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cullity.renwahsdungeon;

import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.shape.Polygon;

/**
 *
 * @author shawn
 */
public class Bow extends Item {

//note, might need an enemy arraylist that constantly updates during shoot timer
    private ArrayList<Arrow> arrows = new ArrayList();

    public Bow() {
        super("bow", true, "b".charAt(0), "bow", 50);

        arrows = null;
    }

    public Bow(int dir, ArrayList obs) {
        super("bow", true, "b".charAt(0), "bow", 50);

        arrows = null;
    }

    public void useBow(int x, int y, int dir, ArrayList obstacles) {//x and y of user//obstacles are walls, rocks, etc
        if (MainApp.currentA.toString().equalsIgnoreCase("ancTown")) {//no shooting arrows in town
            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
            al.setTitle("Input not allowed");
            al.setHeaderText("shooting arrtows in this town is against the law");
            al.setContentText(null);
            al.showAndWait();
            return;
        }

    }

    
    public void setArrows(ArrayList arr) {
        arrows.clear();
        arrows.addAll(arr);
    }

    public ArrayList getArrows() {
        return arrows;
    }
}
