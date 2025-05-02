package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Point d'entrée principal pour l'application JavaFX (vue home.fxml dans /main/view).
 * Charge et affiche la fenêtre d'accueil.
 */
public class Main extends Application {

    /**
     * Méthode appelée automatiquement au démarrage de JavaFX.
     * Elle initialise et affiche l'interface utilisateur principale.
     *
     * @param stage La fenêtre principale de l'application.
     * @throws Exception En cas d'erreur lors du chargement du fichier FXML.
     */
    @Override
    public void start(Stage stage) throws Exception {
        int width = 900;
        int height = 800;

        Scene scene = new Scene(
                new FXMLLoader(getClass().getResource("/main/view/home.fxml")).load(),
                width,
                height
        );

        stage.setTitle("HOME");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Méthode main standard pour lancer l'application.
     *
     * @param args Arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        launch();
    }
}
