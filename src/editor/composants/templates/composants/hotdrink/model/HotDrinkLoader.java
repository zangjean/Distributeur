package editor.composants.templates.composants.hotdrink.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HotDrinkLoader extends VBox {
    public HotDrinkLoader() {
        // Load the FXML file and set the root to this instance
        try {
            FXMLLoader loader = new FXMLLoader(HotDrinkLoader.class.getResource("/editor/composants/templates/composants/hotdrink/view/hotdrink.fxml"));
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

}
