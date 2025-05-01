package editor.composants.templates.composants.pizza.controller;

import editor.composants.hotdrink.model.DragProduct;
import editor.composants.templates.composants.hotdrink.model.ProductCard;
import editor.composants.templates.composants.hotdrink.model.ProductCardLoader;
import editor.composants.templates.controller.PizzaPresetController;
import javafx.animation.*;
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
import javafx.scene.layout.VBox;
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

//    private void placeCard() {
//        // clear any old loaders
//        myOverlay.getChildren().removeIf(n -> n instanceof ProductCardLoader);
//
//        double w = myOverlay.getWidth();
//        double h = myOverlay.getHeight();
//        double cx = w / 2;
//        double cy = h / 2;
//        double margin = 20;
//        double sz = Math.max(60, Math.min(100, (Math.min(w,h) - 2*margin)/3));
//        double r  = (Math.min(w,h) - sz)/2 - margin;
//
//
//       int n = pizzas.size();
//
//        for (int i = 0; i < n; i++) {
//            final int idx = i;
//            double angle = Math.toRadians(-90 + i * (360.0 / n));
//            double x = cx + Math.cos(angle) * r - sz / 2;
//            double y = cy + Math.sin(angle) * r - sz / 2;
//
//
//            try {
//                ProductCardLoader loader = new ProductCardLoader();
//
//
//                loader.getController().setProductCard(pizzas.get(i));
//
//                String info = String.format("%s — €%.2f",
//                        pizzas.get(i).getName(),
//                        pizzas.get(i).getPrice());
//
//                Label lbname = (Label) loader.lookup("#lbNom");
//                Label lbprice = (Label) loader.lookup("#lbPrix");
//
//                lbname.setVisible(false);
//                lbprice.setVisible(false);
//
//                // hover effect: scale + show a tooltip
//                ScaleTransition up = new ScaleTransition(Duration.millis(200), loader);
//                up.setToX(1.1); up.setToY(1.1);
//                ScaleTransition down = new ScaleTransition(Duration.millis(200), loader);
//                down.setToX(1.0); down.setToY(1.0);
//
//                loader.setOnMouseEntered(e -> {
//
//                    up.playFromStart();
//                    hoverInfo.setText(info);
//                    hoverInfo.toFront();
//                    hoverInfo.setVisible(true);
//
//
//
//                });
//                loader.setOnMouseExited(e -> {
//                    down.playFromStart();
//                    hoverInfo.setVisible(false);
//
//                });
//
//                loader.setOnMouseClicked(e -> {
//                    // show the image with real size
//                    ProductCard card = pizzas.get(idx);
//                   Image img = new Image(card.getImagePath());
//                    ImageView imageView = new ImageView(img);
//                    imageView.setPreserveRatio(true);
//                    imageView.setFitWidth(img.getWidth());
//                    imageView.setFitHeight(img.getHeight());
//
//                    StackPane pane = new StackPane(imageView);
//                    pane.setStyle("-fx-background-color: rgba(0,0,0,0.8);");
//                    pane.setPadding(new javafx.geometry.Insets(20));
//
//                    Scene scene = new Scene(pane, img.getWidth() + 40, img.getHeight() + 40, javafx.scene.paint.Color.TRANSPARENT);
//
//                    Stage popup = new Stage();
//                    popup.initModality(Modality.APPLICATION_MODAL);
//                    popup.setTitle(card.getName());
//                    popup.setScene(scene);
//                    popup.show();
//
//                    // Fermer la fenêtre au clic
//                    pane.setOnMouseClicked(ev -> popup.close());
//
//                });
//
//                loader.setLayoutX(x);
//                loader.setLayoutY(y);
//                loader.setPrefSize(sz, sz);
//                myOverlay.getChildren().add(loader);
//
//                // Animation de rotation
//                RotateTransition rt = new RotateTransition(Duration.millis(3000), loader);
//                rt.setFromAngle(0); rt.setToAngle(360);
//
//                rt.setCycleCount(1);
//                rt.play();
//
//
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }

    private void placeCards() {
        // Nettoyer les anciens loaders
        myOverlay.getChildren().removeIf(n -> n instanceof ProductCardLoader);

        double w = myOverlay.getWidth();
        double h = myOverlay.getHeight();
        double cx = w / 2;
        double cy = h / 2;
        double margin = 20;
        double sz = Math.max(60, Math.min(100, (Math.min(w,h) - 2*margin)/3));
        double r  = (Math.min(w,h) - sz)/2 - margin;

        int n = pizzas.size();

        // Ajouter un cercle central avec effet de pulsation
        Circle centerCircle = new Circle(cx, cy, r/4, Color.rgb(255, 255, 255, 0.3));
        DropShadow glow = new DropShadow(10, Color.GOLD);
        centerCircle.setEffect(glow);

        ScaleTransition pulse = new ScaleTransition(Duration.seconds(2), centerCircle);
        pulse.setFromX(0.8);
        pulse.setFromY(0.8);
        pulse.setToX(1.1);
        pulse.setToY(1.1);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setAutoReverse(true);
        pulse.play();

        myOverlay.getChildren().add(centerCircle);

        // Animation séquentielle pour l'apparition des cartes
        SequentialTransition sequentialTransition = new SequentialTransition();

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

                // Position initiale hors écran pour animation d'entrée
                loader.setOpacity(0);
                loader.setLayoutX(cx - sz/2);
                loader.setLayoutY(cy - sz/2);
                loader.setPrefSize(sz, sz);
                myOverlay.getChildren().add(loader);

                // Animation d'entrée
                ParallelTransition entry = new ParallelTransition();

                FadeTransition fadeIn = new FadeTransition(Duration.millis(300), loader);
                fadeIn.setFromValue(0);
                fadeIn.setToValue(1);

                TranslateTransition moveIn = new TranslateTransition(Duration.millis(500), loader);
                moveIn.setFromX(0);
                moveIn.setFromY(0);
                moveIn.setToX(x - cx + sz/2);
                moveIn.setToY(y - cy + sz/2);

                entry.getChildren().addAll(fadeIn, moveIn);

                // Ajouter un délai différent pour chaque carte
                PauseTransition delay = new PauseTransition(Duration.millis(i * 100));
                SequentialTransition cardSequence = new SequentialTransition(delay, entry);
                sequentialTransition.getChildren().add(cardSequence);

                // Effets de survol améliorés
                ScaleTransition up = new ScaleTransition(Duration.millis(200), loader);
                up.setToX(1.2);
                up.setToY(1.2);

                ScaleTransition down = new ScaleTransition(Duration.millis(200), loader);
                down.setToX(1.0);
                down.setToY(1.0);

                DropShadow hoverShadow = new DropShadow();
                hoverShadow.setColor(Color.GOLD);
                hoverShadow.setRadius(15);
                hoverShadow.setSpread(0.5);

                loader.setOnMouseEntered(e -> {
                    up.playFromStart();
                    loader.setEffect(hoverShadow);
                    hoverInfo.setText(info);
//                    hoverInfo.setStyle("-fx-background-color: rgba(0,0,0,0.7); -fx-padding: 10px; -fx-background-radius: 5px;");
                    hoverInfo.setStyle(
                            "-fx-background-color: rgba(0,0,0,0.8); " +
                                    "-fx-padding: 15px; " +
                                    "-fx-background-radius: 10px; " +
                                    "-fx-font-size: 16px; " +
                                    "-fx-text-fill: white; " +
                                    "-fx-font-weight: bold; " +
                                    "-fx-effect: dropshadow(three-pass-box, rgba(255,215,0,0.6), 10, 0, 0, 0);"
                    );
                    // Positionnement au centre
                    hoverInfo.setTranslateX(0);
                    hoverInfo.setTranslateY(0);

                    // Animation de l'info
                    FadeTransition fadeInInfo = new FadeTransition(Duration.millis(150), hoverInfo);
                    fadeInInfo.setFromValue(0);
                    fadeInInfo.setToValue(1);
                    fadeInInfo.play();
                    hoverInfo.toFront();
                    hoverInfo.setVisible(true);

                });

                loader.setOnMouseExited(e -> {
                    down.playFromStart();
                    loader.setEffect(null);
                    hoverInfo.setVisible(false);
                });

                loader.setOnMouseClicked(e -> {
                    showDetailedView(pizzas.get(idx));
                });

                // Animation de rotation subtile et continue
                RotateTransition rt = new RotateTransition(Duration.seconds(10 + i), loader);
                rt.setFromAngle(-5);
                rt.setToAngle(5);
                rt.setInterpolator(Interpolator.EASE_BOTH);
                rt.setCycleCount(Animation.INDEFINITE);
                rt.setAutoReverse(true);
                rt.play();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        sequentialTransition.play();
    }

    private void showDetailedView(ProductCard card) {
        // Affichage amélioré avec animation
        Image img = new Image(card.getImagePath());
        ImageView imageView = new ImageView(img);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(img.getWidth());
        imageView.setFitHeight(img.getHeight());

        Label titleLabel = new Label(card.getName());
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label priceLabel = new Label(String.format("€%.2f", card.getPrice()));
        priceLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: gold;");

        VBox infoBox = new VBox(10, titleLabel, priceLabel);
        infoBox.setAlignment(Pos.CENTER);
        infoBox.setPadding(new javafx.geometry.Insets(20));

        VBox content = new VBox(20, infoBox, imageView);
        content.setAlignment(Pos.CENTER);

        StackPane pane = new StackPane(content);
        pane.setStyle("-fx-background-color: rgba(0,0,0,0.9);");
        pane.setPadding(new javafx.geometry.Insets(30));

        Scene scene = new Scene(pane, img.getWidth() + 100, img.getHeight() + 150, Color.TRANSPARENT);

        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle(card.getName());
        popup.setScene(scene);

        // Animation d'entrée pour la popup
        content.setScaleX(0.7);
        content.setScaleY(0.7);
        content.setOpacity(0);

        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(300), content);
        scaleIn.setToX(1.0);
        scaleIn.setToY(1.0);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), content);
        fadeIn.setToValue(1.0);

        ParallelTransition parallelIn = new ParallelTransition(scaleIn, fadeIn);

        popup.setOnShown(e -> parallelIn.play());
        popup.show();

        // Fermer la fenêtre au clic avec animation
        pane.setOnMouseClicked(ev -> {
            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), content);
            scaleOut.setToX(0.7);
            scaleOut.setToY(0.7);

            FadeTransition fadeOut = new FadeTransition(Duration.millis(200), content);
            fadeOut.setToValue(0);

            ParallelTransition parallelOut = new ParallelTransition(scaleOut, fadeOut);
            parallelOut.setOnFinished(e -> popup.close());
            parallelOut.play();
        });
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
