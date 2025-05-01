package editor.composants.colddrink.controller;

import editor.composants.hotdrink.model.DragProduct;
import editor.composants.templates.composants.hotdrink.model.ProductCard;
import editor.composants.templates.composants.hotdrink.model.ProductCardLoader;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;

import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import javafx.util.Duration;
import java.util.ArrayList;
import javafx.scene.effect.DropShadow;
import java.util.List;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;


public class ColdDrinkListController {

    @FXML
    private TilePane productTilePane;

    private final List<ProductCard> availableProducts = new ArrayList<>();

    public ColdDrinkListController() {
        // Constructor logic if needed
    }

    @FXML
    public void initialize() {
        // Initialize the product list and add products to the TilePane
        initializeProductList();
        displayProducts();

        productTilePane.setPrefColumns(3);

    }

    private void initializeProductList() {
        List<ProductCard> c = new ArrayList<>();
        c.add(new ProductCard("Coca", 3.5, "/editor/composants/templates/ressources/images/colddrink/cd2.jpg"));
        c.add(new ProductCard("Pepsi", 4.0, "/editor/composants/templates/ressources/images/colddrink/cd3.jpg"));
        c.add(new ProductCard("Sprite", 4.5, "/editor/composants/templates/ressources/images/colddrink/cd4.jpg"));
        c.add(new ProductCard("Fanta", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd5.jpg"));
        c.add(new ProductCard("7Up", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd6.jpg"));
        c.add(new ProductCard("Schweppes", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd7.jpg"));
        c.add(new ProductCard("Red Bull", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd8.jpg"));
        c.add(new ProductCard("Monster", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd9.jpg"));
        c.add(new ProductCard("Burn", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd10.jpg"));
        c.add(new ProductCard("Coca Zero", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd11.jpg"));
        c.add(new ProductCard("Coca Light", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd12.jpg"));
        c.add(new ProductCard("Coca Cherry", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd13.jpg"));
        availableProducts.addAll(c);
    }

    private void displayProducts() {
        for (ProductCard product : availableProducts) {
            try {
                ProductCardLoader card = new ProductCardLoader();
                card.getController().setProductCard(product);

                setupDragDetected(card);

                // Ajouter l'effet de survol
                setupHoverEffect(card);
                // Ajouter l'animation
                animateProductCard(card);

                productTilePane.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setupDragDetected(ProductCardLoader card) {
        card.setOnDragDetected(event -> {
            var db = card.startDragAndDrop(TransferMode.COPY);
            var content = new ClipboardContent();
            content.putString(card.getController().getProductCard().getName());
            db.setContent(content);

            DragProduct.setCurrentProduct(card.getController().getProductCard());

            SnapshotParameters snapParams = new SnapshotParameters();
            snapParams.setFill(javafx.scene.paint.Color.TRANSPARENT);
            db.setDragView(card.snapshot(snapParams, null));

            event.consume();
        });
    }

    public TilePane getProductTilePane() {
        return productTilePane;
    }

    private void animateProductCard(ProductCardLoader card) {
        // Animation de fondu
        FadeTransition fade = new FadeTransition(Duration.millis(500), card);
        fade.setFromValue(0);
        fade.setToValue(1);

        // Animation d'échelle
        ScaleTransition scale = new ScaleTransition(Duration.millis(500), card);
        scale.setFromX(0.8);
        scale.setFromY(0.8);
        scale.setToX(1);
        scale.setToY(1);

        // Lancer les animations en parallèle
        new ParallelTransition(fade, scale).play();
    }

    private void setupHoverEffect(ProductCardLoader card) {
        DropShadow shadow = new DropShadow(20, javafx.scene.paint.Color.web("#2196F3", 0.4));

        card.setOnMouseEntered(e -> {
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), card);
            scaleUp.setToX(1.08);
            scaleUp.setToY(1.08);
            scaleUp.play();
            card.setEffect(shadow);
        });

        card.setOnMouseExited(e -> {
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), card);
            scaleDown.setToX(1);
            scaleDown.setToY(1);
            scaleDown.play();
            card.setEffect(null);
        });
    }



    
}

