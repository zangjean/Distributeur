package application.controller.distrib;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class SnackController {

    @FXML
    public void onActionChips(ActionEvent actionEvent) {
        loadMenuSnackView(actionEvent, "Chips");
    }

    @FXML
    public void onActionNuggets(ActionEvent actionEvent) {
        loadMenuSnackView(actionEvent, "Chocolat");
    }

    @FXML
    public void onActionSandwich(ActionEvent actionEvent) {
        loadMenuSnackView(actionEvent, "Sandwich");
    }

    private void loadMenuSnackView(ActionEvent actionEvent, String tabName) {
        try {
            Node source = (Node) actionEvent.getSource();
            Scene currentScene = source.getScene();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/allDist/snack/menuSnack.fxml"));
            BorderPane menuSnackView = loader.load();

            MenuSnackController menuSnackController = loader.getController();

            currentScene.setRoot(menuSnackView);

            javafx.application.Platform.runLater(() -> {
                switch (tabName.toLowerCase()) {
                    case "chips":
                        menuSnackController.tabPane.getSelectionModel().select(menuSnackController.tab_chips);
                        break;
                    case "chocolate":
                        menuSnackController.tabPane.getSelectionModel().select(menuSnackController.tab_chocolate);
                        break;
                    case "sandwich":
                        menuSnackController.tabPane.getSelectionModel().select(menuSnackController.tab_sandwich);
                        break;
                    default:
                        break;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
