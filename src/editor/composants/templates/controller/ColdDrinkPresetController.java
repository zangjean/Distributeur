package editor.composants.templates.controller;

import editor.composants.hotdrink.model.DragProduct;
import editor.composants.savegarde.SaveLoad;
import editor.composants.templates.composants.hotdrink.model.ProductCard;
import editor.composants.templates.composants.hotdrink.model.ProductCardLoader;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ColdDrinkPresetController {

    @FXML private HBox hboxContainer;           // your fx:id="hboxContainer"
    @FXML private ScrollPane scrollPane1,
            scrollPane2,
            scrollPane3;
    @FXML private VBox vbox1, vbox2, vbox3;     // the three columns

    private final Map<String, List<StackPane>> columnSlots = new LinkedHashMap<>();

    private static final int MAX_PER_COLUMN = 15;
    private static final int SLOTS_PER_COLUMN = 10;

    /** keep an ordered map of column‐keys → vbox so save/load is deterministic */
    private final Map<String, VBox> columns = new LinkedHashMap<>();

    @FXML
    public void initialize() {
        // 1) set up your map of columns
        columnSlots.put("col1", new ArrayList<>());
        columnSlots.put("col2", new ArrayList<>());
        columnSlots.put("col3", new ArrayList<>());

        // 2) for each column, add fixed slots
        int colIndex = 1;
        for (VBox vbox : List.of(vbox1, vbox2, vbox3)) {
            String columnKey = "col" + colIndex++;
            List<StackPane> slots = columnSlots.get(columnKey);

            for (int i = 0; i < SLOTS_PER_COLUMN; i++) {
                StackPane slot = new StackPane();
                slot.setId(columnKey + "_slot" + i);
                slot.setPrefSize(150, 150);
                slot.setStyle(
                        "-fx-border-color: #aaa; " +
                                "-fx-background-color: white;"
                );

                setupDropHandlers(slot);
                vbox.getChildren().add(slot);
                slots.add(slot);
            }
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
            ProductCard p = DragProduct.getCurrentProduct();
            if (p == null) return;
            try {
                ProductCardLoader loader = new ProductCardLoader();
                loader.getController().setProductCard(p);
                slot.getChildren().setAll(loader);
                slot.setStyle(
                        "-fx-border-color: #3399ff; " +
                                "-fx-background-color: #d0f0ff;"
                );
                e.setDropCompleted(true);
            } catch (IOException ex) {
                ex.printStackTrace();
                e.setDropCompleted(false);
            }
            e.consume();
        });
        slot.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                slot.getChildren().clear();
                slot.setStyle(
                        "-fx-border-color: #aaa; " +
                                "-fx-background-color: white;"
                );
            }
        });
    }



    private void showWarning(String msg) {
        Alert a = new Alert(Alert.AlertType.WARNING, msg);
        a.setHeaderText(null);
        a.showAndWait();
    }

    // === Saveable  ===

    /** Serialize each column under keys "col1","col2","col3" */
    public SaveLoad getCurrentLayout() {
        SaveLoad layout = new SaveLoad("ColdDrink");
        columnSlots.forEach((colKey, slots) -> {
            for (StackPane slot : slots) {
                if (!slot.getChildren().isEmpty()) {
                    ProductCardLoader ld = (ProductCardLoader)slot.getChildren().get(0);
                    layout.addSlot(slot.getId(), ld.getController().getProductCard());
                }
            }
        });
        return layout;
    }

    /** Clear all columns, then re-add from saved map */
    public void applyLayout(SaveLoad saved) {

        // clear all
        columnSlots.values().forEach(slots ->
                slots.forEach(s -> {
                    s.getChildren().clear();
                    s.setStyle(
                            "-fx-border-color: #aaa; " +
                                    "-fx-background-color: white;"
                    );
                })
        );

        // populate saved

        saved.getSlots().forEach((slotId, product) -> {
            columnSlots.values().stream()
                    .flatMap(List::stream)
                    .filter(s -> s.getId().equals(slotId))
                    .findFirst()
                    .ifPresent(slot -> {
                        try {
                            ProductCardLoader ld = new ProductCardLoader();
                            ld.getController().setProductCard(product);
                            slot.getChildren().setAll(ld);
                            slot.setStyle(
                                    "-fx-border-color: #3399ff; " +
                                            "-fx-background-color: #d0f0ff;"
                            );
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
        });
    }

}



