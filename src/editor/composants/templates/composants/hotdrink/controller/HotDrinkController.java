package editor.composants.templates.composants.hotdrink.controller;

import editor.composants.templates.composants.hotdrink.model.ProductCard;
import editor.composants.templates.composants.hotdrink.model.ProductCardLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class HotDrinkController {
    @FXML
    private TilePane root;

    public HotDrinkController(){

    }

    @FXML
    public void initialize() throws IOException {
        // Initialize the controller
        // Initialize a liste of products
        Set<ProductCard> productCards = new HashSet<>();
        productCards.add(new ProductCard("Cafe Expresso", 1.50, "/editor/composants/templates/ressources/images/cafe.jpg"));

        productCards.add(new ProductCard("Allongé ", 1.50, "/editor/composants/templates/ressources/images/coca.jpg"));
        productCards.add(new ProductCard("Americano", 1.50, "/editor/composants/templates/ressources/images/cafe.jpg"));
        productCards.add(new ProductCard("Coca sans sucre", 1.50, "/editor/composants/templates/ressources/images/coca.jpg"));
        productCards.add(new ProductCard("Latte ", 1.50, "/editor/composants/templates/ressources/images/cafe.jpg"));
        productCards.add(new ProductCard("Coco original", 1.50, "/editor/composants/templates/ressources/images/coca.jpg"));

        // Display the product cards in console
        for (ProductCard productCard : productCards) {
            System.out.println("Product Card: " + productCard.getName());
            System.out.println("Image Path: " + productCard.getImagePath());
            System.out.println("Price: " + productCard.getPrice() + " €");
        }

        // Add the product cards to the root TilePane
        for (ProductCard productCard : productCards) {
            ProductCardLoader productCardLoader = new ProductCardLoader();


            productCardLoader.getController().setProductCard(productCard);


            root.getChildren().add(productCardLoader);
        }





    }
}
