package editor.composants.templates.composants.pizza.controller;

import editor.composants.hotdrink.model.DragProduct;
import editor.composants.templates.composants.hotdrink.model.ProductCard;
import editor.composants.templates.composants.hotdrink.model.ProductCardLoader;
import editor.composants.templates.controller.PizzaPresetController;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PizzaController {


    @FXML
    private Label hoverInfo;
    @FXML
    private AnchorPane myOverlay;

    private List<ProductCard> pizzas = new ArrayList<>();


    public PizzaController() {
        // Constructor
    }

    @FXML
    private void initialize() {

        loadPizza();

        // Add gradient background for myOverlay
        myOverlay.setStyle(
                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #ffe29f, #ffa99f);"
        );
        hoverInfo.mouseTransparentProperty().setValue(true);
        hoverInfo.setTextFill(Color.DARKCYAN);

        // anchor the overlay to all four sides
        AnchorPane.setTopAnchor(   myOverlay, 0.0);
        AnchorPane.setBottomAnchor(myOverlay, 0.0);
        AnchorPane.setLeftAnchor(  myOverlay, 0.0);
        AnchorPane.setRightAnchor( myOverlay, 0.0);

        // acchor hoverInfo
        // fill overlay
        AnchorPane.setTopAnchor(hoverInfo,   0.0);
        AnchorPane.setBottomAnchor(hoverInfo,0.0);
        AnchorPane.setLeftAnchor(hoverInfo,  0.0);
        AnchorPane.setRightAnchor(hoverInfo, 0.0);


        Platform.runLater(this::placeCards);
        // whenever the pane is sized (initial and on resize), re-layout the cards
        myOverlay.widthProperty().addListener((obs, o, nw) -> placeCards());
        myOverlay.heightProperty().addListener((obs, o, nh) -> placeCards());

    }

    private void placeCards() {
        // clear any old loaders
        myOverlay.getChildren().removeIf(n -> n instanceof ProductCardLoader);

        double w = myOverlay.getWidth();
        double h = myOverlay.getHeight();
        double cx = w / 2;
        double cy = h / 2;
        double margin = 20;
        double sz = Math.max(60, Math.min(100, (Math.min(w,h) - 2*margin)/3));
        double r  = (Math.min(w,h) - sz)/2 - margin;


       int n = pizzas.size();

        for (int i = 0; i < n; i++) {
            final int idx = i;
            double angle = Math.toRadians(-90 + i * (360.0 / n));
            double x = cx + Math.cos(angle) * r - sz / 2;
            double y = cy + Math.sin(angle) * r - sz / 2;


            try {
                ProductCardLoader loader = new ProductCardLoader();


                loader.getController().setProductCard(pizzas.get(i));

                String info = String.format("%s — €%.2f",
                        pizzas.get(i).getName(),
                        pizzas.get(i).getPrice());

                Label lbname = (Label) loader.lookup("#lbNom");
                Label lbprice = (Label) loader.lookup("#lbPrix");

                lbname.setVisible(false);
                lbprice.setVisible(false);

                // hover effect: scale + show a tooltip
                ScaleTransition up = new ScaleTransition(Duration.millis(200), loader);
                up.setToX(1.1); up.setToY(1.1);
                ScaleTransition down = new ScaleTransition(Duration.millis(200), loader);
                down.setToX(1.0); down.setToY(1.0);

                loader.setOnMouseEntered(e -> {

                    up.playFromStart();
                    hoverInfo.setText(info);
                    hoverInfo.toFront();
                    hoverInfo.setVisible(true);



                });
                loader.setOnMouseExited(e -> {
                    down.playFromStart();
                    hoverInfo.setVisible(false);

                });

                loader.setOnMouseClicked(e -> {
                    // show the image with real size
                    ProductCard card = pizzas.get(idx);
                   Image img = new Image(card.getImagePath());
                    ImageView imageView = new ImageView(img);
                    imageView.setPreserveRatio(true);
                    imageView.setFitWidth(img.getWidth());
                    imageView.setFitHeight(img.getHeight());

                    StackPane pane = new StackPane(imageView);
                    pane.setStyle("-fx-background-color: rgba(0,0,0,0.8);");
                    pane.setPadding(new javafx.geometry.Insets(20));

                    Scene scene = new Scene(pane, img.getWidth() + 40, img.getHeight() + 40, javafx.scene.paint.Color.TRANSPARENT);

                    Stage popup = new Stage();
                    popup.initModality(Modality.APPLICATION_MODAL);
                    popup.setTitle(card.getName());
                    popup.setScene(scene);
                    popup.show();

                    // Fermer la fenêtre au clic
                    pane.setOnMouseClicked(ev -> popup.close());

                });

                loader.setLayoutX(x);
                loader.setLayoutY(y);
                loader.setPrefSize(sz, sz);
                myOverlay.getChildren().add(loader);

                // Animation de rotation
                RotateTransition rt = new RotateTransition(Duration.millis(3000), loader);
                rt.setFromAngle(0); rt.setToAngle(360);

                rt.setCycleCount(1);
                rt.play();


            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    private void loadPizza()
    {
        pizzas.addAll(List.of(
                new ProductCard("Margherita", 5.00, "/editor/composants/templates/ressources/images/pizza/pizza1.jpg"),
                new ProductCard("Pepperoni", 6.50, "/editor/composants/templates/ressources/images/pizza/pizza2.jpg"),
                new ProductCard("Vegetarian", 5.50, "/editor/composants/templates/ressources/images/pizza/pizza3.jpg"),
                new ProductCard("BBQ Chicken", 7.00, "/editor/composants/templates/ressources/images/pizza/pizza4.jpg"),
                new ProductCard("Hawaiian", 6.50, "/editor/composants/templates/ressources/images/pizza/pizza5.jpg"),
                new ProductCard("Meat Lovers", 8.00, "/editor/composants/templates/ressources/images/pizza/pizza6.jpg"),
                new ProductCard("Seafood", 7.50, "/editor/composants/templates/ressources/images/pizza/pizza7.jpg"),
                new ProductCard("Buffalo Chicken", 7.00, "/editor/composants/templates/ressources/images/pizza/pizza8.jpg"),
                new ProductCard("Spinach and Feta", 6.00, "/editor/composants/templates/ressources/images/pizza/pizza9.jpg"),
                new ProductCard("Four Cheese", 7.00, "/editor/composants/templates/ressources/images/pizza/pizza10.jpg")
        ));
    }

}
