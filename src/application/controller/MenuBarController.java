package application.controller;

import application.MainApp;
import application.controller.connexion.ConnexionController;
import application.controller.distrib.ProductController;
import application.model.distrib.panier.Panier;
import application.model.distrib.panier.ProductForPanier;
import application.model.distrib.productModel.product.Product;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MenuBarController {
    @FXML
    public Label fx_id_label_hello;
    @FXML
    public ImageView fx_id_account;
    @FXML
    public BorderPane fx_id_header;
    @FXML
    public Button fx_id_acceuilButton;

    private Panier panier;

    private ProgressBar progressBar=new ProgressBar();
    private ProgressIndicator progressIndicator=new ProgressIndicator();
    private ProductController productController;
    private int panierType =0;
    private int panierNomProduit=1;
    private int panierTaille=2;
    private int panierPrix=3;
    private int panierQuantiteDemande=4;
    private int panierDelete=5;

    private ConnexionController connexionController;
    private String currentUsername="";
    private Boolean messagePanierEmpty=false;


    @FXML
    public void initialize() {
        this.panier = MainApp.getPanier();
        this.productController = MainApp.productController;
        System.out.println("menuBarController initialisé.");

        if(MainApp.connexion.is_connected()){
            this.currentUsername= MainApp.panier.getUser().getUsername()+" Vous avez "+ MainApp.panier.getUser().getPoints()+" points.";

        }

        fx_id_label_hello.textProperty().bind(
                Bindings.when(MainApp.connexion.isConnectedProperty())
                        .then("Bienvenue, "+this.currentUsername+" !" )
                        .otherwise("Pas d'utilisateur connecté.")
        );







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
        if(MainApp.connexion.is_connected()){
            stage.setTitle(" DECONNEXION OR EDIT ");
        }else{
            stage.setTitle(" CREATE OR CONNEXION ");
        }
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
        panierGrid.add(new Label(" Type  "), this.panierType, 0);
        panierGrid.add(new Label(" Nom Produit "), this.panierNomProduit, 0);
        panierGrid.add(new Label(" Taille (ml) "), this.panierTaille, 0);
        panierGrid.add(new Label(" Prix (€) "), this.panierPrix, 0);
        panierGrid.add(new Label(" Quantite demandé "), this.panierQuantiteDemande, 0);
        panierGrid.add(new Label(" Delete "), this.panierDelete, 0);

        int rowIndex = 1;
        for (ProductForPanier productForPanier : this.panier.getProducts()) {
            panierGrid.add(new Label(productForPanier.getOriginaClassName()), this.panierType, rowIndex);
            panierGrid.add(new Label(productForPanier.getNameProduct()+""), this.panierNomProduit, rowIndex);
            panierGrid.add(new Label(productForPanier.getQuantityML()+""),this.panierTaille,rowIndex);
            panierGrid.add(new Label(productForPanier.getPrice()+""),this.panierPrix,rowIndex);
            panierGrid.add(new Label(productForPanier.getAskingQuantity()+""),this.panierQuantiteDemande,rowIndex);

            Button btnDeleteFromPanier = new Button("Delete");
            deleteProductFromPanier(btnDeleteFromPanier,productForPanier);
            panierGrid.add(btnDeleteFromPanier, this.panierDelete, rowIndex);

            rowIndex++;

        }

        borderPane.setCenter(panierGrid);

        Button btnAchat = new Button("PAY (tot: "+ MainApp.panier.getTotalPrice()+" )");


        VBox vBox=new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.getChildren().add(btnAchat);
        borderPane.setBottom(vBox);
        BorderPane.setAlignment(vBox, Pos.CENTER);

        byAllPanier(btnAchat,vBox);


        Stage newStage = new Stage();
        newStage.setTitle(" Panier ");
        Scene newScene = new Scene(borderPane, 500, 300);
        newStage.setScene(newScene);
        // Rendre la fenêtre modale
        newStage.initModality(Modality.APPLICATION_MODAL);

        // Définir la fenêtre principale comme propriétaire
        newStage.initOwner(((Button) actionEvent.getSource()).getScene().getWindow());

        // Afficher la nouvelle fenêtre
        newStage.show();

    }

    private void byAllPanierRun(ActionEvent actionEvent) {

            VBox vBox=new VBox();
            Label label=new Label("Votre panier contient : ");
            Label nameLabel = new Label();
            nameLabel.setStyle("-fx-font-weight: bold");

            vBox.getChildren().addAll(label,nameLabel);

            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);

            Scene scene = new Scene(vBox,400,200);
            Stage stage = new Stage();
            stage.setTitle("Realisation des produits du panier");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();

            // Fermer la fenêtre source (si elle existe)
            Stage sourceStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            sourceStage.close();

            // Commencer la chaîne
            runProductsSequentially(new ArrayList<>(this.panier.getProducts()),  vBox, nameLabel,this.panier.getProducts().getFirst().getAskingQuantity());

    }

    private void runProductsSequentially(ArrayList<ProductForPanier> productForPaniers,VBox vBox,Label nameLabel,int quantityDemande){
        // Vérifie si la liste est vide
        if (productForPaniers == null || productForPaniers.isEmpty()) {
            nameLabel.setText("Aucun produit à traiter.");
            System.out.println("Panier vide ou null.");
            return;
        }

        // Indice pour suivre le produit en cours
        final int[] currentIndex = {0};
        nameLabel.setText("Préparation de : " + productForPaniers.get(currentIndex[0]).getNameProduct());

        // ProgressBar et ProgressIndicator par produit
        ProgressBar bar = new ProgressBar();
        ProgressIndicator indicator = new ProgressIndicator();

        // Préparer l'UI
        nameLabel.setText("Préparation de : " + productForPaniers.get(currentIndex[0]).getNameProduct());
        bar.setPrefWidth(250);
        vBox.getChildren().addAll(bar, indicator);

        // Service pour traiter les produits de manière séquentielle
        Service<Void> productService = new Service<>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<>() {
                    @Override
                    protected Void call() throws Exception {
                        ProductForPanier product = productForPaniers.get(currentIndex[0]);
                        System.out.println("Traitement du produit : " + product.getNameProduct());

                        // Simulation du traitement (par exemple, une répétition)
                        for (int i = 0; i < product.getQuantityML(); i++) {
                            if (isCancelled()) break;
                            updateProgress(i + 1, product.getQuantityML());
                            Thread.sleep(50); // Simulation d'un délai pour chaque étape
                        }
                        return null;
                    }
                };
            }
        };


        // Lier la progression au ProgressBar et au ProgressIndicator
        bar.progressProperty().bind(productService.progressProperty());
        indicator.progressProperty().bind(productService.progressProperty());

        // Gestion des événements
        productService.setOnSucceeded(event -> {
            System.out.println("Produit traité : " + productForPaniers.get(currentIndex[0]).getNameProduct());

            if(quantityDemande==1){
                currentIndex[0]++; // Passe au produit suivant

                // Si tous les produits sont traités
                if (currentIndex[0] >= productForPaniers.size()) {
                    nameLabel.setText("Tous les produits ont été traités.");
                    System.out.println("Tous les produits ont été traités.");
                    MainApp.panier.clear();
                    pickupProduct(vBox);

                } else {
                    // Préparation du produit suivant
                    ProductForPanier nextProduct = productForPaniers.get(currentIndex[0]);
                    nameLabel.setText("Préparation de : " + nextProduct.getNameProduct());
                    bar.progressProperty().unbind();
                    indicator.progressProperty().unbind();
                    vBox.getChildren().removeAll(bar, indicator);
                    if(quantityDemande>1) {
                        runProductsSequentially(productForPaniers, vBox, nameLabel, quantityDemande-1);
                    }else{
                        ArrayList<ProductForPanier> newproductForPaniers=new ArrayList<>(productForPaniers.subList(currentIndex[0], productForPaniers.size()));
                        runProductsSequentially(newproductForPaniers, vBox, nameLabel,newproductForPaniers.getFirst().getAskingQuantity());
                    }
                }
            }else{
                ProductForPanier nextProduct = productForPaniers.get(currentIndex[0]);
                nameLabel.setText("Préparation de : " + nextProduct.getNameProduct());
                bar.progressProperty().unbind();
                indicator.progressProperty().unbind();
                vBox.getChildren().removeAll(bar, indicator);
                if(quantityDemande>1) {
                    runProductsSequentially(productForPaniers, vBox, nameLabel, quantityDemande-1);
                }else{
                    ArrayList<ProductForPanier> newproductForPaniers=new ArrayList<>(productForPaniers.subList(currentIndex[0], productForPaniers.size()));
                    runProductsSequentially(newproductForPaniers, vBox, nameLabel,newproductForPaniers.getFirst().getAskingQuantity());
                }

            }


        });

        productService.setOnCancelled(event -> {
            System.out.println("Traitement annulé.");
            nameLabel.setText("Traitement annulé.");
        });

        productService.setOnFailed(event -> {
            Throwable exception = productService.getException();
            System.out.println("Erreur lors du traitement du produit : " + exception.getMessage());
            nameLabel.setText("Erreur pendant le traitement.");
        });

        // Démarrer le traitement
        productService.start();

    }

    private void pickupProduct(VBox vBox){
        Button pickup = new Button("All products have been picked up");
        vBox.getChildren().add(pickup);
        pickup.setOnAction(closeEvent -> {
            System.out.println("Button clicked" + " " + "All products have been picked up");
            Stage stage = (Stage) pickup.getScene().getWindow();
            stage.close();
        });

    }

    private void byAllPanierSave(ActionEvent actionEvent) {
        this.panier.updateFavorite(this.panier);
    }


    private void byAllPanier(Button button, VBox vBox){
        button.setOnAction(closeEvent -> {
            if(MainApp.panier.getProducts().isEmpty()){
                if(!this.messagePanierEmpty){
                    Label label = new Label("Votre panier est vide.");
                    label.setStyle("-fx-font-weight: bold; -fx-font-size: 15; -fx-text-fill: red");
                    vBox.getChildren().add(label);
                    this.messagePanierEmpty=true;
                }

            }else {
                // Quand j'achete je dois lancer la fenetre de "realisation des produits du paniers"
                // Et sauvegarder ce qui a ete acheter dans le fichier pour les favoris
                if(MainApp.connexion.is_connected()){
                    int points= MainApp.panier.howManyPointsUserWin();
                    MainApp.panier.setPointsForCurrentUser(points);

                }
                byAllPanierSave(closeEvent);
                byAllPanierRun(closeEvent);
            }



        });
    }




    public void onActionPrintShop(ActionEvent actionEvent) {
        ArrayList<Product> products = this.productController.getProductsModel().getAllProducts();
        System.out.println("Print Shop");
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

    private void deleteProductFromPanier(Button button, ProductForPanier productForPanier){
        button.setOnAction(closeEvent -> {
            System.out.println("Button clicked" + " " + "Delete");
            //this.panier.removeProduct();
            this.panier.removeProduct(productForPanier);
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();


        });

    }

}
