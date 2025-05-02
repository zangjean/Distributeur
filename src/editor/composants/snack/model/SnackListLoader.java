package editor.composants.snack.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class SnackListLoader extends ScrollPane {

    // Constructor
    public SnackListLoader() throws IOException {
        // Initialize the SnackListLoader
        System.out.println("SnackListLoader created");
        // Load the FXML file and set it as the content of this ScrollPane
        try{

            FXMLLoader loader = new FXMLLoader(SnackListLoader.class.getResource("/editor/composants/snack/view/snacklist.fxml"));
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Failed to load SnackListLoader FXML", e);
        }
    }

    // Add methods to load and manage snack items here
}
