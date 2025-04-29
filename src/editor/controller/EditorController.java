package editor.controller;

import editor.composants.colddrink.model.ColdDrinksLoader;
import editor.composants.hotdrink.model.HotDrinksLoader;
import editor.composants.savegarde.SaveLoad;
import editor.composants.templates.composants.colddrink.model.ColdDrinkLoader;
import editor.composants.templates.controller.HotDrinkPresetController;
import editor.composants.templates.controller.TemplateController;
import editor.composants.templates.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EditorController{



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

        // Create a new template loader
        TemplateLoader templateLoader  = new TemplateLoader();

        // Get the template controller from the loader
        TemplateController templateController = templateLoader.getTemplateController();
        templateController.setEditorController(this);

        // Get the current stage
        Stage currentStage = (Stage) root.getScene().getWindow();
        // Get the current stage's position
        double x = currentStage.getX();
        double y = currentStage.getY();
        // Set the new stage's position to the left of the current stage
        newStage.setX(x - 300);
        newStage.setY(y);

        // Create a new scene with the HotdrinkLoader as the root
        Scene scene = new Scene(templateLoader);

        newStage.setTitle("Test stage ");
        newStage.setScene(scene);
        newStage.show();

        // add an listener to close the stage when the main stage is closed

        currentStage.setOnCloseRequest(event1 -> {
            newStage.close();
        });

    }

    @FXML
    private void displayHotDrink(ActionEvent event) throws IOException {
        System.out.println("Display Hot Drink");

        // Get the current stage
        Stage currentStage = (Stage) root.getScene().getWindow();
        // Get the current stage's position
        double x = currentStage.getX();
        double y = currentStage.getY();
        // Set the new stage's position to the left of the current stage

        HotDrinksLoader hotDrinksLoader = new HotDrinksLoader();
        // Create a new scene with the HotdrinkLoader as the root
        Scene scene = new Scene(hotDrinksLoader);
        Stage newStage = new Stage();
        newStage.setResizable(false);
        newStage.setX(x - 400);
        newStage.setY(y);
        newStage.setTitle("Hot Drink List");
        newStage.setScene(scene);
        newStage.show();

    }

    @FXML
    private void displayColdDrink(ActionEvent event) {
        System.out.println("Display Cold Drink");

        // Get the current stage
        Stage currentStage = (Stage) root.getScene().getWindow();
        // Get the current stage's position
        double x = currentStage.getX();
        double y = currentStage.getY();
        // Set the new stage's position to the left of the current stage
        ColdDrinksLoader coldDrinksLoader = new ColdDrinksLoader();
        // Create a new scene with the HotdrinkLoader as the root
        Scene scene = new Scene(coldDrinksLoader);
        Stage newStage = new Stage();
        newStage.setResizable(false);
        newStage.setX(x - 400);
        newStage.setY(y);
        newStage.setTitle("Cold Drink List");
        newStage.setScene(scene);
        newStage.show();
        // add an listener to close the stage when the main stage is closed
        currentStage.setOnCloseRequest(event1 -> {
            newStage.close();
        });
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


    public void setTemplate(String templateName){
        switch (templateName)
        {
            case "HotDrink" -> {
                // Create a new template loader
                HotDrinkPresetLoader temp = new HotDrinkPresetLoader();
                // Get the template controller from the loader
                rightSide.setContent(temp);
                rightSide.setFitToWidth(true);
                rightSide.setFitToHeight(true);

                System.out.println("Template HotDrink");

            }
            // On ajoute d'autres templates ici
            case "Pizza" -> {
                //temp = new PizzaPresetLoader();
            }
            case "Snack" -> {
                //temp = new SnackPresetLoader();
            }
            case "ColdDrink" -> {
                // Create a new template loader
                ColdDrinkPresetLoader temp = new ColdDrinkPresetLoader();
                // Get the template controller from the loader
                rightSide.setContent(temp);
                rightSide.setFitToWidth(true);
                rightSide.setFitToHeight(true);

                System.out.println("Template ColdDrink");
            }
        }
    }

    private void showAlert(String saveError) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Can not " + saveError) ;
        alert.setContentText("Please select a template first");
        alert.showAndWait();
    }

    // For menu
    @FXML
    private void saveLayout(ActionEvent event) {
        System.out.println("Save Layout");
        Node content = rightSide.getContent();

        if(content instanceof Saveable saveable)
        {
            SaveLoad layout = saveable.getLayout();
            // Save the layout to a file
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Layout");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Layout Files", "*.layout"),
                    new FileChooser.ExtensionFilter("All Files", "*.*")
            );

            File file = fileChooser.showSaveDialog(root.getScene().getWindow());

            if(file != null){
                try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                    oos.writeObject(layout);
                    System.out.println("Layout saved to " + file.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        else{
            showAlert("Save ");
        }

        // Vider le contenu de rightSide
        rightSide.setContent(null);
    }

    @FXML
    private void loadLayout(ActionEvent event) {
        Node content = rightSide.getContent();

        if (content instanceof Saveable saveable) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load Layout");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Layout Files", "*.layout"));

            File file = fileChooser.showOpenDialog(root.getScene().getWindow());

            if (file != null) {
                System.out.println("Load Layout: " + file.getAbsolutePath());

                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                    SaveLoad loadedLayout = (SaveLoad) in.readObject();

                    if (loadedLayout == null || loadedLayout.getSlots().isEmpty()) {
                        System.out.println("⚠ File is empty or layout is blank");
                        showAlert("Load Empty");
                        return;
                    }

                    System.out.println("✅ Layout has " + loadedLayout.getSlots().size() + " slots");

                    // apply the layout
                    saveable.applySaveLoad(loadedLayout);
                    System.out.println("✅ Layout applied successfully");

                    // Optional success message
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Layout Loaded");
                    success.setHeaderText(null);
                    success.setContentText("Layout successfully loaded!");
                    success.showAndWait();

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    showAlert("Load Failed");
                }

            } else {
                System.out.println("❌ Load canceled or no file selected.");
            }

        } else {
            showAlert("Load");
        }
    }
}
