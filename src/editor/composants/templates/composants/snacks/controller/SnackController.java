package editor.composants.templates.composants.snacks.controller;

import editor.composants.templates.composants.hotdrink.model.ProductCard;
import editor.composants.templates.composants.hotdrink.model.ProductCardLoader;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SnackController {

    @FXML
    private ScrollPane topScrollpane;
    ;
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
        centerTilepane.setStyle("-fx-background-color: #fbb3b3; -fx-padding: 10px; -fx-border-color: #000000; -fx-border-width: 2px;");
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
                centerTilepane.getChildren().add(productCardLoader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



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
