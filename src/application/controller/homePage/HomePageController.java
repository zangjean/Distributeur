package application.controller.homePage;

import application.controller.connexion.CreateOrConnexionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {

    //HomePage homePageModel=new HomePage();
    private CreateOrConnexionController createOrConnexionController;




    public void on_action_hot_drink(ActionEvent actionEvent) throws IOException {

        int width = 800; int height = 850;
        //Scene scene = new Scene(createOrConnexionView, width, height);
        Scene scene = new Scene((new FXMLLoader(getClass().getResource("/application/view/allDist/hot_drink.fxml"))).load(), width, height);
        Stage stage = new Stage();

        // Obtenir la scène actuelle et la fenêtre
        Node source = (Node) actionEvent.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();

        // Configurer le stage comme modal
        stage.initModality(Modality.APPLICATION_MODAL); // Empêche l'interaction avec d'autres fenêtres de l'application

        stage.setResizable(false);
        stage.setTitle(" HOT DRINK ");
        stage.setScene(scene);
        // Afficher la nouvelle fenêtre et attendre jusqu'à ce qu'elle soit fermée
        stage.show();

        // Fermer la fenêtre actuelle seulement après que la nouvelle ait été fermée
        currentStage.close();
    }

    public void on_action_snacks(ActionEvent actionEvent) {
    }

    public void on_action_pizzas(ActionEvent actionEvent) {
    }

    public void on_action_cold_drink(ActionEvent actionEvent) {
    }
}
