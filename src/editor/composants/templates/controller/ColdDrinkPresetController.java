package editor.composants.templates.controller;

import editor.composants.hotdrink.model.DragProduct;
import editor.composants.templates.composants.hotdrink.model.ProductCard;
import editor.composants.templates.composants.hotdrink.model.ProductCardLoader;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ColdDrinkPresetController {

    @FXML
    private Label counterLabel;  // Create this Label in your FXML


    private final List<StackPane> placedProducts = new ArrayList<>();
    @FXML
    private TilePane tilePane;


    public ColdDrinkPresetController() {
        // Constructor
    }

    private final int MAX_SLOTS = 15; //  You control how many products user can place

    @FXML
    public void initialize() {
        System.out.println("ColdDrinkPresetController initialized");

        tilePane.setPrefColumns(3); // 3 columns
        tilePane.setHgap(10);
        tilePane.setVgap(10);
        tilePane.setAlignment(Pos.CENTER);

        for (int i = 0; i < MAX_SLOTS; i++) {
            StackPane emptySlot = new StackPane();
            emptySlot.setPrefSize(150, 150);
            emptySlot.setStyle("-fx-border-color: #aaa; -fx-background-color: #f0f8ff;");

            setupDropHandlers(emptySlot);

            tilePane.getChildren().add(emptySlot);
            placedProducts.add(emptySlot); // Save slots
        }
    }

    private void setupDropHandlers(StackPane slot) {
        slot.setOnDragOver(event -> {
            if (event.getGestureSource() != slot && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        slot.setOnDragDropped(event -> {

            ProductCard product = DragProduct.getCurrentProduct();
            if (product == null) {
                System.out.println(" No product to drop!");
                return;
            }

            System.out.println("Product dropped: " + product.getName());


            Dragboard db = event.getDragboard();
            boolean success = false;


            if (db.hasString()) {
                String productName = db.getString();
                try {
                    ProductCardLoader loader = new ProductCardLoader();
                    loader.getController().setProductCard(product);

                    slot.getChildren().clear();
                    slot.getChildren().add(loader);

                    success = true;
                    System.out.println("✅ Product " + productName + " placed!");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });

        // Highlight when drag over
        slot.setOnDragEntered(event -> {
            if (event.getGestureSource() != slot && event.getDragboard().hasString()) {
                slot.setStyle("-fx-background-color: #d0f0ff; -fx-border-color: #3399ff; -fx-border-width: 2;");
            }
            event.consume();
        });

        // Remove highlight when drag exits
        slot.setOnDragExited(event -> {
            slot.setStyle("-fx-background-color: #f0f8ff; -fx-border-color: #aaa; -fx-border-width: 1;");
            event.consume();
        });

        // 🗑️ Double-click to remove
        slot.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !slot.getChildren().isEmpty()) {
                slot.getChildren().clear();
                System.out.println("🗑️ Product removed from slot!");
            }
        });
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Limit reached");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }







}
