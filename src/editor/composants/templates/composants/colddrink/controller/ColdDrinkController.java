package editor.composants.templates.composants.colddrink.controller;

import editor.composants.templates.composants.hotdrink.model.ProductCard;
import editor.composants.templates.composants.hotdrink.model.ProductCardLoader;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ColdDrinkController {

    @FXML
    private HBox hboxContainer;
    @FXML
    private ScrollPane scrollPane1, scrollPane2, scrollPane3;
    @FXML
    private VBox vbox1, vbox2, vbox3;


    public ColdDrinkController() {}

    // Example method
    public void initialize() {
        System.out.println("ColdDrinkController initialized");

        // Set the width of the scroll panes to 100% of the parent container
        scrollPane1.prefWidthProperty().bind(hboxContainer.widthProperty().multiply(0.33));
        scrollPane2.prefWidthProperty().bind(hboxContainer.widthProperty().multiply(0.33));
        scrollPane3.prefWidthProperty().bind(hboxContainer.widthProperty().multiply(0.33));


        // Default style
        String defaultStyle =""
                   ;

        // Style when hovered
        String hoverStyle =
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #4caf50, #2196f3, #ff5722, #8bc34a);" +
                        "-fx-background-radius: 12px; " +
                        "-fx-border-radius: 12px; " +
                        "-fx-border-color: transparent; " +
                        "-fx-border-width: 0;" +
                        "-fx-padding: 20;"
                ;

        // Create a list lif of product cards
        List<ProductCard> productCards = List.of(
                new ProductCard("Coca", 3.5, "/editor/composants/templates/ressources/images/colddrink/cd2.jpg"),
                new ProductCard("Pepsi", 4.0, "/editor/composants/templates/ressources/images/colddrink/cd3.jpg"),
                new ProductCard("Sprite", 4.5, "/editor/composants/templates/ressources/images/colddrink/cd4.jpg"),
                new ProductCard("Fanta", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd5.jpg"),
                new ProductCard("7Up", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd6.jpg"),
                new ProductCard("Schweppes", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd7.jpg"),
                new ProductCard("Red Bull", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd8.jpg"),
                new ProductCard("Monster", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd9.jpg"),
                new ProductCard("Burn", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd10.jpg"),
                new ProductCard("Coca Zero", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd11.jpg"),
                new ProductCard("Coca Light", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd12.jpg"),
                new ProductCard("Coca Cherry", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd13.jpg")

        );

        List<ProductCard> productsCol1 = new ArrayList<>(productCards); // Column 1 = normal
        List<ProductCard> productsCol2 = new ArrayList<>(productCards); // Column 2 = shifted
        List<ProductCard> productsCol3 = new ArrayList<>(productCards); // Column 3 = shuffled

        // Column 2: Rotate products by 3 positions
        Collections.rotate(productsCol2, 3); // Move 3 to the right

// Column 3: Shuffle the order
        Collections.shuffle(productsCol3);

        loadProducts(productsCol1, vbox1);
        loadProducts(productsCol2, vbox2);
        loadProducts(productsCol3, vbox3);

        startAutoScroll();

        }

    private void loadProducts(List<ProductCard> products, VBox container) {
        for (ProductCard product : products) {
            try {
                ProductCardLoader card = new ProductCardLoader();
                card.getController().setProductCard(product);
                container.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private Timeline scrollTimeline1, scrollTimeline2, scrollTimeline3;

    private void startAutoScroll() {
        scrollTimeline1 = createAutoScroll(scrollPane1, 1);   // DOWN
        scrollTimeline2 = createAutoScroll(scrollPane2, -1);  // UP
        scrollTimeline3 = createAutoScroll(scrollPane3, 1);   // DOWN

        scrollTimeline1.play();
        scrollTimeline2.play();
        scrollTimeline3.play();
    }

    private Timeline createAutoScroll(ScrollPane pane, int direction) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.02), event -> {
                    double vvalue = pane.getVvalue();
                    double step = 0.0003 * direction;

                    double newValue = vvalue + step;

                    if (newValue > 1.0) newValue = 0;
                    if (newValue < 0.0) newValue = 1.0;

                    pane.setVvalue(newValue);
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);

        // Pause on hover
        pane.setOnMouseEntered(e -> timeline.pause());
        pane.setOnMouseExited(e -> timeline.play());

        return timeline;
    }




}
