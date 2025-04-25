package editor.composants.templates.model;

import editor.composants.templates.controller.TemplateController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class TemplateLoader extends GridPane {

    private TemplateController templateController;

    public TemplateLoader() {
        try {
            FXMLLoader loader = new FXMLLoader(TemplateLoader.class.getResource("/editor/composants/templates/view/template.fxml"));
            loader.setRoot(this);
            loader.load();

            templateController = loader.getController();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public TemplateController getTemplateController() {
        return templateController;
    }
}
