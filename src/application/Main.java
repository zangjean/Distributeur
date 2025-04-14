package application;

import application.model.connexion.Connexion;
import application.model.user.User;
import application.model.user.UserManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static application.model.user.UserManager.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        /*
        HomePage homePage = new HomePage();

        int width = 900; int height = 900 ;

        Scene scene = new Scene(homePage.getLoader().load(), width, height);
        stage.setResizable(false); // Empêche le redimensionnement
        stage.setTitle(homePage.getTitle());
        stage.setScene(scene);
        stage.show();

         */

        int width = 900; int height = 900 ;

        Scene scene = new Scene((new FXMLLoader(getClass().getResource("/application/view/homePage/homePage.fxml"))).load(), width, height);
        stage.setResizable(false); // Empêche le redimensionnement
        stage.setTitle(" DISTRIBUTEUR ");
        stage.setScene(scene);
        stage.show();
        // Charger le fichier CSS
        //scene.getStylesheets().add(getClass().getResource("/application/ressources/sheetstyle.css").toExternalForm());

        //Connexion connexion = new Connexion();

    }

    public static void main(String[] args) {
        launch();
    }

}