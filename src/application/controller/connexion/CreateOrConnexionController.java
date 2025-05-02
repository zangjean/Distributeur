package application.controller.connexion;

import application.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur permettant de gérer l'affichage et les actions associées à la connexion,
 * la déconnexion, la création de compte et l'édition du compte utilisateur.
 * <p>
 * Il adapte dynamiquement le texte des boutons selon l'état de connexion de l'utilisateur.
 */
public class CreateOrConnexionController {

    /**
     * Bouton principal (connexion ou déconnexion selon l'état).
     */
    @FXML
    public Button fx_id_button1;

    /**
     * Bouton secondaire (créer un compte ou éditer le compte selon l'état).
     */
    @FXML
    public Button fx_id_button2;

    /**
     * Méthode d'initialisation appelée automatiquement après le chargement du fichier FXML.
     * Met à jour les libellés des boutons selon que l'utilisateur est connecté ou non.
     */
    @FXML
    public void initialize() {
        if (MainApp.connexion.is_connected()) {
            fx_id_button1.setText("DECONNEXION");
            fx_id_button2.setText("EDITER COMPTE");
        } else {
            fx_id_button1.setText("CONNEXION");
            fx_id_button2.setText("CREER UN COMPTE");
        }
    }

    /**
     * Méthode déclenchée lors du clic sur le bouton principal.
     * Ouvre la fenêtre de connexion si l'utilisateur est déconnecté,
     * ou le déconnecte s'il est déjà connecté.
     *
     * @param actionEvent L'événement déclenché par l'utilisateur.
     * @throws IOException En cas d'échec de chargement de la vue.
     */
    @FXML
    public void on_action_button1(ActionEvent actionEvent) throws IOException {
        if (MainApp.connexion.is_connected()) {
            deconnexion(actionEvent);
        } else {
            connexion(actionEvent);
        }
    }

    /**
     * Méthode déclenchée lors du clic sur le bouton secondaire.
     * Ouvre la fenêtre de création de compte ou d'édition de compte
     * selon que l'utilisateur est connecté ou non.
     *
     * @param actionEvent L'événement déclenché par l'utilisateur.
     * @throws IOException En cas d'erreur de chargement de la vue.
     */
    @FXML
    public void on_action_button2(ActionEvent actionEvent) throws IOException {
        if (MainApp.connexion.is_connected()) {
            editAccount(actionEvent);
        } else {
            createAccount(actionEvent);
        }
    }

    /**
     * Ouvre la fenêtre de connexion et ferme la fenêtre actuelle.
     *
     * @param actionEvent L'événement déclenché par l'utilisateur.
     * @throws IOException Si le fichier FXML ne peut pas être chargé.
     */
    private void connexion(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene((new FXMLLoader(getClass().getResource("/application/view/connexion/connexion.fxml"))).load());
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle(" CONNEXION ");
        stage.setScene(scene);
        stage.show();

        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }

    /**
     * Ouvre la fenêtre de création de compte et ferme la fenêtre actuelle.
     *
     * @param actionEvent L'événement déclenché par l'utilisateur.
     * @throws IOException Si le fichier FXML ne peut pas être chargé.
     */
    private void createAccount(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/connexion/createAccount.fxml"));
        Scene scene = new Scene(loader.load());
        Stage newStage = new Stage();

        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setResizable(false);
        newStage.setTitle("CREER UN COMPTE");
        newStage.setScene(scene);
        newStage.show();

        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }

    /**
     * Déconnecte l'utilisateur et ferme la fenêtre actuelle.
     *
     * @param actionEvent L'événement déclenché par l'utilisateur.
     */
    private void deconnexion(ActionEvent actionEvent) {
        System.out.println("deconnexion");
        MainApp.connexion.disconnect();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Placeholder pour l'action d'édition de compte (à implémenter).
     *
     * @param actionEvent L'événement déclenché par l'utilisateur.
     */
    private void editAccount(ActionEvent actionEvent) {
        System.out.println("editAccount");
    }
}
