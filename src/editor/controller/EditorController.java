package editor.controller;

import editor.composants.templates.hotdrink.model.HotdrinkLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditorController{

    @FXML
    private Button btnTemplate;
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

        // setAction on the button btnTemplate
        //btnTemplate.setOnAction(this::displayStage);



    }

    @FXML
    private void displayStage(ActionEvent event) {
        final Stage newStage = new Stage();
        HotdrinkLoader hotdrinkLoader = new HotdrinkLoader();

        // Create a new scene with the HotdrinkLoader as the root
        Scene scene = new Scene(hotdrinkLoader, 200, 200);

        newStage.setTitle("Test stage ");
        newStage.setScene(scene);
        newStage.show();


    }



}
