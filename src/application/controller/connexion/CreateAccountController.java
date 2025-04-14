package application.controller.connexion;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
            this.errorMessage = "";
            System.out.println("Création de compte validé!");
            this.fx_id_messages.setText(this.errorMessage);

        }
    }
}
