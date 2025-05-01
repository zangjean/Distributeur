package editor.controller;

import editor.composants.colddrink.model.ColdDrinksLoader;
import editor.composants.hotdrink.model.HotDrinksLoader;
import editor.composants.pizza.model.PizzaListLoader;
import editor.composants.savegarde.SaveLoad;
import editor.composants.templates.composants.colddrink.model.ColdDrinkLoader;
import editor.composants.templates.controller.HotDrinkPresetController;
import editor.composants.templates.controller.TemplateController;
import editor.composants.templates.model.*;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

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

    @FXML
    private void quitAll(ActionEvent event) {
        System.out.println("Quit All");
        // Get the current stage
        Stage currentStage = (Stage) root.getScene().getWindow();
        // Close the current stage
        currentStage.close();
        // Close all other stages
        for (Window window : Window.getWindows()) {
            if (window != currentStage) {
                Stage stage = (Stage) window;
                stage.close();
            }
        }
        // Exit the application
        Platform.exit();
    }

    @FXML
    private void displayMember(ActionEvent event) {
        System.out.println("Display Member");
        // Get the current stage
        Stage currentStage = (Stage) root.getScene().getWindow();
        // Get the current stage's position
        double x = currentStage.getX();
        double y = currentStage.getY();
        // Set the new stage's position to the left of the current stage

        // Create a new scene with the HotdrinkLoader as the root

        Scene scene = new Scene(createMemberLoader());
        Stage newStage = new Stage();
        newStage.setResizable(false);
        newStage.setX(x - 400);
        newStage.setY(y);
        newStage.setTitle("Member List");
        newStage.setScene(scene);
        newStage.show();
    }

    private Parent createMemberLoader() {
    VBox vbox = new VBox();
    vbox.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f0f0, #e0e0e0); -fx-padding: 20px; -fx-spacing: 20px;");
    vbox.setPrefSize(400, 500);
    vbox.setAlignment(Pos.CENTER);

    Label title = new Label("Notre équipe");
    title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50; -fx-padding: 0 0 10px 0;");

    // Création des cartes pour chaque membre
    vbox.getChildren().addAll(title, createMemberCard("Jean Zang", "Développeur Backend", "#3498db"),
            createMemberCard("Le Quang Hung", "Développeur de Backend", "#e74c3c"),
            createMemberCard("Hoang Minh Quan", "Développeur Frontend", "#2ecc71"));

    // Bouton pour fermer
    Button closeButton = new Button("Fermer");
    closeButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10px 20px;");
    closeButton.setOnAction(e -> ((Stage) vbox.getScene().getWindow()).close());
    closeButton.setOnMouseEntered(e -> closeButton.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10px 20px;"));
    closeButton.setOnMouseExited(e -> closeButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10px 20px;"));

    vbox.getChildren().add(closeButton);
    return vbox;
}

    private HBox createMemberCard(String name, String role, String color) {
        HBox card = new HBox();
        card.setStyle("-fx-background-color: white; -fx-padding: 15px; -fx-spacing: 15px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0); -fx-background-radius: 5px;");
        card.setPrefWidth(360);
        card.setAlignment(Pos.CENTER_LEFT);

        // Avatar circulaire (simulé avec un rectangle coloré)
        Rectangle avatar = new Rectangle(60, 60);
        avatar.setFill(Paint.valueOf(color));
        avatar.setArcHeight(60);
        avatar.setArcWidth(60);

        VBox info = new VBox(5);
        Label nameLabel = new Label(name);
        nameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        Label roleLabel = new Label(role);
        roleLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #7f8c8d;");

        Button contactButton = new Button("Contact");
        contactButton.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-background-radius: 20;");
        contactButton.setOnMouseEntered(e -> contactButton.setOpacity(0.8));
        contactButton.setOnMouseExited(e -> contactButton.setOpacity(1.0));
        contactButton.setOnAction(e -> showContactAlert(name));

        info.getChildren().addAll(nameLabel, roleLabel, contactButton);
        card.getChildren().addAll(avatar, info);

        // Animation au survol
        card.setOnMouseEntered(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(150), card);
            st.setToX(1.03);
            st.setToY(1.03);
            st.play();
        });

        card.setOnMouseExited(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(150), card);
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();
        });

        return card;
    }

    private void showContactAlert(String name) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Contact");
        alert.setHeaderText(null);
        alert.setContentText("Contacter " + name + " via email : " + name.toLowerCase().replace(" ", ".") + "@etu.univ-poitiers.fr");
        alert.showAndWait();
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
        // Get the current stage
        Stage currentStage = (Stage) root.getScene().getWindow();
        // Get the current stage's position
        double x = currentStage.getX();
        double y = currentStage.getY();
        // Set the new stage's position to the left of the current stage
        // Create a new scene with the HotdrinkLoader as the root
        PizzaListLoader pizzaListLoader = new PizzaListLoader();
        Scene scene = new Scene(pizzaListLoader);
        Stage newStage = new Stage();
        newStage.setResizable(false);
        newStage.setX(x - 400);
        newStage.setY(y);
        newStage.setTitle("Pizza List");
        newStage.setScene(scene);
        newStage.show();
        // add an listener to close the stage when the main stage is closed
        currentStage.setOnCloseRequest(event1 -> {
            newStage.close();
        });
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
                // Create a new template loader
                PizzaPresetLoader temp = new PizzaPresetLoader();

                // Get the template controller from the loader
                rightSide.setContent(temp);
                rightSide.setFitToWidth(true);
                rightSide.setFitToHeight(true);
                System.out.println("Template Pizza Preset");

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
        // 1) Pick the file
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Load Layout");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Layout Files", "*.layout"));
        File file = chooser.showOpenDialog(root.getScene().getWindow());
        if (file == null) return;

        // 2) Deserialize SaveLoad
        SaveLoad loaded;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            loaded = (SaveLoad) in.readObject();
        } catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
            showAlert("Load Failed");
            return;
        }

        if (loaded.getSlots().isEmpty()) {
            showAlert("Load Empty");
            return;
        }

        // 3) Based on the template name, create the proper loader
        Saveable presetLoader = null;
        switch (loaded.getNameTemplate()) {
            case "HotDrink" ->
                    presetLoader = new HotDrinkPresetLoader();
            case "ColdDrink" ->
                    presetLoader = new ColdDrinkPresetLoader();
            case "Pizza" ->
                    presetLoader = new PizzaPresetLoader();
            case "Snack" ->
                    //presetLoader = new SnackPresetLoader();
                    System.out.println("Snack");
            default -> {
                showAlert("Unknown template: " + loaded.getNameTemplate());
                return;
            }
        }

        // 4) Display it in the right pane
        Node node = (Node) presetLoader;
        rightSide.setContent(node);
        rightSide.setFitToWidth(true);
        rightSide.setFitToHeight(true);

        // 5) Apply the saved slots
        presetLoader.applySaveLoad(loaded);

        // 6) Notify success
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setHeaderText(null);
        info.setContentText("Layout “" + loaded.getNameTemplate() + "” loaded!");
        info.showAndWait();
    }

}
