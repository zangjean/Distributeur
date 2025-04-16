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
    private HBox pageBar;
    @FXML
    private TilePane tilePane;

    @FXML
    private VBox root;

    private final Set<ProductCard> productCards = new HashSet<>();

    private final int ITEM_PAGE = 9;


    private int currentPage = 0;

    public HotDrinkController(){

    }

    @FXML
    public void initialize() throws IOException {
        // Initialize the controller
        // Initialize a liste of products

        // set the size of the tilePane
        tilePane.setPrefColumns(3);
//        tilePane.setPrefRows(3);

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

//        // btnAlerg to display the alergie
//        btnAlerg.setOnAction(event -> {
//            // Display the alergies of the first product
//            if(!productCards.isEmpty()){
//                ProductCard productCard = productCards.iterator().next();
//                System.out.println("Alergies: " + productCard.AlergenToStringV1());
//            }
//        });


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
        // Add each product to the list but unique
        productCards.add(new ProductCard("Cafe Expresso", 1.50, "/editor/composants/templates/ressources/images/cafe.jpg"));
        productCards.add(new ProductCard("Allongé ", 1.50, "/editor/composants/templates/ressources/images/coca.jpg"));
        productCards.add(new ProductCard("Americano", 1.50, "/editor/composants/templates/ressources/images/cafe.jpg"));
        productCards.add(new ProductCard("Coca sans sucre", 1.50, "/editor/composants/templates/ressources/images/coca.jpg"));
        productCards.add(new ProductCard("Latte ", 1.50, "/editor/composants/templates/ressources/images/cafe.jpg"));
        productCards.add(new ProductCard("Coco original", 1.50, "/editor/composants/templates/ressources/images/coca.jpg"));
        productCards.add(new ProductCard("Café au lait", 1.50, "/editor/composants/templates/ressources/images/cafe.jpg"));
        productCards.add(new ProductCard("Café crème", 1.50, "/editor/composants/templates/ressources/images/cafe.jpg"));
        productCards.add(new ProductCard("Café noisette", 1.50, "/editor/composants/templates/ressources/images/cafe.jpg"));
        productCards.add(new ProductCard("Pizza Napolitaine", 1.50, "/editor/composants/templates/ressources/images/pizza.jpg"));
        productCards.add(new ProductCard("Pizza Margherita", 1.50, "/editor/composants/templates/ressources/images/pizza.jpg"));
        productCards.add(new ProductCard("Pizza Quattro Stagioni", 1.50, "/editor/composants/templates/ressources/images/pizza.jpg"));
        productCards.add(new ProductCard("BimBim", 1.50, "/editor/composants/templates/ressources/images/snack.jpg"));
        productCards.add(new ProductCard("Pizza dommage", 1.50, "/editor/composants/templates/ressources/images/pizza.jpg"));


        // add 12 more products with 4 different names and 4 images
        for(int i = 0; i < 12; i++){
            if(i % 4 == 0){
                productCards.add(new ProductCard("Café au lait", 1.50, "/editor/composants/templates/ressources/images/cafe.jpg"));
            }
            else if(i % 4 == 1){
                productCards.add(new ProductCard("Café crème", 1.50, "/editor/composants/templates/ressources/images/cafe.jpg"));
            }
            else if(i % 4 == 2){
                productCards.add(new ProductCard("Café noisette", 1.50, "/editor/composants/templates/ressources/images/cafe.jpg"));
            }
            else{
                productCards.add(new ProductCard("Pizza Napolitaine", 1.50, "/editor/composants/templates/ressources/images/pizza.jpg"));
            }
        }

        // Add allergens to the product cards
        for (ProductCard productCard : productCards) {
            // Add allergens to the product card
            productCard.addAlergie(Alergene.DAIRY);
            productCard.addAlergie(Alergene.GLUTEN);
            productCard.addAlergie(Alergene.NUTS);
            productCard.addAlergie(Alergene.SHELLFISH);
            productCard.addAlergie(Alergene.SOY);
            productCard.addAlergie(Alergene.EGGS);
            productCard.addAlergie(Alergene.FISH);
            productCard.addAlergie(Alergene.SESAME);
            productCard.addAlergie(Alergene.CELERY);
            productCard.addAlergie(Alergene.MUSTARD);
            productCard.addAlergie(Alergene.SULPHITES);
            productCard.addAlergie(Alergene.LUPIN);
            productCard.addAlergie(Alergene.PEANUTS);
            productCard.addAlergie(Alergene.CORN);

        }


    }
}
