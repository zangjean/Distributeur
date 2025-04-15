package editor.composants.templates.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class TemplateLoader extends GridPane {

    public TemplateLoader()
    {
        try {
            FXMLLoader loader = new FXMLLoader(TemplateLoader.class.getResource("/editor/composants/templates/view/template.fxml"));
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
