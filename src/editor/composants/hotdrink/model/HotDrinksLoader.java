package editor.composants.hotdrink.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.io.IOException;

public class HotDrinksLoader extends ScrollPane {

    public HotDrinksLoader() throws IOException {
        try {
            // Load the FXML file and set it as the content of this ScrollPane
            FXMLLoader loader = new FXMLLoader(HotDrinksLoader.class.getResource("/editor/composants/hotdrink/view/hotdrinklist.fxml"));
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Failed to load HotDrinksLoader FXML", e);
        }
    }
}
