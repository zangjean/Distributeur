package editor.composants.templates.composants.hotdrink.model;

import editor.composants.templates.composants.hotdrink.controller.ProductCardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ProductCardLoader extends VBox {

    private ProductCardController controller;

    public ProductCardLoader() throws IOException {
        System.out.println("ProductCardLoader created");

        // Load the FXML file and create the controller
        try {
            FXMLLoader loader = new FXMLLoader(ProductCardLoader.class.getResource("/editor/composants/templates/composants/hotdrink/view/productcard.fxml"));
            loader.setRoot(this);
            loader.load();

            controller  = loader.getController();

            //this.getChildren().add(root);
        }
        catch (IOException ie) {
            System.out.println("Error loading ProductCardLoader: " + ie.getMessage());
            ie.printStackTrace();
        }

    }

    public ProductCardController getController() {
        return controller;
    }

}
