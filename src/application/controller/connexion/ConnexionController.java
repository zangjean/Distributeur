package application.controller.connexion;

import application.model.connexion.Connexion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConnexionController {
    public TextField fx_id_username;
    public TextField fx_id_password;
    public Button fx_id_valider;

    public Connexion connexion = new Connexion();
    public Label fx_id_connexion_message;

    public void on_action_valider(javafx.event.ActionEvent actionEvent) {
        System.out.println("Username: "+ fx_id_username.getText());
        System.out.println("Password: "+ fx_id_password.getText());
        boolean connect = connexion.userCanConnect(fx_id_username.getText(),fx_id_password.getText());

        if (connect){
            System.out.println("Connect: "+ connect);
            fx_id_connexion_message.setText("Connexion successful !");
            fx_id_connexion_message.setStyle("-fx-font-size: 20; -fx-text-fill: green ");

            // Fermer la fenêtre de connexion
            Stage stage = (Stage) fx_id_valider.getScene().getWindow();
            stage.close();


        }else {
            System.out.println("Connect: "+ connect);
            fx_id_connexion_message.setText("!! Username or Password incorrect !!");
            fx_id_connexion_message.setStyle("-fx-font-size: 20; -fx-text-fill: red ");

        }



    }
}
