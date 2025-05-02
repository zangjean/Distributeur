package application.controller.distrib.hotDrink;

import application.MainApp;
import application.controller.distrib.ProductController;
import application.model.distrib.panier.Panier;
import application.model.distrib.productModel.product.Product;
import application.model.distrib.productModel.product.beverage.sugar.all.Coffee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

/**
 * Contrôleur de l'interface des boissons chaudes (MenuHotDrink).
 * Permet d'afficher et de sélectionner différentes boissons comme les cafés, cappuccinos et chocolats.
 */
public class MenuHotDrinkController {

    /** Onglet pour les cafés classiques. */
    @FXML public Tab tab_cafe;

    /** Onglet pour les cappuccinos et lattes. */
    @FXML public Tab tab_cappu;

    /** Onglet pour les chocolats chauds. */
    @FXML public Tab tab_choco;

    /** Onglet pour les thés. */
    @FXML public Tab tab_the;

    /** Onglet pour les soupes. */
    @FXML public Tab tab_soupes;

    /** Le conteneur principal des onglets. */
    @FXML public TabPane tabPaneID;

    /** Contrôleur des produits utilisé pour accéder aux données. */
    private ProductController productController;

    /** Le panier de l'utilisateur courant. */
    private Panier panier;

    /**
     * Méthode d'initialisation appelée automatiquement après chargement FXML.
     * Initialise le contrôleur, les produits café et cappuccino, et l'interface chocolat.
     *
     * @throws IOException si une ressource FXML est introuvable.
     */
    @FXML
    public void initialize() throws IOException {
        this.productController = MainApp.getProductController();
        this.panier = MainApp.getPanier();
        initAllCoffee("cafe");
        initAllCoffee("cappu");
        initChocolateScrean();
    }

    /**
     * Récupère la liste des objets {@code Coffee} correspondant au type sélectionné.
     *
     * @param type "cafe" pour les cafés simples, "cappu" pour cappuccino/latte.
     * @return Liste de cafés filtrée selon le type.
     */
    private ArrayList<Coffee> returnCoffee(String type) {
        ArrayList<Product> products = productController.getProductsModel().getAllProducts();
        ArrayList<Coffee> coffees = new ArrayList<>();
        for (Product product : products) {
            if (product instanceof Coffee coffee) {
                String name = product.getName().toLowerCase();
                boolean isCappu = name.contains("latte") || name.contains("cappuccino");
                if ((type.equals("cafe") && !isCappu) || (type.equals("cappu") && isCappu)) {
                    coffees.add(coffee);
                }
            }
        }
        return coffees;
    }

    /**
     * Initialise l'interface d'un type de café avec les boutons et images associés.
     *
     * @param type Type de café ("cafe" ou "cappu").
     */
    private void initAllCoffee(String type) {
        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10);
        flowPane.setVgap(10);

        ArrayList<Coffee> coffees = returnCoffee(type);

        for (Coffee coffee : coffees) {
            String name = coffee.getName().toLowerCase();
            String nameImage = returnStringImage(name);
            Button button = new Button(coffee.getName());

            URL imageUrl = getClass().getResource("/application/ressources/images/allHotDrinks/" + nameImage);
            if (imageUrl != null) {
                ImageView imageView = new ImageView(imageUrl.toExternalForm());
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                VBox vBox = new VBox(imageView, button);
                vBox.setAlignment(Pos.CENTER);
                vBox.setSpacing(5);
                flowPane.getChildren().add(vBox);
            } else {
                System.err.println("Image introuvable : " + nameImage);
            }

            selectCoffee(coffee, button);
        }

