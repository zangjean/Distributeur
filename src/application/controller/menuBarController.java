package application.controller;

import application.Main;
import application.model.distrib.panier.Panier;
import application.model.distrib.panier.ProductForPanier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class menuBarController {
    @FXML
    public Label fx_id_label_hello;
    @FXML
    public ImageView fx_id_account;
    @FXML
    public BorderPane fx_id_header;
    @FXML
    public Button fx_id_acceuilButton;

    private Panier panier;


    @FXML
    public void initialize() {
        this.panier = Main.getPanier();
    }
    @FXML
    public void onMouseEnteredAccount(MouseEvent mouseEvent) {
        StackPane stackPaneAccount = (StackPane) mouseEvent.getSource();

        stackPaneAccount.setStyle("-fx-border-color: red ; -fx-border-width: 5 ; -fx-border-radius: 4");

    }
    @FXML
    public void onMouseExitedAccount(MouseEvent mouseEvent) {
        StackPane stackPaneAccount = (StackPane) mouseEvent.getSource();
        stackPaneAccount.setStyle("-fx-border-color:transparent; ; -fx-border-width: 5");

    }

    @FXML
    public void onMousePressedAccount(MouseEvent mouseEvent) throws IOException {
        int width = 300; int height = 200;
        //Scene scene = new Scene(createOrConnexionView, width, height);
        Scene scene = new Scene((new FXMLLoader(getClass().getResource("/application/view/connexion/createOrConnexion.fxml"))).load(), width, height);
        Stage stage = new Stage();

        // Configurer le stage comme modal
        stage.initModality(Modality.APPLICATION_MODAL); // Empêche l'interaction avec d'autres fenêtres de l'application

        stage.setResizable(false);
        stage.setTitle(" CREATE OR CONNEXION ");
        stage.setScene(scene);
        stage.showAndWait(); // Affiche la fenêtre et attend sa fermeture avant de continuer



    }

    @FXML
    public void onActionAcceuil(ActionEvent actionEvent) {
        try {
            // Vérifie si la page actuelle est différente de la page d'accueil
            Scene currentScene = ((Button) actionEvent.getSource()).getScene();
            String currentFXML = currentScene.getRoot().getClass().getSimpleName();

            if (!"homePage.fxml".equals(currentFXML)) {  // Remplacez "AcceuilView.fxml" par le nom de votre fichier FXML de la page d'accueil
                // Chargement de la page d'accueil
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/homePage/homePage.fxml"));
                BorderPane acceuilView = loader.load();

                currentScene.setRoot(acceuilView);  // Remplace la scène actuelle par la page d'accueil
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onActionPanier(ActionEvent actionEvent) {
        BorderPane borderPane = new BorderPane();
        GridPane panierGrid = new GridPane();
        panierGrid.setGridLinesVisible(true);
        panierGrid.add(new Label(" Nom Produit "), 0, 0);
        panierGrid.add(new Label(" Taille (ml) "), 1, 0);
        panierGrid.add(new Label(" Prix (€) "), 2, 0);
        panierGrid.add(new Label(" Quantite demandé "), 3, 0);
        panierGrid.add(new Label(" Delete "), 4, 0);

        int rowIndex = 1;
        for (ProductForPanier productForPanier : this.panier.getProducts()) {
            panierGrid.add(new Label(productForPanier.getNameProduct()+""), 0, rowIndex);
            panierGrid.add(new Label(productForPanier.getQuantityML()+""),1,rowIndex);
            panierGrid.add(new Label(productForPanier.getPrice()+""),2,rowIndex);
            panierGrid.add(new Label(productForPanier.getAskingQuantity()+""),3,rowIndex);

            rowIndex++;

        }

        borderPane.setCenter(panierGrid);

        Button btnCancel = new Button("Cancel");
        btnCancel.setOnAction(closeEvent -> {
            // Ferme la fenêtre en cours
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
        });

        borderPane.setBottom(btnCancel);
        BorderPane.setAlignment(btnCancel, Pos.CENTER);

        Stage newStage = new Stage();
        newStage.setTitle(" Panier ");
        Scene newScene = new Scene(borderPane, 400, 300);
        newStage.setScene(newScene);
        // Rendre la fenêtre modale
        newStage.initModality(Modality.APPLICATION_MODAL);

        // Définir la fenêtre principale comme propriétaire
        newStage.initOwner(((Button) actionEvent.getSource()).getScene().getWindow());

        // Afficher la nouvelle fenêtre
        newStage.show();

    }
}
