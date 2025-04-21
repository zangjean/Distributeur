package editor.composants.templates.controller;

import editor.composants.templates.composants.hotdrink.model.HotDrinkLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class TemplateController {

    @FXML
    private GridPane root;
    @FXML
    private StackPane stackpane1, stackpane2, stackpane3, stackpane4;

    @FXML
    private ImageView imgView1, imgView2, imgView3, imgView4;

    public TemplateController() {

    }

    @FXML
    public void initialize() {


    }

    @FXML
    private void displayCafe(MouseEvent event)
    {
        System.out.println("Cafe clicked");

        final Stage cafeStage = new Stage();
        cafeStage.setTitle("Cafe");

        HotDrinkLoader hotDrinkLoader = new HotDrinkLoader();
        hotDrinkLoader.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10px; -fx-border-color: #000000; -fx-border-width: 2px;");

        // Set the scene and show the stage
        Scene scene = new Scene(hotDrinkLoader);
        cafeStage.setScene(scene);
        cafeStage.show();

    }

    @FXML
    private void displayPizza(MouseEvent event)
    {
        System.out.println("Display Pizza clicked");
    }

    @FXML
    private void displaySnack(MouseEvent event)
    {
        System.out.println("Display Snack clicked");
    }

    @FXML
    private void displayCoca(MouseEvent event)
    {
        System.out.println("Display Coca clicked");
    }
}

