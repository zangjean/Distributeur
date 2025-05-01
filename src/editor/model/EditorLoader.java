package editor.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class EditorLoader extends BorderPane {

    public EditorLoader(){
        try {
            FXMLLoader loader = new FXMLLoader(EditorLoader.class.getResource("/editor/view/editorView.fxml"));
            loader.setRoot(this);
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }

}
