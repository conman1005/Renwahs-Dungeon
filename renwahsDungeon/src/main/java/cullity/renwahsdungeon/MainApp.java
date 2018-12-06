package cullity.renwahsdungeon;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//

public class MainApp extends Application {

    public ArrayList<Item> inv = new ArrayList();
    public Item currentI;//current item selected
    public Person currentP; //current user/save file

    public void deleteItem() {
        inv.remove(currentI);

        String newI = "";//new inventory
        for (int i = 0; i < inv.size(); i++) {
            newI += inv.get(i);
        }
        currentP.setInventory(newI);
        if (inv.size() > 0) {
            currentI = inv.get(0);//change to spot if that gets put in
        } else {
            currentI = null;
        }

        showItems();
        //if there is a spot variable for item then change it here
    }

    public void showItems() {
        //show items in the boxes
        
                
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Renwah's Dungeon");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> System.exit(0));
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