        if (type.equals("cafe")) {
            this.tab_cafe.setContent(flowPane);
        } else if (type.equals("cappu")) {
            this.tab_cappu.setContent(flowPane);
        }
    }

    /**
     * Retourne le nom d'image associé à un produit café.
     *
     * @param name Nom du produit.
     * @return Nom de fichier image (ex. "latte.png").
     */
    private String returnStringImage(String name) {
        String nameImage = "";

        if (name.contains("expresso")) nameImage = "expresso.png";
        else if (name.contains("ristretto")) nameImage = "ristretto.png";
        else if (name.contains("lungo")) nameImage = "lungo.png";
        else if (name.contains("latte")) nameImage = "latte.png";
        else if (name.contains("cappuccino")) nameImage = "cappuccino.png";
        else if (name.contains("noisette")) nameImage = "noisette.png";
        else if (name.contains("caramel")) nameImage = "caramel.png";
        else if (name.contains("macchiato")) nameImage = "macchiato.png";
        else if (name.contains("viennese")) nameImage = "viennese.png";
        else if (name.contains("irish")) nameImage = "irish.png";

        System.out.println("NAMEIMAGE -> " + nameImage);
        return nameImage;
    }

    /**
     * Configure le bouton du café pour afficher une fenêtre d'ajout au panier avec choix de taille.
     *
     * @param coffee Objet café.
     * @param button Bouton déclencheur.
     */
    private void selectCoffee(Coffee coffee, Button button) {
        button.setOnAction(event -> {
            System.out.println("Button clicked: " + coffee.getName());

            GridPane select = new GridPane();
            select.setGridLinesVisible(true);
            select.add(new Label(" Taille (ml) "), 0, 0);
            select.add(new Label(" Prix (€) "), 1, 0);
            select.add(new Label(" Select "), 2, 0);

            Map<Integer, Double> prices = coffee.getQuantityPriceMap();
            int rowIndex = 1;
            for (Map.Entry<Integer, Double> entry : prices.entrySet()) {
                select.add(new Label(entry.getKey() + ""), 0, rowIndex);
                select.add(new Label(entry.getValue() + ""), 1, rowIndex);
                Button btnAdd = new Button("Add");
                addOnPanier(btnAdd, entry, coffee);
                select.add(btnAdd, 2, rowIndex);
                rowIndex++;
            }

            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(select);
            Button btnCancel = new Button("Cancel");
            btnCancel.setOnAction(e -> ((Stage) btnCancel.getScene().getWindow()).close());
            borderPane.setBottom(btnCancel);
            BorderPane.setAlignment(btnCancel, Pos.CENTER);

            Stage newStage = new Stage();
            newStage.setTitle(coffee.getName());
            newStage.setScene(new Scene(borderPane, 400, 300));
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.initOwner(button.getScene().getWindow());
            newStage.show();
        });
    }

    /**
     * Affiche une fenêtre pour sélectionner le niveau de sucre, puis ajoute le produit au panier.
     *
     * @param button Bouton déclencheur.
     * @param entry  Taille et prix du produit.
     * @param coffee Café sélectionné.
     */
    private void addOnPanier(Button button, Map.Entry<Integer, Double> entry, Coffee coffee) {
        button.setOnAction(event -> {
            BorderPane borderPane = new BorderPane();
            Stage newStage = new Stage();
            Button buttonValidate = new Button("Valider");

            Label sucreLabel = new Label("Sucre :");
            Slider sucreSlider = new Slider(0, 5, 0);
            sucreSlider.setMajorTickUnit(1);
            sucreSlider.setMinorTickCount(0);
            sucreSlider.setSnapToTicks(true);
            sucreSlider.setShowTickLabels(true);
            sucreSlider.setShowTickMarks(true);
            sucreSlider.setBlockIncrement(1);

            Label valueLabel = new Label("Niveau : 0");
            sucreSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                    valueLabel.setText("Niveau : " + newVal.intValue()));

            VBox sucreBox = new VBox(5, sucreLabel, sucreSlider, valueLabel, buttonValidate);
            sucreBox.setAlignment(Pos.CENTER);
            borderPane.setCenter(sucreBox);

            Scene scene = new Scene(borderPane, 400, 300);
            newStage.setScene(scene);
            newStage.setTitle("Choisissez le niveau de sucre");
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setResizable(false);
            newStage.show();

            buttonValidate.setOnAction(actionEvent -> {
                coffee.addSugar((int) sucreSlider.getValue());
                this.panier.addProduct(coffee, entry.getKey(), entry.getValue());
                newStage.close();
            });
        });
    }

    /**
     * Initialise l'écran de personnalisation de chocolat chaud dans l'onglet dédié.
     *
     * @throws IOException si le fichier FXML ne peut pas être chargé.
     */
    private void initChocolateScrean() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/view/allDist/hotDrink/create_chocolate.fxml"));
        ScrollPane scrollPane = fxmlLoader.load();

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(scrollPane);
        this.tab_choco.setContent(borderPane);
    }
}
