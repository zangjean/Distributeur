package application.controller.distrib;

import application.Main;
import application.model.distrib.panier.Panier;
import application.model.distrib.productModel.product.Product;
import application.model.distrib.productModel.product.food.Snacks;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Map;

public class MenuSnackController {
    @FXML
    public TabPane tabPane;
    @FXML
    public Tab tab_chips;
    @FXML
    public Tab tab_chocolate;
    @FXML
    public Tab tab_sandwich;

    private ProductController productController;
    private Panier panier;

    @FXML
    public void initialize() {
        this.productController = Main.getProductController();
        this.panier = Main.getPanier();
        initAllChips();
        initAllChocolate();
        initAllSandwich();
    }


    private void initAllChips() {
        FlowPane flowPane_chips = new FlowPane();
        flowPane_chips.setHgap(10);
        flowPane_chips.setVgap(10);

        String[] chipFlavors = {"Original", "BBQ", "Poulet", "Boeuf"};
        for (String flavor : chipFlavors) {
            Button flavorButton = new Button(flavor);
            try {
                // Charger l'image avec solution de secours pour les images manquantes
                String imagePath = "../../ressources/images/chips/" + flavor.toLowerCase() + ".jpg";
                java.io.InputStream imageStream = getClass().getResourceAsStream(imagePath);
                if (imageStream == null) {
                    System.out.println("Image non trouvée pour la saveur: " + flavor + " sur le chemin: " + imagePath);
                } else {
                    Image image = new Image(imageStream);
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(40);
                    imageView.setFitHeight(40);
                    imageView.setPreserveRatio(true);
                    flavorButton.setGraphic(imageView);
                }
            } catch (Exception e) {
                System.out.println("Erreur lors du chargement de l'image pour la saveur: " + flavor + " - " + e.getMessage());
            }

            flavorButton.setOnAction(event -> openSelectSnackDialog(flavor, flavorButton));
            flowPane_chips.getChildren().add(flavorButton);
        }


        this.tab_chips.setContent(flowPane_chips);
    }




    private void initAllChocolate() {
        FlowPane flowPane_chocolate = new FlowPane();
        flowPane_chocolate.setHgap(10);
        flowPane_chocolate.setVgap(10);

        String[] chocolateTypes = {"Lait", "Noir", "Blanc"};
        String[] chocolateImages = {"../../ressources/images/chocolate/milk.jpg", "../../ressources/images/chocolate/dark.jpg", "../../ressources/images/chocolate/white.jpg"};

        for (int i = 0; i < chocolateTypes.length; i++) {
            String type = chocolateTypes[i];
            Button typeButton = new Button(type);
            try {
                String imagePath = chocolateImages[i];
                java.io.InputStream imageStream = getClass().getResourceAsStream(imagePath);
                if (imageStream == null) {
                    System.out.println("Image non trouvée pour le type de chocolat : " + type + " sur le chemin : " + imagePath);
                } else {
                    Image image = new Image(imageStream);
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(40);
                    imageView.setFitHeight(40);
                    imageView.setPreserveRatio(true);
                    typeButton.setGraphic(imageView);
                }
            } catch (Exception e) {
                System.out.println("Erreur lors du chargement de l'image pour le type de chocolat : " + type + " - " + e.getMessage());
            }
            typeButton.setOnAction(event -> openSelectSnackDialog(type, typeButton));
            flowPane_chocolate.getChildren().add(typeButton);
        }

        this.tab_chocolate.setContent(flowPane_chocolate);
    }

