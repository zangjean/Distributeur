package application.controller.connexion;

import application.MainApp;
import application.model.connexion.Connexion;
import application.model.distrib.panier.Panier;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Contrôleur pour la fenêtre de connexion.
 * Gère les interactions utilisateur liées à la tentative de connexion,
 * la vérification des identifiants, et la fermeture de la fenêtre si la connexion réussit.
 */
public class ConnexionController {

    /**
     * Champ de texte pour le nom d'utilisateur (lié à l'interface FXML).
     */
    @FXML
    public TextField fx_id_username;

    /**
     * Champ de mot de passe pour l'utilisateur (lié à l'interface FXML).
     */
    @FXML
    public PasswordField fx_id_password;

    /**
     * Bouton pour valider la connexion (lié à l'interface FXML).
     */
    @FXML
    public Button fx_id_valider;

    /**
     * Objet de gestion de la connexion utilisateur.
     */
    private Connexion connexion = MainApp.connexion;

    /**
     * Label affichant un message de retour à l'utilisateur (succès ou échec).
     */
    @FXML
    public Label fx_id_connexion_message;

    /**
     * Objet représentant le panier de l'utilisateur.
     */
    private Panier panier = MainApp.panier;

    /**
     * Méthode appelée lors du clic sur le bouton de validation.
     * Vérifie si les identifiants sont valides, affiche un message correspondant,
     * et ferme la fenêtre si la connexion réussit.
     *
     * @param actionEvent L'événement déclenché par le clic sur le bouton de validation.
     */
    public void on_action_valider(javafx.event.ActionEvent actionEvent) {
        System.out.println("Username: " + fx_id_username.getText());
        System.out.println("Password: " + fx_id_password.getText());
        boolean connect = connexion.userCanConnect(fx_id_username.getText(), fx_id_password.getText());

        if (connect) {
            fx_id_connexion_message.setText("Connexion successful !");
            fx_id_connexion_message.setStyle("-fx-font-size: 20; -fx-text-fill: green ");
            this.panier.setUser(connexion.getConnectedUser());

            // Fermer la fenêtre de connexion
            Stage stage = (Stage) fx_id_valider.getScene().getWindow();
            stage.close();

        } else {
            System.out.println("Connect: " + connect);
            fx_id_connexion_message.setText("!! Username or Password incorrect !!");
            fx_id_connexion_message.setStyle("-fx-font-size: 20; -fx-text-fill: red ");
        }
    }
}
