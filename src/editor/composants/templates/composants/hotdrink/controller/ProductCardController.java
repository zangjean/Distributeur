package editor.composants.templates.composants.hotdrink.controller;

import editor.composants.templates.composants.hotdrink.model.ProductCard;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.Objects;

public class ProductCardController {

    private ProductCard currentProductCard;

    @FXML
    private ImageView imgView;

    @FXML
    private Label lbNom, lbPrix;

    public ProductCardController(){

    }

//    @FXML
//    public void initialize() {
//        // Initialize the controller
//        // You can set default values or perform any setup here
//        // For example, you can set the default image and text for the labels
//
//    }

    public void setProductCard(ProductCard productCard) {

        this.currentProductCard = productCard;
        lbNom.setText(productCard.getName());
        lbPrix.setText(productCard.getPrice() + " €");

        Image image = new Image(getClass().getResourceAsStream(productCard.getImagePath()));

        imgView.setImage(image);

    }

    public ProductCard getProductCard() {
        return currentProductCard;
    }

}
