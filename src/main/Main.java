package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        int width = 900; int height = 800 ;

        Scene scene = new Scene((new FXMLLoader(getClass().getResource("/main/view/home.fxml"))).load(), width, height);
        //stage.setResizable(false); // Empêche le redimensionnement
        stage.setTitle(" HOME ");
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
