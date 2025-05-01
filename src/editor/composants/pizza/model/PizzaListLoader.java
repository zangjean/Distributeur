package editor.composants.pizza.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PizzaListLoader extends ScrollPane {

    public PizzaListLoader() {
        // Load the FXML file and set it as the content of this AnchorPane
        try {
            FXMLLoader loader = new FXMLLoader(PizzaListLoader.class.getResource("/editor/composants/pizza/view/pizzalist.fxml"));
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load PizzaListLoader FXML", e);
        }
    }
}
