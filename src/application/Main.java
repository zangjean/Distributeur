package application;

import application.controller.connexion.ConnexionController;
import application.controller.distrib.ProductController;
import application.controller.MenuBarController;
import application.model.connexion.Connexion;
import application.model.distrib.panier.Panier;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static final ProductController productController = new ProductController();
    public static final Panier panier = new Panier();
    public static final Connexion connexion = new Connexion();

    @Override
    public void start(Stage stage) throws Exception {
        int width = 900; int height = 800 ;

        Scene scene = new Scene((new FXMLLoader(getClass().getResource("/application/view/homePage/homePage.fxml"))).load(), width, height);
        stage.setResizable(false); // Empêche le redimensionnement
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

    private void executeBeforeClosing() {
        // Logique à exécuter avant la fermeture
        System.out.println("Exécution de la logique avant fermeture...");
        //si il n'y apas d'utiisateur, pas besoin de sauvegarderle panier a la fermeture
        // si il y a un utilisateur il faut sauvegarder le contenu du panieraffin de le sauvegarder
        if(panier.getUser()== null){
            System.out.println("Pas d'utilisateur");
        }else {
            System.out.println("Il y a un utilisateur courant -> il faut save le panier");
        }
    }

    @Override
    public void stop() {
        // Code à exécuter avant la fermeture
        executeBeforeClosing();
    }



}