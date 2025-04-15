package editor.composants.templates.hotdrink.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class HotdrinkLoader extends GridPane {

    public HotdrinkLoader()
    {
        try {
            FXMLLoader loader = new FXMLLoader(HotdrinkLoader.class.getResource("/editor/composants/templates/hotdrink/view/hotdrink.fxml"));
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
