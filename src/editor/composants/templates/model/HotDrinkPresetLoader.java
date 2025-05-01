package editor.composants.templates.model;

import editor.composants.savegarde.SaveLoad;
import editor.composants.templates.controller.HotDrinkPresetController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class HotDrinkPresetLoader extends GridPane implements Saveable {

    private HotDrinkPresetController controller;

    public HotDrinkPresetLoader() {
        try {
            FXMLLoader loader = new FXMLLoader(TemplateLoader.class.getResource("/editor/composants/templates/view/hotdrink_preset.fxml"));
            loader.setRoot(this);
            loader.load();

            controller = loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
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


