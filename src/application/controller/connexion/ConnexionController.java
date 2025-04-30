package application.controller.connexion;

import application.Main;
import application.model.connexion.Connexion;
import application.model.distrib.panier.Panier;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConnexionController {
    @FXML
    public TextField fx_id_username;
    @FXML
    public TextField fx_id_password;
    @FXML
    public Button fx_id_valider;

    private Connexion connexion = Main.connexion;
    @FXML
    public Label fx_id_connexion_message;

    private Panier panier=Main.panier;

    public void on_action_valider(javafx.event.ActionEvent actionEvent) {
        System.out.println("Username: "+ fx_id_username.getText());
        System.out.println("Password: "+ fx_id_password.getText());
        boolean connect = connexion.userCanConnect(fx_id_username.getText(),fx_id_password.getText());

        if (connect){
            System.out.println("Connect: "+ connect);
            System.out.println("Connexion successful !");
            fx_id_connexion_message.setText("Connexion successful !");
            fx_id_connexion_message.setStyle("-fx-font-size: 20; -fx-text-fill: green ");
            this.panier.setUser(connexion.getConnectedUser());

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
