package editor.composants.colddrink.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class ColdDrinksLoader extends ScrollPane  {

    public ColdDrinksLoader() {
        try {
            // Load the FXML file and set it as the content of this ScrollPane
            FXMLLoader loader = new FXMLLoader(ColdDrinksLoader.class.getResource("/editor/composants/colddrink/view/colddrinklist.fxml"));
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load ColdDrinksLoader FXML", e);
        }
    }

}
