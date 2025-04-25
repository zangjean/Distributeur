package editor.composants.templates.controller;

import editor.composants.templates.composants.hotdrink.model.HotDrinkLoader;
import editor.controller.EditorController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class TemplateController {

    private EditorController editorController; // parce que je vais communiquer avec autre chose

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

        // When click on the image, choose this template and set up the editor to drag and drop the components
        imgView1.setOnMouseClicked(this::setUpEditor);

    }

    @FXML
    private void displayCafe(MouseEvent event)
    {
        System.out.println("Cafe clicked");

        final Stage cafeStage = new Stage();
        cafeStage.setTitle("Hot Drink Template");

        HotDrinkLoader hotDrinkLoader = new HotDrinkLoader();
        hotDrinkLoader.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10px; -fx-border-color: #000000; -fx-border-width: 2px;");

        // Set the scene and show the stage
        Scene scene = new Scene(hotDrinkLoader);
        cafeStage.setScene(scene);
        cafeStage.showAndWait();

    }

    private void setUpEditor(MouseEvent event) {
        System.out.println("Image clicked");
        // Set up the editor to drag and drop the components
        // editorController.setUpDragAndDrop();
        // Load the template
        editorController.setTemplate("HotDrink");
        // Close the current window
        Window window = imgView1.getScene().getWindow();
        if (window != null) {
            Stage stage = (Stage) window;
            stage.close();
        }

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

    public void setEditorController(EditorController editorController) {
        this.editorController = editorController;
    }
}

