package application.controller;

import application.Main;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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


    @FXML
    public void initialize() {
        this.panier = Main.getPanier();
        this.productController = Main.productController;
        System.out.println("menuBarController initialisé.");

        fx_id_label_hello.textProperty().bind(
                Bindings.when(Main.connexion.isConnectedProperty())
                        .then("Bienvenue, utilisateur connecté !")
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
        if(Main.connexion.is_connected()){
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
            deleteProductFromPanier(btnDeleteFromPanier);
            panierGrid.add(btnDeleteFromPanier, this.panierDelete, rowIndex);

            rowIndex++;

        }

        borderPane.setCenter(panierGrid);

        Button btnAchat = new Button("Payer");
        byAllPanier(btnAchat);

        borderPane.setBottom(btnAchat);
        BorderPane.setAlignment(btnAchat, Pos.CENTER);

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
        vBox.getChildren().addAll(label,nameLabel,progressBar,progressIndicator);


        for (ProductForPanier productForPanier : this.panier.getProducts()) {
            runOneProduct(productForPanier,vBox);
            nameLabel.setText(productForPanier.getNameProduct());
        }
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        Scene scene = new Scene(vBox,400,200);
        Stage stage = new Stage();
        stage.setTitle("Realisation des produits du panier");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    private void byAllPanierSave(ActionEvent actionEvent) {
        this.panier.updateFavorite(this.panier);
    }


    private void byAllPanier(Button button){
        button.setOnAction(closeEvent -> {
            // Quand j'achete je dois lancer la fenetre de "realisation des produits du paniers"
            // Et sauvegarder ce qui a ete acheter dans le fichier pour les favoris
           //byAllPanierRun(closeEvent);
            System.out.println("Button clicked Sauvgarde du panier DEBUT");

           byAllPanierSave(closeEvent);
           System.out.println("Button clicked Sauvgarde du panier FIN");


        });
    }

    private void runOneProduct(ProductForPanier productForPanier,VBox vBox){
      int length = productForPanier.getQuantityML()*100_000_000;
      int nbrRepetitions = productForPanier.getAskingQuantity();


        Service service = new Service() {
            @Override
            protected Task<Integer> createTask() {
                return new Task<Integer>() {
                    @Override
                    protected Integer call() throws Exception {
                        for(int i=0;i<nbrRepetitions;i++){
                            updateProgress(i,nbrRepetitions);
                            Thread.sleep(productForPanier.getQuantityML() * 100L);
                            updateMessage("Progression : " + (i + 1) + " / " + nbrRepetitions);

                        }
                        return null;
                    }
                };
            }
        };
        progressBar.progressProperty().bind(service.progressProperty());
        progressIndicator.progressProperty().bind(service.progressProperty());
        //vBox.getChildren().addAll(progressBar,progressIndicator);

        Label progressLabel = new Label();
        progressLabel.textProperty().bind(service.messageProperty());

        // Gestion des états du service (facultatif)
        service.setOnRunning(e -> System.out.println("Tâche démarrée..."));
        service.setOnSucceeded(e -> System.out.println("Tâche terminée avec succès."));
        service.setOnFailed(e -> System.out.println("Une erreur est survenue."));

        // Lance l'exécution
        service.start();





    }

    public void onActionPrintShop(ActionEvent actionEvent) {
        ArrayList<Product> products = this.productController.getProductsModel().getAllProducts();
        System.out.println("Print Shop");
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

    private void deleteProductFromPanier(Button button){
        button.setOnAction(closeEvent -> {
            System.out.println("Button clicked" + " " + "Delete");
            //this.panier.removeProduct();
        });

    }

    public void setConnexionController(ConnexionController connexionController) {
        this.connexionController = connexionController;
        if (connexionController != null) {
            System.out.println("ConnexionController injecté avec succès dans MenuBarController.");
        } else {
            System.out.println("Attention : ConnexionController n'est pas injecté !");
        }


    }

}
