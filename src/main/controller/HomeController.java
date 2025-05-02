package main.controller;

import editor.model.EditorLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur de l'écran d'accueil principal.
 * Permet à l'utilisateur de choisir entre lancer l'application de distribution ou l'éditeur.
 */
public class HomeController {

    /**
     * Lance l'application principale de distribution.
     * Remplace la scène actuelle par la vue `homePage.fxml`.
     *
     * @param actionEvent L'événement déclenché par le clic sur le bouton.
     */
    public void runApplication(ActionEvent actionEvent) {
        System.out.println("runApplication");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/homePage/homePage.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Distributeur");
            stage.setScene(new Scene(root, 900, 800));

            // Fermer l'ancienne fenêtre
            Stage oldStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            //oldStage.close();
            oldStage.setWidth(200);
            oldStage.setHeight(200);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lance l'éditeur de contenu ou de configuration.
     * Charge une interface graphique à partir de {@link EditorLoader}.
     *
     * @param actionEvent L'événement déclenché par le clic sur le bouton.
     */
    public void runEditeur(ActionEvent actionEvent) {
        System.out.println("runEditeur");
        try {
            EditorLoader editorLoader = new EditorLoader();

            Stage stage = new Stage();
            stage.setTitle("Éditeur");
            Scene scene = new Scene(editorLoader, 900, 800);
            stage.setScene(scene);

            // Fermer l'ancienne fenêtre
            Stage oldStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            oldStage.setWidth(200);
            oldStage.setHeight(200);
//            oldStage.close();

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
