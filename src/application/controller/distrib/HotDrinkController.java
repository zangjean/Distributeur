package application.controller.distrib;


import application.MainApp;
import application.model.distrib.panier.Panier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;

public class HotDrinkController {

    @FXML
    public ImageView imageFav1;
    @FXML
    public Label labelFav1;
    @FXML
    public ImageView imageFav2;
    @FXML
    public Label labelFav2;
    @FXML
    public ImageView imageFav3;
    @FXML
    public Label labelFav3;

    private Panier panier;

    @FXML
    public void initialize() {
        //Main.productController.getProductsModel().get
        this.panier = MainApp.panier;
        initAllFavProd(panier.return3FavoriteProduct());
    }

    private void initAllFavProd(ArrayList<String> list) {
        if(list.size()==0){
            System.out.println("Aucun produit favoris");
        }else{
            System.out.println("Voici le nom des Produits Favoris : ");
            if(list.size()>=1){
                this.labelFav1.setText(list.getFirst());
                System.out.println(list.getFirst());
            }
            if(list.size()>=2) {
                this.labelFav2.setText(list.get(1));
                System.out.println(list.get(1));
            }
            if (list.size()>=3){
                this.labelFav3.setText(list.get(2));
                System.out.println(list.get(2));
            }
        }
    }


    private void onActionHotDrink(ActionEvent actionEvent, String name) {
        try {
            // Obtenir le noeud source de l'événement (le bouton) et la scène actuelle
            Node source = (Node) actionEvent.getSource();
            Scene currentScene = source.getScene();

            // Charger la nouvelle vue (hot drink)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/allDist/hotDrink/menuHotDrink.fxml"));
            BorderPane hotDrinkView = loader.load();

            // Récupérer le TabPane à partir de la vue chargée
            TabPane tabPane = (TabPane) hotDrinkView.lookup("#tabPaneID");

            // Vérifier que le TabPane existe dans la vue
            if (tabPane != null) {
                // Parcourir les onglets et définir celui correspondant à `name` comme actif
                for (Tab tab : tabPane.getTabs()) {
                    if (tab.getText().equalsIgnoreCase(name)) { // Vérification basée sur le nom de l'onglet
                        tabPane.getSelectionModel().select(tab); // Active l'onglet correspondant
                        break;
                    }
                }
            } else {
                System.out.println("Erreur : TabPane introuvable dans la vue chargée.");
            }

            // Définir la nouvelle vue comme racine de la scène actuelle
            currentScene.setRoot(hotDrinkView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void onActionCafe(ActionEvent actionEvent) {
        onActionHotDrink(actionEvent,"CAFE EN GRAIN");
    }

    public void onActionMocha(ActionEvent actionEvent) {
        onActionHotDrink(actionEvent,"CAPPUCCINO, LATTE");
    }

    public void onActionCreateChocolate(ActionEvent actionEvent) {
        onActionHotDrink(actionEvent,"CHOCOLAT");
    }

    public void onActionThe(ActionEvent actionEvent) {
        onActionHotDrink(actionEvent,"THE");
    }

    public void onActionSoupes(ActionEvent actionEvent) {
        onActionHotDrink(actionEvent,"SOUPES");
    }
}
