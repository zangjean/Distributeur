package application.controller.connexion;

import application.MainApp;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Pair;

import java.util.Objects;

public class CreateAccountController {
    public TextField fx_id_username;
    public TextField fx_id_password;
    public TextField fx_id_firstname;
    public TextField fx_id_lastname;
    public TextField fx_id_phone_number;
    public TextField fx_id_mail_adress;
    public Button fx_id_valider;

    public Label fx_id_messages;
    private String errorMessage;

    public void on_action_valider(ActionEvent actionEvent) {
        System.out.println("Username: "+ fx_id_username.getText());
        System.out.println("Password: "+ fx_id_password.getText());
        if( (Objects.equals(fx_id_password.getText(), "")) || (Objects.equals(fx_id_username.getText(),"")) )
        {
            this.errorMessage = "!!Username and Password can't be empty!!";
            System.out.println(this.errorMessage);
            this.fx_id_messages.setText(this.errorMessage);
            this.fx_id_messages.setStyle("-fx-font-size: 20; -fx-text-fill: red ");
        }else
        {
            //this.errorMessage = "Création de compte validé!";
            System.out.println("Création de compte en cour:");
            this.fx_id_messages.setText(this.errorMessage);

            Pair<Boolean,String> createAccountResult = MainApp.connexion.createAccount(this.fx_id_username.getText(),this.fx_id_password.getText());
            if(createAccountResult.getKey()){
                System.out.println("Donnes VALIDE creation POSSIBLE ");

                this.fx_id_messages.setStyle("-fx-font-size: 20; -fx-text-fill: green ");
                this.fx_id_messages.setText(createAccountResult.getValue());
            }else{
                System.out.println("Donnes invalide creation impossible ");
                this.fx_id_messages.setStyle("-fx-font-size: 20; -fx-text-fill: red ");
                this.fx_id_messages.setText(createAccountResult.getValue());
            }

        }
    }
}
