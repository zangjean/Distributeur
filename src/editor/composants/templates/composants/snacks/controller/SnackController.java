package editor.composants.templates.composants.snacks.controller;

import editor.composants.templates.composants.hotdrink.model.ProductCard;
import editor.composants.templates.composants.hotdrink.model.ProductCardLoader;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class SnackController {

    @FXML
    private ScrollPane topScrollpane, bottomScrollpane, leftScrollpane, rightScrollpane;

    @FXML
    private TilePane centerTilepane;
    @FXML
    private BorderPane root;

    @FXML
    private HBox topHbox, bottomHbox;
    @FXML
    private VBox leftVbox, rightVbox;

    private  List<ProductCard> snacks = new ArrayList<>();

    public  SnackController() {
        // Constructor
    }

    @FXML
    public void initialize() {
        // Initialize the controller
        System.out.println("SnackController initialized");

        // Set up the layout
        centerTilepane.setStyle("-fx-background-color: #f4bebe; -fx-padding: 10px; -fx-border-color: #000000; -fx-border-width: 2px;");
        topHbox.setStyle("-fx-background-color: #d8d6d6; -fx-padding: 10px; -fx-border-color: #000000; -fx-border-width: 2px;");
        bottomHbox.setStyle("-fx-background-color: #d8d6d6; -fx-padding: 10px; -fx-border-color: #000000; -fx-border-width: 2px;");
        leftVbox.setStyle("-fx-background-color: #c6e1c6; -fx-padding: 10px; -fx-border-color: #000000; -fx-border-width: 2px;");
        rightVbox.setStyle("-fx-background-color: #c6e1c6; -fx-padding: 10px; -fx-border-color: #000000; -fx-border-width: 2px;");
        root.setStyle("-fx-background-color: #dedefa; -fx-padding: 10px; -fx-border-color: #000000; -fx-border-width: 2px;");

        centerTilepane.setHgap(10);
        centerTilepane.setVgap(10);
        centerTilepane.setPrefColumns(2);



        // Set up the components

        root.setPrefSize( 900, 1000 );


       topHbox.prefHeightProperty().bind(root.heightProperty().divide(5));
        bottomHbox.prefHeightProperty().bind(root.heightProperty().divide(5));
        leftVbox.prefWidthProperty().bind(root.widthProperty().divide(5));
        rightVbox.prefWidthProperty().bind(root.widthProperty().divide(5));
        centerTilepane.prefWidthProperty().bind(root.widthProperty().divide(5).multiply(3));
        centerTilepane.prefHeightProperty().bind(root.heightProperty().divide(5).multiply(3));


        initSnacks();

        // Add the snacks to the topHbox

        for (ProductCard snack : snacks) {
            try {
                ProductCardLoader productCardLoader = new ProductCardLoader();

                productCardLoader.getController().setProductCard(snack);
                productCardLoader.prefWidthProperty().bind(topHbox.widthProperty());

                // Appliquer l'effet hover
                applyHoverEffect(productCardLoader);
                // Appliquer l'effet de combinaison
                applyCombinedEffect(productCardLoader);
                topHbox.getChildren().add(productCardLoader);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Add the snacks to the bottomHbox
        for (ProductCard snack : snacks) {
            try {
                ProductCardLoader productCardLoader = new ProductCardLoader();
                productCardLoader.getController().setProductCard(snack);
                productCardLoader.prefWidthProperty().bind(bottomHbox.widthProperty());
                // Appliquer l'effet hover
                applyHoverEffect(productCardLoader);
                // Appliquer l'effet de combinaison
                applyCombinedEffect(productCardLoader);
                bottomHbox.getChildren().add(productCardLoader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Add the snacks to the leftVbox
        for (ProductCard snack : snacks) {
            try {
                ProductCardLoader productCardLoader = new ProductCardLoader();
                productCardLoader.getController().setProductCard(snack);

                productCardLoader.prefWidthProperty().bind(leftVbox.widthProperty());
                // Appliquer l'effet hover
                applyHoverEffect(productCardLoader);
                // Appliquer l'effet de combinaison
                applyCombinedEffect(productCardLoader);
                leftVbox.getChildren().add(productCardLoader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Add the snacks to the rightVbox
        for (ProductCard snack : snacks) {
            try {
                ProductCardLoader productCardLoader = new ProductCardLoader();
                productCardLoader.getController().setProductCard(snack);
                productCardLoader.prefWidthProperty().bind(rightVbox.widthProperty());
                // Appliquer l'effet hover
                applyHoverEffect(productCardLoader);
                // Appliquer l'effet de combinaison
                applyCombinedEffect(productCardLoader);


                rightVbox.getChildren().add(productCardLoader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Add the snacks to the centerTilepane
        for (ProductCard snack : snacks) {
            try {
                ProductCardLoader productCardLoader = new ProductCardLoader();
                productCardLoader.getController().setProductCard(snack);

                // Appliquer l'effet hover
                applyHoverEffect(productCardLoader);
                centerTilepane.getChildren().add(productCardLoader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        // Animations
        initScrollingAnimationsV2();

    }
    private void initScrollingAnimationsV1() {
        // Animation de défilement horizontal pour topScrollpane
        Timeline topAnimation = new Timeline(
                new KeyFrame(Duration.millis(50), e -> {
                    double hvalue = topScrollpane.getHvalue();
                    if (hvalue >= 0.99) {
                        // Retour au début
                        topScrollpane.setHvalue(0);
                    } else {
                        topScrollpane.setHvalue(hvalue + 0.002);
                    }
                })
        );
        topAnimation.setCycleCount(Animation.INDEFINITE);
        topAnimation.play();

        // Animation de défilement horizontal pour bottomScrollpane (sens inverse)
        Timeline bottomAnimation = new Timeline(
                new KeyFrame(Duration.millis(40), e -> {
                    double hvalue = bottomScrollpane.getHvalue();
                    if (hvalue <= 0.01) {
                        // Retour à la fin
                        bottomScrollpane.setHvalue(1);
                    } else {
                        bottomScrollpane.setHvalue(hvalue - 0.0015);
                    }
                })
        );
        bottomAnimation.setCycleCount(Animation.INDEFINITE);
        bottomAnimation.play();

        // Animation de défilement vertical pour leftScrollpane
        Timeline leftAnimation = new Timeline(
                new KeyFrame(Duration.millis(60), e -> {
                    double vvalue = leftScrollpane.getVvalue();
                    if (vvalue >= 0.99) {
                        // Retour au début
                        leftScrollpane.setVvalue(0);
                    } else {
                        leftScrollpane.setVvalue(vvalue + 0.001);
                    }
                })
        );
        leftAnimation.setCycleCount(Animation.INDEFINITE);
        leftAnimation.play();

        // Animation de défilement vertical pour rightScrollpane (sens inverse)
        Timeline rightAnimation = new Timeline(
                new KeyFrame(Duration.millis(45), e -> {
                    double vvalue = rightScrollpane.getVvalue();
                    if (vvalue <= 0.01) {
                        // Retour à la fin
                        rightScrollpane.setVvalue(1);
                    } else {
                        rightScrollpane.setVvalue(vvalue - 0.0012);
                    }
                })
        );
        rightAnimation.setCycleCount(Animation.INDEFINITE);
        rightAnimation.play();
    }

    private void initScrollingAnimationsV2() {
        // Transition de défilement horizontal pour topScrollpane
        Timeline topAnimation = new Timeline(
                new KeyFrame(Duration.millis(100), e -> {
                    double hvalue = topScrollpane.getHvalue();
                    if (hvalue >= 0.99) {
                        topScrollpane.setHvalue(0); // Retour au début
                    } else {
                        topScrollpane.setHvalue(hvalue + 0.002);
                    }
                })
        );
        topAnimation.setCycleCount(Animation.INDEFINITE);
        topAnimation.play();

        // Transition de défilement horizontal pour bottomScrollpane (sens inverse)
        Timeline bottomAnimation = new Timeline(
                new KeyFrame(Duration.millis(100), e -> {
                    double hvalue = bottomScrollpane.getHvalue();
                    if (hvalue <= 0.01) {
                        bottomScrollpane.setHvalue(1); // Retour à la fin
                    } else {
                        bottomScrollpane.setHvalue(hvalue - 0.002);
                    }
                })
        );
        bottomAnimation.setCycleCount(Animation.INDEFINITE);
        bottomAnimation.play();

        // Transition de défilement vertical pour leftScrollpane
        Timeline leftAnimation = new Timeline(
                new KeyFrame(Duration.millis(100), e -> {
                    double vvalue = leftScrollpane.getVvalue();
                    if (vvalue >= 0.99) {
                        leftScrollpane.setVvalue(0); // Retour au début
                    } else {
                        leftScrollpane.setVvalue(vvalue + 0.002);
                    }
                })
        );
        leftAnimation.setCycleCount(Animation.INDEFINITE);
        leftAnimation.play();

        // Transition de défilement vertical pour rightScrollpane (sens inverse)
        Timeline rightAnimation = new Timeline(
                new KeyFrame(Duration.millis(100), e -> {
                    double vvalue = rightScrollpane.getVvalue();
                    if (vvalue <= 0.01) {
                        rightScrollpane.setVvalue(1); // Retour à la fin
                    } else {
                        rightScrollpane.setVvalue(vvalue - 0.002);
                    }
                })
        );
        rightAnimation.setCycleCount(Animation.INDEFINITE);
        rightAnimation.play();
    }

    private void applyHoverEffect(ProductCardLoader cardLoader) {
        // Effet d'échelle et d'ombre pour le hover
        cardLoader.setOnMouseEntered(event -> {
            // Appliquer une bordure colorée
            cardLoader.setStyle("-fx-border-color: #3498db; -fx-border-width: 2px; -fx-border-radius: 5px;");

            // Animation d'élévation et d'agrandissement
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), cardLoader);
            scaleTransition.setToX(1.05);
            scaleTransition.setToY(1.05);

            // Effet d'ombre
            DropShadow shadow = new DropShadow();
            shadow.setColor(Color.rgb(0, 0, 0, 0.5));
            shadow.setRadius(10);
            cardLoader.setEffect(shadow);

            scaleTransition.play();
        });

        cardLoader.setOnMouseExited(event -> {
            // Rétablir le style normal
            cardLoader.setStyle("-fx-border-color: transparent;");

            // Animation de retour à la taille normale
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), cardLoader);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);

            // Supprimer l'effet d'ombre
            cardLoader.setEffect(null);

            scaleTransition.play();
        });
    }

    private void applyFadeInEffect(Node node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), node);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    private void applyRotationEffect(Node node) {
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(1000), node);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.play();
    }

    private void applyTranslationEffect(Node node) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1000), node);
        translateTransition.setByX(50);
        translateTransition.setCycleCount(Animation.INDEFINITE);
        translateTransition.setAutoReverse(true);
        translateTransition.play();
    }

    private void applyZoomEffect(Node node) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), node);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);
        scaleTransition.setCycleCount(Animation.INDEFINITE);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
    }

    private void applyCombinedEffect(Node node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), node);
        fadeTransition.setFromValue(0.5);
        fadeTransition.setToValue(1.0);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(1000), node);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);

        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition, scaleTransition);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.setAutoReverse(true);
        parallelTransition.play();
    }

    private void applyHighlightEffect(Node node) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0.5);

        node.setOnMouseEntered(e -> node.setEffect(colorAdjust));
        node.setOnMouseExited(e -> node.setEffect(null));
    }



    private void initSnacks() {
        snacks.addAll(List.of(
                new ProductCard("Bimbim", 1.5, "/editor/composants/templates/ressources/images/snack/snack1.jpg"),
                new ProductCard("Chips", 2.0, "/editor/composants/templates/ressources/images/snack/snack2.jpg"),
                new ProductCard("Chocolat", 2.5, "/editor/composants/templates/ressources/images/snack/snack3.jpg"),
                new ProductCard("Bonbon", 1.0, "/editor/composants/templates/ressources/images/snack/snack4.jpg"),
                new ProductCard("Barre chocolatée", 2.0, "/editor/composants/templates/ressources/images/snack/snack5.jpg"),
                new ProductCard("Barre de céréales", 1.5, "/editor/composants/templates/ressources/images/snack/snack6.jpg"),
                new ProductCard("Potate Pomlisse", 2.5, "/editor/composants/templates/ressources/images/snack/snack7.jpg"),
                new ProductCard("Barre de chocolat", 2.5, "/editor/composants/templates/ressources/images/snack/snack8.jpg"),
                new ProductCard("Vico", 2.5, "/editor/composants/templates/ressources/images/snack/snack9.jpg"),
                new ProductCard("Brets", 2.5, "/editor/composants/templates/ressources/images/snack/snack10.jpg"),
                new ProductCard("M&M's", 2.5, "/editor/composants/templates/ressources/images/snack/snack11.jpg"),
                new ProductCard("Pringles", 2.5, "/editor/composants/templates/ressources/images/snack/snack12.jpg"),
                new ProductCard("Mikado", 2.5, "/editor/composants/templates/ressources/images/snack/snack13.jpg"),
                new ProductCard("Mentos", 2.5, "/editor/composants/templates/ressources/images/snack/snack14.jpg"),
                new ProductCard("Tic Tac", 2.5, "/editor/composants/templates/ressources/images/snack/snack15.jpg")

        ));
    }
}
