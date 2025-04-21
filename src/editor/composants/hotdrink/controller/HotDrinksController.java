package editor.composants.hotdrink.controller;

import editor.composants.templates.composants.hotdrink.model.ProductCard;
import editor.composants.templates.composants.hotdrink.model.ProductCardLoader;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class HotDrinksController {
    @FXML
    private TilePane tilePane;

    private Set<ProductCard> productCards = new HashSet<>();

    public HotDrinksController() {

    }

    @FXML
    public void initialize() throws IOException {
        // Initialize the controller here
        System.out.println("HotDrinksController initialized");

        // set the tilePane to be scrollable
        tilePane.setPrefSize(800, 700);
        tilePane.setPrefColumns(4);

        // add product cards to the tilePane
        int delay = 0;
        int step = 60;
        //int index = 0;
        loadProductCard(productCards);
        for (ProductCard productCard : productCards) {
            ProductCardLoader productCardLoader = new ProductCardLoader();

            // Set un peu de style
            productCardLoader.setStyle("-fx-background-color: #fae0e0; -fx-border-color: black; -fx-border-width: 1px; -fx-border-style: solid; -fx-border-radius: 5px");

            productCardLoader.setOnMouseEntered(event -> {
                productCardLoader.setStyle("-fx-background-color: #f8d9d9; -fx-border-color: orange ; -fx-border-width: 1px; -fx-border-style: solid; -fx-border-radius: 5px");
            });
            productCardLoader.setOnMouseExited(event -> {
                productCardLoader.setStyle("-fx-background-color: #fae0e0; -fx-border-color: black; -fx-border-width: 1px; -fx-border-style: solid; -fx-border-radius: 5px");
            });

            productCardLoader.getController().setProductCard(productCard);

            // Setup drag and drop functionality
            setupDragAndDrop(productCardLoader, productCard);

            tilePane.getChildren().add(productCardLoader);

            // Animate the card appearance
            animateCardAppearanceV2(productCardLoader, delay);
            //animateCardAppearanceV2(productCardLoader, index * 100);

            delay += step;
            //index++
        }

    }


    public void loadProductCard(Set<ProductCard> productCards) {
        // Add product cards to the set
        productCards.addAll(Set.of(
                new ProductCard("Basieu", 3.00, "/editor/composants/templates/ressources/images/hotdrink/basieu.jpg"),
                new ProductCard("Cafe 3", 3.25, "/editor/composants/templates/ressources/images/hotdrink/cafe3.jpg"),
                new ProductCard("Cafe 5", 3.25, "/editor/composants/templates/ressources/images/hotdrink/cafe5.jpg"),
                new ProductCard("Cafe 6", 3.25, "/editor/composants/templates/ressources/images/hotdrink/cafe6.jpg"),
                new ProductCard("Cafe 7", 3.25, "/editor/composants/templates/ressources/images/hotdrink/cafe7.jpg"),
                new ProductCard("Cafe 8", 3.25, "/editor/composants/templates/ressources/images/hotdrink/cafe8.jpg"),
                new ProductCard("Cafe 11", 3.25, "/editor/composants/templates/ressources/images/hotdrink/cafe11.jpg"),
                new ProductCard("Cafe 12", 3.25, "/editor/composants/templates/ressources/images/hotdrink/cafe12.jpg"),
                new ProductCard("Cafe 13", 3.25, "/editor/composants/templates/ressources/images/hotdrink/cafe13.jpg"),
                new ProductCard("Cafe 14", 3.25, "/editor/composants/templates/ressources/images/hotdrink/cafe14.jpg"),
                new ProductCard("Cafe 15", 3.25, "/editor/composants/templates/ressources/images/hotdrink/cafe15.jpg"),
                new ProductCard("Cafe au Creme", 3.50, "/editor/composants/templates/ressources/images/hotdrink/cafe_au_creme.jpg"),
                new ProductCard("Cafe au Lait", 3.00, "/editor/composants/templates/ressources/images/hotdrink/cafe_au_lait.jpg"),
                new ProductCard("Cafe Creeme", 3.50, "/editor/composants/templates/ressources/images/hotdrink/cafe_creeme.jpg"),
                new ProductCard("Cafe Cremee", 3.50, "/editor/composants/templates/ressources/images/hotdrink/cafe_cremee.jpg"),
                new ProductCard("Cafe Tiramisu", 3.75, "/editor/composants/templates/ressources/images/hotdrink/cafe_tiramisu.jpg"),
                new ProductCard("Cafe Wisky", 4.00, "/editor/composants/templates/ressources/images/hotdrink/cafe_wisky.jpg"),
                new ProductCard("Café2", 3.00, "/editor/composants/templates/ressources/images/hotdrink/café2.jpg"),
                new ProductCard("Coldbrew", 3.50, "/editor/composants/templates/ressources/images/hotdrink/coldbrew.jpg"),
                new ProductCard("Cortado", 3.00, "/editor/composants/templates/ressources/images/hotdrink/cortado.jpg"),
                new ProductCard("Fin Coffee", 3.25, "/editor/composants/templates/ressources/images/hotdrink/fin_coffee.jpg"),
                new ProductCard("Flat White", 3.00, "/editor/composants/templates/ressources/images/hotdrink/flatwhite.jpg"),
                new ProductCard("Halloween Cafe", 3.75, "/editor/composants/templates/ressources/images/hotdrink/halloween_cafe.jpg"),
                new ProductCard("Hot Chocolat", 2.50, "/editor/composants/templates/ressources/images/hotdrink/hotchocolat.jpg"),
                new ProductCard("Hot Wine", 4.00, "/editor/composants/templates/ressources/images/hotdrink/hotwine.jpg"),
                new ProductCard("Irish Café", 4.25, "/editor/composants/templates/ressources/images/hotdrink/irish_café.jpg"),
                new ProductCard("Latte", 3.00, "/editor/composants/templates/ressources/images/hotdrink/latte.jpg"),
                new ProductCard("Latte Macchiato", 3.50, "/editor/composants/templates/ressources/images/hotdrink/latte_machiato.jpg"),
                new ProductCard("Macchiato", 3.25, "/editor/composants/templates/ressources/images/hotdrink/machiato.jpg"),
                new ProductCard("Mocha", 3.50, "/editor/composants/templates/ressources/images/hotdrink/mocha.jpg"),
                new ProductCard("Soupe", 2.75, "/editor/composants/templates/ressources/images/hotdrink/soupe.jpg"),
                new ProductCard("Expresso", 2.50, "/editor/composants/templates/ressources/images/hotdrink/expresso.jpg")
        ));

    }

    private void animateCardAppearance(ProductCardLoader card) {
        card.setOpacity(0); // Start transparent
        card.setScaleX(0.95);
        card.setScaleY(0.95);

        FadeTransition fade = new FadeTransition(Duration.millis(300), card);
        fade.setFromValue(0);
        fade.setToValue(1);

        ScaleTransition scale = new ScaleTransition(Duration.millis(300), card);
        scale.setFromX(0.95);
        scale.setFromY(0.95);
        scale.setToX(1);
        scale.setToY(1);

        ParallelTransition animation = new ParallelTransition(fade, scale);
        animation.play();
    }

    private void animateCardAppearanceV1(Node node, int delayMillis) {
        FadeTransition fade = new FadeTransition(Duration.millis(300), node);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setDelay(Duration.millis(delayMillis));

        ScaleTransition scale = new ScaleTransition(Duration.millis(300), node);
        scale.setFromX(0.95);
        scale.setFromY(0.95);
        scale.setToX(1);
        scale.setToY(1);
        scale.setDelay(Duration.millis(delayMillis));

        new ParallelTransition(fade, scale).play();
    }
    private void animateCardAppearanceV2(Node node, int delayMillis) {
        node.setOpacity(0);
        node.setScaleX(0.85);
        node.setScaleY(0.85);

        Platform.runLater(() -> {
            FadeTransition fade = new FadeTransition(Duration.millis(400), node);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.setDelay(Duration.millis(delayMillis));

            ScaleTransition scale = new ScaleTransition(Duration.millis(400), node);
            scale.setFromX(0.85);
            scale.setFromY(0.85);
            scale.setToX(1);
            scale.setToY(1);
            scale.setDelay(Duration.millis(delayMillis));

            new ParallelTransition(fade, scale).play();
        });
    }

    //
    private void setupDragAndDrop(ProductCardLoader cardLoader, ProductCard productCard) {
        cardLoader.setOnDragDetected(event -> {
            Dragboard db = cardLoader.startDragAndDrop(TransferMode.COPY);

            ClipboardContent content = new ClipboardContent();
            content.putString(productCard.getName()); // You can use ID or serialize JSON if needed
            db.setContent(content);

            // Optional: use image preview while dragging
            SnapshotParameters snapParams = new SnapshotParameters();
            snapParams.setFill(Color.TRANSPARENT);
            db.setDragView(cardLoader.snapshot(snapParams, null));

            event.consume();
        });
    }






}
