package editor.composants.templates.controller;

import editor.composants.hotdrink.model.DragProduct;
import editor.composants.savegarde.SaveLoad;
import editor.composants.templates.composants.hotdrink.model.ProductCardLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class PizzaPresetController {
    @FXML
    private Label hoverInfo;
    @FXML
    private AnchorPane myOverlay;

    private Map<String, StackPane> slots = new LinkedHashMap<>();
    private static final int MAX_SLOTS = 12;

    @FXML
    private AnchorPane root;

    public PizzaPresetController() {
        // Constructor
    }

    @FXML
    private void initialize() {
        // Initialize the pizza base with a default color
        double centerX = myOverlay.getPrefWidth() / 2;
        double centerY = myOverlay.getPrefHeight() / 2;
        double radius = 150;

        AnchorPane.setTopAnchor(myOverlay, 0.0);
        AnchorPane.setBottomAnchor(myOverlay, 0.0);
        AnchorPane.setRightAnchor(myOverlay, 0.0);
        AnchorPane.setLeftAnchor(myOverlay, 0.0);

        myOverlay.setStyle("-fx-background-color: red");


        hoverInfo.setText("Je suis la");

        for (int i = 0; i < MAX_SLOTS; i++) {
            StackPane slot = new StackPane();
            slot.setId("slot" + i);
            slot.setPrefSize(60, 60);
            slot.setStyle("-fx-border-color:#aaa; -fx-background-color:transparent;");

            // position circulaire
            double angle = 2 * Math.PI * i / MAX_SLOTS;
            slot.setLayoutX(centerX + Math.cos(angle) * radius - 30);
            slot.setLayoutY(centerY + Math.sin(angle) * radius - 30);

            setupDropHandlers(slot);
            myOverlay.getChildren().add(slot);
            slots.put(slot.getId(), slot);
        }
    }

    private void setupDropHandlers(StackPane slot) {
        slot.setOnDragOver(e -> {
            if (e.getDragboard().hasString()) {
                e.acceptTransferModes(TransferMode.COPY);
            }
            e.consume();
        });
        slot.setOnDragDropped(e -> {
            var product = DragProduct.getCurrentProduct();
            if (product != null) {
                try {
                    ProductCardLoader loader = new ProductCardLoader();
                    loader.getController().setProductCard(product);
                    slot.getChildren().setAll(loader);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                e.setDropCompleted(true);
            }
            e.consume();
        });
        slot.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                slot.getChildren().clear();
            }
        });

    }

    public SaveLoad getCurrentLayout() {
        SaveLoad layout = new SaveLoad("PizzaPreset");
        slots.forEach((id, slot) -> {
            if (!slot.getChildren().isEmpty()) {

                ProductCardLoader loader = (ProductCardLoader) slot.getChildren().get(0);
                layout.addSlot(id, loader.getController().getProductCard());
            }
        });
        return layout;
    }

    // src/editor/composants/templates/controller/PizzaPresetController.java
    public void applyLayout(SaveLoad saveLoad) {
        // vider tous les emplacements
        slots.values().forEach(slot -> slot.getChildren().clear());
        // restaurer
        saveLoad.getSlots().forEach((id, product) -> {
            StackPane slot = slots.get(id);
            if (slot != null) {
                try {
                    ProductCardLoader loader = new ProductCardLoader();
                    loader.getController().setProductCard(product);
                    slot.getChildren().setAll(loader);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
