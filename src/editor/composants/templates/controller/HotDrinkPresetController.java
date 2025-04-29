package editor.composants.templates.controller;

import editor.composants.hotdrink.model.DragProduct;
import editor.composants.savegarde.SaveLoad;
import editor.composants.templates.composants.hotdrink.model.ProductCard;
import editor.composants.templates.composants.hotdrink.model.ProductCardLoader;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotDrinkPresetController {

    private final Map<String, StackPane> slotMap = new HashMap<>();

    @FXML
    private StackPane slot10, slot11, slot12;
    @FXML
    private StackPane slot00, slot01, slot02;
    @FXML
    private GridPane gridPane;

    public HotDrinkPresetController() {}

    @FXML
    public void initialize() {

        System.out.println("HotDrinkPresetController initialized");

        // Set up the grid pane with the hot drink presets
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Set up the slots
        for( StackPane slot : List.of(slot00, slot01, slot02, slot10, slot11, slot12) )
        {
            setUpDgragAndDrop(slot);
        }

        // Set up the slot map
        slotMap.put("slot00", slot00);
        slotMap.put("slot01", slot01);
        slotMap.put("slot02", slot02);
        slotMap.put("slot10", slot10);
        slotMap.put("slot11", slot11);
        slotMap.put("slot12", slot12);

    }

    private void setUpDgragAndDrop(StackPane slot) {


        // Use drag over to make it accept a copy / component
        slot.setOnDragOver(event -> {
            if (event.getGestureSource() != slot && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume(); // consume the event to prevent it from being handled by other nodes
        }
        );
        // Drag dropped on the slot

        slot.setOnDragDropped(event -> {

            ProductCard productCard = DragProduct.getCurrentProduct();

            System.out.println("Drop triggered");
            Dragboard dragboard = event.getDragboard();

            // Check if the dragboard has a string
            boolean isSucceeded = false;

            if(dragboard.hasString()) {
                try {
                    ProductCardLoader loader = new ProductCardLoader();
                    loader.getController().setProductCard(productCard);
                    System.out.println("set new ProductCardLoader");

                    // Double click to remove the product card
                    loader.setOnMouseClicked(event1 -> {
                        if (event1.getClickCount() == 2) {
                            System.out.println("Product card : Removed");
                            slot.getChildren().clear();
                            slot.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: black; -fx-border-width: 1px; -fx-border-style: solid");
                        }
                    });

                    slot.getChildren().clear(); // clear the slot before adding the new component
                    slot.getChildren().add(loader);
                    DragProduct.clear();

                    System.out.println("Added loader to slot");

                    isSucceeded = true;

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            // Set the dragboard to be accepted
            event.setDropCompleted(isSucceeded); // set the drop completed to true or false based on the result
            event.consume(); // consume the event to prevent it from being handled by other nodes
        });
    }

    public SaveLoad getCurrentLayout() {
        SaveLoad layout = new SaveLoad("HotDrink");
        for (Map.Entry<String, StackPane> entry : slotMap.entrySet()) {
            String slotId = entry.getKey();
            StackPane slot = entry.getValue();

            if (!slot.getChildren().isEmpty()) {
                Node child = slot.getChildren().get(0);

                if (child instanceof ProductCardLoader loader) {
                    ProductCard product = loader.getController().getProductCard();

                    if (product != null) {
                        layout.addSlot(slotId, product);
                        System.out.println("📦 Added to layout: " + slotId + " → " + product.getName());
                    } else {
                        System.out.println(" ProductCard is null in " + slotId);
                    }
                }
            }
        }
        System.out.println("✅ Total saved slots: " + layout.getSlots().size());
        return layout;
    }

    public void applyLayout(SaveLoad saveLoad) {

        for(Map.Entry<String, ProductCard> entry : saveLoad.getSlots().entrySet())
        {

            System.out.println(" - " + entry.getKey() + " = " + entry.getValue().getName());
            String slotName = entry.getKey();
            ProductCard productCard = entry.getValue();

            StackPane slot = slotMap.get(slotName);

            if (slot != null) {
                try {
                    ProductCardLoader loader = new ProductCardLoader();
                    loader.getController().setProductCard(productCard);

                    slot.setStyle("-fx-background-color: #fae0e0; -fx-border-color: black; -fx-border-width: 1px; -fx-border-style: solid; -fx-border-radius: 5px");

                    // Double click to remove the product card
                    loader.setOnMouseClicked(event -> {
                        if (event.getClickCount() == 2) {
                            System.out.println("Product card : Removed");
                            slot.getChildren().clear();
                            slot.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: black; -fx-border-width: 1px; -fx-border-style: solid");
                        }
                    });
                    slot.getChildren().clear(); // clear the slot before adding the new component
                    slot.getChildren().add(loader);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Slot " + slotName + " not found");
            }
        }
    }
}
