package editor.composants.templates.composants.colddrink.controller;

import editor.composants.templates.composants.hotdrink.model.ProductCard;
import editor.composants.templates.composants.hotdrink.model.ProductCardLoader;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    private Timeline scrollTimeline1, scrollTimeline2, scrollTimeline3;



    public ColdDrinkController() {}

    // Example method
//    public void initialize() {
//        System.out.println("ColdDrinkController initialized");
//
//        // Set the width of the scroll panes to 100% of the parent container
//        scrollPane1.prefWidthProperty().bind(hboxContainer.widthProperty().multiply(0.33));
//        scrollPane2.prefWidthProperty().bind(hboxContainer.widthProperty().multiply(0.33));
//        scrollPane3.prefWidthProperty().bind(hboxContainer.widthProperty().multiply(0.33));
//
//
//        // Default style
//        String defaultStyle =""
//                   ;
//
//        // Style when hovered
//        String hoverStyle =
//                "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #4caf50, #2196f3, #ff5722, #8bc34a);" +
//                        "-fx-background-radius: 12px; " +
//                        "-fx-border-radius: 12px; " +
//                        "-fx-border-color: transparent; " +
//                        "-fx-border-width: 0;" +
//                        "-fx-padding: 20;"
//                ;
//
//        // Create a list lif of product cards
//        List<ProductCard> productCards = List.of(
//                new ProductCard("Coca", 3.5, "/editor/composants/templates/ressources/images/colddrink/cd2.jpg"),
//                new ProductCard("Pepsi", 4.0, "/editor/composants/templates/ressources/images/colddrink/cd3.jpg"),
//                new ProductCard("Sprite", 4.5, "/editor/composants/templates/ressources/images/colddrink/cd4.jpg"),
//                new ProductCard("Fanta", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd5.jpg"),
//                new ProductCard("7Up", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd6.jpg"),
//                new ProductCard("Schweppes", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd7.jpg"),
//                new ProductCard("Red Bull", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd8.jpg"),
//                new ProductCard("Monster", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd9.jpg"),
//                new ProductCard("Burn", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd10.jpg"),
//                new ProductCard("Coca Zero", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd11.jpg"),
//                new ProductCard("Coca Light", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd12.jpg"),
//                new ProductCard("Coca Cherry", 3.0, "/editor/composants/templates/ressources/images/colddrink/cd13.jpg")
//
//        );
//
//        List<ProductCard> productsCol1 = new ArrayList<>(productCards); // Column 1 = normal
//        List<ProductCard> productsCol2 = new ArrayList<>(productCards); // Column 2 = shifted
//        List<ProductCard> productsCol3 = new ArrayList<>(productCards); // Column 3 = shuffled
//
//        // Column 2: Rotate products by 3 positions
//        Collections.rotate(productsCol2, 3); // Move 3 to the right
//
//// Column 3: Shuffle the order
//        Collections.shuffle(productsCol3);
//
//        loadProducts(productsCol1, vbox1);
//        loadProducts(productsCol2, vbox2);
//        loadProducts(productsCol3, vbox3);
//
//        startAutoScroll();
//
//        }
//
//    private void loadProducts(List<ProductCard> products, VBox container) {
//        for (ProductCard product : products) {
//            try {
//                ProductCardLoader card = new ProductCardLoader();
//                card.getController().setProductCard(product);
//                container.getChildren().add(card);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//
//    private void startAutoScroll() {
//        scrollTimeline1 = createAutoScroll(scrollPane1, 1);   // DOWN
//        scrollTimeline2 = createAutoScroll(scrollPane2, -1);  // UP
//        scrollTimeline3 = createAutoScroll(scrollPane3, 1);   // DOWN
//
//        scrollTimeline1.play();
//        scrollTimeline2.play();
//        scrollTimeline3.play();
//    }
//
//    private Timeline createAutoScroll(ScrollPane pane, int direction) {
//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.seconds(0.02), event -> {
//                    double vvalue = pane.getVvalue();
//                    double step = 0.0003 * direction;
//
//                    double newValue = vvalue + step;
//
//                    if (newValue > 1.0) newValue = 0;
//                    if (newValue < 0.0) newValue = 1.0;
//
//                    pane.setVvalue(newValue);
//                })
//        );
//        timeline.setCycleCount(Animation.INDEFINITE);
//
//        // Pause on hover
//        pane.setOnMouseEntered(e -> timeline.pause());
//        pane.setOnMouseExited(e -> timeline.play());
//
//        return timeline;
//    }

