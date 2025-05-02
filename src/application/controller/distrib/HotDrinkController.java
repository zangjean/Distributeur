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

/**
 * Contrôleur principal pour la gestion de l'écran des boissons chaudes.
 * Permet de naviguer entre différents types de boissons et d'afficher les favoris.
 */
public class HotDrinkController {

    /** Image du premier produit favori. */
    @FXML
    public ImageView imageFav1;

    /** Label du premier produit favori. */
    @FXML
    public Label labelFav1;

    /** Image du deuxième produit favori. */
    @FXML
    public ImageView imageFav2;

    /** Label du deuxième produit favori. */
    @FXML
    public Label labelFav2;

    /** Image du troisième produit favori. */
    @FXML
    public ImageView imageFav3;

    /** Label du troisième produit favori. */
    @FXML
    public Label labelFav3;

    /** Instance du panier utilisateur. */
    private Panier panier;

    /**
     * Initialise le contrôleur après le chargement de la vue.
     * Récupère les 3 produits favoris depuis le panier et les affiche.
     */
    @FXML
    public void initialize() {
        this.panier = MainApp.panier;
        initAllFavProd(panier.return3FavoriteProduct());
    }

    /**
     * Initialise les libellés des produits favoris avec les noms fournis.
     *
     * @param list Liste des noms des produits favoris.
     */
    private void initAllFavProd(ArrayList<String> list) {
        if (list.size() == 0) {
            System.out.println("Aucun produit favoris");
        } else {
            System.out.println("Voici le nom des Produits Favoris : ");
            if (list.size() >= 1) {
                this.labelFav1.setText(list.getFirst());
                System.out.println(list.getFirst());
            }
            if (list.size() >= 2) {
                this.labelFav2.setText(list.get(1));
                System.out.println(list.get(1));
            }
            if (list.size() >= 3) {
                this.labelFav3.setText(list.get(2));
                System.out.println(list.get(2));
            }
        }
    }

    /**
     * Affiche l'interface de sélection de boisson chaude et sélectionne automatiquement
     * l'onglet correspondant au type de boisson.
     *
     * @param actionEvent Événement déclenché par l'utilisateur.
     * @param name        Le nom de l'onglet à activer.
     */
    private void onActionHotDrink(ActionEvent actionEvent, String name) {
        try {
            Node source = (Node) actionEvent.getSource();
            Scene currentScene = source.getScene();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/allDist/hotDrink/menuHotDrink.fxml"));
            BorderPane hotDrinkView = loader.load();

            TabPane tabPane = (TabPane) hotDrinkView.lookup("#tabPaneID");

            if (tabPane != null) {
                for (Tab tab : tabPane.getTabs()) {
                    if (tab.getText().equalsIgnoreCase(name)) {
                        tabPane.getSelectionModel().select(tab);
                        break;
                    }
                }
            } else {
                System.out.println("Erreur : TabPane introuvable dans la vue chargée.");
            }

            currentScene.setRoot(hotDrinkView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ouvre la vue de sélection pour le café en grains.
     *
     * @param actionEvent L'événement déclenché par l'action utilisateur.
     */
    @FXML
    public void onActionCafe(ActionEvent actionEvent) {
        onActionHotDrink(actionEvent, "CAFE EN GRAIN");
    }

    /**
     * Ouvre la vue de sélection pour les boissons type mocha (latte, cappuccino).
     *
     * @param actionEvent L'événement déclenché par l'action utilisateur.
     */
    @FXML
    public void onActionMocha(ActionEvent actionEvent) {
        onActionHotDrink(actionEvent, "CAPPUCCINO, LATTE");
    }

    /**
     * Ouvre la vue de sélection pour le chocolat chaud.
     *
     * @param actionEvent L'événement déclenché par l'action utilisateur.
     */
    @FXML
    public void onActionCreateChocolate(ActionEvent actionEvent) {
        onActionHotDrink(actionEvent, "CHOCOLAT");
    }

    /**
     * Ouvre la vue de sélection pour le thé.
     *
     * @param actionEvent L'événement déclenché par l'action utilisateur.
     */
    @FXML
    public void onActionThe(ActionEvent actionEvent) {
        onActionHotDrink(actionEvent, "THE");
    }

    /**
     * Ouvre la vue de sélection pour les soupes.
     *
     * @param actionEvent L'événement déclenché par l'action utilisateur.
     */
    @FXML
    public void onActionSoupes(ActionEvent actionEvent) {
        onActionHotDrink(actionEvent, "SOUPES");
    }
}
