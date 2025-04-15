package editor;

import editor.model.EditorLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditorApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        EditorLoader editorLoader = new EditorLoader();

        primaryStage.setTitle("Editor Application");
        Scene scene = new Scene(editorLoader);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
