package editor.controller;

import editor.composants.templates.model.TemplateLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class EditorController{


    Map<String, Stage> stageMaps = new HashMap<>();

    @FXML
    private Button btnTemplate, btnHotDrink, btnColdDrink, btnPersonalisation, btnPizza, btnSnack;
    @FXML
    private BorderPane root;
    @FXML
    private ScrollPane rightSide;

    @FXML
    private VBox leftSide;

    public EditorController()
    {

    }

    @FXML
    public void initialize()
    {
        // Initialize the controller here
        System.out.println("EditorController initialized");

        // leftSide take 30% of the screen's width and rightSide take 70% of the screen's width
        // Bind with the width of the screen
        leftSide.prefWidthProperty().bind(root.widthProperty().multiply(0.25));
        rightSide.prefWidthProperty().bind(root.widthProperty().multiply(0.75));

        // set border for the leftSide
        leftSide.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-border-style: solid; -fx-background-color: #f0f0f0;");

        // unset border for the rightSide
        //rightSide.setStyle("-fx-border-color: transparent; -fx-border-width: 0px; -fx-border-style: solid; -fx-background-color: #f0f0f0;");

        // set border for the leftSide
        rightSide.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-border-style: solid; -fx-background-color: #f0f0f0;");


    }

    // set and show stage for the template functionning on the loader dans model

    @FXML
    private void displayTemplate(ActionEvent event) {
        // Create a new stage for the template
        final Stage newStage = new Stage();
        newStage.setResizable(false);

        // Create a new HotdrinkLoader
        TemplateLoader hotdrinkLoader = new TemplateLoader();

        // Get the current stage
        Stage currentStage = (Stage) root.getScene().getWindow();
        // Get the current stage's position
        double x = currentStage.getX();
        double y = currentStage.getY();
        // Set the new stage's position to the left of the current stage
        newStage.setX(x - 300);
        newStage.setY(y);

        // Create a new scene with the HotdrinkLoader as the root
        Scene scene = new Scene(hotdrinkLoader);

        newStage.setTitle("Test stage ");
        newStage.setScene(scene);
        newStage.showAndWait();
    }

    @FXML
    private void displayHotDrink(ActionEvent event) {
        System.out.println("Display Hot Drink");
    }

    @FXML
    private void displayColdDrink(ActionEvent event) {
        System.out.println("Display Cold Drink");
    }

    @FXML
    private void displayPersonalisation(ActionEvent event) {
        System.out.println("Display Personalisation");
    }
    @FXML
    private void displayPizza(ActionEvent event) {
        System.out.println("Display Pizza");
    }
    @FXML
    private void displaySnack(ActionEvent event) {
        System.out.println("Display Snack");
    }





}
