package editor.composants.templates.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.TilePane;

import java.io.IOException;

public class ColdDrinkPresetLoader extends TilePane {

    public ColdDrinkPresetLoader() {
        try {
            FXMLLoader loader = new FXMLLoader(ColdDrinkPresetLoader.class.getResource("/editor/composants/templates/view/colddrink_preset.fxml"));
            loader.setRoot(this);
            loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
