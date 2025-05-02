package application.controller.connexion;

import application.MainApp;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Pair;

import java.util.Objects;

/**
 * Contrôleur pour la création de compte utilisateur.
 * Gère les champs de saisie et la logique de validation pour enregistrer un nouveau compte.
 */
public class CreateAccountController {

    /**
     * Champ de texte pour le nom d'utilisateur.
     */
    public TextField fx_id_username;

    /**
     * Champ de texte pour le mot de passe.
     */
    public TextField fx_id_password;

    /**
     * Champ de texte pour le prénom de l'utilisateur.
     */
    public TextField fx_id_firstname;

    /**
     * Champ de texte pour le nom de famille de l'utilisateur.
     */
    public TextField fx_id_lastname;

    /**
     * Champ de texte pour le numéro de téléphone.
     */
    public TextField fx_id_phone_number;

    /**
     * Champ de texte pour l'adresse email.
     */
    public TextField fx_id_mail_adress;

    /**
     * Bouton pour valider la création du compte.
     */
    public Button fx_id_valider;

    /**
     * Label pour afficher les messages d'information ou d'erreur.
     */
    public Label fx_id_messages;

    /**
     * Chaîne de caractères contenant le dernier message d'erreur.
     */
    private String errorMessage;

    /**
     * Méthode appelée lors du clic sur le bouton de validation.
     * Valide les champs de saisie requis, tente de créer un compte via l'objet {@code Connexion},
     * et affiche un message approprié selon le résultat.
     *
     * @param actionEvent L'événement déclenché par l'action sur le bouton.
     */
    public void on_action_valider(ActionEvent actionEvent) {
        System.out.println("Username: "+ fx_id_username.getText());
        System.out.println("Password: "+ fx_id_password.getText());
        if( (Objects.equals(fx_id_password.getText(), "")) || (Objects.equals(fx_id_username.getText(),"")) )
        {
            this.errorMessage = "!!Username and Password can't be empty!!";
            System.out.println(this.errorMessage);
            this.fx_id_messages.setText(this.errorMessage);
            this.fx_id_messages.setStyle("-fx-font-size: 20; -fx-text-fill: red ");
        } else {
            System.out.println("Création de compte en cours:");
            this.fx_id_messages.setText(this.errorMessage);

            Pair<Boolean, String> createAccountResult = MainApp.connexion.createAccount(
                    this.fx_id_username.getText(), this.fx_id_password.getText());

            if (createAccountResult.getKey()) {
                System.out.println("Données VALIDE, création POSSIBLE");
                this.fx_id_messages.setStyle("-fx-font-size: 20; -fx-text-fill: green ");
                this.fx_id_messages.setText(createAccountResult.getValue());

                // Exemple de future extension pour enregistrer le prénom :
                /*
                if(this.fx_id_firstname.getText()!=null){
                    MainApp.connexion.setUserFirstName(this.fx_id_username,this.fx_id_firstname);
                }
                 */
            } else {
                System.out.println("Données invalide, création impossible");
                this.fx_id_messages.setStyle("-fx-font-size: 20; -fx-text-fill: red ");
                this.fx_id_messages.setText(createAccountResult.getValue());
            }
        }
    }
}
