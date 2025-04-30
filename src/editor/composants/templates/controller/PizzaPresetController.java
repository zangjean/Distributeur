package editor.composants.templates.controller;
//
//import editor.composants.hotdrink.model.DragProduct;
//import editor.composants.savegarde.SaveLoad;
//import editor.composants.templates.composants.hotdrink.model.ProductCard;
//import editor.composants.templates.composants.hotdrink.model.ProductCardLoader;
//import javafx.application.Platform;
//import javafx.fxml.FXML;
//import javafx.geometry.Bounds;
//import javafx.scene.Node;
//import javafx.scene.control.Label;
//import javafx.scene.effect.DropShadow;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.ClipboardContent;
//import javafx.scene.input.Dragboard;
//import javafx.scene.input.TransferMode;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.TilePane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//public class PizzaPresetController {
//    @FXML
//    private AnchorPane myOverlay;
//    @FXML
//    private Label hoverInfo;
//
//    private static final int MAX_SLOTS = 10;
//    private Map<String, StackPane> slots = new LinkedHashMap<>();
//    // Définir un rayon de base et un espacement supplémentaire
//    private static final double BASE_RADIUS    = 150;
//    private static final double SLOT_SPACING   = 60;
//
//
//    public PizzaPresetController() {
//        // Constructor
//    }
//
//    @FXML
//    private void initialize() {
//
//        hoverInfo.mouseTransparentProperty().setValue(true);
//
//        // anchor the overlay to all four sides
//        AnchorPane.setTopAnchor(   myOverlay, 0.0);
//        AnchorPane.setBottomAnchor(myOverlay, 0.0);
//        AnchorPane.setLeftAnchor(  myOverlay, 0.0);
//        AnchorPane.setRightAnchor( myOverlay, 0.0);
//
//        // acchor hoverInfo
//        // fill overlay
//        AnchorPane.setTopAnchor(hoverInfo,   0.0);
//        AnchorPane.setBottomAnchor(hoverInfo,0.0);
//        AnchorPane.setLeftAnchor(hoverInfo,  0.0);
//        AnchorPane.setRightAnchor(hoverInfo, 0.0);
//
//
//        Platform.runLater(this::addSlots);
//        myOverlay.widthProperty().addListener((o,old,nw)-> addSlots());
//        myOverlay.heightProperty().addListener((o,old,nh)-> addSlots());
//
//        System.out.println("addSlots: overlay is "
//                + myOverlay.getWidth() + "×" + myOverlay.getHeight());
//
//        // Add gradient background for myOverlay
//        myOverlay.setStyle(
//                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #ffe29f, #ffa99f);"
//        );
//    }
//
//    private void addSlots() {
//        // 1. Supprimer les anciens slots de l'Overlay et de la map
//        slots.values().forEach(myOverlay.getChildren()::remove);
//        slots.clear();
//
//        // 2. Calculer le centre et le rayon
//        double centerX = myOverlay.getWidth()  / 2;
//        double centerY = myOverlay.getHeight() / 2;
//        double radius  = BASE_RADIUS + SLOT_SPACING;
//
//        // 3. Recréer les slots
//        for (int i = 0; i < MAX_SLOTS; i++) {
//            int slotIndex = i + 1;
//            StackPane slot = new StackPane();
//            slot.setId("slot" + i);
//            slot.setPrefSize(100, 100);
//            slot.setStyle("-fx-border-color: red; -fx-background-color: white;");
//            slot.setEffect(new DropShadow(10, Color.GRAY));
//
//            // Handlers de drag & click
//            slot.setOnDragOver(e -> {
//                if (e.getDragboard().hasString()) e.acceptTransferModes(TransferMode.COPY);
//                e.consume();
//            });
////            slot.setOnDragDropped(e -> {
////                ProductCard card = DragProduct.getCurrentProduct();
////                if (card != null) {
////                    try {
////                        ProductCardLoader loader = new ProductCardLoader();
////                        loader.getController().setProductCard(card);
////
////                        Label lbname = (Label) loader.lookup("#lbNom");
////                        Label lbprice = (Label) loader.lookup("#lbPrix");
////
////                        lbname.setVisible(false);
////                        lbprice.setVisible(false);
////
////                        // Afficher hoverInfo au survol du loader (donc de l'image et du reste)
////                        loader.setOnMouseEntered(evt -> {
////                            hoverInfo.setText(card.getName() + " — €" + card.getPrice());
////                            hoverInfo.setLayoutX(slot.getLayoutX() + 20);
////                            hoverInfo.setLayoutY(slot.getLayoutY() + 20);
////                            hoverInfo.setVisible(true);
////                        });
////                        loader.setOnMouseExited(evt -> hoverInfo.setVisible(false));
////
////                        slot.getChildren().setAll(loader);
////                    } catch (IOException ex) { ex.printStackTrace(); }
////                }
////
////                e.setDropCompleted(true);
////                e.consume();
////            });
//            slot.setOnDragDropped(e -> {
//                Dragboard db = e.getDragboard();
//                boolean success = false;
//                if (db.hasString()) {
//                    ProductCard card = DragProduct.getCurrentProduct();
//                    if (card != null) {
//                        try {
//                            // 1) load the full card just to initialise it
//                            ProductCardLoader fullLoader = new ProductCardLoader();
//                            fullLoader.getController().setProductCard(card);
//
//                            // 2) pull out the ImageView (make sure you have a getter in your controller)
//                            ImageView imgView = fullLoader.getController().getImgView();
//
//                            // 3) size it to the slot
//                            imgView.setFitWidth(slot.getPrefWidth());
//                            imgView.setFitHeight(slot.getPrefHeight());
//                            imgView.setPreserveRatio(true);
//
//                            // 4) wrap it in its own pane so it stays clipped/draggable
//                            StackPane imageOnly = new StackPane(imgView);
//                            imageOnly.setPrefSize(slot.getPrefWidth(), slot.getPrefHeight());
//
//                            imageOnly.setUserData(card);
//
//                            // 5) add the hover info to the image
//                            imageOnly.setOnMouseEntered(evt -> {
//                                hoverInfo.setText(card.getName() + " — €" + card.getPrice());
//                                hoverInfo.setLayoutX(slot.getLayoutX() + 20);
//                                hoverInfo.setLayoutY(slot.getLayoutY() + 20);
//                                hoverInfo.setVisible(true);
//                            });
//                            imageOnly.setOnMouseExited(evt -> hoverInfo.setVisible(false));
//
//                            // 5) reinstall your drag‐again logic if you want move back out
//                            imageOnly.setOnDragDetected(evt -> {
//                                Dragboard db2 = imageOnly.startDragAndDrop(TransferMode.MOVE);
//                                ClipboardContent cc = new ClipboardContent();
//                                cc.putString(card.getName());
//                                db2.setContent(cc);
//                                DragProduct.setCurrentProduct(card);
//                                evt.consume();
//                            });
//                            imageOnly.setOnDragDone(evt2 -> {
//                                if (evt2.getTransferMode() == TransferMode.MOVE) {
//                                    slot.getChildren().clear();
//                                }
//                                evt2.consume();
//                            });
//
//                            // 6) put *only* the image into the slot
//                            slot.getChildren().setAll(imageOnly);
//                            success = true;
//                        } catch (IOException ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//                }
//                e.setDropCompleted(success);
//                e.consume();
//            });
//            slot.setOnMouseClicked(e -> {
//                if (e.getClickCount() == 2) {
//                    slot.getChildren().clear();
//                    slot.setStyle("-fx-border-color: #aaa; -fx-background-color: white;");
//                }
//            });
//            slot.setOnMouseEntered(e -> {
//                hoverInfo.setText("Slot " + slotIndex);
//                hoverInfo.setLayoutX(slot.getLayoutX() + 20);
//                hoverInfo.setLayoutY(slot.getLayoutY() + 20);
//                // show the hover info
//
//                hoverInfo.setVisible(true);
//                // highlight the slot
//                slot.setStyle(
//                        "-fx-border-color: #0288d1; -fx-background-color: #e0f7fa;"
//                );
//                slot.setEffect(new DropShadow(12, Color.DEEPSKYBLUE));
//            });
//
//            slot.setOnMouseExited(e -> {
//                hoverInfo.setVisible(false);
//                // remove highlight
//                slot.setStyle("-fx-border-color: red; -fx-background-color: white;");
//                slot.setEffect(new DropShadow(10, Color.GRAY));
//            });
//
//            // Position circulaire
//            double angle = 2 * Math.PI * i / MAX_SLOTS;
//            double x = centerX + radius * Math.cos(angle) - slot.getPrefWidth() / 2;
//            double y = centerY + radius * Math.sin(angle) - slot.getPrefHeight() / 2;
//            slot.setLayoutX(x);
//            slot.setLayoutY(y);
//
//
//
//            // Ajout au pane et à la map
//            myOverlay.getChildren().add(slot);
//            slots.put(slot.getId(), slot);
//        }
//    }
//
//    public SaveLoad getCurrentLayout() {
//        SaveLoad saveLoad = new SaveLoad("Pizza");
//        for (var entry : slots.entrySet()) {
//            String slotId = entry.getKey();
//            StackPane slot = entry.getValue();
//
//            if (slot.getChildren().isEmpty()) continue;
//
//            Node child = slot.getChildren().get(0);
//            ProductCard card = null;
//
//            // Case 1: you left the full loader in
//            if (child instanceof ProductCardLoader loader) {
//                card = loader.getController().getProductCard();
//
//                // Case 2: you dropped the image-only StackPane
//            } else if (child instanceof StackPane sp
//                    && sp.getUserData() instanceof ProductCard pc) {
//                card = pc;
//            }
//
//            if (card != null) {
//                saveLoad.addSlot(slotId, card);
//            }
//        }
//        return saveLoad;
//    }
//
//    public void applyLayout(SaveLoad saveLoad) {
//        // 1) Clear every slot first
//        for (StackPane slot : slots.values()) {
//            slot.getChildren().clear();
//        }
//
//        // 2) For each saved entry, rebuild its little image-only pane
//        saveLoad.getSlots().forEach((slotId, card) -> {
//            StackPane slot = slots.get(slotId);
//            if (slot == null || card == null) return;
//
//            // Create an ImageView from the saved path
//            ImageView iv = new ImageView(card.getImagePath());
//            iv.setPreserveRatio(true);
//            iv.setFitWidth(slot.getPrefWidth());
//            iv.setFitHeight(slot.getPrefHeight());
//
//            // Wrap in its own StackPane so clipping and dragging still work
//            StackPane imageOnly = new StackPane(iv);
//            imageOnly.setPrefSize(slot.getPrefWidth(), slot.getPrefHeight());
//            imageOnly.setUserData(card);
//
//            // Hover: show name & price
//            imageOnly.setOnMouseEntered(evt -> {
//                hoverInfo.setText(card.getName() + " — €" + String.format("%.2f", card.getPrice()));
//                // position it near the slot
//                Bounds b = slot.localToParent(slot.getBoundsInLocal());
//                hoverInfo.setLayoutX(b.getMinX());
//                hoverInfo.setLayoutY(b.getMinY() - hoverInfo.getHeight());
//                hoverInfo.setVisible(true);
//            });
//            imageOnly.setOnMouseExited(evt -> hoverInfo.setVisible(false));
//
//            // (Re)install drag-out logic if you want to move it again
//            imageOnly.setOnDragDetected(evt -> {
//                Dragboard db = imageOnly.startDragAndDrop(TransferMode.MOVE);
//                ClipboardContent cc = new ClipboardContent();
//                cc.putString(card.getName());
//                db.setContent(cc);
//                DragProduct.setCurrentProduct(card);
//                evt.consume();
//            });
//            imageOnly.setOnDragDone(evt -> {
//                if (evt.getTransferMode() == TransferMode.MOVE) {
//                    slot.getChildren().clear();
//                }
//                evt.consume();
//            });
//
//            // Put it back into the slot
//            slot.getChildren().add(imageOnly);
//        });
//    }
//
//
//
//
//}


