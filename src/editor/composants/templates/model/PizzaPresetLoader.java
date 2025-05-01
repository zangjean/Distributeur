package editor.composants.templates.model;

import editor.composants.savegarde.SaveLoad;
import editor.composants.templates.controller.PizzaPresetController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PizzaPresetLoader extends AnchorPane implements Saveable  {

    private PizzaPresetController controller;
    public PizzaPresetLoader()
    {
        // Constructor
        System.out.println("PizzaPresetLoader created");
        try {
            FXMLLoader loader = new FXMLLoader(PizzaPresetLoader.class.getResource("/editor/composants/templates/view/pizza_preset.fxml"));
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
