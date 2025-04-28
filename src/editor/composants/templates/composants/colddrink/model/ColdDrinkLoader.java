package editor.composants.templates.composants.colddrink.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ColdDrinkLoader extends HBox {

    public ColdDrinkLoader() {
        // Load the FXML file and set the root to this instance
        try {
            FXMLLoader loader = new FXMLLoader(ColdDrinkLoader.class.getResource("/editor/composants/templates/composants/colddrink/view/colddrink.fxml"));
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