// Deuxième version de PizzaPresetController.java

import editor.composants.hotdrink.model.DragProduct;
import editor.composants.savegarde.SaveLoad;
import editor.composants.templates.composants.hotdrink.model.ProductCard;
import editor.composants.templates.composants.hotdrink.model.ProductCardLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class PizzaPresetController {
    @FXML private AnchorPane myOverlay;
    @FXML private Label hoverInfo;

    private static final int MAX_SLOTS = 10;
    private Map<String,StackPane> slots = new LinkedHashMap<>();
    // Définir un rayon de base et un espacement supplémentaire
    private static final double BASE_RADIUS    = 150;
    private static final double SLOT_SPACING   = 60;

    @FXML
    private void initialize() {
        // make hoverInfo non-mouse-blocking
        hoverInfo.setMouseTransparent(true);
        hoverInfo.setVisible(false);

        // anchor overlay & hoverInfo to fill
        AnchorPane.setTopAnchor(   myOverlay, 0.0);
        AnchorPane.setBottomAnchor(myOverlay, 0.0);
        AnchorPane.setLeftAnchor(  myOverlay, 0.0);
        AnchorPane.setRightAnchor( myOverlay, 0.0);
        AnchorPane.setTopAnchor(hoverInfo,   0.0);
        AnchorPane.setBottomAnchor(hoverInfo,0.0);
        AnchorPane.setLeftAnchor(hoverInfo,  0.0);
        AnchorPane.setRightAnchor(hoverInfo, 0.0);



        // add gradient background for myOverlay
        myOverlay.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #ffe29f, #ffa99f);"
        );

        // center hoverInfo
        double x = (myOverlay.getWidth() - hoverInfo.getWidth()) / 2;
        double y = (myOverlay.getHeight() - hoverInfo.getHeight()) / 2;
        hoverInfo.setLayoutX(x);
        hoverInfo.setLayoutY(y);



        // initial slot creation once we have real size
        Platform.runLater(this::createSlots);
        myOverlay.widthProperty().addListener((o,old,nw)-> createSlots());
        myOverlay.heightProperty().addListener((o,old,nh)-> createSlots());
    }

    private void createSlots() {
        // clear old
        slots.values().forEach(myOverlay.getChildren()::remove);
        slots.clear();

        double w = myOverlay.getWidth(), h = myOverlay.getHeight();
        if (w<=0 || h<=0) return;

        double cx = w/2, cy = h/2;
        double radius = BASE_RADIUS + SLOT_SPACING;

        for (int i = 0; i < MAX_SLOTS; i++) {
            StackPane slot = new StackPane();
            String slotId = "slot"+i;
            slot.setId(slotId);
            slot.setPrefSize(100,100);
            slot.setStyle("-fx-border-color: red; -fx-background-color: white;");

            // drag over
            slot.setOnDragOver(e -> {
                System.out.println("Drag detected in slot "+slotId);
                if (e.getDragboard().hasString()) {
                    e.acceptTransferModes(TransferMode.COPY, TransferMode.MOVE);
                }
                e.consume();
            });

            // drop
            slot.setOnDragDropped(e -> {
                System.out.println("Dragged into slot "+slotId);
                Dragboard db = e.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    ProductCard card = DragProduct.getCurrentProduct();
                    if (card != null) {
                        placeCardInSlot(slot, card);
                        success = true;
                    }
                }
                e.setDropCompleted(success);
                e.consume();
            });

            // double-click to clear
            slot.setOnMouseClicked(e -> {
                if (e.getClickCount()==2) slot.getChildren().clear();
            });

            // hover placeholder
            int id = i+1;
            slot.setOnMouseEntered(e -> {
                hoverInfo.setText("Slot "+ id);
                hoverInfo.setVisible(true);
                hoverInfo.toFront();
            });
            slot.setOnMouseExited(e -> hoverInfo.setVisible(false));

            // position
            double angle = 2*Math.PI*i/MAX_SLOTS;
            double x = cx + radius*Math.cos(angle) - slot.getPrefWidth()/2;
            double y = cy + radius*Math.sin(angle) - slot.getPrefHeight()/2;
            slot.setLayoutX(x);
            slot.setLayoutY(y);


            myOverlay.getChildren().add(slot);
            slots.put(slotId, slot);
        }
    }

    private void placeCardInSlot(StackPane slot, ProductCard card) {
        try {
            // load full loader so image is in cache
            ProductCardLoader full = new ProductCardLoader();
            full.getController().setProductCard(card);

            Label lbname = (Label) full.lookup("#lbNom");
            Label lbprice = (Label) full.lookup("#lbPrix");
            lbname.setVisible(false);
            lbprice.setVisible(false);

            // extract just the image
//            ImageView iv = new ImageView(new Image(card.getImagePath()));
            ImageView iv = full.getController().getImgView();
            iv.setPreserveRatio(true);
            iv.setFitWidth(slot.getPrefWidth());
            iv.setFitHeight(slot.getPrefHeight());

            StackPane imageOnly = new StackPane(iv);
            imageOnly.setPrefSize(slot.getPrefWidth(), slot.getPrefHeight());
            imageOnly.setUserData(card);

            // hover shows name/price
            imageOnly.setOnMouseEntered(ev -> {
                hoverInfo.setText(card.getName()+" — €"+String.format("%.2f",card.getPrice()));
                hoverInfo.setVisible(true);

            });
            imageOnly.setOnMouseExited(ev -> hoverInfo.setVisible(false));

            // allow dragging back out
            imageOnly.setOnDragDetected(ev -> {
                Dragboard db2 = imageOnly.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent cc = new ClipboardContent();
                cc.putString(card.getName());
                db2.setContent(cc);
                DragProduct.setCurrentProduct(card);
                ev.consume();
            });
            imageOnly.setOnDragDone(ev -> {
                if (ev.getTransferMode()==TransferMode.MOVE) slot.getChildren().clear();
                ev.consume();
            });

            slot.getChildren().setAll(imageOnly);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

//    public SaveLoad getCurrentLayout() {
//        SaveLoad out = new SaveLoad("Pizza");
//        slots.forEach((id,slot)->{
//            if (!slot.getChildren().isEmpty()) {
//                Node c = slot.getChildren().get(0);
//                ProductCard card = null;
//                // if we left a loader
//                if (c instanceof ProductCardLoader pl) {
//                    card = pl.getController().getProductCard();
//                }
//                // or if it's our image-only pane
//                else if (c instanceof StackPane sp
//                        && sp.getUserData() instanceof ProductCard pc) {
//                    card = pc;
//                }
//                if (card!=null) out.addSlot(id, card);
//            }
//        });
//        return out;
//    }

    public SaveLoad getCurrentLayout() {
        SaveLoad saveLoad = new SaveLoad("Pizza");
        slots.forEach((slotId, slot) -> {
            if (!slot.getChildren().isEmpty()) {
                try {
                    Node child = slot.getChildren().get(0);
                    ProductCard card = null;

                    // Vérifier si le nœud est un ProductCardLoader
                    if (child instanceof ProductCardLoader loader) {
                        card = loader.getController().getProductCard();
                    }
                    // Vérifier si le nœud est une StackPane avec un ProductCard
                    else if (child instanceof StackPane sp && sp.getUserData() instanceof ProductCard pc) {
                        card = pc;
                    }

                    // Valider les données du ProductCard
                    if (card != null && card.getName() != null && !card.getName().isEmpty()
                            && card.getImagePath() != null && !card.getImagePath().isEmpty()) {
                        saveLoad.addSlot(slotId, card);
                        System.out.println("Slot sauvegardé : " + slotId + " avec la carte : " + card.getName());
                    } else {
                        System.err.println("Données invalides pour le slot : " + slotId);
                    }
                } catch (Exception e) {
                    System.err.println("Erreur lors de la sauvegarde du slot : " + slotId);
                    e.printStackTrace();
                }
            } else {
                System.out.println("Slot vide : " + slotId);
            }
        });
        return saveLoad;
    }

    public void applyLayout(SaveLoad save) {
        // clear all
        slots.values().forEach(s->s.getChildren().clear());
        // rebuild each saved
        save.getSlots().forEach((slotId,card)-> {
            StackPane slot = slots.get(slotId);
            if (slot!=null && card!=null) {
                placeCardInSlot(slot, card);
            }
        });
    }
}
