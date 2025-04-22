package application.controller.distrib;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class PizzaController {

    @FXML
    public void onActionMargherita(ActionEvent actionEvent) {
        loadMenuPizzaView(actionEvent, "Margherita");
    }

    @FXML
    public void onActionPepperoni(ActionEvent actionEvent) {
        loadMenuPizzaView(actionEvent, "Pepperoni");
    }

    @FXML
    public void onActionVeggie(ActionEvent actionEvent) {
        loadMenuPizzaView(actionEvent, "Veggie");
    }

    @FXML
    public void onActionBBQ(ActionEvent actionEvent) {
        loadMenuPizzaView(actionEvent, "BBQ");
    }

    @FXML
    public void onActionHawaiian(ActionEvent actionEvent) {
        loadMenuPizzaView(actionEvent, "Hawaiian");
    }

    private void loadMenuPizzaView(ActionEvent actionEvent, String tabName) {
        try {
            Node source = (Node) actionEvent.getSource();
            Scene currentScene = source.getScene();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/allDist/pizza/menuPizza.fxml"));
            BorderPane menuPizzaView = loader.load();

            MenuPizzaController menuPizzaController = loader.getController();

            currentScene.setRoot(menuPizzaView);

            javafx.application.Platform.runLater(() -> {
                switch (tabName.toLowerCase()) {
                    case "margherita":
                        menuPizzaController.tabPane.getSelectionModel().select(menuPizzaController.tab_margherita);
                        break;
                    case "pepperoni":
                        menuPizzaController.tabPane.getSelectionModel().select(menuPizzaController.tab_pepperoni);
                        break;
                    case "veggie":
                        menuPizzaController.tabPane.getSelectionModel().select(menuPizzaController.tab_veggie);
                        break;
                    case "bbq":
                        menuPizzaController.tabPane.getSelectionModel().select(menuPizzaController.tab_bbq);
                        break;
                    case "hawaiian":
                        menuPizzaController.tabPane.getSelectionModel().select(menuPizzaController.tab_hawaiian);
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
