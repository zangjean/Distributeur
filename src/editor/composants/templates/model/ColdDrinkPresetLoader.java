package editor.composants.templates.model;

import editor.composants.savegarde.SaveLoad;
import editor.composants.templates.controller.ColdDrinkPresetController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

import java.io.IOException;

public  class ColdDrinkPresetLoader extends HBox implements Saveable {

    private ColdDrinkPresetController controller;
    public ColdDrinkPresetLoader() {
        try {
            FXMLLoader loader = new FXMLLoader(ColdDrinkPresetLoader.class.getResource("/editor/composants/templates/view/colddrink_preset.fxml"));
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
