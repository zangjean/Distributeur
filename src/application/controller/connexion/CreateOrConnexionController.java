package application.controller.connexion;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class CreateOrConnexionController {

    public Button fx_id_connexion;
    public Button fx_id_creer_un_compte;

    public CreateOrConnexionController(){

    }

    public void onMousePressedConnexion(MouseEvent mouseEvent) throws IOException {

    }

    public void on_action_connexion(ActionEvent actionEvent) throws IOException {
        // Fermer la fenêtre actuelle
        //Window currentStage = ((Node) actionEvent.getSource()).getScene().getWindow();
        //attend que le fenetre soit completement chargé avant de fermer l'ancienne
        //Platform.runLater(currentStage::hide);

        Scene scene = new Scene((new FXMLLoader(getClass().getResource("/application/view/connexion/connexion.fxml"))).load());
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL); // Empêche l'interaction avec d'autres fenêtres de l'application

        stage.setResizable(false);
        stage.setTitle(" CONNEXION ");
        stage.setScene(scene);
        stage.show(); // Affiche la fenêtre et attend sa fermeture avant de continuer

        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();

    }

    public void on_action_creer_un_compte(ActionEvent actionEvent) throws IOException {
        // Charger la vue pour la création de compte
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/connexion/createAccount.fxml"));
        Scene scene = new Scene(loader.load());
        Stage newStage = new Stage();

        // Configurer la nouvelle fenêtre
        newStage.initModality(Modality.APPLICATION_MODAL); // Empêche l'interaction avec d'autres fenêtres
        newStage.setResizable(false);
        newStage.setTitle("CREER UN COMPTE");
        newStage.setScene(scene);

        // Afficher la fenêtre et attendre sa fermeture
        newStage.show();

        // Fermer la fenêtre actuelle après la fermeture de la nouvelle fenêtre
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();

    }
}
