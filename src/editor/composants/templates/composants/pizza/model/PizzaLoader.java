package editor.composants.templates.composants.pizza.model;

import editor.composants.templates.composants.hotdrink.model.HotDrinkLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PizzaLoader extends AnchorPane {

    public PizzaLoader() {
        // Load the FXML file and set the root to this instance
        try {
            FXMLLoader loader = new FXMLLoader(PizzaLoader.class.getResource("/editor/composants/templates/composants/pizza/view/pizza.fxml"));
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
