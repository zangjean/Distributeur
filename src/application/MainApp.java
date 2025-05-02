package application;

import application.controller.distrib.ProductController;
import application.model.connexion.Connexion;
import application.model.distrib.panier.Panier;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static application.utils.Utils.*;

/**
 * Classe principale de l'application JavaFX.
 * Initialise et démarre l'interface utilisateur, et configure les objets globaux.
 */
public class MainApp extends Application {

    /** Contrôleur global des produits disponibles. */
    public static final ProductController productController = new ProductController();

    /** Panier d'achat associé à la session en cours. */
    public static final Panier panier = new Panier();

    /** Gestionnaire de connexion utilisateur. */
    public static final Connexion connexion = new Connexion();

    /**
     * Point d’entrée JavaFX. Lance l’interface graphique avec la page d’accueil.
     *
     * @param stage Fenêtre principale.
     * @throws Exception En cas de problème lors du chargement du fichier FXML.
     */
    @Override
    public void start(Stage stage) throws Exception {
        int width = 900;
        int height = 800;

        Scene scene = new Scene(
                new FXMLLoader(getClass().getResource("/application/view/homePage/homePage.fxml")).load(),
                width,
                height
        );

        stage.setTitle("DISTRIBUTEUR");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Méthode appelée automatiquement à la fermeture de l'application.
     * Elle permet d'exécuter une logique de sauvegarde conditionnelle.
     */
    @Override
    public void stop() {
        executeBeforeClosing();  // Appelle la méthode statique utilitaire de sauvegarde.
    }

    /**
     * Point d’entrée du programme Java standard.
     *
     * @param args Arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        launch();  // Lance JavaFX
    }

    /**
     * Accès au contrôleur de produits global.
     *
     * @return {@link ProductController} de l'application.
     */
    public static ProductController getProductController() {
        return productController;
    }

    /**
     * Accès au panier global de l'utilisateur.
     *
     * @return {@link Panier} associé à la session.
     */
    public static Panier getPanier() {
        return panier;
    }
}
