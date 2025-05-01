package main.controller;

import editor.model.EditorLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    public void runApplication(ActionEvent actionEvent) {
        System.out.println("runApplication");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/homePage/homePage.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Distributeur");
            stage.setScene(new Scene(root, 900, 800));

            Stage oldStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            oldStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void runEditeur(ActionEvent actionEvent) {
        System.out.println("runEditeur");
        try {
            // Charge la vue de l’éditeur
            EditorLoader editorLoader = new EditorLoader();

            // Création et configuration de la nouvelle fenêtre (Stage)
            Stage stage = new Stage();
            stage.setTitle("Éditeur");
            Scene scene = new Scene(editorLoader, 900, 800);
            stage.setScene(scene);

            // Fermer la fenêtre actuelle (celle du bouton)
            Stage oldStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            oldStage.close();

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
