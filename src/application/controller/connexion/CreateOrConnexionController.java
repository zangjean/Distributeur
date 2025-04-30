package application.controller.connexion;

import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    @FXML
    public Button fx_id_button1;
    @FXML
    public Button fx_id_button2;

    @FXML
    public void initialize() {
        if(Main.connexion.is_connected()){
            fx_id_button1.setText("DECONNEXION");
            fx_id_button2.setText("EDITER COMPTE");

        }else{
            fx_id_button1.setText("CONNEXION");
            fx_id_button2.setText("CREER UN COMPTE");

        }

    }

    @FXML
    public void on_action_button1(ActionEvent actionEvent) throws IOException {
        if(Main.connexion.is_connected()){
            deconnexion(actionEvent);
        }else{
            connexion(actionEvent);
        }


    }



    @FXML
    public void on_action_button2(ActionEvent actionEvent) throws IOException {
        if(Main.connexion.is_connected()){
            editAccount(actionEvent);
        }else{
            createAccount(actionEvent);
        }

    }


    private void connexion(ActionEvent actionEvent) throws IOException {
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

    private void createAccount(ActionEvent actionEvent) throws IOException {
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

    private void deconnexion(ActionEvent actionEvent) {
        System.out.println("deconnexion");
        Main.connexion.disconnect();
    }

    private void editAccount(ActionEvent actionEvent) {
        System.out.println("editAccount");
    }
}
