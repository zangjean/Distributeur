package editor.composants.pizza.controller;

import editor.composants.hotdrink.model.DragProduct;
import editor.composants.pizza.model.SpiralPane;
import editor.composants.templates.composants.hotdrink.model.ProductCard;
import editor.composants.templates.composants.hotdrink.model.ProductCardLoader;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PizzaListController {

    @FXML
    private TilePane tilePane;

    public PizzaListController() throws IOException {
        // Constructor logic if needed

    }

    @FXML
    public void initialize() {
        List<ProductCard> pizzaList = new ArrayList<>();
        initProductList(pizzaList);
        tilePane.setHgap(20);
        tilePane.setVgap(20);
        tilePane.setPadding(new Insets(20));
        tilePane.setBackground(new Background(
                new BackgroundFill(Color.web("#f0f0f8"), CornerRadii.EMPTY, Insets.EMPTY)
        ));
        tilePane.setPrefColumns(4);

        for (ProductCard p : pizzaList) {
            try {
                ProductCardLoader loader = new ProductCardLoader();
                loader.getController().setProductCard(p);

                // make it draggable:
                setupDragDetected(loader);
                setupHoverAnimation(loader);
                setupClickAnimation(loader);
                // Fond blanc arrondi + ombre portée + marge interne
                loader.setBackground(new Background(
                        new BackgroundFill(Color.WHITE, new CornerRadii(8), Insets.EMPTY)
                ));
                loader.setEffect(new DropShadow(8, Color.gray(0, 0.2)));
                TilePane.setMargin(loader, new Insets(10));
                tilePane.getChildren().add(loader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setupDragDetected(ProductCardLoader loader) {
        loader.setOnDragDetected(evt -> {
            System.out.println("Drag detected " + loader.getController().getProductCard().getName());
            // remember which product is being dragged
            DragProduct.setCurrentProduct(loader.getController().getProductCard());

            // start the drag & drop gesture
            Dragboard db = loader.startDragAndDrop(TransferMode.COPY);

            // put the product name (or ID) on the dragboard:
            ClipboardContent content = new ClipboardContent();
            content.putString(loader.getController().getProductCard().getName());
            db.setContent(content);

            // show a snapshot of the card under the cursor:
            db.setDragView(loader.snapshot(null, null));

            evt.consume();
        });
    }

    private void initProductList(List<ProductCard> pizzaList) {

        pizzaList.addAll(List.of(
                new ProductCard("Margherita", 8.0, "/editor/composants/templates/ressources/images/pizza/pizza1.jpg"),
                new ProductCard("Pepperoni", 9.0, "/editor/composants/templates/ressources/images/pizza/pizza2.jpg"),
                new ProductCard("Vegetarian", 7.5, "/editor/composants/templates/ressources/images/pizza/pizza3.jpg"),
                new ProductCard("BBQ Chicken", 10.0, "/editor/composants/templates/ressources/images/pizza/pizza4.jpg"),
                new ProductCard("Hawaiian", 9.5, "/editor/composants/templates/ressources/images/pizza/pizza5.jpg"),
                new ProductCard("Meat Lovers", 11.0, "/editor/composants/templates/ressources/images/pizza/pizza6.jpg"),
                new ProductCard("Seafood", 12.0, "/editor/composants/templates/ressources/images/pizza/pizza7.jpg"),
                new ProductCard("Buffalo Chicken", 10.5, "/editor/composants/templates/ressources/images/pizza/pizza8.jpg"),
                new ProductCard("Four Cheese", 9.0, "/editor/composants/templates/ressources/images/pizza/pizza9.jpg"),
                new ProductCard("Spinach and Feta", 8.5, "/editor/composants/templates/ressources/images/pizza/pizza10.jpg"),
                new ProductCard("Pesto Chicken", 10.0, "/editor/composants/templates/ressources/images/pizza/pizza11.jpg"),
                new ProductCard("Caprese", 9.0, "/editor/composants/templates/ressources/images/pizza/pizza12.jpg"),
                new ProductCard("Tandoori Chicken", 11.0, "/editor/composants/templates/ressources/images/pizza/pizza13.jpg"),
                new ProductCard("Greek", 9.5, "/editor/composants/templates/ressources/images/pizza/pizza14.jpg"),
                new ProductCard("BBQ Veggie", 8.0, "/editor/composants/templates/ressources/images/pizza/pizza15.jpg"),
                new ProductCard("Buffalo Veggie", 8.5, "/editor/composants/templates/ressources/images/pizza/pizza16.jpg")
        ));
    }

    // agrandissement au survol
    private void setupHoverAnimation(Node node) {
        ScaleTransition enter = new ScaleTransition(Duration.millis(200), node);
        enter.setToX(1.1);
        enter.setToY(1.1);
        ScaleTransition exit = new ScaleTransition(Duration.millis(200), node);
        exit.setToX(1.0);
        exit.setToY(1.0);
        node.setOnMouseEntered(e -> enter.playFromStart());
        node.setOnMouseExited(e -> exit.playFromStart());
    }

    // rotation au clic
    private void setupClickAnimation(Node node) {
        RotateTransition rotate = new RotateTransition(Duration.millis(500), node);
        rotate.setByAngle(360);
        node.setOnMouseClicked(e -> rotate.playFromStart());
    }
}