    private void initAllSandwich() {
        FlowPane flowPane_sandwich = new FlowPane();
        flowPane_sandwich.setHgap(10);
        flowPane_sandwich.setVgap(10);

        String[] sandwichTypes = {"Jambon", "Fromage", "Végétarien"};
        String[] sandwichImages = {"../../ressources/images/sandwich/ham.jpg", "../../ressources/images/sandwich/cheese.jpg", "../../ressources/images/sandwich/veggie.jpg"};

        for (int i = 0; i < sandwichTypes.length; i++) {
            String type = sandwichTypes[i];
            Button typeButton = new Button(type);
            try {
                String imagePath = sandwichImages[i];
                java.io.InputStream imageStream = getClass().getResourceAsStream(imagePath);
                if (imageStream == null) {
                    System.out.println("Image non trouvée pour le type de sandwich : " + type + " sur le chemin: " + imagePath);
                } else {
                    Image image = new Image(imageStream);
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(40);
                    imageView.setFitHeight(40);
                    imageView.setPreserveRatio(true);
                    typeButton.setGraphic(imageView);
                }
            } catch (Exception e) {
                System.out.println("Erreur lors du chargement de l'image pour le type de sandwich : " + type + " - " + e.getMessage());
            }
            typeButton.setOnAction(event -> openSelectSnackDialog(type, typeButton));
            flowPane_sandwich.getChildren().add(typeButton);
        }

        this.tab_sandwich.setContent(flowPane_sandwich);
    }


    private void openSelectSnackDialog(String snackName, Button button) {
        ArrayList<Snacks> snacks = returnSnacks();
        Snacks targetSnack = null;
        for (Snacks snack : snacks) {
            if (snack.getName().equalsIgnoreCase(snackName)) {
                targetSnack = snack;
                break;
            }
        }
        if (targetSnack != null) {
            showSelectSnackWindow(targetSnack, button);
        } else {
            System.out.println(snackName + " snack non trouvée.");
        }
    }

    private void showSelectSnackWindow(Snacks snack, Button button) {
        GridPane select = new GridPane();
        select.setGridLinesVisible(true);
        select.add(new Label(" Taille "), 0, 0);
        select.add(new Label(" Prix (€) "), 1, 0);
        select.add(new Label(" Select "), 2, 0);

        Map<Integer, Double> prices = snack.getQuantityPriceMap();

        String[] sizeLabels = {"Petit", "Grand"};
        int[] sizeValues = {1, 2}; 
        double[] fixedPrices = {3.0, 5.0}; 

        int rowIndex = 1;
        for (int i = 0; i < sizeLabels.length; i++) {
            String sizeLabel = sizeLabels[i];
            int sizeValue = sizeValues[i];
            Double price = fixedPrices[i];

            select.add(new Label(sizeLabel), 0, rowIndex);
            select.add(new Label(String.format("%.2f", price)), 1, rowIndex);

            Button btnAdd = new Button("Add");
            Map.Entry<Integer, Double> entry = new java.util.AbstractMap.SimpleEntry<>(sizeValue, price);
            addOnPanier(btnAdd, entry, snack);

            select.add(btnAdd, 2, rowIndex);
            rowIndex++;
        }

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(select);

        Button btnCancel = new Button("Cancel");
        btnCancel.setOnAction(closeEvent -> {
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
        });

        borderPane.setBottom(btnCancel);
        BorderPane.setAlignment(btnCancel, Pos.CENTER);

        Stage newStage = new Stage();
        newStage.setTitle(snack.getName());

        Scene newScene = new Scene(borderPane, 400, 300);
        newStage.setScene(newScene);

        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.initOwner(button.getScene().getWindow());

        newStage.show();
    }

    private void addOnPanier(Button button, Map.Entry<Integer, Double> entry, Snacks snack) {
        button.setOnAction(event -> {
            System.out.println("Bouton cliqué pour la taille " + entry.getKey());
            this.panier.addProduct(snack, entry.getKey(), entry.getValue());
        });
    }

    private ArrayList<Snacks> returnSnacks() {
        ArrayList<Product> products = productController.getProductsModel().getAllProducts();
        ArrayList<Snacks> snacks = new ArrayList<>();
        for (Product product : products) {
            if (product.getClass().equals(Snacks.class)) {
                snacks.add((Snacks) product);
            }
        }
        return snacks;
    }
}
