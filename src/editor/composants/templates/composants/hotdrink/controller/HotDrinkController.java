package editor.composants.templates.composants.hotdrink.controller;

import application.model.distrib.productModel.product.Product;
import editor.composants.templates.composants.hotdrink.model.Alergene;
import editor.composants.templates.composants.hotdrink.model.ProductCard;
import editor.composants.templates.composants.hotdrink.model.ProductCardLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

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

    @FXML
    public void initialize() throws IOException {
        // Initialize a liste of products

        // set the size of the tilePane
        tilePane.setPrefColumns(3);
        tilePane.setPrefRows(2);

        loadProductCard(productCards);

        displayPage(currentPage);

        btnPre.setOnAction(event -> {
            try {
                displayPage(currentPage - 1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btnSuiv.setOnAction(event -> {
            try {
                displayPage(currentPage + 1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void displayPage(int pageIndex) throws IOException {
        // Get the total number of pages
        int totalPages = (int) Math.ceil((double) productCards.size() / ITEM_PAGE);
        // get the current page
        currentPage = Math.max(0, Math.min(pageIndex, totalPages - 1));


        // Display the product cards for the current page
        tilePane.getChildren().clear();
        int start = currentPage * ITEM_PAGE;
        int end = Math.min(start + ITEM_PAGE, productCards.size());

        // Get a subset of product cards for the current page between start and end
        Set<ProductCard> productCardsForPage = new HashSet<>();
        int index = 0;
        for (ProductCard productCard : productCards) {
            if (index >= start && index < end) {
                productCardsForPage.add(productCard);
                ProductCardLoader productCardLoader = new ProductCardLoader();
                productCardLoader.getController().setProductCard(productCard);
                tilePane.getChildren().add(productCardLoader);
            }
            index++;
        }



        // Update the button states
//        btnPre.setDisable(currentPage == 0);
        if(currentPage == 0){
            // button disapears
            btnPre.setVisible(false);
        }
        else{
            btnPre.setVisible(true);
        }


        //btnSuiv.setDisable(currentPage == totalPages - 1);
        if(currentPage == totalPages - 1){
            // button disapears
            btnSuiv.setVisible(false);
        }
        else{
            btnSuiv.setVisible(true);
        }


    }

    private void displayProductCards(Set<ProductCard> productCards) throws IOException {
        // Clear the TilePane before adding new product cards
        tilePane.getChildren().clear();

        // Calculate the start and end indices for the current page
        int startIndex = currentPage * ITEM_PAGE;
        int endIndex = Math.min(startIndex + ITEM_PAGE, productCards.size());

        // Add product cards for the current page
        int index = 0;
        for (ProductCard productCard : productCards) {
            if (index >= startIndex && index < endIndex) {
                ProductCardLoader productCardLoader = new ProductCardLoader();
                productCardLoader.getController().setProductCard(productCard);
                tilePane.getChildren().add(productCardLoader);
            }
            index++;
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
}
