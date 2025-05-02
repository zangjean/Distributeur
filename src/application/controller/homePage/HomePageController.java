package application.controller.homePage;

import application.controller.connexion.CreateOrConnexionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Contrôleur de la page d'accueil de l'application.
 * Gère la navigation vers les différentes catégories de produits comme les boissons chaudes,
 * snacks, pizzas et boissons froides.
 */
public class HomePageController {

    /** Bouton pour accéder à la section des boissons chaudes. */
    @FXML
    public Button fx_id_button_BoissonsChaude;

    /** Bouton pour accéder à la section des snacks. */
    @FXML
    public Button fx_id_button_Snacks;

    /** Bouton pour accéder à la section des pizzas. */
    @FXML
    public Button fx_id_button_Pizzas;

    /** Bouton pour accéder à la section des boissons froides. */
    @FXML
    public Button fx_id_button_BoissonsFroide;

    /** Contrôleur pour la gestion de la connexion (non utilisé ici mais initialisé). */
    private CreateOrConnexionController createOrConnexionController;

    /**
     * Méthode appelée lors du clic sur le bouton "Boissons Chaudes".
     * Charge et affiche la vue correspondant à cette catégorie.
     *
     * @param actionEvent L'événement généré par le clic.
     * @throws IOException Si la vue ne peut pas être chargée.
     */
    @FXML
    public void on_action_hot_drink(ActionEvent actionEvent) throws IOException {
        try {
            Node source = (Node) actionEvent.getSource();
            Scene currentScene = source.getScene();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/allDist/hotDrink/hot_drink.fxml"));
            BorderPane hotDrinkView = loader.load();

            currentScene.setRoot(hotDrinkView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode appelée lors du clic sur le bouton "Snacks".
     * À implémenter : chargement de la vue des snacks.
     *
     * @param actionEvent L'événement déclenché par le clic.
     */
    @FXML
    public void on_action_snacks(ActionEvent actionEvent) {
        // TODO: Implémenter l'accès à la vue "Snacks"
    }

    /**
     * Méthode appelée lors du clic sur le bouton "Pizzas".
     * À implémenter : chargement de la vue des pizzas.
     *
     * @param actionEvent L'événement déclenché par le clic.
     */
    @FXML
    public void on_action_pizzas(ActionEvent actionEvent) {
        // TODO: Implémenter l'accès à la vue "Pizzas"
    }

    /**
     * Méthode appelée lors du clic sur le bouton "Boissons Froides".
     * À implémenter : chargement de la vue des boissons froides.
     *
     * @param actionEvent L'événement déclenché par le clic.
     */
    @FXML
    public void on_action_cold_drink(ActionEvent actionEvent) {
        try {
            Node source = (Node) actionEvent.getSource();
            Scene currentScene = source.getScene();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/allDist/coldDrink/coldDrinkGrid.fxml"));
            BorderPane hotDrinkView = loader.load();

            currentScene.setRoot(hotDrinkView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
