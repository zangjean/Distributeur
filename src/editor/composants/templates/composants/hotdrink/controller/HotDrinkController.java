package editor.composants.templates.composants.hotdrink.controller;

import application.model.distrib.productModel.product.Product;
import editor.composants.templates.composants.hotdrink.model.Alergene;
import editor.composants.templates.composants.hotdrink.model.ProductCard;
import editor.composants.templates.composants.hotdrink.model.ProductCardLoader;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class HotDrinkController {
//    @FXML
//    private Button btnAlerg;
    @FXML
    private Button btnPre, btnSuiv;

    @FXML
    private TilePane tilePane;

    @FXML
    private VBox root;

    private final Set<ProductCard> productCards = new HashSet<>();

    private final int ITEM_PAGE = 6;


    private int currentPage = 0;

    public HotDrinkController(){

    }

//    @FXML
//    public void initialize() throws IOException {
//        // Initialize a liste of products
//
//        // set the size of the tilePane
//        tilePane.setPrefColumns(3);
//        tilePane.setPrefRows(2);
//
//        loadProductCard(productCards);
//
//        displayPage(currentPage);
//
//        btnPre.setOnAction(event -> {
//            try {
//                displayPage(currentPage - 1);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        btnSuiv.setOnAction(event -> {
//            try {
//                displayPage(currentPage + 1);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
//
//    private void displayPage(int pageIndex) throws IOException {
//        // Get the total number of pages
//        int totalPages = (int) Math.ceil((double) productCards.size() / ITEM_PAGE);
//        // get the current page
//        currentPage = Math.max(0, Math.min(pageIndex, totalPages - 1));
//
//
//        // Display the product cards for the current page
//        tilePane.getChildren().clear();
//        int start = currentPage * ITEM_PAGE;
//        int end = Math.min(start + ITEM_PAGE, productCards.size());
//
//        // Get a subset of product cards for the current page between start and end
//        Set<ProductCard> productCardsForPage = new HashSet<>();
//        int index = 0;
//        for (ProductCard productCard : productCards) {
//            if (index >= start && index < end) {
//                productCardsForPage.add(productCard);
//                ProductCardLoader productCardLoader = new ProductCardLoader();
//                productCardLoader.getController().setProductCard(productCard);
//                tilePane.getChildren().add(productCardLoader);
//            }
//            index++;
//        }
//
//
//
//        // Update the button states
////        btnPre.setDisable(currentPage == 0);
//        if(currentPage == 0){
//            // button disapears
//            btnPre.setVisible(false);
//        }
//        else{
//            btnPre.setVisible(true);
//        }
//
//
//        //btnSuiv.setDisable(currentPage == totalPages - 1);
//        if(currentPage == totalPages - 1){
//            // button disapears
//            btnSuiv.setVisible(false);
//        }
//        else{
//            btnSuiv.setVisible(true);
//        }
//
//
//    }
//
//    private void displayProductCards(Set<ProductCard> productCards) throws IOException {
//        // Clear the TilePane before adding new product cards
//        tilePane.getChildren().clear();
//
//        // Calculate the start and end indices for the current page
//        int startIndex = currentPage * ITEM_PAGE;
//        int endIndex = Math.min(startIndex + ITEM_PAGE, productCards.size());
//
//        // Add product cards for the current page
//        int index = 0;
//        for (ProductCard productCard : productCards) {
//            if (index >= startIndex && index < endIndex) {
//                ProductCardLoader productCardLoader = new ProductCardLoader();
//                productCardLoader.getController().setProductCard(productCard);
//                tilePane.getChildren().add(productCardLoader);
//            }
//            index++;
//        }
//
//    }

@FXML
public void initialize() throws IOException {
    // Configuration du TilePane avec effet de fond
    tilePane.setPrefColumns(3);
    tilePane.setPrefRows(2);
    tilePane.setHgap(15);
    tilePane.setVgap(15);
    tilePane.setStyle("-fx-background-color: rgba(50, 50, 50, 0.1); -fx-background-radius: 10px; -fx-padding: 15px;");

    // Style des boutons de navigation
    String buttonStyle = "-fx-background-color: #6b4226; -fx-text-fill: white; " +
            "-fx-font-size: 16px; -fx-background-radius: 30px; " +
            "-fx-padding: 10px 20px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 5, 0, 0, 2);";

    btnPre.setStyle(buttonStyle);
    btnSuiv.setStyle(buttonStyle);

    // Effets hover sur les boutons
    btnPre.setOnMouseEntered(e -> btnPre.setStyle(buttonStyle + "-fx-background-color: #8b5d3a;"));
    btnPre.setOnMouseExited(e -> btnPre.setStyle(buttonStyle));
    btnSuiv.setOnMouseEntered(e -> btnSuiv.setStyle(buttonStyle + "-fx-background-color: #8b5d3a;"));
    btnSuiv.setOnMouseExited(e -> btnSuiv.setStyle(buttonStyle));

    loadProductCard(productCards);
    displayPage(currentPage);

    // Actions des boutons avec animations
    btnPre.setOnAction(event -> {
        try {
            animatePageChange(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });

    btnSuiv.setOnAction(event -> {
        try {
            animatePageChange(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });

    // Animation d'apparition initiale
    FadeTransition fadeIn = new FadeTransition(Duration.millis(800), tilePane);
    fadeIn.setFromValue(0);
    fadeIn.setToValue(1);
    fadeIn.play();
}

    private void animatePageChange(boolean forward) throws IOException {
        // Animation de sortie
        FadeTransition fadeOut = new FadeTransition(Duration.millis(250), tilePane);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

        // Translation pour effet de défilement
        TranslateTransition slideOut = new TranslateTransition(Duration.millis(250), tilePane);
        slideOut.setByX(forward ? -100 : 100);

        ParallelTransition parallelOut = new ParallelTransition(fadeOut, slideOut);
        parallelOut.setOnFinished(e -> {
            try {
                // Change page
                displayPage(currentPage + (forward ? 1 : -1));

                // Animation d'entrée
                FadeTransition fadeIn = new FadeTransition(Duration.millis(250), tilePane);
                fadeIn.setFromValue(0);
                fadeIn.setToValue(1);

                TranslateTransition slideIn = new TranslateTransition(Duration.millis(250), tilePane);
                slideIn.setFromX(forward ? 100 : -100);
                slideIn.setToX(0);

                ParallelTransition parallelIn = new ParallelTransition(fadeIn, slideIn);
                parallelIn.play();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        parallelOut.play();
    }

    private void displayPage(int pageIndex) throws IOException {
        int totalPages = (int) Math.ceil((double) productCards.size() / ITEM_PAGE);
        currentPage = Math.max(0, Math.min(pageIndex, totalPages - 1));

        tilePane.getChildren().clear();
        int start = currentPage * ITEM_PAGE;
        int end = Math.min(start + ITEM_PAGE, productCards.size());

        // Création d'une animation séquentielle pour les cartes
        SequentialTransition sequentialAppear = new SequentialTransition();

        int index = 0;
        for (ProductCard productCard : productCards) {
            if (index >= start && index < end) {
                ProductCardLoader productCardLoader = new ProductCardLoader();
                productCardLoader.getController().setProductCard(productCard);

                // Style de base et dimensions
                productCardLoader.setPrefSize(200, 250);
                productCardLoader.setStyle("-fx-background-color: white; -fx-background-radius: 8px; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 8, 0, 0, 3);");

                // Ajouter l'effet de hover
                applyHoverEffect(productCardLoader);
                tilePane.getChildren().add(productCardLoader);

                // Animation d'apparition individuelle
                FadeTransition cardFade = new FadeTransition(Duration.millis(200), productCardLoader);
                cardFade.setFromValue(0);
                cardFade.setToValue(1);

                ScaleTransition cardScale = new ScaleTransition(Duration.millis(200), productCardLoader);
                cardScale.setFromX(0.8);
                cardScale.setFromY(0.8);
                cardScale.setToX(1);
                cardScale.setToY(1);

                ParallelTransition cardAnim = new ParallelTransition(cardFade, cardScale);

                // Ajouter un délai pour chaque carte
                PauseTransition delay = new PauseTransition(Duration.millis(50 * (index - start)));
                SequentialTransition cardSequence = new SequentialTransition(delay, cardAnim);
                sequentialAppear.getChildren().add(cardSequence);
            }
            index++;
        }

        sequentialAppear.play();

        // Gestion visibilité des boutons
        btnPre.setVisible(currentPage > 0);
        btnSuiv.setVisible(currentPage < totalPages - 1);

        // Ajouter un indicateur de pagination
        updatePaginationIndicator(root, currentPage, totalPages);
    }

    private void updatePaginationIndicator(VBox parent, int currentPage, int totalPages) {
        // Rechercher s'il existe déjà un indicateur
        parent.getChildren().removeIf(node -> node.getId() != null && node.getId().equals("paginationIndicator"));

        // Créer un nouvel indicateur
        HBox indicator = new HBox(5);
        indicator.setId("paginationIndicator");
        indicator.setAlignment(Pos.CENTER);
        indicator.setPadding(new Insets(10, 0, 0, 0));

        for (int i = 0; i < totalPages; i++) {
            Circle dot = new Circle(5);
            dot.setFill(i == currentPage ? Color.web("#6b4226") : Color.LIGHTGRAY);
            indicator.getChildren().add(dot);
        }

        parent.getChildren().add(indicator);
    }

    private void applyHoverEffect(ProductCardLoader cardLoader) {
        // Effet d'échelle et d'ombre pour le hover
        cardLoader.setOnMouseEntered(event -> {
            // Animation d'agrandissement
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), cardLoader);
            scaleTransition.setToX(1.05);
            scaleTransition.setToY(1.05);

            // Effet d'ombre
            DropShadow shadow = new DropShadow();
            shadow.setColor(Color.rgb(107, 66, 38, 0.7));
            shadow.setRadius(15);
            cardLoader.setEffect(shadow);

            // Bordure colorée
            cardLoader.setStyle("-fx-background-color: white; -fx-background-radius: 8px; " +
                    "-fx-border-color: #6b4226; -fx-border-width: 2px; -fx-border-radius: 8px;");

            scaleTransition.play();
        });

        cardLoader.setOnMouseExited(event -> {
            // Animation de retour à la taille normale
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), cardLoader);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);

            // Supprimer l'effet d'ombre
            cardLoader.setEffect(null);

            // Rétablir le style normal
            cardLoader.setStyle("-fx-background-color: white; -fx-background-radius: 8px; " +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 8, 0, 0, 3);");

            scaleTransition.play();
        });
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
}