@FXML
public void initialize() {
    System.out.println("ColdDrinkController initialized");

    // Style du conteneur principal avec dégradé subtil
    hboxContainer.setStyle("-fx-background-color: linear-gradient(to bottom, rgba(240,248,255,0.8), rgba(230,240,250,0.9));" +
            "-fx-padding: 15px; -fx-spacing: 20px;");

    // Configuration des ScrollPanes avec style amélioré
    setupScrollPanes();

    // Ajouter des en-têtes avec style distinctif
    addColumnHeader(vbox1, "Classiques", "#1e88e5");
    addColumnHeader(vbox2, "Populaires", "#7cb342");
    addColumnHeader(vbox3, "Découvertes", "#f57c00");

    // Styles des colonnes
    String columnStyle = "-fx-background-color: white; " +
            "-fx-background-radius: 12px; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 12, 0, 0, 4); " +
            "-fx-padding: 15px;";

    vbox1.setStyle(columnStyle);
    vbox2.setStyle(columnStyle);
    vbox3.setStyle(columnStyle);

    vbox1.setSpacing(15);
    vbox2.setSpacing(15);
    vbox3.setSpacing(15);

    // Créer les listes de produits
    List<ProductCard> productCards = List.of(
            new ProductCard("Coca-Cola", 3.5, "/editor/composants/templates/ressources/images/colddrink/cd2.jpg"),
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

    List<ProductCard> productsCol1 = new ArrayList<>(productCards);
    List<ProductCard> productsCol2 = new ArrayList<>(productCards);
    List<ProductCard> productsCol3 = new ArrayList<>(productCards);

    Collections.rotate(productsCol2, 3);
    Collections.shuffle(productsCol3);

    // Charger les produits avec animations avancées
    loadProductsWithEnhancedAnimation(productsCol1, vbox1, 0);
    loadProductsWithEnhancedAnimation(productsCol2, vbox2, 150);
    loadProductsWithEnhancedAnimation(productsCol3, vbox3, 300);

    // Démarrer le défilement automatique après un délai
    Timeline delayedStart = new Timeline(new KeyFrame(Duration.seconds(1.5), e -> startAutoScroll()));
    delayedStart.play();
}

    private void startAutoScroll() {
        scrollTimeline1 = createAutoScroll(scrollPane1, 1);   // DOWN
        scrollTimeline2 = createAutoScroll(scrollPane2, -1);  // UP
        scrollTimeline3 = createAutoScroll(scrollPane3, 1);   // DOWN

        scrollTimeline1.play();
        scrollTimeline2.play();
        scrollTimeline3.play();

        // Pause on hover
        scrollPane1.setOnMouseEntered(e -> scrollTimeline1.pause());
        scrollPane1.setOnMouseExited(e -> scrollTimeline1.play());

        scrollPane2.setOnMouseEntered(e -> scrollTimeline2.pause());
        scrollPane2.setOnMouseExited(e -> scrollTimeline2.play());

        scrollPane3.setOnMouseEntered(e -> scrollTimeline3.pause());
        scrollPane3.setOnMouseExited(e -> scrollTimeline3.play());
    }

    private void setupScrollPanes() {
        // Configuration des ScrollPanes
        scrollPane1.prefWidthProperty().bind(hboxContainer.widthProperty().multiply(0.33));
        scrollPane2.prefWidthProperty().bind(hboxContainer.widthProperty().multiply(0.33));
        scrollPane3.prefWidthProperty().bind(hboxContainer.widthProperty().multiply(0.33));

        scrollPane1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane2.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane3.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // Style des ScrollPanes - cacher les bordures
        String scrollStyle = "-fx-background: transparent; -fx-background-color: transparent; " +
                "-fx-padding: 0; -fx-background-insets: 0;";
        scrollPane1.setStyle(scrollStyle);
        scrollPane2.setStyle(scrollStyle);
        scrollPane3.setStyle(scrollStyle);
    }

    private void addColumnHeader(VBox container, String title, String color) {
        javafx.scene.control.Label header = new javafx.scene.control.Label(title);
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 0 0 15 0; " +
                "-fx-text-fill: " + color + ";");
        header.setMaxWidth(Double.MAX_VALUE);
        header.setAlignment(javafx.geometry.Pos.CENTER);

        // Ajouter une ligne de séparation sous le titre
        javafx.scene.shape.Line separator = new javafx.scene.shape.Line();
        separator.setStartX(0);
        separator.setEndX(container.getWidth());
        separator.setStroke(Color.web(color, 0.3));
        separator.setStrokeWidth(2);
        separator.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);

        VBox headerContainer = new VBox(5, header, separator);
        headerContainer.setAlignment(javafx.geometry.Pos.CENTER);

        container.getChildren().add(0, headerContainer);
    }

    private void loadProductsWithEnhancedAnimation(List<ProductCard> products, VBox container, int baseDelay) {
        int delay = baseDelay;

        for (ProductCard product : products) {
            Timeline cardDelay = new Timeline(new KeyFrame(Duration.millis(delay), e -> {
                try {
                    ProductCardLoader card = new ProductCardLoader();
                    card.getController().setProductCard(product);

                    // Style de base de la carte
                    card.setStyle("-fx-background-color: white; -fx-background-radius: 8px; " +
                            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);");

                    // Configuration initiale pour l'animation
                    card.setOpacity(0);
                    card.setTranslateY(40);
                    card.setRotate(-5);
                    card.setScaleX(0.9);
                    card.setScaleY(0.9);
                    container.getChildren().add(card);

                    // Animations combinées d'entrée
                    FadeTransition fadeIn = new FadeTransition(Duration.millis(500), card);
                    fadeIn.setFromValue(0);
                    fadeIn.setToValue(1);

                    TranslateTransition slideIn = new TranslateTransition(Duration.millis(600), card);
                    slideIn.setFromY(40);
                    slideIn.setToY(0);
                    slideIn.setInterpolator(Interpolator.EASE_OUT);

                    RotateTransition rotateIn = new RotateTransition(Duration.millis(500), card);
                    rotateIn.setFromAngle(-5);
                    rotateIn.setToAngle(0);
                    rotateIn.setInterpolator(Interpolator.EASE_OUT);

                    ScaleTransition scaleIn = new ScaleTransition(Duration.millis(500), card);
                    scaleIn.setFromX(0.9);
                    scaleIn.setFromY(0.9);
                    scaleIn.setToX(1.0);
                    scaleIn.setToY(1.0);
                    scaleIn.setInterpolator(Interpolator.EASE_OUT);

                    // Animation de rebond finale
                    ScaleTransition bounce = new ScaleTransition(Duration.millis(150), card);
                    bounce.setFromX(1.0);
                    bounce.setFromY(1.0);
                    bounce.setToX(1.03);
                    bounce.setToY(1.03);
                    bounce.setCycleCount(2);
                    bounce.setAutoReverse(true);

                    ParallelTransition entryAnimation = new ParallelTransition(fadeIn, slideIn, rotateIn, scaleIn);
                    entryAnimation.setInterpolator(Interpolator.EASE_OUT);
                    entryAnimation.setOnFinished(event -> bounce.play());
                    entryAnimation.play();

                    // Effets de survol améliorés
                    applyEnhancedHoverEffect(card);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }));
            cardDelay.play();
            delay += 100; // Délai incrémental pour l'effet cascade
        }
    }

    private void applyEnhancedHoverEffect(ProductCardLoader card) {
        // Effets de survol
        card.setOnMouseEntered(e -> {
            // Style amélioré
            card.setStyle("-fx-background-color: white; -fx-background-radius: 8px; " +
                    "-fx-border-color: #3498db; -fx-border-width: 2px; -fx-border-radius: 6px; " +
                    "-fx-effect: dropshadow(three-pass-box, rgba(52,152,219,0.5), 12, 0, 0, 6);");

            // Animation d'agrandissement
            ParallelTransition hover = new ParallelTransition(
                    createScaleTransition(card, 1.07, 200),
                    createRotateTransition(card, 2, 300)
            );
            hover.play();
        });

        card.setOnMouseExited(e -> {
            // Restauration du style normal
            card.setStyle("-fx-background-color: white; -fx-background-radius: 8px; " +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);");

            // Animation de retour
            ParallelTransition exit = new ParallelTransition(
                    createScaleTransition(card, 1.0, 200),
                    createRotateTransition(card, 0, 300)
            );
            exit.play();
        });

        // Animation continue subtile
        Timeline subtle = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(card.translateYProperty(), 0)),
                new KeyFrame(Duration.seconds(4), new KeyValue(card.translateYProperty(), -2)),
                new KeyFrame(Duration.seconds(8), new KeyValue(card.translateYProperty(), 0))
        );
        subtle.setCycleCount(Animation.INDEFINITE);
        subtle.play();
    }

    private ScaleTransition createScaleTransition(Node node, double scale, int duration) {
        ScaleTransition st = new ScaleTransition(Duration.millis(duration), node);
        st.setToX(scale);
        st.setToY(scale);
        st.setInterpolator(Interpolator.EASE_OUT);
        return st;
    }

    private RotateTransition createRotateTransition(Node node, double angle, int duration) {
        RotateTransition rt = new RotateTransition(Duration.millis(duration), node);
        rt.setToAngle(angle);
        rt.setInterpolator(Interpolator.EASE_OUT);
        return rt;
    }

    private Timeline createAutoScroll(ScrollPane pane, int direction) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.02), event -> {
                    double vvalue = pane.getVvalue();
                    double step = 0.0003 * direction;

                    double newValue = vvalue + step;

                    if (newValue > 1.0) {
                        // Animation de rebond lors du retour au début
                        FadeTransition flash = new FadeTransition(Duration.millis(300), pane.getContent());
                        flash.setFromValue(1.0);
                        flash.setToValue(0.8);
                        flash.setCycleCount(2);
                        flash.setAutoReverse(true);
                        flash.play();
                        newValue = 0;
                    }
                    if (newValue < 0.0) {
                        // Animation de rebond lors du retour à la fin
                        FadeTransition flash = new FadeTransition(Duration.millis(300), pane.getContent());
                        flash.setFromValue(1.0);
                        flash.setToValue(0.8);
                        flash.setCycleCount(2);
                        flash.setAutoReverse(true);
                        flash.play();
                        newValue = 1.0;
                    }

                    pane.setVvalue(newValue);
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        return timeline;
    }




}
