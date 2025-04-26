package application.controller.distrib;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class HotDrinkController {

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
