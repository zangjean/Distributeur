package editor.composants.templates.composants.snacks.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class SnackLoader extends BorderPane {

    public SnackLoader() {

        try {
            FXMLLoader loader = new FXMLLoader(SnackLoader.class.getResource("/editor/composants/templates/composants/snacks/view/snack.fxml"));
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
