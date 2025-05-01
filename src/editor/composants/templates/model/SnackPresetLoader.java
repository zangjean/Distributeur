package editor.composants.templates.model;

import editor.composants.savegarde.SaveLoad;
import editor.composants.templates.controller.SnackPresetController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import javax.swing.border.Border;
import java.io.IOException;

public class SnackPresetLoader extends BorderPane implements Saveable {

    private SnackPresetController controller;
    public SnackPresetLoader() {

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




    @Override
    public SaveLoad getLayout() {
        return controller.getCurrentLayout();
    }
    @Override
    public void applySaveLoad(SaveLoad saveLoad) {
        controller.applyLayout(saveLoad);
    }
}
