package editor.composants.templates.composants.snacks.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class SnackController {

    @FXML
    private TilePane centerTilepane;
    @FXML
    private BorderPane root;

    @FXML
    private HBox topHbox, bottomHbox;
    @FXML
    private VBox leftVbox, rightVbox;

    public  SnackController() {
        // Constructor
    }

    @FXML
    public void initialize() {
        // Initialize the controller
        System.out.println("SnackController initialized");

        // Set up the layout
        centerTilepane.setStyle("-fx-background-color: red; -fx-padding: 10px; -fx-border-color: #000000; -fx-border-width: 2px;");
        topHbox.setStyle("-fx-background-color: black; -fx-padding: 10px; -fx-border-color: #000000; -fx-border-width: 2px;");
        bottomHbox.setStyle("-fx-background-color: orange; -fx-padding: 10px; -fx-border-color: #000000; -fx-border-width: 2px;");
        leftVbox.setStyle("-fx-background-color: yellow; -fx-padding: 10px; -fx-border-color: #000000; -fx-border-width: 2px;");
        rightVbox.setStyle("-fx-background-color: green; -fx-padding: 10px; -fx-border-color: #000000; -fx-border-width: 2px;");
        root.setStyle("-fx-background-color: blue; -fx-padding: 10px; -fx-border-color: #000000; -fx-border-width: 2px;");


        // Set up the components

        root.setPrefSize( 600, 800 );

        topHbox.prefWidthProperty().bind(root.widthProperty());
        bottomHbox.prefWidthProperty().bind(root.widthProperty());

        leftVbox.prefHeightProperty().bind(root.heightProperty());
        rightVbox.prefHeightProperty().bind(root.heightProperty());



    }
}
