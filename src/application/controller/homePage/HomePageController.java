package application.controller.homePage;

import application.controller.connexion.CreateOrConnexionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class HomePageController {

    //HomePage homePageModel=new HomePage();
    private CreateOrConnexionController createOrConnexionController;


    @FXML
    public void on_action_hot_drink(ActionEvent actionEvent) throws IOException {
        try {
            // Obtenir le noeud source de l'événement (le bouton) et la scène actuelle
            Node source = (Node) actionEvent.getSource();
            Scene currentScene = source.getScene();

            // Charger la nouvelle vue (hot drink)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/allDist/hotDrink/hot_drink.fxml"));
            BorderPane hotDrinkView = loader.load();

            // Définir la nouvelle vue comme racine de la scène actuelle
            currentScene.setRoot(hotDrinkView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void on_action_snacks(ActionEvent actionEvent) {
    }
    @FXML
    public void on_action_pizzas(ActionEvent actionEvent) {
    }
    @FXML
    public void on_action_cold_drink(ActionEvent actionEvent) {
    }
}
