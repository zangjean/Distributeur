package application;

import application.controller.distrib.ProductController;
import application.model.connexion.Connexion;
import application.model.distrib.panier.Panier;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static application.utils.Utils.*;

public class MainApp extends Application {
    public static final ProductController productController = new ProductController();
    public static final Panier panier = new Panier();
    public static final Connexion connexion = new Connexion();

    @Override
    public void start(Stage stage) throws Exception {
        int width = 900; int height = 800 ;

        Scene scene = new Scene((new FXMLLoader(getClass().getResource("/application/view/homePage/homePage.fxml"))).load(), width, height);
        //stage.setResizable(false); // Empêche le redimensionnement
        stage.setTitle(" DISTRIBUTEUR ");
        stage.setScene(scene);

        stage.show();
        // Charger le fichier CSS
        //scene.getStylesheets().add(getClass().getResource("/application/ressources/sheetstyle.css").toExternalForm());

        //Connexion connexion = new Connexion();


    }

    public static void main(String[] args) {
        launch();
    }

    public static ProductController getProductController() {
        return productController;
    }

    public static Panier getPanier() {
        return panier;
    }

    @Override
    public void stop() {
        // Code à exécuter avant la fermeture
        executeBeforeClosing();
    }



}