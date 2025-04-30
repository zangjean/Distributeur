package editor.composants.templates.controller;

import editor.composants.templates.composants.colddrink.model.ColdDrinkLoader;
import editor.composants.templates.composants.hotdrink.model.HotDrinkLoader;
import editor.composants.templates.composants.pizza.model.PizzaLoader;
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
        imgView2.setOnMouseClicked(this::setUpPizza);;
        imgView4.setOnMouseClicked(this::setUpCoca);


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
        // Set up the editor to drag and drop the components
        // editorController.setUpDragAndDrop();
        // Load the template
        editorController.setTemplate("HotDrink");
        System.out.println("Editor is set");

    }

    private void setUpPizza(MouseEvent mouseEvent) {
        // Set up the editor to drag and drop the components
        // editorController.setUpDragAndDrop();
        // Load the template
        editorController.setTemplate("Pizza");
        System.out.println("Editor is set");
    }
    @FXML
    private void displayPizza(MouseEvent event)
    {
        System.out.println("Display Pizza clicked");

        final Stage pizzaStage = new Stage();
        pizzaStage.setTitle("Pizza Template");
        // Load the template
        PizzaLoader pizzaLoader = new PizzaLoader();
        pizzaLoader.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10px; -fx-border-color: #000000; -fx-border-width: 2px;");
        // Set the scene and show the stage
         Scene scene = new Scene(pizzaLoader);
         pizzaStage.setScene(scene);
         pizzaStage.showAndWait();


    }

    @FXML
    private void displaySnack(MouseEvent event)
    {
        System.out.println("Display Snack clicked");
    }

    //
    private  void setUpCoca(MouseEvent event) {
        // Set up the editor to drag and drop the components
        // editorController.setUpDragAndDrop();
        // Load the template
        editorController.setTemplate("ColdDrink");
        System.out.println("Editor is set");
    }
    @FXML
    private void displayCoca(MouseEvent event)
    {
        System.out.println("Display Coca clicked");

        final Stage cocaStage = new Stage();
        cocaStage.setTitle("Cold Drink Template");
        // Load the template
        ColdDrinkLoader coldDrinkLoader = new ColdDrinkLoader();
        coldDrinkLoader.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10px; -fx-border-color: #000000; -fx-border-width: 2px;");
        // Set the scene and show the stage
        Scene scene = new Scene(coldDrinkLoader);
        cocaStage.setScene(scene);
        cocaStage.showAndWait();

    }

    public void setEditorController(EditorController editorController) {
        this.editorController = editorController;
    }


}

