/*
 * Made By: Shawn Benedict
 * Date: Jan 11, 2019
 * Made to
 */
package cullity.renwahsdungeon;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author shawnb58
 */
public class Arrow extends Rectangle {

    private ImagePattern imageP;
    private int d;//direction 
//1=up
//2=right
//3=down
//4=left

    public Arrow() {
        super(15, 30, 50, 25);

        imageP = new ImagePattern(new Image(getClass().getResource("/arrow.png").toString()));
        this.setFill(imageP);
        d = 0;

    }

    public Arrow(int dir, double x, double y) {
        super(x, y, 50, 25);
        imageP = new ImagePattern(new Image(getClass().getResource("/arrow.png").toString()));
        this.setFill(imageP);
        d = dir;
    }

    public void setImageP(String im) {
        imageP = new ImagePattern(new Image(getClass().getResource("/" + im + ".png").toString()));
        this.setFill(imageP);
    }

    public ImagePattern getImageP() {
        return imageP;
    }

    public void setD(int dir) {
        d = dir;
    }

    public int getD() {
        return d;
    }

}
