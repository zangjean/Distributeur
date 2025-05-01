package editor.composants.templates.model;

import editor.composants.templates.controller.SnackPresetController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import javax.swing.border.Border;
import java.io.IOException;

public class SnackPresetLoader extends BorderPane {

    public SnackPresetLoader() {

        SnackPresetController controller = new SnackPresetController();
        // Load the FXML file and set the root
        try {
            FXMLLoader loader = new FXMLLoader(SnackPresetLoader.class.getResource("/editor/composants/templates/view/snack_preset.fxml"));
            loader.setRoot(this);
            loader.load();
            controller = loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
