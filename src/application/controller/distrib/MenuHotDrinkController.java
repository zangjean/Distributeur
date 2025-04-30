package application.controller.distrib;

import application.Main;
import application.model.distrib.panier.Panier;
import application.model.distrib.panier.ProductForPanier;
import application.model.distrib.productModel.product.Product;
import application.model.distrib.productModel.product.beverage.sugar.all.Coffee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static java.util.function.Predicate.not;

public class MenuHotDrinkController {
    @FXML
    public Tab tab_cafe;
    @FXML
    public Tab tab_cappu;
    @FXML
    public Tab tab_choco;
    @FXML
    public Tab tab_the;
    @FXML
    public Tab tab_soupes;

    @FXML
    public TabPane tabPaneID;

    private ProductController productController;
    private Panier panier;


    @FXML
    public void initialize() throws IOException {
        //this.grid_cafe = new GridPane();

        this.productController = Main.getProductController();
        this.panier = Main.getPanier();
        initAllCoffee("cafe");
        initAllCoffee("cappu");
        initChocolateScrean();

    }
    private ArrayList<Coffee> returnCoffee(String type){
        ArrayList<Product> products = productController.getProductsModel().getAllProducts();
        ArrayList<Coffee> coffees = new ArrayList<>();
        for (Product product : products) {
            if(product.getClass().equals(Coffee.class)){
                if(type.equals("cafe")){
                    if (!((product.getName().toLowerCase().contains("latte"))|| (product.getName().toLowerCase().contains("cappuccino")))){
                        coffees.add((Coffee) product);
                    }
                }else{
                    if ((product.getName().toLowerCase().contains("latte"))|| (product.getName().toLowerCase().contains("cappuccino"))){
                        coffees.add((Coffee) product);
                    }
                }

            }
        }
        return coffees;
    }

    private void initAllCoffee(String type) {
        // Créer un FlowPane à la place d'un GridPane
        FlowPane flowPane = new FlowPane();

        // Définir les espacements entre les éléments
        flowPane.setHgap(10); // Espace horizontal entre les éléments
        flowPane.setVgap(10); // Espace vertical entre les lignes

        // Récupérer la liste de café
        ArrayList<Coffee> coffees = returnCoffee(type);

        // Parcourir tous les cafés pour générer des boutons
        for (Coffee coffee : coffees) {
            Button button = new Button(coffee.getName());

            // Configurer une action pour le bouton
            selectCoffee(coffee, button);

            // Ajouter le bouton au FlowPane
            flowPane.getChildren().add(button);
        }

        // Définir le FlowPane comme contenu du tab
        if(type.equals("cafe")){
            this.tab_cafe.setContent(flowPane);
        }else if(type.equals("cappu")){
            this.tab_cappu.setContent(flowPane);
        }
    }


    private void selectCoffee(Coffee coffee,Button button){
        button.setOnAction(event -> {
            System.out.println("Button clicked" + " " + coffee.getName());

            // Créer un GridPane pour afficher les informations
            GridPane select = new GridPane();
            select.setGridLinesVisible(true);
            select.add(new Label(" Taille (ml) "), 0, 0);
            select.add(new Label(" Prix (€) "), 1, 0);
            select.add(new Label(" Select "), 2, 0);

            Map<Integer, Double> prices = coffee.getQuantityPriceMap();

            int rowIndex = 1; // Commence à la ligne 1 (car ligne 0 contient les en-têtes)
            for (Map.Entry<Integer, Double> entry : prices.entrySet()) {
                select.add(new Label(entry.getKey() + ""), 0, rowIndex);
                select.add(new Label(entry.getValue() + ""), 1, rowIndex);

                // Créer un nouveau bouton pour chaque ligne
                Button btnAdd = new Button("Add");

                // Ajouter l'action au bouton (optionnel)
                addOnPanier(btnAdd,entry,coffee);

                select.add(btnAdd, 2, rowIndex);
                rowIndex++;
            }

            // Créer un BorderPane
            BorderPane borderPane = new BorderPane();

            // Ajouter le GridPane dans la partie centrale du BorderPane
            borderPane.setCenter(select);

            // Créer un bouton "Cancel"
            Button btnCancel = new Button("Cancel");

            // Définir une action pour fermer la fenêtre lorsque le bouton est cliqué
            btnCancel.setOnAction(closeEvent -> {
                // Ferme la fenêtre en cours
                Stage stage = (Stage) btnCancel.getScene().getWindow();
                stage.close();
            });

            // Ajouter le bouton "Cancel" dans la partie basse du BorderPane
            borderPane.setBottom(btnCancel);

            // Centrer le bouton "Cancel" dans la section basse
            BorderPane.setAlignment(btnCancel, Pos.CENTER);

            // Créer une nouvelle fenêtre (Stage) avec le BorderPane
            Stage newStage = new Stage();
            newStage.setTitle(coffee.getName());

            // Créer une scène avec le BorderPane
            Scene newScene = new Scene(borderPane, 400, 300);

            // Ajouter la scène à la fenêtre
            newStage.setScene(newScene);

            // Rendre la fenêtre modale
            newStage.initModality(Modality.APPLICATION_MODAL);

            // Définir la fenêtre principale comme propriétaire
            newStage.initOwner(button.getScene().getWindow());

            // Afficher la nouvelle fenêtre
            newStage.show();
        });
    }

    private void addOnPanier(Button button, Map.Entry<Integer, Double> entry, Coffee coffee){
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

            // Pour afficher la valeur sélectionnée
            Label valueLabel = new Label("Niveau : 0");
            sucreSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
                valueLabel.setText("Niveau : " + newVal.intValue());
            });

            VBox sucreBox = new VBox(5, sucreLabel, sucreSlider, valueLabel, buttonValidate);
            sucreBox.setAlignment(Pos.CENTER);

            borderPane.setCenter(sucreBox);
            Scene scene = new Scene(borderPane, 400, 300);
            newStage.setScene(scene);
            newStage.setTitle(" Choisissez le niveau de sucre ");
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setResizable(false);
            newStage.show();

            buttonValidate.setOnAction(actionEvent -> {
                coffee.addSugar((int) sucreSlider.getValue());
                this.panier.addProduct(coffee,entry.getKey(),entry.getValue());
                newStage.close();
            });
        });
    }


    private void initChocolateScrean() throws IOException {
        // Charger le fichier FXML du ScrollPane
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/view/allDist/hotDrink/create_chocolate.fxml"));
        ScrollPane scrollPane = fxmlLoader.load();

        // Créer un nouveau BorderPane pour organiser le ScrollPane
        BorderPane borderPane = new BorderPane();

        // Définir le ScrollPane dans la région centrale du BorderPane
        borderPane.setCenter(scrollPane);

        // Définir ce BorderPane comme le contenu de l'onglet "tab_choco"
        this.tab_choco.setContent(borderPane);
    }




}
